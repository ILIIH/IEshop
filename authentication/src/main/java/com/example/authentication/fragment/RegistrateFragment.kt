package com.example.authentication.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.authentication.R
import com.example.authentication.databinding.FragmentSingnUpBinding
import com.example.authentication.fragment.viewModel.authComponentViewModel
import com.example.authentication.fragment.viewModel.authViewModel
import com.example.authentication.fragment.viewModel.authViewModelFactory
import com.example.core_ui.LoadingFragment
import dagger.Lazy
import javax.inject.Inject


class RegistrateFragment : Fragment() , AdapterView.OnItemClickListener{
    @Inject
    internal lateinit var authViewModelFactory: Lazy<authViewModelFactory>

    val authViewModel: authViewModel by viewModels { authViewModelFactory.get() }
    val componentViewModel: authComponentViewModel by viewModels()

    val loadingFragment = LoadingFragment()

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
    val currentCountry: String = "Tirana, Albania"
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

        // prepearing spiner
        (view.spinner as AutoCompleteTextView).setText("Kabul, Afghanistan")
        val spinnerArrayAdapter =
            ArrayAdapter<String>(requireActivity().applicationContext, android.R.layout.simple_spinner_item, countries)
        (view.spinner as AutoCompleteTextView).setAdapter(spinnerArrayAdapter)


        view.buttonSignInFacebookText.setOnClickListener { }
        view.signInButton.setOnClickListener { findNavController().navigate(R.id.change_to_registrate) }
        view.signUpButton.setOnClickListener {
            if(view.checkBoxAgrreTerms.isChecked){
                authViewModel.registrate(
                    view.username.text.toString(),
                    view.Surname.text.toString(),
                    view.username.text.toString(),
                    "",
                    view.Telephone.text.toString(),
                    null,
                    null,
                    view.editTextPassword.text.toString(),
                    view.emailAddress.text.toString(),
                    currentCountry
                    )

                //findNavController().navigate(R.id.confirm_phone)
            }
            else {
               // TODO("Сделать подсвечивание красным в случае если не был нажат чекбокс согадасия с правилами ")
            }
             }
        return view.root
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        = p0?.getItemAtPosition(p2).toString()
    }
}
