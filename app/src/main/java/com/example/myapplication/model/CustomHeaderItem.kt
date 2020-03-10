package com.example.myapplication.model


//custom_header_item is a model class for custom_header
data class CustomHeaderItem(val id: Long, val icon: Int,val headerTitle:String) {
   constructor(id: Long, icon: Int):this(id, icon, "")
}