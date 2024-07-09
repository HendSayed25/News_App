package com.example.homeactivity.Categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.homeactivity.R
import com.example.homeactivity.databinding.ItemCategoryBinding
import com.example.homeactivity.databinding.LeftSideCategoryBinding
class CategoryAdapter(val category:List<Category?>?): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

   class ViewHolder(val itemBinding:ItemCategoryBinding):RecyclerView.ViewHolder(itemBinding.root){
       fun bind(item:Category?){
            itemBinding.itemCategory=item
            itemBinding.invalidateAll() //ti check all items is bind
       }

       lateinit var  side_category:LeftSideCategoryBinding
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var viewBind:ItemCategoryBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            if(viewType==LEFT_SIDE)R.layout.left_side_category
            else R.layout.right_side_category,
            parent,false)

        return ViewHolder(viewBind)
    }

    val LEFT_SIDE=10
    val RIGHT_SIDE=20
    override fun getItemViewType(position: Int): Int {//this function return the position of view i click to it
        return if(position%2==0)LEFT_SIDE else RIGHT_SIDE

    }

    override fun getItemCount(): Int {
        return category!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         var Cat=category?.get(position)

        holder.side_category.materialCard.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context,Cat!!.backgroundColor))

         holder.bind(Cat!!)

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