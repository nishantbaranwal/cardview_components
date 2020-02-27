package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.FragmentActivity

class ListFragmentActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_fragment)
        val listFragment1 = ListFragment1()
        supportFragmentManager.beginTransaction()
            .replace(R.id.list_frame_layout, listFragment1, "sfdjsdkf").commit()
    }

}
