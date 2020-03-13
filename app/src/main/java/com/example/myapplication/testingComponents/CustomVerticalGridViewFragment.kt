package com.example.myapplication.testingComponents

import android.view.View
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.VerticalGridView
import com.example.myapplication.R

class CustomVerticalGridViewFragment :RowsSupportFragment() {
    override fun findGridViewFromRoot(view: View): VerticalGridView? {
        return view.findViewById<View>(R.id.container_list) as VerticalGridView
    }
}