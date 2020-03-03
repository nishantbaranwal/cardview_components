package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.GridView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.leanback.widget.HorizontalGridView
import androidx.leanback.widget.ShadowOverlayContainer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.custom_lb_list_row.view.*
import kotlinx.android.synthetic.main.image_card_view.view.*

class CustomListRowView(context: Context, attributeSet: AttributeSet?) : LinearLayout(context,attributeSet){
    val ctx=context
    private var mHorizontalGridView: HorizontalGridView?=null
    var i:Int?=null
    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.custom_lb_list_row, this)

        mHorizontalGridView = findViewById(R.id.grid_view)
        mHorizontalGridView!!.setHasFixedSize(false)
        header.setOnKeyListener { v, keyCode, event ->
            if(event.action == KeyEvent.ACTION_DOWN){
                if(keyCode==KeyEvent.KEYCODE_DPAD_RIGHT){
                    mHorizontalGridView!!.selectedPosition=0
                    getTextView().visibility = View.VISIBLE
                }
            }
            return@setOnKeyListener false
        }
    }

    fun selectedPosition():Boolean{
        return mHorizontalGridView!=null && mHorizontalGridView!!.selectedPosition==0
    }

    fun getGridView(): HorizontalGridView {
        return mHorizontalGridView!!
    }

    fun setTextView(a:String){
        titleName.setText(a)
    }

    fun getTextView():TextView{
        return titleName
    }

    fun getTitleName():String{
        return titleName.text.toString()
    }
    fun setImageView(icon:Int){
        i = icon
            Glide.with(ctx)
                .load(icon)
                .apply(RequestOptions.centerCropTransform())
                .into(header)
        header.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
                header.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), android.graphics.PorterDuff.Mode.MULTIPLY)
            else
                Glide.with(ctx)
                    .load(icon)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
                    .into(header)

        }
    }
    fun getImage():Int{
        return i!!
    }
    fun getImageView():ImageView{
        return header
    }
}