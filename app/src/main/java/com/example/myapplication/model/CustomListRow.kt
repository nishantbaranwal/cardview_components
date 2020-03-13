package com.example.myapplication.model

import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.ObjectAdapter

//custom_list_row for binding custom_header_item and list_row_adapter
data class CustomListRow(
    val headerAdapter: ArrayObjectAdapter,
    val adapter: ObjectAdapter,
    val headerLayoutRes: Int,
    val customListRowDataClassList: ArrayList<CustomListRowDataClass>,
    val customTemplateLayoutRes: Int
)