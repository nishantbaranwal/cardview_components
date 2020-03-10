package com.example.myapplication.presenter

import android.view.ViewGroup
import androidx.leanback.widget.*
import com.example.myapplication.view.CustomListRowView
import com.example.myapplication.R
import com.example.myapplication.model.CustomListRow
import kotlinx.android.synthetic.main.custom_lb_list_row.view.*

//custom_list_row_presenter for binding custom_header_item & card i.e;
//data binding and presenting the whole custom_view in custom_list_row_view
class CustomListRowPresenter : ListRowPresenter {
    constructor(ZOOM_EFFECT: Int):super(ZOOM_EFFECT)
    constructor():super()

//    var mBrowseRowsFadingEdgeLength: Int = -1
    var customListRowView: CustomListRowView?=null

    override fun createRowViewHolder(parent: ViewGroup?): RowPresenter.ViewHolder {
        customListRowView = CustomListRowView(
            parent!!.getContext(),
            R.layout.custom_lb_list_row,
            null
        )
        val rowView = customListRowView
//dynamically setting height of each row
//        setupFadingEffect(rowView!!)
//        if (rowHeight != 0) {
//            rowView.grid_view.setRowHeight(rowHeight)
//        }
        return ViewHolder(rowView, rowView!!.grid_view, this)
    }

    //overriding to remove select_effect
    override fun isUsingDefaultListSelectEffect(): Boolean {
        return false
    }

    //overriding to remove shadow_effect
    override fun isUsingDefaultShadow(): Boolean {
        return false
    }

    //override to bind data of custom_list_row inside custom_list_row_view
    override fun onBindRowViewHolder(holder: RowPresenter.ViewHolder, item: Any) {
        val vh = holder as ViewHolder
        val rowItem:Any ?
        //if item will be of type ListRow it will go default onBindViewHolder class
        if(item is ListRow) {
            super.onBindRowViewHolder(holder, item)
        }
        else {
            rowItem = item as CustomListRow
            vh.bridgeAdapter?.setAdapter(rowItem.adapter)
            customListRowView!!.grid_view.adapter = vh.bridgeAdapter
            customListRowView!!.grid_view.contentDescription = ""
            customListRowView!!.setImageView(rowItem.headerItem!!.icon)
            customListRowView!!.id = rowItem.headerItem!!.id
            customListRowView!!.header_title.text = rowItem.headerItem!!.headerTitle
        }
    }

//    private fun setupFadingEffect(rowView: CustomListRowView) {
//        // content is completely faded at 1/2 padding of left, fading length is 1/2 of padding.
//        val gridView = rowView.grid_view
//        if (mBrowseRowsFadingEdgeLength < 0) {
//            val ta = gridView.context
//                .obtainStyledAttributes(R.styleable.LeanbackTheme)
//            mBrowseRowsFadingEdgeLength = ta.getDimension(
//                R.styleable.LeanbackTheme_browseRowsFadingEdgeLength, 0f
//            ).toInt()
//            ta.recycle()
//        }
//        gridView.fadingLeftEdgeLength = mBrowseRowsFadingEdgeLength
//    }
}