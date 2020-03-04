package com.example.myapplication.model

data class CustomListRowDataClass(val titleName: String
                                  , val titleDesc: String,  val rating: Int
) {
    constructor( titleName: String
                ,  titleDesc: String
                ) : this(titleName,titleDesc, -1 )
}