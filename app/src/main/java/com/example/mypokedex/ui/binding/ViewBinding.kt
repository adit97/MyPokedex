package com.example.mypokedex.ui.binding

import android.annotation.SuppressLint
import android.app.Activity
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.mypokedex.R
import com.example.mypokedex.core.utils.toast
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette

@BindingAdapter("toast")
fun bindToast(view: View, text: String?) {
    if (text == null) return
    view.context.toast(text)
}

@BindingAdapter("imageUrl", "paletteView")
fun loadImage(view: AppCompatImageView, url: String?, paletteView: View?) {
    Glide.with(view.context)
        .load(url)
        .listener(
            GlidePalette.with(url)
                .use(BitmapPalette.Profile.MUTED_LIGHT)
                .intoCallBack { palette ->
                    val rgb = palette?.dominantSwatch?.rgb
                    if (rgb != null) {
                        paletteView?.setBackgroundColor(rgb)
                    }
                }
                .crossfade(true))
        .into(view)
}

@BindingAdapter("onBackPressed")
fun bindOnBackPressed(view: View, finish: Boolean) {
    val context = view.context
    if (finish && context is Activity) {
        view.setOnClickListener {
            context.onBackPressed()
        }
    }
}

@BindingAdapter("setFavorite")
fun bindOnClickFavorite(view: AppCompatImageView, isFavorite: Boolean) {
    if (isFavorite) {
        view.setImageResource(R.drawable.ic_round_favorite_36)
    } else {
        view.setImageResource(R.drawable.ic_round_favorite_border_36)
    }
}

@Suppress("DEPRECATION")
@SuppressLint("UseCompatLoadingForDrawables")
@BindingAdapter("setType")
fun bindOnPokemonType(view: LinearLayoutCompat, pokemonTypes: String?) {
    val context = view.context
    pokemonTypes?.split(", ")?.forEach {
        val textView = AppCompatTextView(context)
        val params = LinearLayoutCompat.LayoutParams(
            LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
            LinearLayoutCompat.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 0, 16, 0)
        params.gravity = Gravity.CENTER
        textView.layoutParams = params
        textView.text = it
        textView.background = context.resources.getDrawable(R.drawable.text_view_round, null)
        textView.setTextColor(context.resources.getColor(R.color.white))
        view.addView(textView)
    }
}