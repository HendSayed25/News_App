package com.example.homeactivity.News

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

//this function use to load image in xml using it's url which come from apis
@BindingAdapter("imageUrl")
fun loadImageFromUrl(imageView:ImageView,Url:String){
    Glide.with(imageView).
    load(Url).into(imageView)

}
