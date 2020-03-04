package com.example.myapplication.model

data class CustomHeaderItem(val id: Long, val icon: Int,val headerTitle:String) {
   constructor(id: Long, icon: Int):this(id, icon, "")
}