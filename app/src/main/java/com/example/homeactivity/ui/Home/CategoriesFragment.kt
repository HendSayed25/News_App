package com.example.homeactivity.ui.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.homeactivity.R

class CategoriesFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories,container,false)
    }

    var Category= listOf(
        Category("sport", R.drawable.sport, R.string.sports, R.color.red),
        Category("politics", R.drawable.politics, R.string.politics, R.color.blue),
        Category("health", R.drawable.health, R.string.health, R.color.pink),
        Category("business", R.drawable.bussines, R.string.bussines, R.color.brown_orange),
        Category("environment", R.drawable.environment, R.string.environment, R.color.baby_blue),
        Category("science", R.drawable.science, R.string.science, R.color.yellow)
        )

    lateinit var recyclerView:RecyclerView
     var recycler_adapter=CategoryAdapter(Category)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView=view.findViewById(R.id.recycler)
        recyclerView.adapter=recycler_adapter

        recycler_adapter.onItemClickListener=object: CategoryAdapter.onItemClickListner {
            override fun onItemClick(pos: Int, item: Category) {
                if(onCategoryClick!=null){
                    onCategoryClick?.onItemClick(item)
                }

            }

        }

    }

    var onCategoryClick: onCategoryClickListner?=null
    interface onCategoryClickListner{
      fun onItemClick(cat:Category)
    }
}