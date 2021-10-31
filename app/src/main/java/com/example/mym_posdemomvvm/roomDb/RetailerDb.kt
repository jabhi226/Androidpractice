package com.example.mym_posdemomvvm.roomDb

import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mym_posdemomvvm.daos.ManufactureDao
import com.example.mym_posdemomvvm.daos.MedicineDao
import com.example.mym_posdemomvvm.models.Manufacture
import com.example.mym_posdemomvvm.models.Medicine
import com.example.mym_posdemomvvm.utils.Constants
import java.io.*
import java.lang.Exception

@Database(entities = [Medicine::class, Manufacture::class], version = Constants.ROOM_DB_VERSION)
abstract class RetailerDb: RoomDatabase() {

    companion object{
        private var instance: RetailerDb? = null

        fun getInstance(c: Context): RetailerDb{
            if (instance == null){
                instance = Room.databaseBuilder(c.applicationContext, RetailerDb::class.java, Constants.ROOM_DB_NAME)
//                    .createFromFile(File(c.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "freshDb.sqlite"))
//                    .createFromAsset("freshDb.sqlite")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(roomCallback)
                    .build()
            }
            return instance!!
        }

        private val roomCallback: Callback = object: Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Thread{
                    val medDao = instance?.medicineDao
//                    medDao?.insert(Medicine("Dolo 650",false,15,manufactureId = 0))
//                    medDao?.insert(Medicine("Crocin 650",false,15,manufactureId = 0))
//                    medDao?.insert(Medicine("Calpol 100",false,10,manufactureId = 0))
//                    medDao?.insert(Medicine("Zifi 650",true,10,manufactureId = 0))
                }.start()
            }
        }
    }

    abstract val medicineDao: MedicineDao
    abstract val manufactureDao: ManufactureDao
}