package com.example.homeactivity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.homeactivity.ui.Home.CategoriesFragment
import com.example.homeactivity.ui.Home.Category
import com.example.homeactivity.ui.Home.SettingFragment

class HomeActivity : AppCompatActivity() {

    lateinit var menuButton:ImageView
    lateinit var drawerLayout:DrawerLayout
    lateinit var category_icon:ImageView
    lateinit var setting_icon:ImageView
    var CategoryFragment= CategoriesFragment()
    var SettingFragment=SettingFragment()
    lateinit var titleOfPage:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        menuButton = findViewById(R.id.menu_button)
        drawerLayout=findViewById(R.id.drawer_layout)
        category_icon=findViewById(R.id.categories_icon)
        setting_icon=findViewById(R.id.setting_icon)
        titleOfPage=findViewById(R.id.textOfTitle)



        menuButton.setOnClickListener {
            drawerLayout.open()

        }

        //as default appear the category Fragment when run the program
        pushFragment(CategoryFragment)
        titleOfPage.setText("News App")


        //push category fragment in frameLayout
        category_icon.setOnClickListener {
            pushFragment(CategoryFragment)
            titleOfPage.setText("News App")
        }

       //push setting fragment in frameLayout
        setting_icon.setOnClickListener {
            pushFragment(SettingFragment)
            titleOfPage.setText("Settings")
        }



     /*   catogriesFragment.onCategoryClick=object: CategoriesFragment.onCategoryClickListner{
            override fun onItemClick(cat: Category) {
             //   pushFragment(NewsFragment(),true)
            }

        }*/


    }

    private fun pushFragment(fragment: Fragment, pushToSatck:Boolean=false){
        //assign this action in variable to determine if push fragment to stack or not then will commit it anywhere
      /*  val transaction=supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment)
        if(pushToSatck){
            transaction.addToBackStack("")
        }
        transaction.commit()
*/


        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()
    }


}