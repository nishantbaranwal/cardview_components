package com.example.myapplication.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.leanback.widget.BaseCardView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import kotlinx.android.synthetic.main.image_card_view.view.container_view
import kotlinx.android.synthetic.main.image_card_view.view.iv_icon
import kotlinx.android.synthetic.main.image_card_view.view.tv_title

class ImageCardView(private var ctx: Context,attrs: AttributeSet?= null) : BaseCardView(ctx,attrs){

    private var view: View? = null

    init {
        buildContainerCardView()
    }

    private fun buildContainerCardView(){
        val inflater: LayoutInflater = LayoutInflater.from(ctx)
        view = inflater.inflate(R.layout.image_card_view,this)

    }

    fun setMainContainerDimensions(width: Int, height: Int) {
        val lp = container_view.layoutParams
        lp.width = width
        lp.height = height
        container_view.layoutParams=lp
        Log.d("sdfsdf","sfsaf")
    }

    fun setTitle(title:String){
        view?.tv_title?.text = title
    }

    fun setIcon(icon: Int){
        Glide.with(ctx)
            .load(icon)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
            .into(iv_icon)
    }
}