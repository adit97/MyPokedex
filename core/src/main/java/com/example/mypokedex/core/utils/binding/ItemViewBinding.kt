package com.example.mypokedex.core.utils.binding

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette


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
