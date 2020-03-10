package com.example.myapplication.model

//model class for template
data class CustomListRowDataClass(val titleName: String
                                  , val titleDesc: String,  val rating: Int
) {
    constructor( titleName: String
                ,  titleDesc: String
                ) : this(titleName,titleDesc, -1 )
}