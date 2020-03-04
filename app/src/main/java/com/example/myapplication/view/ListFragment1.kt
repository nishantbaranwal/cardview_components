package com.example.myapplication.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.KeyEvent
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import com.example.myapplication.R
import com.example.myapplication.model.CustomHeaderItem
import com.example.myapplication.model.CustomListRow
import com.example.myapplication.model.CustomListRowDataClass
import com.example.myapplication.model.ImageModel
import com.example.myapplication.presenter.CardPresenter
import com.example.myapplication.presenter.CustomListRowPresenter
import kotlinx.android.synthetic.main.custom_lb_list_row.view.*
import java.util.*


class ListFragment1 : RowsSupportFragment() {

    private var mBackgroundManager: BackgroundManager? = null
    var mMetrics: DisplayMetrics? = null

    var customListRowDataClassListHulk = arrayListOf<CustomListRowDataClass>()
    var customListRowDataClassListSuperman = arrayListOf<CustomListRowDataClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prepareBackgroundManager()
        setupRows()
    }

    @SuppressLint("")
    private fun setupRows() {

        val customListRowPresenter = CustomListRowPresenter()
        val mRowsAdapter = ArrayObjectAdapter(customListRowPresenter)
        val cardPresenter =
            CardPresenter(customListRowPresenter)
        var listRowAdapter = ArrayObjectAdapter(cardPresenter)

        for (i in 0 until 5) {
            val imageModel= ImageModel()
            imageModel.icon= R.drawable.hulk
            imageModel.text="Hulk "+i
            customListRowDataClassListHulk.add(
                CustomListRowDataClass(
                    imageModel.text,
                    imageModel.text + " is a movie to watch",
                    Random().nextInt(4) + 1
                )
            )
            listRowAdapter.add(imageModel)
        }

        val header1 = CustomHeaderItem(0, R.drawable.hulk)
        val listRow1 = CustomListRow(header1, listRowAdapter)

        listRowAdapter= ArrayObjectAdapter(cardPresenter)
        for (i in 0 until 5) {
            val imageModel= ImageModel()
            imageModel.icon= R.drawable.superman
            imageModel.text="Superman "+i
            customListRowDataClassListSuperman.add(
                CustomListRowDataClass(
                    imageModel.text,
                    imageModel.text + " is a movie to watch"
                )
            )
            listRowAdapter.add(imageModel)
        }

        val header2 =
            CustomHeaderItem(1, R.drawable.superman, "Superman")
        val listRow2 = CustomListRow(header2, listRowAdapter)
        mRowsAdapter.add(listRow1)
        mRowsAdapter.add(listRow2)

        adapter =mRowsAdapter
        setOnItemViewSelectedListener { itemViewHolder, item, rowViewHolder, row ->
            if(itemViewHolder!=null && item is ImageModel) {
                Log.d("dfsf",selectedPosition.toString())
                val listRowView =((rowViewHolder.view) as CustomListRowView)

                listRowView.titleName
                    .visibility = VISIBLE
                listRowView.title_desc.visibility = VISIBLE
                listRowView.rating.visibility = VISIBLE
                itemViewHolder.view.setOnKeyListener { v, keyCode, event ->
                    if(event.action ==  KeyEvent.ACTION_DOWN){
                        when(keyCode) {
                            KeyEvent.KEYCODE_DPAD_LEFT -> {
                                if (listRowView.grid_view.selectedPosition==0) {
                                    listRowView.header_ll.setFocusable(true)
                                    listRowView.header_ll.isFocusableInTouchMode = true
                                    listRowView.header_ll
                                        .requestFocus()
                                    listRowView.titleName
                                            .visibility = GONE
                                    listRowView.title_desc.visibility = GONE
                                    listRowView.rating.visibility = GONE
                                }
                            }
                            KeyEvent.KEYCODE_DPAD_UP->{
//                                listRowView.header_ll.setFocusable(false)
//                                listRowView.header_ll.isFocusableInTouchMode= false
                                listRowView.titleName
                                    .visibility = GONE
                                listRowView.title_desc.visibility = GONE
                                listRowView.rating.visibility = GONE
                            }
                            KeyEvent.KEYCODE_DPAD_DOWN->{
//                                listRowView.header_ll.setFocusable(false)
//                                listRowView.header_ll.isFocusableInTouchMode= false
                                listRowView.titleName
                                    .visibility = GONE
                                listRowView.title_desc.visibility = GONE
                                listRowView.rating.visibility = GONE
                            }
                        }
                    }
                    return@setOnKeyListener false
                }
                if(rowViewHolder!=null) {
                    if (selectedPosition == 0) {
                        listRowView.titleName.setText(customListRowDataClassListHulk
                            .get(listRowView.grid_view.selectedPosition).titleName)
                        listRowView.rating.setText("  "+customListRowDataClassListHulk
                            .get(listRowView.grid_view.selectedPosition).rating.toString()+"✰")
                        listRowView.title_desc.setText(customListRowDataClassListHulk
                            .get(listRowView.grid_view.selectedPosition).titleDesc)

                    } else {
                        listRowView.titleName.setText(customListRowDataClassListSuperman
                            .get(listRowView.grid_view.selectedPosition).titleName)
                        listRowView.title_desc.setText(customListRowDataClassListSuperman
                            .get(listRowView.grid_view.selectedPosition).titleDesc)
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
