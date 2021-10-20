package com.example.mym_posdemomvvm.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mym_posdemomvvm.models.Medicine

@Dao
interface MedicineDao {
    @Insert
    fun insert(m: Medicine)

    @Update
    fun update(m: Medicine)

    @Delete
    fun delete(m: Medicine)

    @Query("SELECT * FROM medicines")
    fun getAllMedicines(): LiveData<List<Medicine>>
}