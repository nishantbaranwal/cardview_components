package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.ListRowView
import androidx.leanback.widget.Visibility
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        //render_card_view_initialisation
//        render_card_view.setIcon(android.R.drawable.presence_audio_away)
//        render_card_view.setTitle("Hello World!")
//        render_card_view.selectedColor = R.color.selected
//        render_card_view.focusedColor = R.color.focused
//        render_card_view.unFocusedColor = R.color.unfocused
//
//        render_card_view2.setMainContainerDimensions(400,300)
//        render_card_view2.setTitle("Super Man")
//        render_card_view2.setTitleColor(R.color.colorAccent)
////        render_card_view2.setIcon(R.drawable.hulk)
//        render_card_view2.setIcon("https://img.cinemablend.com/filter:scale/quill/8/d/3/c/2/1/8d3c21e54e16aff6ea6eb9385fcf5120ce05b0fb.jpg")
//        customListRowView1.setFocusable(true)
//        customListRowView1.isFocusableInTouchMode = true
        customListRowView1.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus)
                ll_title_info.visibility = VISIBLE
            else
                ll_title_info.visibility = GONE
        }

    }

    fun setTitleName(s: String) {
        titleName.setText(s)

    }
}
