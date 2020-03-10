package com.example.myapplication.presenter

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.leanback.widget.Presenter
import com.example.myapplication.view.ImageCardView
import com.example.myapplication.R
import com.example.myapplication.model.ImageModel

class CardPresenter : Presenter() {
    var cardView: ImageCardView?=null
    var i:Int=0
    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {

        val cardView: ImageCardView = viewHolder!!.view as ImageCardView
        cardView.setMainContainerDimensions(400,300)
        val imageModel = item as ImageModel
        cardView.setTitle(imageModel.text)
        cardView.setIcon(imageModel.icon)
        var animSet: AnimatorSet?
        var scaleX: ObjectAnimator?
        var scaleY: ObjectAnimator?
        cardView.setOnKeyListener { v, keyCode, event ->
            if(event.action == KeyEvent.ACTION_DOWN){
                if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN || keyCode == KeyEvent.KEYCODE_DPAD_UP ){
                    scaleX = ObjectAnimator.ofFloat(cardView, View.SCALE_X, 1.0f)
                    scaleY = ObjectAnimator.ofFloat(cardView, View.SCALE_Y, 1.0f)
                    cardView.pivotY = 0.0f
                    animSet = AnimatorSet()
                    animSet!!.setDuration(300).interpolator = AccelerateDecelerateInterpolator()
                    animSet!!.playTogether(scaleX, scaleY)
                    animSet!!.start()
                }

            }
            return@setOnKeyListener false
        }

        cardView.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                scaleX = ObjectAnimator.ofFloat(cardView, View.SCALE_X, 1.2f)
                scaleY = ObjectAnimator.ofFloat(cardView, View.SCALE_Y, 1.2f)
                cardView.pivotY = cardView.measuredHeight.toFloat()/2
                animSet = AnimatorSet()
            }
            else{
                scaleX = ObjectAnimator.ofFloat(cardView, View.SCALE_X, 1.0f)
                scaleY = ObjectAnimator.ofFloat(cardView, View.SCALE_Y, 1.0f)
                cardView.pivotY = 0.0f
                animSet = AnimatorSet()
            }
            animSet!!.setDuration(300).interpolator = AccelerateDecelerateInterpolator()
            animSet!!.playTogether(scaleX, scaleY)
            animSet!!.start()
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        cardView!!.setFocusable(false)
        cardView!!.setFocusableInTouchMode(false)    }

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        cardView = ImageCardView(parent!!.getContext(), null)
        cardView!!.setSelected(false)
        cardView!!.setFocusable(true)
        cardView!!.setFocusableInTouchMode(true)
        cardView!!.setCardType(0)
        cardView!!.setMainContainerDimensions(400,300)
        cardView!!.setTitle("Hulk")
        cardView!!.setIcon(R.drawable.hulk)


        return ViewHolder(cardView!!)
    }
}