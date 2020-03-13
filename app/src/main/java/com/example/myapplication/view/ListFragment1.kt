package com.example.myapplication.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import androidx.leanback.widget.FocusHighlight.ZOOM_FACTOR_NONE
import com.example.myapplication.R
import com.example.myapplication.model.CustomHeaderItem
import com.example.myapplication.model.CustomListRow
import com.example.myapplication.model.CustomListRowDataClass
import com.example.myapplication.model.ImageModel
import com.example.myapplication.presenter.CardPresenter
import com.example.myapplication.presenter.CustomHeaderPresenter
import com.example.myapplication.presenter.CustomListRowPresenter
import kotlinx.android.synthetic.main.custom_lb_list_row.view.*
import java.util.*

class ListFragment1 : RowsSupportFragment() {

    //custom_list_row_data_class is the model class description_template
    //& both variables are container of rowview card's template
    // i.e. contains all data of rowview card's template
    // e.g. line no.51
    private var customListRowDataClassListHulk = arrayListOf<CustomListRowDataClass>()
    private var customListRowDataClassListSuperman = arrayListOf<CustomListRowDataClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRows()
    }

    //setting up each row
    @SuppressLint("SetTextI18n")
    private fun setupRows() {

        //custom_list_row_presenter for binding custom_header_item & card i.e;
        //data binding and presenting the whole custom_view in custom_list_row_view


        val customListRowPresenter = CustomListRowPresenter(ZOOM_FACTOR_NONE)
        val mRowsAdapter = ArrayObjectAdapter(customListRowPresenter)

        //customisation of how each card should be presentated with custom_card_view
        val cardPresenter = CardPresenter()

        // arraylist list of object adapters for coupling each card's presenter
        var listRowAdapter = ArrayObjectAdapter(cardPresenter)

        //card model data insertion for row 1
        for (i in 0 until 5) {
            val imageModel= ImageModel(R.drawable.hulk, "Hulk $i")
            customListRowDataClassListHulk.add(

                CustomListRowDataClass(
                    imageModel.text,
                    imageModel.text + " is a movie to watch",
                    Random().nextInt(4) + 1
                )
            )
            listRowAdapter.add(imageModel)
        }

        //custom_header_item for custom_header_insertion_template
        val customHeaderPresenter = CustomHeaderPresenter()
        var headerAdapter = ArrayObjectAdapter(customHeaderPresenter)
        headerAdapter.add( CustomHeaderItem(0, R.drawable.hulk))

        //custom_list_row for binding custom_header_item and list_row_adapter
        val listRow1 = CustomListRow(headerAdapter, listRowAdapter,R.layout.custom_header_view,
            customListRowDataClassListHulk,R.layout.custom_template)

        //card model data insertion for row 2
        listRowAdapter= ArrayObjectAdapter(cardPresenter)
        for (i in 0 until 5) {

            val imageModel= ImageModel(R.drawable.superman, "Superman $i")
            customListRowDataClassListSuperman.add(
                CustomListRowDataClass(imageModel.text,
                    imageModel.text + " is a movie to watch"))
            listRowAdapter.add(imageModel)

        }
        headerAdapter = ArrayObjectAdapter(customHeaderPresenter)
        headerAdapter.add(CustomHeaderItem(1, R.drawable.superman, "Superman"))
        val listRow2 = CustomListRow(
            headerAdapter,
            listRowAdapter,
            R.layout.custom_header_view,
            customListRowDataClassListSuperman,
            R.layout.custom_template
        )
        mRowsAdapter.add(listRow1)
        mRowsAdapter.add(listRow2)

        // custom_rows_adapter for data showcase from RowsSupportFragment
        adapter = mRowsAdapter

        //list_row_view's item_selection_listener
        setOnItemViewSelectedListener { itemViewHolder, item , rowViewHolder, _ ->

            if(itemViewHolder!=null && item is ImageModel) {
                //custom_list_row_view is an holder or parent view for all views
                // i.e. header_template, horizontal_grid_view and template inside one row
                val listRowView =((rowViewHolder.view) as CustomListRowView)

                showTemplate(listRowView)


                //ImageCardView on_key_event_listener
                itemViewHolder.view.setOnKeyListener { _, keyCode, event ->
                    if(event.action ==  KeyEvent.ACTION_DOWN){
                        when(keyCode) {
                            KeyEvent.KEYCODE_DPAD_LEFT -> {
                                if (listRowView.grid_view.selectedPosition==0) {
                                    hideTemplate(listRowView)
                                    listRowView.header_container.requestFocus()
                                }
                            }
                            KeyEvent.KEYCODE_DPAD_UP,KeyEvent.KEYCODE_DPAD_DOWN->{
                                listRowView.grid_view.selectedPosition=0
                                hideTemplate(listRowView)
                            }
                        }
                    }
                    return@setOnKeyListener false
                }



                if(rowViewHolder!=null) {
                    //template_insertion
                    if (selectedPosition == 0) {
                        listRowView.expandTemplate(listRow1.customListRowDataClassList.get(listRowView.grid_view.selectedPosition),listRow1.customTemplateLayoutRes)

                        listRowView.expandTemplate(listRow1.customListRowDataClassList.get(listRowView.grid_view.selectedPosition),listRow1.customTemplateLayoutRes)
//                        listRowView.titleName.text = this.customListRowDataClassListHulk[listRowView.grid_view.selectedPosition].titleName
//
//                        listRowView.rating.text=
//                            """  ${this.customListRowDataClassListHulk[listRowView.grid_view.selectedPosition].rating}✰"""
//
//                        listRowView.title_desc.text=
//                            customListRowDataClassListHulk[listRowView.grid_view.selectedPosition].titleDesc

                    } else {
                        listRowView.expandTemplate(listRow2.customListRowDataClassList.get(listRowView.grid_view.selectedPosition),listRow2.customTemplateLayoutRes)

//                        listRowView.titleName.text=
//                            customListRowDataClassListSuperman[listRowView.grid_view.selectedPosition].titleName
//                        listRowView.title_desc.text =
//                            customListRowDataClassListSuperman[listRowView.grid_view.selectedPosition].titleDesc
                    }
                }
            }
        }

    }

    private fun showTemplate(listRowView: CustomListRowView) {
        listRowView.title_template.visibility = VISIBLE
    }

    private fun hideTemplate(listRowView: CustomListRowView) {
        listRowView.title_template.visibility = GONE
    }
}
