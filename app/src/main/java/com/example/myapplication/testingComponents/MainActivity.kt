package com.example.myapplication.testingComponents

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.leanback.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import com.example.myapplication.R
import com.example.myapplication.databinding.CustomTemplateBinding
import com.example.myapplication.model.CustomHeaderItem
import com.example.myapplication.model.CustomListRow
import com.example.myapplication.model.CustomListRowDataClass
import com.example.myapplication.model.ImageModel
import com.example.myapplication.presenter.CardPresenter
import com.example.myapplication.presenter.CustomHeaderPresenter
import com.example.myapplication.presenter.CustomListRowPresenter
import kotlinx.android.synthetic.main.custom_lb_list_row.view.*
import java.util.*


class MainActivity : AppCompatActivity() {

//    private var animSet: AnimatorSet?=null
//    private var scaleX: ObjectAnimator?=null
//    private var scaleY: ObjectAnimator?=null
//    private var binding: ActivityMainBinding? = null
    private var customListRowDataClassListHulk = arrayListOf<CustomListRowDataClass>()
    private var customListRowDataClassListSuperman = arrayListOf<CustomListRowDataClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val customListRowPresenter = CustomListRowPresenter(FocusHighlight.ZOOM_FACTOR_NONE)
        val mRowsAdapter = ArrayObjectAdapter(customListRowPresenter)

        //customisation of how each card should be presentated with custom_card_view
        val cardPresenter = CardPresenter()

        // arraylist list of object adapters for coupling each card's presenter
        var listRowAdapter = ArrayObjectAdapter(cardPresenter)

        //card model data insertion for row 1
        for (i in 0 until 5) {
            val imageModel= ImageModel(R.drawable.hulk, "Hulk $i")
            customListRowDataClassListHulk.add(

                CustomListRowDataClass(
                    imageModel.text,
                    imageModel.text + " is a movie to watch",
                    Random().nextInt(4) + 1
                )
            )
            listRowAdapter.add(imageModel)
        }

        //custom_header_item for custom_header_insertion_template
        val customHeaderPresenter = CustomHeaderPresenter()
        var headerAdapter = ArrayObjectAdapter(customHeaderPresenter)
        headerAdapter.add( CustomHeaderItem(0, R.drawable.hulk))
        //custom_list_row for binding custom_header_item and list_row_adapter
        val listRow1 = CustomListRow(
            headerAdapter,
            listRowAdapter,
            R.layout.custom_header_view,
            customListRowDataClassListHulk,
            R.layout.custom_template
        )

        //card model data insertion for row 2
        listRowAdapter= ArrayObjectAdapter(cardPresenter)
        for (i in 0 until 5) {

            val imageModel= ImageModel(R.drawable.superman, "Superman $i")
            customListRowDataClassListSuperman.add(
                CustomListRowDataClass(imageModel.text,
                    imageModel.text + " is a movie to watch")
            )
            listRowAdapter.add(imageModel)

        }
        headerAdapter = ArrayObjectAdapter(customHeaderPresenter)
        headerAdapter.add(CustomHeaderItem(1, R.drawable.superman, "Superman"))
        val listRow2 = CustomListRow(
            headerAdapter,
            listRowAdapter,
            R.layout.custom_header_view,
            customListRowDataClassListHulk,
            R.layout.custom_template
        )
        mRowsAdapter.add(listRow1)
        mRowsAdapter.add(listRow2)
        val itemBridgeAdapter=ItemBridgeAdapter()

        itemBridgeAdapter.setAdapter(mRowsAdapter)
        container_list.adapter = (itemBridgeAdapter)
        container_list.setOnChildSelectedListener { parent, view, position, id ->
            Log.d("MainActivityClass", "view class is ${view.title_template::class.java}" +
                    " , parentClass is ${parent::class.java} , position is ${position}")
            val positionGridView = view.grid_view.selectedPosition

                if (position == 0) {
                    expandTemplate(
                        customListRowDataClassListHulk.get(view.grid_view.selectedPosition),
                        R.layout.custom_template,
                        view.title_template
                    )
                }
                else
                    if (position == 1)
                        expandTemplate(
                            customListRowDataClassListSuperman.get(positionGridView),
                            R.layout.custom_template,
                            view.title_template
                        )

        }
    }

    fun expandTemplate(
        headerAdapter: CustomListRowDataClass,
        layoutRes: Int, title_template: FrameLayout
    ) {
        title_template.removeAllViews()

        val binding: CustomTemplateBinding = DataBindingUtil.inflate(layoutInflater,
            layoutRes,title_template,false)
        binding.templateData= CustomListRowDataClass("","")

        binding.templateData= headerAdapter
        title_template.addView(binding.root)
    }

}
