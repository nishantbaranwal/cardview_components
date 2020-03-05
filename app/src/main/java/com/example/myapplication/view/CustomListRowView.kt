package com.example.myapplication.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.*
import androidx.leanback.widget.HorizontalGridView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.custom_lb_list_row.view.*
import android.content.Context.MODE_PRIVATE
import com.example.myapplication.R


@SuppressLint("ViewConstructor")
class CustomListRowView(context: Context, layoutResource:Int, attributeSet: AttributeSet?)
    : LinearLayout(context,attributeSet){

    val ctx=context
    private var mHorizontalGridView: HorizontalGridView?=null
    var i:Int?=null
    var id:Long?=null

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(layoutResource, this)
        mHorizontalGridView = findViewById(R.id.grid_view)
        mHorizontalGridView!!.setHasFixedSize(false)

        header_ll.setOnKeyListener { v, keyCode, event ->
            if(event.action == KeyEvent.ACTION_DOWN){
                if(keyCode==KeyEvent.KEYCODE_DPAD_RIGHT){
                    mHorizontalGridView!!.selectedPosition=0
                    titleName.visibility = View.VISIBLE
                    title_desc.visibility = View.VISIBLE
                    rating.visibility = View.VISIBLE

                }
                if(keyCode==KeyEvent.KEYCODE_DPAD_DOWN || keyCode==KeyEvent.KEYCODE_DPAD_UP ){
                    titleName.visibility = View.GONE
                    title_desc.visibility = View.GONE
                    rating.visibility = View.GONE

                }
            }
            return@setOnKeyListener false
        }

        header_ll.setOnFocusChangeListener { v, hasFocus ->
            val animSet: AnimatorSet?
            val scaleX: ObjectAnimator?
            val scaleY: ObjectAnimator?
            val editor = context.getSharedPreferences("Focusing", MODE_PRIVATE).edit()

            if(hasFocus){

                titleName.visibility = View.GONE
                title_desc.visibility = View.GONE
                rating.visibility = View.GONE
                scaleX = ObjectAnimator.ofFloat(header_ll, View.SCALE_X, 1.2f)
                scaleY = ObjectAnimator.ofFloat(header_ll, View.SCALE_Y, 1.2f)
                header_ll.pivotY = header_ll.measuredHeight.toFloat()/2
                animSet = AnimatorSet()
                editor.putBoolean("isFocused", true)
                editor.apply()

            }
            else{

                scaleX = ObjectAnimator.ofFloat(header_ll, View.SCALE_X, 1.0f)
                scaleY = ObjectAnimator.ofFloat(header_ll, View.SCALE_Y, 1.0f)
                header_ll.pivotY = 0.0f
                animSet = AnimatorSet()
                editor.putBoolean("isFocused", false)
                editor.apply()

            }
            animSet.setDuration(300).interpolator = AccelerateDecelerateInterpolator()
            animSet.playTogether(scaleX, scaleY)
            animSet.start()
        }
        header_ll.requestFocus()
    }

    fun setImageView(icon:Int){
        i = icon
        Glide.with(ctx)
            .load(icon)
            .apply(RequestOptions.centerCropTransform())
            .into(header)

    }
}