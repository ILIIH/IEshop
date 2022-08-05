package com.example.authentication.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.authentication.R
import com.example.authentication.databinding.FragmentLoginBinding
import com.example.authentication.di.AuthDepsProvider
import com.example.authentication.di.authDepsProvider
import com.example.authentication.fragment.viewModel.authComponentViewModel
import com.example.authentication.fragment.viewModel.authViewModel
import com.example.authentication.fragment.viewModel.authViewModelFactory
import com.example.core.model.Result
import dagger.Lazy
import javax.inject.Inject

class LoginFragment : Fragment() {

    @Inject
    internal lateinit var authViewModelFactory: Lazy<authViewModelFactory>

    val authViewModel: authViewModel by viewModels { authViewModelFactory.get() }
    val componentViewModel: authComponentViewModel by viewModels()

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
    ): View? {
        val view = FragmentLoginBinding.inflate(layoutInflater, container, false)

        authViewModel._loginState.observe(requireActivity()) { result ->
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
        }

        view.signUpButton.setOnClickListener { findNavController().navigate(R.id.to_registrate) }
        return view.root
    }
}
