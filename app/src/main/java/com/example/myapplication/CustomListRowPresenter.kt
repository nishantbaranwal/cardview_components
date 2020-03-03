package com.example.myapplication

import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.leanback.widget.*

class CustomListRowPresenter: ListRowPresenter() {

    var mBrowseRowsFadingEdgeLength: Int = -1
    var customListRowView:CustomListRowView?=null
    override fun createRowViewHolder(parent: ViewGroup?): RowPresenter.ViewHolder {
        customListRowView =CustomListRowView(parent!!.getContext(),null)
        val rowView = customListRowView
        setupFadingEffect(rowView!!)
        if (rowHeight != 0) {
            rowView.getGridView().setRowHeight(rowHeight)
        }
        return ViewHolder(rowView, rowView.getGridView(), this)
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
            customListRowView!!.getGridView().adapter = vh.bridgeAdapter
            customListRowView!!.getGridView().contentDescription = ""
            customListRowView!!.setImageView(rowItem.headerItem!!.icon)

//            customListRowView!!.getGridView().setTitle("safsaf")
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
//        customListRowView!!.getGridView().adapter = vh!!.bridgeAdapter
//        customListRowView!!.getGridView().contentDescription = rowItem.contentDescription
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
        val gridView = rowView.getGridView()
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
    fun setListener(s:String){
        customListRowView!!.setTextView(s)
    }



}