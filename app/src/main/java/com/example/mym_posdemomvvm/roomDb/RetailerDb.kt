package com.example.mym_posdemomvvm.roomDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mym_posdemomvvm.daos.MedicineDao
import com.example.mym_posdemomvvm.models.Medicine
import com.example.mym_posdemomvvm.utils.Constants

@Database(entities = [Medicine::class], version = Constants.ROOM_DB_VERSION)
abstract class RetailerDb: RoomDatabase() {

    companion object{
        private var instance: RetailerDb? = null

        fun getInstance(c: Context): RetailerDb{
            if (instance == null){
                instance = Room.databaseBuilder(c.applicationContext, RetailerDb::class.java, "Retailer_Database")
                    .fallbackToDestructiveMigration()
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
                    medDao?.insert(Medicine("Dolo 650",false,15))
                    medDao?.insert(Medicine("Crocin 650",false,15))
                    medDao?.insert(Medicine("Calpol 100",false,10))
                    medDao?.insert(Medicine("Zifi 650",true,10))
                }.start()
            }
        }
    }

    abstract val medicineDao: MedicineDao
}