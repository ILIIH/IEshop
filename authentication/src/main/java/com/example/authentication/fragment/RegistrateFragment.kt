package com.example.authentication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.authentication.R
import com.example.authentication.databinding.FragmentSingnUpBinding

class RegistrateFragment : Fragment() {

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentSingnUpBinding.inflate(layoutInflater, container, false)

        // prepearing spiner
        (view.spinner as AutoCompleteTextView).setText("Kabul, Afghanistan")
        val spinnerArrayAdapter =
            ArrayAdapter<String>(requireActivity().applicationContext, android.R.layout.simple_spinner_item, countries)
        (view.spinner as AutoCompleteTextView).setAdapter(spinnerArrayAdapter)


        view.buttonSignInFacebookText.setOnClickListener { }
        view.signInButton.setOnClickListener { findNavController().navigate(R.id.change_to_registrate) }
        view.signUpButton.setOnClickListener { findNavController().navigate(R.id.confirm_phone) }
        return view.root
    }
}
