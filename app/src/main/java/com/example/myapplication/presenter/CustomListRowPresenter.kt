package com.example.myapplication.presenter

import android.view.ViewGroup
import androidx.leanback.widget.*
import com.example.myapplication.view.CustomListRowView
import com.example.myapplication.R
import com.example.myapplication.model.CustomListRow
import kotlinx.android.synthetic.main.custom_lb_list_row.view.*

class CustomListRowPresenter: ListRowPresenter() {

    var mBrowseRowsFadingEdgeLength: Int = -1
    var customListRowView: CustomListRowView?=null

    override fun createRowViewHolder(parent: ViewGroup?): RowPresenter.ViewHolder {
        customListRowView = CustomListRowView(
            parent!!.getContext(),
            R.layout.custom_lb_list_row,
            null
        )
        val rowView = customListRowView
        setupFadingEffect(rowView!!)
        if (rowHeight != 0) {
            rowView.grid_view.setRowHeight(rowHeight)
        }
        return ViewHolder(rowView, rowView.grid_view, this)
    }

    override fun onBindRowViewHolder(holder: RowPresenter.ViewHolder, item: Any) {
        val vh = holder as ViewHolder
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
            customListRowView!!.id = rowItem.headerItem!!.id
            customListRowView!!.header_title.text = rowItem.headerItem!!.headerTitle
        }
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