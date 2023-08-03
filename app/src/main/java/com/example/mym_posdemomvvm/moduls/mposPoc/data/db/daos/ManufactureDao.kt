package com.example.mym_posdemomvvm.moduls.mposPoc.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mym_posdemomvvm.moduls.mposPoc.data.models.Manufacture

/**
 * Data Access Objects are the main classes where you define your database interactions.
 * They can include a variety of query methods.
 */
@Dao
interface ManufactureDao {
    @Insert
    fun insertManufacture(m: Manufacture)

    @Update
    fun updateManufacture(m: Manufacture)

    @Delete
    fun deleteManufacture(m: Manufacture)

    @Query("DELETE FROM MANUFACTURE WHERE 1 == 1 ;")
    fun deleteAllManufacture()

    @Query("SELECT * FROM MANUFACTURE")
    fun getAllManufactures(): LiveData<List<Manufacture>>
}