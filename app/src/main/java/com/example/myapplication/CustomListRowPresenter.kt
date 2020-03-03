package com.example.myapplication

import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.leanback.widget.*
import kotlinx.android.synthetic.main.custom_lb_list_row.view.*

class CustomListRowPresenter: ListRowPresenter() {

    var mBrowseRowsFadingEdgeLength: Int = -1
    var customListRowView:CustomListRowView?=null

    override fun createRowViewHolder(parent: ViewGroup?): RowPresenter.ViewHolder {
        customListRowView =CustomListRowView(parent!!.getContext(),R.layout.custom_lb_list_row,null)
        val rowView = customListRowView
        setupFadingEffect(rowView!!)
        if (rowHeight != 0) {
            rowView.grid_view.setRowHeight(rowHeight)
        }
        return ViewHolder(rowView, rowView.grid_view, this)
    }

    override fun onBindRowViewHolder(holder: RowPresenter.ViewHolder, item: Any) {
        val vh = holder as ListRowPresenter.ViewHolder
        val rowItem:Any ?
        if(item is ListRow) {
            super.onBindRowViewHolder(holder, item)
        }
        else {
            rowItem = item as CustomListRow
            vh.bridgeAdapter?.setAdapter(rowItem.mAdapter)
            customListRowView!!.grid_view.adapter = vh.bridgeAdapter
            customListRowView!!.grid_view.contentDescription = ""
            customListRowView!!.setImageView(rowItem.headerItem!!.icon)


            customListRowView!!.header_title.text=rowItem.headerItem!!.headerTitle

//            customListRowView!!.grid_view.setTitle("safsaf")
//            customListRowView!!.setTextView(rowItem.)
//            val headerName:String=rowItem.headerItem.name
//            if(headerName.equals("Superman")) {
//                customListRowView!!.setImageView(R.drawable.superman)
//                customListRowView!!.setTextView("SuperMan")
//
//            }
//            else {
//                customListRowView!!.setImageView(R.drawable.hulk)
//                customListRowView!!.setTextView("Hulk")
//
//            }

        }
//        rowItem.adapter
//        vh!!.bridgeAdapter?.setAdapter(rowItem.adapter)
//        customListRowView!!.grid_view.adapter = vh!!.bridgeAdapter
//        customListRowView!!.grid_view.contentDescription = rowItem.contentDescription
//        val headerName:String=rowItem.headerItem.name
//        if(headerName.equals("Superman")) {
//            customListRowView!!.setImageView(R.drawable.superman)
//            customListRowView!!.setTextView("SuperMan")
//
//        }
//        else {
//            customListRowView!!.setImageView(R.drawable.hulk)
//            customListRowView!!.setTextView("Hulk")
//
//        }

    }






    private fun setupFadingEffect(rowView: CustomListRowView) {
        // content is completely faded at 1/2 padding of left, fading length is 1/2 of padding.
        val gridView = rowView.grid_view
        if (mBrowseRowsFadingEdgeLength < 0) {
            val ta = gridView.context
                .obtainStyledAttributes(R.styleable.LeanbackTheme)
            mBrowseRowsFadingEdgeLength = ta.getDimension(
                R.styleable.LeanbackTheme_browseRowsFadingEdgeLength, 0f
            ).toInt()
            ta.recycle()
        }
        gridView.fadingLeftEdgeLength = mBrowseRowsFadingEdgeLength
    }

}