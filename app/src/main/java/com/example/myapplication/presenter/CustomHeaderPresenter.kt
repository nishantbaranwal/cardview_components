package com.example.myapplication.presenter

import android.view.ViewGroup
import androidx.leanback.widget.Presenter
import com.example.myapplication.R
import com.example.myapplication.model.CustomHeaderItem
import com.example.myapplication.view.CustomHeaderView
import com.example.myapplication.view.ImageCardView

class CustomHeaderPresenter :Presenter(){
    private var cardView: CustomHeaderView?=null

    fun getLayoutResourceFile(): Int {
        if(cardView!=null)
        return cardView!!.getLayoutResourceFile()
        else
            return -1
    }
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        cardView = CustomHeaderView(parent!!.context, null)
        cardView!!.isSelected=false
        cardView!!.setFocusable(true)
        cardView!!.isFocusableInTouchMode=true
        cardView!!.cardType = 0
        cardView!!.setMainContainerDimensions(100,100)
        cardView!!.setTitle("Hulk")
        cardView!!.setIcon(R.drawable.hulk)


        return ViewHolder(cardView!!)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val cardView = viewHolder!!.view as CustomHeaderView
        cardView.setMainContainerDimensions(100,100)
        val customHeaderModel = item as CustomHeaderItem
        cardView.setTitle(customHeaderModel.headerTitle)
        cardView.setIcon(customHeaderModel.icon)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        cardView=null
    }

}