package com.example.myapplication.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.*
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HorizontalGridView
import kotlinx.android.synthetic.main.custom_lb_list_row.view.*
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.CustomHeaderViewBinding
import com.example.myapplication.databinding.CustomTemplateBinding
import com.example.myapplication.model.CustomHeaderItem
import com.example.myapplication.model.CustomListRowDataClass


//custom_list_row_view is an holder or parent view for all views
// i.e. header_template, horizontal_grid_view and template inside one row
@SuppressLint("ViewConstructor")
class CustomListRowView(context: Context, layoutResource:Int, attributeSet: AttributeSet?)
    : LinearLayout(context,attributeSet){
    fun expandHeader(headerAdapter: ArrayObjectAdapter, layoutRes: Int) {

        val binding:CustomHeaderViewBinding = DataBindingUtil.inflate((context as Activity).layoutInflater,
            layoutRes,header_container,false)
        binding.headerData= headerAdapter[0] as CustomHeaderItem
        header_container.addView(binding.root)
    }

    fun expandTemplate(
        headerAdapter: CustomListRowDataClass,
        layoutRes: Int
    ) {
        title_template.removeAllViews()

        val binding:CustomTemplateBinding = DataBindingUtil.inflate((context as Activity).layoutInflater,
            layoutRes,title_template,false)
        binding.templateData= CustomListRowDataClass("","")

        binding.templateData= headerAdapter
        title_template.addView(binding.root)
    }


    private var mHorizontalGridView: HorizontalGridView?=null
    var id:Long?=null

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(layoutResource, this)
        mHorizontalGridView = findViewById(R.id.grid_view)
        mHorizontalGridView!!.setHasFixedSize(false)
        title_template.isFocusableInTouchMode=false
        title_template.isFocusable=false
//        grid_view.setOnKeyListener { v, keyCode, event ->
//            if (event.action == KeyEvent.ACTION_DOWN) {
//                if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
//                    mHorizontalGridView!!.selectedPosition = 0
//                    title_template.visibility = View.VISIBLE
//                }
//                if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN || keyCode == KeyEvent.KEYCODE_DPAD_UP) {
//                    title_template.visibility = View.GONE
//                }
//            }
//            return@setOnKeyListener false
//        }



        //focus_management between header_ll
        header_container.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                    title_template.visibility = View.VISIBLE
                    title_template.clearFocus()

                    mHorizontalGridView!!.selectedPosition = 0
//                    mHorizontalGridView!!.getChildAt(0).requestFocus()

                }
                if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN || keyCode == KeyEvent.KEYCODE_DPAD_UP) {
                    title_template.visibility = View.GONE
                }
            }
            return@setOnKeyListener false
        }

        //focus_management of header_ll
        header_container.setOnFocusChangeListener { v, hasFocus ->
            val animSet: AnimatorSet?
            val scaleX: ObjectAnimator?
            val scaleY: ObjectAnimator?
            if(hasFocus){

                title_template.visibility = View.GONE
                scaleX = ObjectAnimator.ofFloat(header_container, View.SCALE_X, 1.2f)
                scaleY = ObjectAnimator.ofFloat(header_container, View.SCALE_Y, 1.2f)
                header_container.pivotY = header_container.measuredHeight.toFloat()/2
                grid_view.clearFocus()
                animSet = AnimatorSet()
            }
            else{

                scaleX = ObjectAnimator.ofFloat(header_container, View.SCALE_X, 1.0f)
                scaleY = ObjectAnimator.ofFloat(header_container, View.SCALE_Y, 1.0f)
                header_container.pivotY = 0.0f
                animSet = AnimatorSet()
            }
            animSet.setDuration(300).interpolator = AccelerateDecelerateInterpolator()
            animSet.playTogether(scaleX, scaleY)
            animSet.start()
        }
        header_container.requestFocus()
    }
}