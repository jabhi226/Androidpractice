package com.example.mym_posdemomvvm.moduls.test.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mym_posdemomvvm.R

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        println("---> SecondFragment onCreateView")
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

}