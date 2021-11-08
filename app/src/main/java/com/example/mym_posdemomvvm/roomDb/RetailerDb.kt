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
                    val manufactureDao = instance?.manufactureDao
                    manufactureDao?.insertManufacture(
                        Manufacture(
                        "Cipla private LTD",
                        "admin.cipla.com",
                        "cipla.com",
                        isGlobal = false,
                        isActive = true
                        ))

                    val medicineDao = instance?.medicineDao
                    for (i in 1..300000){
                        medicineDao?.insert(
                            Medicine(
                                "Crocin 650 Advance",
                                false,
                                15,
                                0,
                                1
                            )
                        )
                    }
                }.start()
            }
        }
    }

    abstract val medicineDao: MedicineDao
    abstract val manufactureDao: ManufactureDao
}