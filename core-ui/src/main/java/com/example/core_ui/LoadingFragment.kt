package com.example.core_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.core_ui.databinding.LoadingBinding


class LoadingFragment : DialogFragment() {

    companion object{
        val TAG = "LoadingFragmentTag"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LoadingBinding.inflate(layoutInflater, container, false)
        return view.root
    }

}