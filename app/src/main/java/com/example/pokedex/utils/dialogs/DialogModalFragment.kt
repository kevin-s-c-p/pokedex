package com.example.pokedex.utils.dialogs

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentDialogModalBinding
import com.example.pokedex.utils.gone
import com.example.pokedex.utils.load

class DialogModalFragment: DialogFragment() {

    private lateinit var binding: FragmentDialogModalBinding

    companion object {
        const val ALERT = 1
        const val SUCCESS = 2
        const val ERROR = 3
    }

    private lateinit var clickId: OnClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDialogModalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title: String? = arguments?.getString("title")
        val bodyText: String? = arguments?.getString("body")
        val status: Int = arguments?.getInt("status") ?: ALERT
        val imagePokemon = arguments?.getString("imagePokemon") ?: ""

        setTitles(title, bodyText)
        setIconAlert(status)
        setImagePokemon(imagePokemon)

        binding.cardAlert.buttonCancel.setOnClickListener { clickId.onClickListener(it.id) }
        binding.cardAlert.buttonAccept.setOnClickListener { clickId.onClickListener(it.id) }
    }

    private fun setImagePokemon(imagePokemon: String) {
        binding.cardAlert.iconImage.load(imagePokemon)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            clickId = context as OnClickListener
        }catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setTitles(title: String?, body: String?) {
        if (title.isNullOrEmpty()) {
            binding.cardAlert.title.gone()
        } else {
            binding.cardAlert.title.text = title
        }

        if (body.isNullOrEmpty()) {
            binding.cardAlert.textBody.gone()
        } else {
            binding.cardAlert.textBody.text = body
        }
    }

    private fun setIconAlert(status: Int) {
        if (status == SUCCESS || status == ERROR) {
            binding.cardAlert.buttonCancel.gone()
        }

        /*val icon: Int = when(status) {
            ERROR -> R.drawable.icon_error
            SUCCESS -> R.drawable.ic_accept
            ALERT -> R.drawable.ic_questions
            else -> R.drawable.ic_questions
        }*/

        //binding.cardAlert.iconImage.setImageResource(icon)
    }

    fun setOnClickListener(
        firstButton: () -> Unit = {},
        secondButton: () -> Unit = {}
    ) {
        clickId = object : OnClickListener {
            override fun onClickListener(buttonSelected: Int) {
                when(buttonSelected) {
                    R.id.button_cancel -> firstButton()
                    R.id.button_accept -> secondButton()
                }
            }

        }
    }

    class Builder {
        private val bundle = bundleOf()

        fun title(title: String): Builder {
            bundle.putString("title", title)
            return this
        }

        fun messageBody(message: String): Builder {
            bundle.putString("body", message)
            return this
        }

        fun statusAlert(status: Int): Builder {
            bundle.putInt("status", status)
            return this
        }

        fun imagePokemon(image: String): Builder {
            bundle.putString("imagePokemon", image)
            return this
        }

        fun textFirstButton(textFirstButton: String): Builder {
            bundle.putString("buttonOne", textFirstButton)
            return this
        }

        fun textSecondButton(textSecondButton: String): Builder {
            bundle.putString("buttonTwo", textSecondButton)
            return this
        }

        fun create(): DialogModalFragment = DialogModalFragment().apply {
            arguments = bundle
        }
    }

    interface OnClickListener {
        fun onClickListener(buttonSelected: Int)
    }
}