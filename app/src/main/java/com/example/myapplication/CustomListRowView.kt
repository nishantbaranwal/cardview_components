package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.GridView
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.leanback.widget.HorizontalGridView
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

//        orientation = VERTICAL
//        val adapter = CustomAdapter(this)
//
//        mHorizontalGridView!!.adapter = adapter
        Log.d("dgfsdgsg",context.toString())

    }

    fun getGridView(): HorizontalGridView {
        return mHorizontalGridView!!
    }

    fun setTextView(a:String){
        titleName.setText(a)
//        mHorizontalGridView!!.set
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