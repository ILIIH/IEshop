package com.example.core_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment





class LoadingFragment : DialogFragment() {

    companion object{
        val TAG = "LoadingFragmentTag"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_login, container, false)
        dialog!!.setTitle("simple dialog")
        return rootView
    }

}