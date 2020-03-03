package com.example.myapplication

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import androidx.leanback.widget.ClassPresenterSelector
import kotlinx.android.synthetic.main.custom_lb_list_row.view.*


class ListFragment1 : RowsSupportFragment() {

    private var mBackgroundManager: BackgroundManager? = null
    var mMetrics: DisplayMetrics? = null
    var desc1 = arrayListOf<String>()
    var desc2 = arrayListOf<String>()
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
            desc1.add(imageModel.text+" is a movie to watch")
            listRowAdapter.add(imageModel)
        }

        val header1 = CustomHeaderItem(0, R.drawable.hulk,"Hulk")
        val listRow1 = CustomListRow(header1, listRowAdapter)

        listRowAdapter= ArrayObjectAdapter(cardPresenter)
        for (i in 0 until 5) {
            val imageModel=ImageModel()
            imageModel.icon=R.drawable.superman
            imageModel.text="Superman "+i
            desc2.add(imageModel.text+" is a movie to watch")
            listRowAdapter.add(imageModel)
        }

        val header2 = CustomHeaderItem(1, R.drawable.superman,"Hulk")
        val listRow2 = CustomListRow(header2, listRowAdapter)
        mRowsAdapter.add(listRow1)
        mRowsAdapter.add(listRow2)

        adapter =mRowsAdapter
        setOnItemViewSelectedListener { itemViewHolder, item, rowViewHolder, row ->
            if(itemViewHolder!=null && item is ImageModel) {

                val listRowView =((rowViewHolder.view) as CustomListRowView)
                itemViewHolder.view.setOnKeyListener { v, keyCode, event ->
                    if(event.action ==  KeyEvent.ACTION_DOWN){
                        when(keyCode) {
                            KeyEvent.KEYCODE_DPAD_LEFT -> {
                                if (listRowView.grid_view.selectedPosition==0) {
                                    listRowView.header
                                        .requestFocus()
                                    listRowView.titleName
                                            .visibility = GONE
                                    listRowView.title_desc.visibility = GONE

                                }
                            }
                        }
                    }
                    return@setOnKeyListener false
                }
                if(rowViewHolder!=null) {
                    if (listRowView.getImage() == R.drawable.hulk) {
                        listRowView.titleName
                                    .visibility = VISIBLE
                        listRowView.title_desc.visibility=VISIBLE
                        listRowView.titleName.setText("Hulk " + listRowView.grid_view.selectedPosition)
                        val desc=desc1.get(listRowView.grid_view.selectedPosition)
                        listRowView.title_desc.setText(desc)

                    } else {
                        listRowView.titleName
                            .visibility = VISIBLE
                        listRowView.titleName
                                    .visibility = VISIBLE
                        listRowView.titleName.setText("Superman " + listRowView.grid_view.selectedPosition)
                        listRowView.title_desc.setText(desc2.get(listRowView.grid_view.selectedPosition))

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
