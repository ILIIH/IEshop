package com.example.authentication.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.authentication.R
import com.example.authentication.databinding.FragmentSingnUpBinding
import com.example.authentication.fragment.viewModel.authComponentViewModel
import com.example.authentication.fragment.viewModel.authViewModel
import com.example.authentication.fragment.viewModel.authViewModelFactory
import com.example.core.domain.error.UIState
import com.example.core_ui.screens.ErrorDialogFragment
import com.example.core_ui.screens.LoadingFragment
import com.example.core_ui.util.hideKeyboard
import com.facebook.*
import com.facebook.FacebookSdk.getApplicationContext
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import dagger.Lazy
import javax.inject.Inject


class RegistrateFragment : Fragment() {
    @Inject
    internal lateinit var authViewModelFactory: Lazy<authViewModelFactory>

    val authViewModel: authViewModel by viewModels { authViewModelFactory.get() }
    val componentViewModel: authComponentViewModel by viewModels()


    val callbackManager = CallbackManager.Factory.create();
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
    var currentCountry: String = "Tirana, Albania"
    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.authComponent.injectRegistrate(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = FragmentSingnUpBinding.inflate(layoutInflater, container, false)
        val checkBox = view.checkboxAgrreTerms
        val password = view.editTextPassword
        val loginButton =  view.signInFacebookButton

        authViewModel._loginState.observe(viewLifecycleOwner) { status ->
            when (status) {
                is UIState.Error -> {
                    if(loadingFragment.dialog!=null)loadingFragment.dismiss()
                    val ErrorMeasage = Bundle()
                    ErrorMeasage.putString("Measage",status.error.toString())
                    errorFragment.arguments = ErrorMeasage
                    errorFragment.show(requireActivity().supportFragmentManager, ErrorDialogFragment.TAG)

                }
                is UIState.Success -> {
                    if(loadingFragment.dialog!=null)loadingFragment.dismiss()
                    Log.i("RepoLog","Status = " + status.data!!)
                    findNavController().navigate(RegistrateFragmentDirections.confirmPhone(status.data!!))
                }
                is UIState.Loading ->{
                    if(loadingFragment.dialog==null)
                   loadingFragment.show(requireActivity().supportFragmentManager, LoadingFragment.TAG)
                }
            }
        }

        // prepearing spiner
        val spiner = (view.spinner as AutoCompleteTextView)
        spiner.setText("Kabul, Afghanistan")
        val spinnerArrayAdapter =
            ArrayAdapter<String>(requireActivity().applicationContext, android.R.layout.simple_spinner_item, countries)
        (view.spinner as AutoCompleteTextView).setAdapter(spinnerArrayAdapter)

        spiner.setOnItemClickListener { adapterView, _, i, _ ->
            currentCountry = adapterView.getItemAtPosition(i).toString()
        }

        ///////////////////
        loginButton.fragment = this
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {
                findNavController().navigate(com.example.main.R.id.main_navigation)

            }

            override fun onCancel() {
                val ErrorMeasage = Bundle()
                ErrorMeasage.putString("Measage","Cancel of request")
                errorFragment.arguments = ErrorMeasage
                errorFragment.show(requireActivity().supportFragmentManager, ErrorDialogFragment.TAG)
            }

            override fun onError(exception: FacebookException) {
                val ErrorMeasage = Bundle()
                ErrorMeasage.putString("Measage","Error of request $exception")
                errorFragment.arguments = ErrorMeasage
                errorFragment.show(requireActivity().supportFragmentManager, ErrorDialogFragment.TAG)
            }
        })

        ///////////// Facebook SignIn //////////////////////////

        view.signInFacebookLayout.setOnClickListener {
            loginButton.performClick()
        }

        loginButton.setOnClickListener {
            val accessToken = AccessToken.getCurrentAccessToken()
            val isLoggedIn = accessToken != null && !accessToken.isExpired
            if(isLoggedIn) LoginManager.getInstance().logInWithReadPermissions(
                activity, listOf("public_profile")
            )

            val request = GraphRequest.newMeRequest(
                accessToken
            ) { `object`, response ->
                val username = `object`.getString("first_name")
                val surname = `object`.getString("last_name")
                val link = `object`.getString("link")
                val picture = `object`.getJSONObject("picture").getJSONObject("data").getString("url")
                val curCountry = `object`.getJSONObject("location").getString("name")

                authViewModel.registrate(
                    username,
                    surname,
                    username,
                    picture,
                    "facebook",
                    listOf(),
                    listOf(),
                    link,
                    "facebook",
                    curCountry
                )
            }
            val parameters = Bundle()
            parameters.putString("fields", "id,name,link,picture.type(large)")
            request.parameters = parameters
            request.executeAsync()

        }

        ///////////////////////


        /////////////////////////

        view.signInButton.setOnClickListener { findNavController().navigate(R.id.change_to_registrate) }
        view.signUpButton.setOnClickListener {
            if (view.checkboxAgrreTerms.isChecked) {
                authViewModel.registrate(
                    view.username.text.toString(),
                    view.Surname.text.toString(),
                    view.username.text.toString(),
                    "",
                    view.Telephone.text.toString(),
                    listOf(),
                    listOf(),
                    password.text.toString(),
                    view.emailAddress.text.toString(),
                    currentCountry
                )
            } else {
                checkBox.requestFocus()
                view.termsTitle.requestFocus()
                view.termsTitle.setTextColor(Color.RED)
                view.TermLink.setTextColor(Color.RED)
            }
        }
        checkBox.setOnClickListener {
            view.signUpButton.requestFocus()
        }

        view.emailAddress.setOnKeyListener{ _, i, keyEvent ->
            if(keyEvent.action == KeyEvent.ACTION_UP &&
                (i == KeyEvent.KEYCODE_ENTER)
            ) {
                password.requestFocus()
            }
            false
        }
        password.setOnKeyListener { _, i, keyEvent ->
            if(keyEvent.action == KeyEvent.ACTION_UP &&
                    (i == KeyEvent.KEYCODE_ENTER)&& password.text.length > 8
            ) {
                    hideKeyboard()
        }
             false;
        }


        return view.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(requireActivity().application);
        AccessToken.getCurrentAccessToken();

        super.onCreate(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

}

