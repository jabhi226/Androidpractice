package com.example.mym_posdemomvvm.roomDb

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mym_posdemomvvm.daos.ManufactureDao
import com.example.mym_posdemomvvm.daos.MedicineDao
import com.example.mym_posdemomvvm.models.Manufacture
import com.example.mym_posdemomvvm.models.Medicine1
import com.example.mym_posdemomvvm.utils.Constants
import com.example.mym_posdemomvvm.utils.Utils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

@Database(
    entities = [
        Medicine1::class,
        Manufacture::class,
//        Medicine::class,
//    Demo::class,
//    GenericRB::class,
//        Parcel::class
    ],
    version = Constants.ROOM_DB_VERSION,
    exportSchema = false
)
abstract class RetailerDb : RoomDatabase() {

    var context: Context? = null

    companion object {
        private var instance: RetailerDb? = null

        fun getInstance(c: Context): RetailerDb {

            if (instance == null) {
                instance = Room.databaseBuilder(
                    c.applicationContext,
                    RetailerDb::class.java,
                    Constants.ROOM_DB_NAME
                )
//                    .createFromFile(File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath, "exportedDb.sqlite"))
//                    .createFromAsset("sample_fresh_db.sqlite")
//                    .createFromAsset("generic_sample.sqlite")
//                    .createFromAsset("chinook.db")
//                    .createFromAsset("data.sqlite")
//                    .createFromAsset("constants.db")
//                    .createFromAsset("InitialParcelTracker.db")
//                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
//                    .addMigrations(EMPTY_MIGRATION)
                    .build()
            }
            instance!!.context = c
            return instance!!
        }

        private val roomCallback: Callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                Thread {
                    Log.d("ONDBCREATE", ": PRELOAD DATA")
                    val manufactureDao = instance?.manufactureDao
                    manufactureDao?.insertManufacture(
                        Manufacture(
                            "Cipla private LTD",
                            "admin.cipla.com",
                            "cipla.com",
                            isGlobal = false,
                            isActive = true
                        )
                    )
//
                    val medicineDao = instance?.medicineDao
                    val a = Utils.getJsonFromAssets(instance!!.context!!, "medicine.json")
                    val array = JSONArray(a)
                    Log.d("MED_SIZE", "${array.length()}")
                    for (i in 0 until array.length()) {
                        Thread{
                            GlobalScope.launch {
                                val currentMed = JSONObject(array[i].toString())
                                Log.d("MED_SIZE", "${i}")
                                medicineDao?.insert(
                                    Medicine1(
                                        currentMed.getString("product_ucode"),
                                        "",
                                        currentMed.getString("product_name"),
                                        currentMed.getString("packform_name"),
                                        100,
                                        "",
                                        currentMed.getString("manufacturer"),
                                        currentMed.getString("manufacturer_id"),
                                        "",
                                        1,
                                        "",
                                        "",
                                        1,
                                        6,
                                        6,
                                        12,
                                        1,
                                        "",
                                        "",
                                        "",
                                        1,
                                        "",
                                        "",
                                        "",
                                        "",
                                        "",
                                        "",
                                        "",
                                    )
                                )
                            }
                        }.start()
                    }
                }.start()
            }

            override fun onOpen(db: SupportSQLiteDatabase) {
                Log.d("ONDBOPEN", ": DATABASE LOADED")
            }
        }


        private val MIGRATION_2_3 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE gen_temp (genericId TEXT, createdTime TEXT, created_By TEXT, deleted INTEGER, remarks TEXT, updatedTime TEXT, updatedBy TEXT, bannedOn TEXT, genericDescription TEXT, genericDosage TEXT, genericDosageNoSpace TEXT, genericName TEXT, genericType TEXT, isBanned INTEGER, isH1 INTEGER, isTB INTEGER,  PRIMARY KEY(genericId))")
                database.execSQL("INSERT INTO gen_temp SELECT * FROM gen")
                database.execSQL("DROP TABLE gen")
                database.execSQL("ALTER TABLE gen_temp RENAME TO gen")
            }
        }

        private val EMPTY_MIGRATION = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
            }
        }
    }

    abstract val medicineDao: MedicineDao
    abstract val manufactureDao: ManufactureDao
}