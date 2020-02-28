package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CustomAdapter(ctx: CustomListRowView) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {


    private var context : CustomListRowView = ctx

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_layout,parent,false)
        return MyViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
//        if(resultList1 != null)
//            return resultList1?.size!!
//        else
            return 4
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

            Glide.with(context)
                .load(R.drawable.hulk)
                .into(holder.iv_poster_path)

            holder.tv_original_language.setText("fsafsafsaf"+position)
            holder.tv_original_title.setText("fsafsafsaf"+position)
            holder.tv_release_date.setText("fsafsafsaf"+position)
//        holder.cardView.setOnClickListener{
//            (context as MainActivity).setTitleName("HULK"+position)
////            ToDo
//        }
        holder.linearLayout.setFocusable(true);
        holder.linearLayout.setFocusableInTouchMode(true);

        holder.linearLayout.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) {
                (context).setTextView("HULK"+position+" Lorem ipsum dolor sit amet," +
                        " consectetur adipiscing elit," +
                        " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                        " Ut enim ad minim veniam," +
                        " quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat." +
                        " Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." +
                        " Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
//                Toast.makeText(context,"Focus at position"+position,Toast.LENGTH_SHORT).show()
            }
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var linearLayout:LinearLayout = itemView.findViewById(R.id.ll)
        var cardView = itemView.findViewById<CardView>(R.id.cardView)
        var iv_poster_path : ImageView = itemView.findViewById(R.id.iv_poster_path)
        var tv_original_title : TextView = itemView.findViewById(R.id.original_title)
        var tv_original_language : TextView = itemView.findViewById(R.id.original_language)
        var tv_release_date : TextView = itemView.findViewById(R.id.release_date)

    }





    override fun getItemViewType(position: Int): Int {
        return 4
    }
}
