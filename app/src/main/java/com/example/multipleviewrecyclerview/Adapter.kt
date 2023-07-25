package com.example.multipleviewrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(context: Context,arrayList: ArrayList<Data>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }

    private val context: Context = context
    var list: ArrayList<Data> = arrayList

    override fun onCreateViewHolder(viewgroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ONE) {
            return ViewHolderOne(
                LayoutInflater.from(context).inflate(R.layout.layout_one, viewgroup, false)
            )
        }
        return ViewHolderTwo(
            LayoutInflater.from(context).inflate(R.layout.layout_two, viewgroup, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (list[position].viewType == VIEW_TYPE_ONE) {
            (holder as ViewHolderOne).bind(position) }
        else
        { (holder as ViewHolderTwo).bind(position)}
    }

    private inner class ViewHolderOne(itemView: View):RecyclerView.ViewHolder(itemView) {
        var text: TextView = itemView.findViewById(R.id.tv_layoutOne)
        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            text.text = recyclerViewModel.textData

        }
    }

    private inner class ViewHolderTwo(itemView: View):RecyclerView.ViewHolder(itemView) {
       var text: TextView = itemView.findViewById(R.id.tv_layoutTwo)
       var image: ImageView = itemView.findViewById(R.id.ImgView_layoutTwo)
       fun bind(position: Int) {
           val recyclerViewModel = list[position]
           text.text = recyclerViewModel.textData
           image.setImageResource(list[position].image!!)
       }
   }

    override fun getItemViewType(position: Int): Int {
        if (list[position].viewType == VIEW_TYPE_ONE) {
            return VIEW_TYPE_ONE}
        else if(list[position].viewType == VIEW_TYPE_TWO)
            return VIEW_TYPE_TWO
        return super.getItemViewType(position)
    }
}