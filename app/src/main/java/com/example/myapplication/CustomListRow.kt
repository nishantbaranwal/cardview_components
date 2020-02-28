package com.example.myapplication

import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ObjectAdapter
import androidx.leanback.widget.Row

class CustomListRow(header: CustomHeaderItem, adapter: ObjectAdapter) : CustomRow(header) {

    var mAdapter: ObjectAdapter = adapter

}