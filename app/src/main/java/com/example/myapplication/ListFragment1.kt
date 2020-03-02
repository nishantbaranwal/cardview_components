package com.example.myapplication

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import androidx.leanback.widget.ClassPresenterSelector



class ListFragment1 : RowsSupportFragment() {

    private var mBackgroundManager: BackgroundManager? = null
    var mMetrics: DisplayMetrics? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prepareBackgroundManager()
        setupRows()
    }

    private fun setupRows() {


        val customListRowPresenter = CustomListRowPresenter()
        val mRowsAdapter = ArrayObjectAdapter(customListRowPresenter)
        val cardPresenter = CardPresenter(customListRowPresenter)
        var listRowAdapter = ArrayObjectAdapter(cardPresenter)

        for (i in 0 until 5) {
            val imageModel=ImageModel()
            imageModel.icon=R.drawable.hulk
            imageModel.text="Hulk "+i
            listRowAdapter.add(imageModel)
        }

        val header1 = CustomHeaderItem(0, R.drawable.hulk)
        val listRow1 = CustomListRow(header1, listRowAdapter)

        listRowAdapter= ArrayObjectAdapter(cardPresenter)
        for (i in 0 until 5) {
            val imageModel=ImageModel()
            imageModel.icon=R.drawable.superman
            imageModel.text="Superman "+i
            listRowAdapter.add(imageModel)
        }


        val header2 = CustomHeaderItem(1, R.drawable.superman)
        val listRow2 = CustomListRow(header2, listRowAdapter)
        mRowsAdapter.add(listRow1)
        mRowsAdapter.add(listRow2)

        adapter =mRowsAdapter
        var i=0
        val s = "Hulk"
        var j=0
        setOnItemViewSelectedListener { itemViewHolder, item, rowViewHolder, row ->

                if(itemViewHolder!=null && item is ImageModel) {
                    val listRowView =((rowViewHolder.view) as CustomListRowView)
                    itemViewHolder.view.setOnKeyListener { v, keyCode, event ->
                        if(event.action ==  KeyEvent.ACTION_DOWN){
                            when(keyCode) {
                                KeyEvent.KEYCODE_DPAD_LEFT -> {
                                    if(listRowView.getImage()==R.drawable.hulk) {
                                        if (i >= 0) i--
                                        if (i==-1) {
                                            listRowView.getImageView()
                                                .requestFocus()
                                            listRowView.getTextView()
                                                .visibility = View.GONE

                                        }
                                    }
                                    else {
                                        if (listRowView.getImage() == R.drawable.superman) {
                                            if (j >= 0) j--
                                            if (j == -1) {
                                                listRowView.getImageView()
                                                    .requestFocus()
//                                                j=0
                                                listRowView.getTextView()
                                                    .visibility = View.GONE
                                            }
                                        }
                                    }
                                    Log.d("position100","i:"+i+", j:"+j)

                                }
                                KeyEvent.KEYCODE_DPAD_RIGHT-> {
                                    if((listRowView.getGridView().getChildAt(0)
                                                as ShadowOverlayContainer).getChildAt(0).hasFocus()
                                        && listRowView.getImage() == R.drawable.hulk) {
                                        i=0
                                    }
                                    else
                                    if((listRowView.getGridView().getChildAt(0)
                                                as ShadowOverlayContainer).getChildAt(0).hasFocus()
                                        && listRowView.getImage() == R.drawable.superman) {
                                        j=0
                                    }
                                    if(listRowView.getImage()==R.drawable.hulk) {
                                        if (i < 4) i++
//                                        if(listRowView.getImageView().hasFocus()){
//                                            listRowView.getGridView().getChildAt(i).requestFocus()
//
//                                        }
                                    }
                                    else
                                        if(listRowView.getImage()==R.drawable.superman){
                                        if (j < 4) j++
                                    }
                                    Log.d("position101","i:"+i+", j:"+j)
                                }
                            }

                        }
                        return@setOnKeyListener false
                    }
                    if(rowViewHolder!=null) {
                        if (listRowView.getImage() == R.drawable.hulk) {
//                            if(listRowView.getImageView().hasFocus()){
//                                i=0
//                            }
                            if(i==-1){
                                listRowView.getTextView().visibility= View.GONE
                            }
                            else {
                                listRowView.getTextView()
                                    .visibility = View.VISIBLE
                                listRowView.setTextView("Hulk" + i)

                            }

                        } else {
//                            if(listRowView.getImageView().hasFocus()){
//                                j=0
//                            }
                            if(j==-1){
                                listRowView.getTextView().visibility= View.GONE
                            }
                            else {
                                listRowView.getTextView()
                                    .visibility = View.VISIBLE
                                listRowView.setTextView("Superman" + j)
                            }
                        }

                    }

                }
        }

    }


    fun prepareBackgroundManager() {
        mBackgroundManager = BackgroundManager.getInstance(activity)
        if (!mBackgroundManager!!.isAttached()) {
            mBackgroundManager!!.attach(activity!!.getWindow())
            mMetrics = DisplayMetrics()
            activity!!.getWindowManager().defaultDisplay.getMetrics(mMetrics)
        }
    }

}
