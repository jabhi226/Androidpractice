package com.example.mym_posdemomvvm.utils

import android.content.Context
import android.widget.Toast
import java.io.IOException
import java.io.InputStream

object Utils {
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


}