package com.example.authentication.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.authentication.databinding.FragmentConfirmationBinding
import com.example.authentication.fragment.viewModel.authComponentViewModel
import com.example.authentication.fragment.viewModel.authViewModel
import com.example.authentication.fragment.viewModel.authViewModelFactory
import com.example.core.domain.error.UIState
import com.example.core_ui.screens.ErrorDialogFragment
import com.example.core_ui.screens.LoadingFragment
import dagger.Lazy
import javax.inject.Inject


class ConfirmationFragment : Fragment() {

    @Inject
    internal lateinit var authViewModelFactory: Lazy<authViewModelFactory>

    val authViewModel: authViewModel by viewModels { authViewModelFactory.get() }
    val componentViewModel: authComponentViewModel by viewModels()

    val loadingFragment = LoadingFragment()
    val errorFragment = ErrorDialogFragment()

    val args: ConfirmationFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.authComponent.injectConfirmation(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = FragmentConfirmationBinding.inflate(layoutInflater, container, false)

        authViewModel.sendEmail(args.curUser)

        view.continueButton.setOnClickListener {
            authViewModel.validate(view.FirstN.text.toString()
            +view.SecondN.text.toString()+view.ThirdN.text.toString()+view.ForthN.text.toString())

        }

        authViewModel._loginState.observe(requireActivity()) { result ->

            when (result) {
                is UIState.Error -> {

                    val ErrorMessage = Bundle()
                    ErrorMessage.putString("Measage",result.error.toString())
                    errorFragment.arguments = ErrorMessage
                    errorFragment.show(requireActivity().supportFragmentManager, ErrorDialogFragment.TAG)

                }is UIState.Success -> {
                authViewModel.login(args.curUser.login,args.curUser.password,false)
                requireActivity().viewModelStore.clear();
                findNavController().navigate(com.example.main.R.id.main_navigation)
                }
                is UIState.Loading ->{
                    if(loadingFragment.dialog!=null)
                    loadingFragment.show(requireActivity().supportFragmentManager, LoadingFragment.TAG)
                }
            }
        }

        return view.root
    }
}
