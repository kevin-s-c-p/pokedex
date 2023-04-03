package com.example.pokedex.utils.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.pokedex.R

class LoaderProgressDialog :  DialogFragment() {

    private var message: String? = ""

    companion object {
        private const val MESSAGE = "message"

        fun newInstance() = LoaderProgressDialog()

        fun newInstance(message: String): LoaderProgressDialog {
            val loaderProgressDialog = LoaderProgressDialog()
            val args = Bundle()
            args.putString(MESSAGE, message)
            loaderProgressDialog.arguments = args
            return loaderProgressDialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.TransparentDialogTheme)

        isCancelable = false
        arguments?.let {
            message = it.getString(MESSAGE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_alert_progress, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (!message.isNullOrBlank()) {
            //txtLoading.text = message
        }

    }

    fun isShowing() = dialog != null && dialog?.isShowing ?: false

}