package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.leanback.widget.BaseCardView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.image_card_view.view.container_view
import kotlinx.android.synthetic.main.image_card_view.view.iv_icon
import kotlinx.android.synthetic.main.image_card_view.view.tv_title

class ImageCardView(private var ctx: Context,attrs: AttributeSet?= null) : BaseCardView(ctx,attrs){

    var view: View? = null


    init {
        buildContainerCardView()
    }

    private fun buildContainerCardView(){
        val inflater: LayoutInflater = LayoutInflater.from(ctx)
        view = inflater.inflate(R.layout.image_card_view,this)
        Glide.with(ctx)
            .load(R.drawable.hulk)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
            .into(iv_icon);
    }

    fun setMainContainerDimensions(width: Int, height: Int) {
        val lp = container_view.layoutParams
        lp.width = width
        lp.height = height
        container_view.setLayoutParams(lp)
    }
    fun setTitle(title:String){
        view?.tv_title?.setText(title)
    }

}