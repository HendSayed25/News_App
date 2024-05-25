package com.example.homeactivity.ui.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homeactivity.R

class CategoryAdapter(val category:List<Category>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

   class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
       var title:TextView=itemView.findViewById(R.id.title_of_item)
       var image:ImageView=itemView.findViewById(R.id.image)

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return category.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         var Cat=category[position]
         holder.title.setText(Cat.titleId)
         holder.image.setImageResource(Cat.imageId)
         holder.itemView.setBackgroundColor(Cat.backgroundColor)

         if(onItemClickListener!=null){
             holder.itemView.setOnClickListener{
                 onItemClickListener?.onItemClick(position,Cat)
             }
         }
    }

    var onItemClickListener:onItemClickListner?=null

    interface onItemClickListner{
        fun onItemClick(pos:Int,item:Category)
    }
}