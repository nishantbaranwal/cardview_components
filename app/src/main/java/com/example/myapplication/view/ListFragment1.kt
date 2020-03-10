package com.example.myapplication.view

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
import com.example.myapplication.presenter.CustomListRowPresenter
import kotlinx.android.synthetic.main.custom_lb_list_row.view.*
import java.util.*

class ListFragment1 : RowsSupportFragment() {

    //custom_list_row_data_class is the model class description_template
    //& both variables are container of rowview card's template
    // i.e. contains all data of rowview card's template
    // e.g. line no.51
    var customListRowDataClassListHulk = arrayListOf<CustomListRowDataClass>()
    var customListRowDataClassListSuperman = arrayListOf<CustomListRowDataClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRows()
    }

    //setting up each row
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
            val imageModel= ImageModel(R.drawable.hulk,"Hulk "+i)
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
        val header1 = CustomHeaderItem(0, R.drawable.hulk)
        //custom_list_row for binding custom_header_item and list_row_adapter
        val listRow1 = CustomListRow(header1, listRowAdapter)

        //card model data insertion for row 2
        listRowAdapter= ArrayObjectAdapter(cardPresenter)
        for (i in 0 until 5) {
            val imageModel= ImageModel(R.drawable.superman,"Superman "+i)

            customListRowDataClassListSuperman.add(
                CustomListRowDataClass(imageModel.text,
                    imageModel.text + " is a movie to watch"))

            listRowAdapter.add(imageModel)
        }

        val header2 = CustomHeaderItem(1, R.drawable.superman, "Superman")
        val listRow2 = CustomListRow(header2, listRowAdapter)
        mRowsAdapter.add(listRow1)
        mRowsAdapter.add(listRow2)

        // custom_rows_adapter for data showcase from RowsSupportFragment
        adapter =mRowsAdapter

        //list_row_view's item_selection_listener
        setOnItemViewSelectedListener { itemViewHolder, item, rowViewHolder, row ->

            if(itemViewHolder!=null && item is ImageModel) {
                //custom_list_row_view is an holder or parent view for all views
                // i.e. header_template, horizontal_grid_view and template inside one row
                val listRowView =((rowViewHolder.view) as CustomListRowView)

                showTemplate(listRowView)


                //ImageCardView on_key_event_listener
                itemViewHolder.view.setOnKeyListener { v, keyCode, event ->
                    if(event.action ==  KeyEvent.ACTION_DOWN){
                        when(keyCode) {
                            KeyEvent.KEYCODE_DPAD_LEFT -> {
                                if (listRowView.grid_view.selectedPosition==0) {
                                    hideTemplate(listRowView)
                                    listRowView.header_ll.requestFocus()
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

    private fun showTemplate(listRowView: CustomListRowView) {
        listRowView.titleName
            .visibility = VISIBLE
        listRowView.title_desc.visibility = VISIBLE
        listRowView.rating.visibility = VISIBLE
    }

    private fun hideTemplate(listRowView: CustomListRowView) {
        listRowView.titleName
            .visibility = GONE
        listRowView.title_desc.visibility = GONE
        listRowView.rating.visibility = GONE
    }
}
