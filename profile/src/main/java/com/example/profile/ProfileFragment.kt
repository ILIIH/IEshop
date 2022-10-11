package com.example.profile

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import com.example.core_ui.screens.ErrorDialogFragment
import com.example.profile.viewModel.profileComponentViewModel
import com.example.profile.viewModel.profileViewModel
import com.example.profile.viewModel.profileViewModelFactory
import dagger.Lazy
import javax.inject.Inject


class ProfileFragment : Fragment() {

    @Inject
    internal lateinit var profileViewModelFactory: Lazy<profileViewModelFactory>

    val profileViewModel: profileViewModel by viewModels { profileViewModelFactory.get() }
    val componentViewModel: profileComponentViewModel by viewModels()

    val errorFragment = ErrorDialogFragment()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.profileComponent.injectProfile(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

}