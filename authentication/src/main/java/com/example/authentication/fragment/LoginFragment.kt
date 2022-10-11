package com.example.authentication.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.authentication.R
import com.example.authentication.databinding.FragmentLoginBinding
import com.example.authentication.fragment.viewModel.authComponentViewModel
import com.example.authentication.fragment.viewModel.authViewModel
import com.example.authentication.fragment.viewModel.authViewModelFactory
import com.example.core.domain.error.UIState
import com.example.core_ui.screens.ErrorDialogFragment
import com.example.core_ui.screens.LoadingFragment
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import dagger.Lazy
import javax.inject.Inject

class LoginFragment : Fragment() {

    @Inject
    internal lateinit var authViewModelFactory: Lazy<authViewModelFactory>

    val authViewModel: authViewModel by viewModels { authViewModelFactory.get() }
    val componentViewModel: authComponentViewModel by viewModels()

    val loadingFragment = LoadingFragment()
    val errorFragment = ErrorDialogFragment()

    // should to replace width list of country from datyabace when it will be ready TODO
    val countries = arrayOf(
        "Tirana, Albania",
        "Yerevan, Armenia", "Vienna, Austri",
        "Baku, Azerbaijan", "Brasilia, Brazil",
        "Minsk, Belarus", "Brussels, Belgium",
        "Brasilia, Brazil", "Copenhagen, Denmark",
        "Prague, Czechia", "Brasilia, Brazil",
        "Helsinki, Finland", "Paris, France",
        "Tokyo, Japan", "Kyiv, Ukraine",
        "London, United Kingdom", "Washington, D.C, USA",
        "Ankara, Turkey"
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.authComponent.injectLogin(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = FragmentLoginBinding.inflate(layoutInflater, container, false)
        val loginButton = view.signInFacebookButton
        val callbackManager = CallbackManager.Factory.create()

        // prepare Spiner with counties
        (view.spinner as AutoCompleteTextView).setText("Kabul, Afghanistan")
        val spinnerArrayAdapter =
            ArrayAdapter<String>(requireActivity().applicationContext, android.R.layout.simple_spinner_item, countries)
        (view.spinner as AutoCompleteTextView).setAdapter(spinnerArrayAdapter)

        // ///////////// FaceBook Login ////////////////////

        view.signInFacebookLayout.setOnClickListener {
            loginButton.performClick()
        }

        loginButton.setOnClickListener {
            val accessToken = AccessToken.getCurrentAccessToken()
            val isLoggedIn = accessToken != null && !accessToken.isExpired
            if (isLoggedIn) LoginManager.getInstance().logInWithReadPermissions(
                activity,
                listOf("public_profile")
            )

            val request = GraphRequest.newMeRequest(
                accessToken
            ) { `object`, response ->
                val username = `object`.getString("first_name")
                val link = `object`.getString("link")

                authViewModel.login(
                    username,
                    link
                )
            }
            val parameters = Bundle()
            parameters.putString("fields", "id,name,link,picture.type(large)")
            request.parameters = parameters
            request.executeAsync()
        }

        // Callback registration
        loginButton.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                }

                override fun onCancel() {
                    val ErrorMeasage = Bundle()
                    ErrorMeasage.putString("Measage", "Cancel of request")
                    errorFragment.arguments = ErrorMeasage
                    errorFragment.show(requireActivity().supportFragmentManager, ErrorDialogFragment.TAG)
                }

                override fun onError(exception: FacebookException) {
                    val ErrorMeasage = Bundle()
                    ErrorMeasage.putString("Measage", "Error of request $exception")
                    errorFragment.arguments = ErrorMeasage
                    errorFragment.show(requireActivity().supportFragmentManager, ErrorDialogFragment.TAG)
                }
            }
        )
        // ////////////////////////////////////////////////

        authViewModel._loginState.observe(requireActivity()) { result ->

            when (result) {
                is UIState.Error -> {
                    if (loadingFragment.dialog != null)loadingFragment.dismiss()
                    val ErrorMeasage = Bundle()
                    ErrorMeasage.putString("Measage", result.error.toString())
                    errorFragment.arguments = ErrorMeasage
                    errorFragment.show(requireActivity().supportFragmentManager, ErrorDialogFragment.TAG)
                } is UIState.Success -> {
                    if (loadingFragment.dialog != null)loadingFragment.dismiss()
                    requireActivity().viewModelStore.clear()
                    findNavController().navigate(com.example.main.R.id.main_navigation)
                }
                is UIState.Loading -> {
                    if(loadingFragment.dialog==null)
                    loadingFragment.show(requireActivity().supportFragmentManager, LoadingFragment.TAG)

                }
            }
        }

        view.signInButton.setOnClickListener {
            authViewModel.login(view.editTextTextLogin.text.toString(), view.editTextPassword.text.toString())
        }

        view.signUpButton.setOnClickListener { findNavController().navigate(R.id.to_registrate) }

        return view.root
    }
}
