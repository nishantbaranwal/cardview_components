package com.example.myapplication.model

import androidx.leanback.widget.ObjectAdapter.NO_ID

open class CustomRow(headerItem: CustomHeaderItem) {

    internal var flags = FLAG_ID_USE_HEADER
        private set
    var headerItem: CustomHeaderItem? = headerItem
    private var mId = NO_ID.toLong()

    var id: Long
        get() {
            if (flags and FLAG_ID_USE_MASK == FLAG_ID_USE_HEADER) {
                val header = headerItem
                return header?.id ?: NO_ID.toLong()
            } else {
                return mId
            }
        }
        set(id) {
            mId = id
            setFlags(
                FLAG_ID_USE_ID,
                FLAG_ID_USE_MASK
            )
        }

    open val isRenderedAsCustomRowView: Boolean
        get() = true


    internal fun setFlags(flags: Int, mask: Int) {
        this.flags = this.flags and mask.inv() or (flags and mask)
    }

    companion object {

        private val FLAG_ID_USE_MASK = 1
        private val FLAG_ID_USE_HEADER = 1
        private val FLAG_ID_USE_ID = 0
    }
}
