package com.example.homeactivity.ui.Home

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.homeactivity.Categories.CategoriesFragment
import com.example.homeactivity.News.NewsFragment
import com.example.homeactivity.R
import com.example.homeactivity.databinding.ActivityMainBinding
import com.example.homeactivity.databinding.AppBarMainBinding
import java.util.Locale

class HomeActivity : AppCompatActivity() {


    var CategoryFragment= CategoriesFragment()
    var SettingFragment=SettingFragment()
    var NewsFragment=NewsFragment()
    lateinit var viewBiniding:ActivityMainBinding
    lateinit var appBarBinding:AppBarMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        appBarBinding.menuButton.setOnClickListener {
            viewBiniding.drawerLayout.open()

        }

        //to apply the last language the user use before close the app
        var LastSelectedLanguage=SharedPreferenceHelper.getLanguage(applicationContext)
        changeLanguage(LastSelectedLanguage)

        //as default appear the category Fragment when run the program
        pushFragment(CategoryFragment)
        if(LastSelectedLanguage=="en") viewBiniding.appBarMain.textOfTitle.setText("News App")
        else viewBiniding.appBarMain.textOfTitle.setText("الاخبار")


        CategoryFragment.onCategoryClick=object:CategoriesFragment.onCategoryClickListner{
            override fun onItemClick(cat: Category) {
                pushFragment(NewsFragment.getInstance(cat),true)
            }
        }

        //push category fragment in frameLayout
        viewBiniding.categoriesIcon.setOnClickListener {
            pushFragment(CategoryFragment)
            if(LastSelectedLanguage=="en") viewBiniding.appBarMain.textOfTitle.setText("News App")
            else viewBiniding.appBarMain.textOfTitle.setText("الاخبار")
        }

       //push setting fragment in frameLayout
        viewBiniding.settingIcon.setOnClickListener {
            pushFragment(SettingFragment)
            if(LastSelectedLanguage=="en")viewBiniding.appBarMain.textOfTitle.setText("Settings")
            else viewBiniding.appBarMain.textOfTitle.setText("الاعدادات")
        }

    }

    private fun pushFragment(fragment: Fragment, pushToSatck:Boolean=false){
        //assign this action in variable to determine if push fragment to stack or not then will commit it anywhere
        val transaction=supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment)
        if(pushToSatck){ //this variable use to when click to back button back to the previous screen
            transaction.addToBackStack("")
        }
        transaction.commit()

       viewBiniding.drawerLayout.close() //when push the fragment the drawable will close

    }

    //to restart the main activity which contains the fragment
    fun restartFragment(){
        val intent=intent
        finish()
        startActivity(intent)
    }

    private fun changeLanguage(language:String){
        val locale = Locale(language)
        val res: Resources =resources
        val config: Configuration =res.configuration
        Locale.setDefault(locale)
        config.setLocale(locale)
        res.updateConfiguration(config,res.displayMetrics)
    }
}