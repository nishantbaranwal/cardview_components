package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.custom_lb_list_row.view.*

class CustomListRowDataClass(context: Context, layoutResource:Int, attributeSet: AttributeSet?): LinearLayout(context,attributeSet) {
    init{
        val inflater = LayoutInflater.from(context)
        inflater.inflate(layoutResource, this)
        header.setOnKeyListener { v, keyCode, event ->
            if(event.action == KeyEvent.ACTION_DOWN){
                if(keyCode== KeyEvent.KEYCODE_DPAD_RIGHT){
                    grid_view!!.selectedPosition=0
                    titleName.visibility = View.VISIBLE
                }
            }
            return@setOnKeyListener false
        }
    }
}