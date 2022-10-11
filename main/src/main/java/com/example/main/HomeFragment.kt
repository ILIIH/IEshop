package com.example.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.authentication.fragment.viewModel.mainComponentViewModel
import com.example.authentication.fragment.viewModel.mainViewModel
import com.example.authentication.fragment.viewModel.mainViewModelFactory
import com.example.core.domain.error.UIState
import com.example.core_ui.screens.ErrorDialogFragment
import com.example.main.databinding.FragmentHomeBinding
import dagger.Lazy
import javax.inject.Inject
import com.example.core_ui.adapters.recycler.ProductAdapter

class HomeFragment : Fragment() {
    @Inject
    internal lateinit var mainViewModelFactory: Lazy<mainViewModelFactory>

    val mainViewModel: mainViewModel by viewModels { mainViewModelFactory.get() }
    val componentViewModel: mainComponentViewModel by viewModels()

    val errorFragment = ErrorDialogFragment()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.mainComponent.injectHome(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val prodAdapter = ProductAdapter()
        view.newRecycle.setLayoutManager(GridLayoutManager(requireContext(), 2))
        view.newRecycle.adapter = prodAdapter

        val prodFavAdapter = ProductAdapter()
        prodFavAdapter.setRecyclerViewLayout()
        view.recomendedRecycle.adapter = prodFavAdapter

        val chategoryAdapter = ProductAdapter()
        chategoryAdapter.setRecyclerViewLayout()
        view.categoryRecycle.adapter = chategoryAdapter

        mainViewModel.updateChategory {
            chategoryAdapter.submitList(it)
        }

        mainViewModel._productLoadingState.observe(requireActivity()) { list ->
            when (list) {
                is UIState.Error -> {
                    val ErrorMessage = Bundle()
                    ErrorMessage.putString("Measage", list.error.toString())
                    errorFragment.arguments = ErrorMessage
                    errorFragment.show(requireActivity().supportFragmentManager, ErrorDialogFragment.TAG)
                }
                is UIState.Success -> {
                    prodAdapter.submitList(list.data)
                }
            }
        }

        mainViewModel._productFavLoadingState.observe(requireActivity()) { list ->
            when (list) {
                is UIState.Error -> {
                    val ErrorMessage = Bundle()
                    ErrorMessage.putString("Measage", list.error.toString())
                    errorFragment.arguments = ErrorMessage
                    errorFragment.show(requireActivity().supportFragmentManager, ErrorDialogFragment.TAG)
                }
                is UIState.Success -> {
                    prodFavAdapter.submitList(list.data)
                }
            }
        }

        return view.root
    }
}
