package com.example.homeactivity.ui.Home

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.homeactivity.R
import java.util.Locale

class SettingFragment:Fragment() {

    lateinit var spinner:Spinner
    lateinit var adapter_Spinner:ArrayAdapter<String>
    var Languages= arrayOf("English","Arabic")
    var change_Language=true
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.setting_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinner=requireActivity().findViewById(R.id.spinner_language)
        fillSpinner(spinner)

        spinner.onItemSelectedListener=object:OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(change_Language==true)change_Language=false
                else{
                    if(position==0)changeLanguage("en")
                    else changeLanguage("ar")

                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

    }

    private fun fillSpinner(spinner:Spinner){

        var LastSelectedLanguage=SharedPreferenceHelper.getLanguage(requireContext())

        if(LastSelectedLanguage=="en"){
            adapter_Spinner=ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayOf("English","Arabic"))
        }
        else{
            adapter_Spinner=ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayOf("Arabic","English"))
        }

        adapter_Spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) //this line make spaces between the items in spinner
        spinner.adapter = adapter_Spinner

    }


    private fun changeLanguage(language:String){
        val locale = Locale(language)
        val res: Resources =resources
        val config: Configuration =res.configuration
        Locale.setDefault(locale)
        config.setLocale(locale)
        res.updateConfiguration(config,res.displayMetrics)

        //save the language
        SharedPreferenceHelper.saveLanguage(requireContext(),language)

        // casting requireActivity of fragment as activity and restart it
        (requireActivity() as HomeActivity).restartFragment()

    }
}