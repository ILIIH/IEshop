package com.example.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.authentication.fragment.viewModel.mainComponentViewModel
import com.example.authentication.fragment.viewModel.mainViewModel
import com.example.authentication.fragment.viewModel.mainViewModelFactory
import com.example.core.domain.error.UIState
import com.example.core_ui.screens.ErrorDialogFragment
import com.example.main.databinding.FragmentHomeBinding
import dagger.Lazy
import javax.inject.Inject

class HomeFragment : Fragment() {
    @Inject
    internal lateinit var mainViewModelFactory: Lazy<mainViewModelFactory>

    val mainViewModel: mainViewModel by viewModels { mainViewModelFactory.get() }
    val componentViewModel: mainComponentViewModel by viewModels()

    val errorFragment = ErrorDialogFragment()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.mainComponent.injectHome(this)
        mainViewModel.loadAllProduct()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val prodAdapter = ProductAdapter()
        view.newRecycle.adapter = prodAdapter
        mainViewModel._productLoadingState.observe(requireActivity()) { list ->
            when (list) {
                is UIState.Error -> {
                    val ErrorMessage = Bundle()
                    ErrorMessage.putString("Measage",list.error.toString())
                    errorFragment.arguments = ErrorMessage
                    errorFragment.show(requireActivity().supportFragmentManager, ErrorDialogFragment.TAG)
                }
                is UIState.Success -> {
                    prodAdapter.submitList(list.data)
                }
            }
        }
        return view.root
    }
}
