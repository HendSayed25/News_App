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
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.homeactivity.R
import com.example.homeactivity.databinding.SettingFragmentBinding
import java.util.Locale

class SettingFragment:Fragment() {

    lateinit var adapter_Spinner:ArrayAdapter<String>
    lateinit var viewBinding:SettingFragmentBinding
    var Languages= arrayOf("English","Arabic")
    var change_Language=true
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding=DataBindingUtil.inflate(inflater,R.layout.setting_fragment,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fillSpinner(viewBinding.spinnerLanguage)

        viewBinding.spinnerLanguage.onItemSelectedListener=object:OnItemSelectedListener{
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