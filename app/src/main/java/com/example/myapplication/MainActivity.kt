package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        //render_card_view_initialisation
        render_card_view.setIcon(android.R.drawable.presence_audio_away)
        render_card_view.setTitle("Hello World!")
        render_card_view.selectedColor = R.color.selected
        render_card_view.focusedColor = R.color.focused
        render_card_view.unFocusedColor = R.color.unfocused


        render_card_view2.setMainContainerDimensions(400,300)
        render_card_view2.setTitle("Hulk")


    }
}
