package com.example.authentication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.authentication.R
import com.example.authentication.databinding.FragmentSingnUpBinding

class RegistrateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentSingnUpBinding.inflate(layoutInflater, container, false)
        view.buttonSignInFacebookText.setOnClickListener { }
        view.signInButton.setOnClickListener { findNavController().navigate(R.id.change_to_registrate) }
        view.signUpButton.setOnClickListener { findNavController().navigate(R.id.confirm_phone) }
        return view.root
    }
}
