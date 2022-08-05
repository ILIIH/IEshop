package com.example.authentication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.authentication.R
import com.example.authentication.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = FragmentOnboardingBinding.inflate(layoutInflater, container, false)
        view.getStartedButton.setOnClickListener { findNavController().navigate(R.id.login_from_home) }
        return view.root
    }
}
