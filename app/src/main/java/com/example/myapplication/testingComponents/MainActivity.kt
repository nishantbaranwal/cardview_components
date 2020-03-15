package com.example.myapplication.testingComponents

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.leanback.app.HeadersSupportFragment
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import com.example.myapplication.R
import com.example.myapplication.databinding.CustomTemplateBinding
import com.example.myapplication.model.CustomHeaderItem
import com.example.myapplication.model.CustomListRow
import com.example.myapplication.model.CustomListRowDataClass
import com.example.myapplication.model.ImageModel
import com.example.myapplication.presenter.CardPresenter
import com.example.myapplication.presenter.CustomHeaderPresenter
import com.example.myapplication.presenter.CustomListRowPresenter
import com.example.myapplication.view.CustomListRowView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_lb_list_row.view.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private var customListRowDataClassListHulk = arrayListOf<CustomListRowDataClass>()
    private var customListRowDataClassListSuperman = arrayListOf<CustomListRowDataClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val customListRowPresenter = CustomListRowPresenter(FocusHighlight.ZOOM_FACTOR_NONE)
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
        val listRow1 = CustomListRow(
            headerAdapter,
            listRowAdapter,
            R.layout.custom_header_view,
            customListRowDataClassListHulk,
            R.layout.custom_template
        )

        //card model data insertion for row 2
        listRowAdapter= ArrayObjectAdapter(cardPresenter)
        for (i in 0 until 5) {

            val imageModel= ImageModel(R.drawable.superman, "Superman $i")
            customListRowDataClassListSuperman.add(
                CustomListRowDataClass(imageModel.text,
                    imageModel.text + " is a movie to watch")
            )
            listRowAdapter.add(imageModel)

        }
        headerAdapter = ArrayObjectAdapter(customHeaderPresenter)
        headerAdapter.add(CustomHeaderItem(1, R.drawable.superman, "Superman"))
        val listRow2 = CustomListRow(
            headerAdapter,
            listRowAdapter,
            R.layout.custom_header_view,
            customListRowDataClassListHulk,
            R.layout.custom_template
        )
        mRowsAdapter.add(listRow1)
        mRowsAdapter.add(listRow2)
        val itemBridgeAdapter=ItemBridgeAdapter()

        itemBridgeAdapter.setAdapter(mRowsAdapter)
        val rowsSupportFragment=RowsSupportFragment()
        val mainFragmentAdapter=rowsSupportFragment.getMainFragmentRowsAdapter()
        mainFragmentAdapter.setAdapter(mRowsAdapter)
        rowsSupportFragment.setOnItemViewSelectedListener { itemViewHolder, item , rowViewHolder, _ ->

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
                            KeyEvent.KEYCODE_DPAD_UP, KeyEvent.KEYCODE_DPAD_DOWN->{
                                Log.d("Current_focus",currentFocus.toString())
                                hideTemplate(listRowView)
                                listRowView.grid_view.selectedPosition = 0
                            }
                        }
                    }
                    return@setOnKeyListener false
                }



                if(rowViewHolder!=null) {
                    //template_insertion
                    if (rowsSupportFragment.selectedPosition == 0) {
                        listRowView.expandTemplate(listRow1.customListRowDataClassList.get(listRowView.grid_view.selectedPosition),listRow1.customTemplateLayoutRes)
                        listRowView.expandTemplate(listRow1.customListRowDataClassList.get(listRowView.grid_view.selectedPosition),listRow1.customTemplateLayoutRes)
                    } else {
                        listRowView.expandTemplate(listRow2.customListRowDataClassList.get(listRowView.grid_view.selectedPosition),listRow2.customTemplateLayoutRes)
                    }
                }
            }
        }


        header_frame_layout.setNumColumns(1)
        val headersAdapter=ArrayObjectAdapter()
        headersAdapter.add(HeaderItem(0, "Superman"))
        headersAdapter.add(HeaderItem(1, "Superman"))
        val itemBridgeAdapter1=ItemBridgeAdapter()
        itemBridgeAdapter1.setAdapter(headersAdapter)
        header_frame_layout.adapter= itemBridgeAdapter1
        val manager: FragmentManager = supportFragmentManager
        val trans: FragmentTransaction = manager.beginTransaction()
        trans.add(R.id.list_frame_layout, rowsSupportFragment, "as")
        trans.commit()

    }

    fun expandTemplate(
        headerAdapter: CustomListRowDataClass,
        layoutRes: Int, title_template: FrameLayout
    ) {
        title_template.removeAllViews()

        val binding: CustomTemplateBinding = DataBindingUtil.inflate(layoutInflater,
            layoutRes,title_template,false)
        binding.templateData= CustomListRowDataClass("","")

        binding.templateData= headerAdapter
        title_template.addView(binding.root)
    }
    private fun showTemplate(listRowView: CustomListRowView) {
        listRowView.title_template.visibility = View.VISIBLE
    }

    private fun hideTemplate(listRowView: CustomListRowView) {
        listRowView.title_template.visibility = View.GONE
    }
}
