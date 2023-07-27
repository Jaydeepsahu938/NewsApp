package com.example.multipleviewrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adapter(context: Context,arrayList: List<Article>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val context: Context = context
    var list: List<Article> = arrayList

    override fun onCreateViewHolder(viewgroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderTwo(
            LayoutInflater.from(context).inflate(R.layout.layout_two, viewgroup, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderTwo).bind(position)
    }


    private inner class ViewHolderTwo(itemView: View):RecyclerView.ViewHolder(itemView) {
       var text: TextView = itemView.findViewById(R.id.tv_layoutTwo)
       var image: ImageView = itemView.findViewById(R.id.ImgView_layoutTwo)
       var title:TextView=itemView.findViewById(R.id.tv_tittle)
        var url:TextView=itemView.findViewById(R.id.news_url)
       fun bind(position: Int) {
           val recyclerViewModel = list[position]
           text.text = recyclerViewModel.description
           title.text=recyclerViewModel.title
           url.text=recyclerViewModel.url
           if(recyclerViewModel.urlToImage!= null)
               {
                   image.visibility=View.VISIBLE
                   Glide.with(context).load(recyclerViewModel.urlToImage).into(image)
               }
           else{
               image.visibility=View.GONE
             }
       }
   }

}