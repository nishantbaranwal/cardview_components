package com.example.myapplication

import android.graphics.Color
import android.view.ViewGroup
import androidx.leanback.widget.Presenter

class CardPresenter: Presenter() {
    var cardView:ImageCardView?=null
    var i:Int=0
    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {

        val cardView:ImageCardView = viewHolder!!.view as ImageCardView
        cardView.setMainContainerDimensions(400,300)
        val s = "Hulk"+i++
        cardView.setTitle(s)
        cardView.setIcon(R.drawable.hulk)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        cardView = null
    }

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        cardView = ImageCardView(parent!!.getContext(), null)
        cardView!!.setSelected(false)
        cardView!!.setFocusable(true)
        cardView!!.setFocusableInTouchMode(true)
        cardView!!.setCardType(0)
        cardView!!.setMainContainerDimensions(400,300)
        cardView!!.setTitle("Hulk")
        cardView!!.setIcon(R.drawable.hulk)
//        cardView!!.setTitleColor(Color.RED)
//        cardView!!.setBackgroundColor(Color.BLUE)
        return ViewHolder(cardView!!)
    }
}