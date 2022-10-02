package com.example.core_ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.core_ui.R
import com.example.core_ui.databinding.ErrorDialogBinding
import com.example.core_ui.databinding.LoadingBinding

class ErrorDialogFragment: DialogFragment() {
    companion object{
        val TAG = "ErrorFragmentTag"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = ErrorDialogBinding.inflate(layoutInflater, container, false)
        val textMeasge = arguments?.getString("Measage");
        view.textMessage.text = textMeasge

        view.buttonAction.setOnClickListener {
            this.dismiss()
        }
        view.ExitIcon.setOnClickListener {
            this.dismiss()
        }
        return view.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.FullScreenDialog)
    }
}