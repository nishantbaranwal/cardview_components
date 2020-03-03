package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.leanback.widget.HorizontalGridView
import androidx.leanback.widget.ShadowOverlayContainer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.custom_lb_list_row.view.*
import kotlinx.android.synthetic.main.image_card_view.view.*

@SuppressLint("ViewConstructor")
class CustomListRowView(context: Context, layoutResource:Int, attributeSet: AttributeSet?) : LinearLayout(context,attributeSet){
    val ctx=context
    private var mHorizontalGridView: HorizontalGridView?=null
    var i:Int?=null
    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(layoutResource, this)

        mHorizontalGridView = findViewById(R.id.grid_view)
        mHorizontalGridView!!.setHasFixedSize(false)
        header.setOnKeyListener { v, keyCode, event ->
            if(event.action == KeyEvent.ACTION_DOWN){
                if(keyCode==KeyEvent.KEYCODE_DPAD_RIGHT){
                    mHorizontalGridView!!.selectedPosition=0
                    titleName.visibility = View.VISIBLE
                    title_desc.visibility = View.VISIBLE
                }
            }
            return@setOnKeyListener false
        }
    }

    fun setImageView(icon:Int){
        i = icon
            Glide.with(ctx)
                .load(icon)
                .apply(RequestOptions.centerCropTransform())
                .into(header)
        header.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                titleName.visibility=View.GONE
                header.setColorFilter(
                    ContextCompat.getColor(context, R.color.colorPrimary),
                    android.graphics.PorterDuff.Mode.MULTIPLY

                )
            }
            else
            {
                header.setColorFilter(
                    ContextCompat.getColor(context, R.color.colorAccent),
                    android.graphics.PorterDuff.Mode.MULTIPLY

                )
            }
        }
    }
    fun getImage():Int{
        return i!!
    }
}