package com.example.authentication.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.authentication.R
import com.example.authentication.databinding.FragmentLoginBinding
import com.example.authentication.di.AuthDepsProvider
import com.example.authentication.fragment.viewModel.authComponentViewModel
import com.example.authentication.fragment.viewModel.authViewModel
import com.example.authentication.fragment.viewModel.authViewModelFactory
import com.example.core.domain.Result
import com.example.core_ui.LoadingFragment
import dagger.Lazy
import javax.inject.Inject


class LoginFragment : Fragment() {

    @Inject
    internal lateinit var authViewModelFactory: Lazy<authViewModelFactory>

    val authViewModel: authViewModel by viewModels { authViewModelFactory.get() }
    val componentViewModel: authComponentViewModel by viewModels()

    val loadingFragment = LoadingFragment()

    // should to replace width list of country from datyabace when it will be ready TODO
    val Countries = arrayOf(
        "Kabul, Afghanistan", "Tirana, Albania",
        "Yerevan, Armenia", "Vienna, Austri",
        "Baku, Azerbaijan", "Brasilia, Brazil",
        "Minsk, Belarus", "Brussels, Belgium",
        "Brasilia, Brazil", "Copenhagen, Denmark",
        "Prague, Czechia", "Brasilia, Brazil",
        "Helsinki, Finland", "Paris, France",
        "Tokyo, Japan", "Kyiv, Ukraine",
        "London, United Kingdom", "Washington, D.C, United States of America",
        "Ankara, Turkey"
    )


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(requireActivity() is AuthDepsProvider) {
            Log.i("AppProv","in Fragment is AuthDepsProvider")
        }
        componentViewModel.authComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = FragmentLoginBinding.inflate(layoutInflater, container, false)

        // prepare Spiner with counties
        val spinnerArrayAdapter =
            ArrayAdapter<String>(requireActivity().applicationContext, android.R.layout.simple_spinner_item, Countries)
        view.spinner.adapter = spinnerArrayAdapter

        ///////////////

        authViewModel._loginState.observe(requireActivity()) { result ->
            loadingFragment.dismiss()
            when (result) {
                is Result.Error -> Toast.makeText(context, getString(R.string.DatabaceError), Toast.LENGTH_SHORT).show()
                is Result.Success -> {
                    Toast.makeText(context, getString(R.string.SuccessLogin), Toast.LENGTH_SHORT).show()
                    //  TODO()add navigation
                }
            }
        }

        view.signInButton.setOnClickListener {
            authViewModel.login(view.editTextTextLogin.text.toString(), view.editTextPassword.text.toString())
            loadingFragment.show(requireActivity().supportFragmentManager,LoadingFragment.TAG)

        }

        view.signUpButton.setOnClickListener { findNavController().navigate(R.id.to_registrate) }
        return view.root
    }
}
