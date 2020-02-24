package com.example.myapplication

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.leanback.widget.BaseCardView
import kotlinx.android.synthetic.main.rendered_card_view.view.*
import android.graphics.Color.parseColor
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.animation.AnimationUtils


class RenderingCardView(private var ctx: Context,attrs:AttributeSet?= null) : BaseCardView(ctx,attrs) {
    var view: View ? = null
    var selectedColor: Int = R.color.selected
    var focusedColor: Int = R.color.focused
    var unFocusedColor: Int = R.color.unfocused

    init {
        //initialise the card's layout
        buildContainerCardView()

        if(view?.tv_title != null)
            view?.tv_title?.setTextColor(ContextCompat.getColor(context, unFocusedColor))
        if(view?.iv_icon != null)
            view?.iv_icon?.setColorFilter(ContextCompat.getColor(context, unFocusedColor));

        //focus handler
        container_view.setOnFocusChangeListener {
                v, hasFocus ->
            if(hasFocus) {
                //if container has focus
                val animZoomIn = AnimationUtils.loadAnimation(
                    ctx,
                    R.anim.zoom_in
                )
                container_view.startAnimation(animZoomIn)
                if(view?.tv_title != null)
                    view?.tv_title?.setTextColor(ContextCompat.getColor(context, focusedColor))
                if(view?.iv_icon != null)
                    view?.iv_icon?.setColorFilter(ContextCompat.getColor(context, focusedColor));

            }
            else{
                //if container does not have focus

                if(view?.tv_title != null)
                    view?.tv_title?.setTextColor(ContextCompat.getColor(context, unFocusedColor))
                if(view?.iv_icon != null)
                    view?.iv_icon?.setColorFilter(ContextCompat.getColor(context, unFocusedColor));

            }
        }

        // if container is clicked
        container_view.setOnClickListener(){
            if(view?.tv_title != null)
                view?.tv_title?.setTextColor(ContextCompat.getColor(context, selectedColor))
            if(view?.iv_icon != null)
                view?.iv_icon?.setColorFilter(ContextCompat.getColor(context, selectedColor));
        }


    }

    fun buildContainerCardView(){
        val inflater:LayoutInflater = LayoutInflater.from(ctx)
        view = inflater.inflate(R.layout.rendered_card_view,this)
    }

    
    fun setTitle(title:String){
        view?.tv_title?.setText(title)
    }

    fun setIcon(icon: Int){
        view?.iv_icon?.setImageDrawable(ctx.getDrawable(icon))
    }
}