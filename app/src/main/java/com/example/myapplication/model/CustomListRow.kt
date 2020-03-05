package com.example.myapplication.model

import androidx.leanback.widget.ItemBridgeAdapter
import androidx.leanback.widget.ObjectAdapter

class CustomListRow(header: CustomHeaderItem, adapter: ObjectAdapter) : CustomRow(header) {

    var mAdapter: ObjectAdapter = adapter

}