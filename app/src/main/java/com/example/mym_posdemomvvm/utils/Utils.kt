package com.example.mym_posdemomvvm.utils

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun Bundle?.getStoredFragment(supportFragmentManager: FragmentManager, tag: String): Fragment? {
        if (this == null) return null
        return supportFragmentManager.getFragment(this, tag)
    }

    fun FragmentManager.storeFragment(id: Int, outState: Bundle, tag: String){
        this.findFragmentById(id)?.let {
            this.putFragment(outState, tag, it)
        }
    }
    fun showToast(context: Context, text: String){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    fun getJsonFromAssets(context: Context, fileName: String?): String? {
        val jsonString: String = try {
            val is1: InputStream = context.assets.open(fileName!!)
            val size: Int = is1.available()
            val buffer = ByteArray(size)
            is1.read(buffer)
            is1.close()
            String(buffer, charset("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }


    const val TIMESTAMP = "yyyy-MM-dd HH:mm:ss"
    fun Date.getFormattedDate(inputFormat: String): String {
        return try {
            val formattedDate =
                SimpleDateFormat(inputFormat, Locale.ENGLISH)
            val convertedDate = formattedDate.format(this)
            convertedDate
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            Date().getFormattedDate(TIMESTAMP)
        }
    }
}