package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.leanback.widget.HorizontalGridView
import kotlinx.android.synthetic.main.custom_grid_view.view.*

class CustomGridView(context: Context,attributeSet: AttributeSet?=null) : HorizontalGridView(context,attributeSet){
    init {
        LayoutInflater.from(context).inflate(R.layout.custom_grid_view,this)
        grid_view.setHasFixedSize(false)
    }
//    fun getGridView():HorizontalGridView{
//        return grid_view
//    }
//    fun getTitle(): TextView{
//        return titleName
//    }
}