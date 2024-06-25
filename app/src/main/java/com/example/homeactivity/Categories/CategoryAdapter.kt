package com.example.homeactivity.Categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.homeactivity.R
import com.google.android.material.card.MaterialCardView

class CategoryAdapter(val category:List<Category>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

   class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
       var title:TextView=itemView.findViewById(R.id.text_card)
       var Image:ImageView=itemView.findViewById(R.id.image_card)
       var materialCard:MaterialCardView=itemView.findViewById(R.id.material_card)

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(
                if(viewType==LEFT_SIDE)R.layout.left_side_category
                else R.layout.right_side_category, parent, false)

        return ViewHolder(view)
    }

    val LEFT_SIDE=10
    val RIGHT_SIDE=20
    override fun getItemViewType(position: Int): Int {//this function return the position of view i click to it
        return if(position%2==0)LEFT_SIDE else RIGHT_SIDE

    }

    override fun getItemCount(): Int {
        return category.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         var Cat=category[position]
         holder.title.setText(Cat.titleId)
         holder.Image.setImageResource(Cat.imageId)
         holder.materialCard.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,Cat.backgroundColor))

         if(onItemClickListener!=null){
             holder.itemView.setOnClickListener{
                 onItemClickListener?.onItemClick(position,Cat)
             }
         }
    }

    var onItemClickListener: onItemClickListner?=null

    interface onItemClickListner{
        fun onItemClick(pos:Int,item: Category)
    }
}