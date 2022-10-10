package com.andriivanov.excitelcountries.utils

import android.app.AlertDialog
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.andriivanov.excitelcountries.R

fun View.visibleOrGone(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun ImageView.loadUrl(url: String) {
    val imageLoader = ImageLoader.Builder(this.context)
        .components { add(SvgDecoder.Factory()) }
        .build()

    val request = ImageRequest.Builder(this.context)
        .crossfade(true)
        .crossfade(500)
        .placeholder(R.drawable.ic_circle)
        .error(R.drawable.ic_error)
        .data(url)
        .target(this)
        .build()

    imageLoader.enqueue(request)
}

fun Fragment.showDialog(title: String, message: String, onOkayAction: () -> Unit = {}) {
    // generate and show a new dialog
    val dialog = AlertDialog.Builder(requireContext()).apply {
        setTitle(title)
        setMessage(message)
        setCancelable(false)
        setPositiveButton(getString(R.string.button_ok)) { dialog, _ ->
            dialog.dismiss()
            onOkayAction.invoke()
        }
    }.create()
    dialog?.show()
}

