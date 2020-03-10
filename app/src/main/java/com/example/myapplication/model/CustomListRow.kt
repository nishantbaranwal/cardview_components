package com.example.myapplication.model

import androidx.leanback.widget.ObjectAdapter

//custom_list_row for binding custom_header_item and list_row_adapter
data class CustomListRow(val header: CustomHeaderItem,val adapter: ObjectAdapter) : CustomRow(header)