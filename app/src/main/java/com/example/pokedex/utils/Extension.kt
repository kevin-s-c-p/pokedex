package com.example.pokedex.utils

import BaseViewModel.Companion.PROGRESS_START
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.example.pokedex.items.PoekmonItem
import com.example.pokedex.utils.dialogs.DialogModalFragment
import com.example.pokedex.utils.dialogs.ProgressDialog

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun ImageView.load(idPokemon: String) {
    Glide.with(this)
        .load(idPokemon)
        .into(this)
}

fun showMessageDialog(title: String, message: String, childFragment: FragmentManager, pokemon: PoekmonItem, callback: () -> Unit) {
    DialogModalFragment.Builder()
        .statusAlert(DialogModalFragment.ALERT)
        .title(title)
        .messageBody(message)
        .imagePokemon(pokemon.imagePokemon)
        .create().apply {
            setOnClickListener(
                firstButton = {
                    dismissNow()
                },
                secondButton = {
                    callback.invoke()
                    dismissNow()
                }
            )
        }
        .show(childFragment, null)
}

fun showMessageDialog(title: String, childFragment: FragmentManager, isCorrect: Boolean? = false, callback: () -> Unit) {
    val status = when (isCorrect){
        true -> DialogModalFragment.SUCCESS
        false -> DialogModalFragment.ERROR
        else -> DialogModalFragment.ALERT
    }
    DialogModalFragment.Builder()
        .statusAlert(status)
        .title(title)
        .create().apply {
            setOnClickListener(
                firstButton = {
                    dismissNow()
                },
                secondButton = {
                    callback.invoke()
                    dismissNow()
                }
            )
        }
        .show(childFragment, null)
}

fun showMessageDialog(title: String, childFragment: FragmentManager, isCorrect: Boolean? = false) {
    val status = when (isCorrect){
        true -> DialogModalFragment.SUCCESS
        false -> DialogModalFragment.ERROR
        else -> DialogModalFragment.ALERT
    }
    DialogModalFragment.Builder()
        .statusAlert(status)
        .title(title)
        .create().apply {
            setOnClickListener(
                firstButton = {
                    dismissNow()
                },
                secondButton = {
                    dismissNow()
                }
            )
        }
        .show(childFragment, null)
}

fun showIconLoad(progress: Int, child: FragmentManager) {
    when(progress){
        PROGRESS_START ->{
            ProgressDialog.showProgressDialog(child, "")
        }
        else->{
            ProgressDialog.hideProgressDialog()
        }
    }
}