package com.example.mym_posdemomvvm.moduls.test.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.mym_posdemomvvm.R
import com.example.mym_posdemomvvm.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        println("---> onCreate2 ")
    }

    override fun onStart() {
        super.onStart()
        println("---> onStart2 ")

        CoroutineScope(Dispatchers.Main).launch {
            this@SecondActivity.finish()
            delay(5000)
            Utils.showToast(this@SecondActivity, "sdfsdfsdf")
        }
    }

    override fun onResume() {
        super.onResume()
        println("---> onResume2 ")
    }

    override fun onPause() {
        super.onPause()
        println("---> onPause2 ")
    }

    override fun onStop() {
        super.onStop()
        println("---> onStop2 ")
    }

    override fun onRestart() {
        super.onRestart()
        println("---> onRestart2 ")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("---> onDestroy2 ")
    }

//    override fun onBackPressed() {
//        finishAffinity()
//    }
}