package com.example.myapplication.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.leanback.widget.BaseCardView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import kotlinx.android.synthetic.main.image_card_view.view.*

class CustomHeaderView(private var ctx: Context,attrs: AttributeSet?= null) : BaseCardView(ctx,attrs){

    private var view: View? = null

    init {
        buildContainerCardView()
    }

    private fun buildContainerCardView(){
        val inflater: LayoutInflater = LayoutInflater.from(ctx)
        view = inflater.inflate(R.layout.custom_header_view,this)

    }

    fun setMainContainerDimensions(width: Int, height: Int) {
        val lp = container_view.layoutParams
        lp.width = width
        lp.height = height
        container_view.layoutParams=lp
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

    fun getLayoutResourceFile(): Int {
        return R.layout.custom_header_view
    }
}