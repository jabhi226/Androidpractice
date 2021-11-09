package com.example.mym_posdemomvvm.daos

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.example.mym_posdemomvvm.models.GenericRB
import com.example.mym_posdemomvvm.models.Medicine
import com.example.mym_posdemomvvm.models.Medicine1

@Dao
interface MedicineDao {
    @Insert
    fun insert(m: Medicine)

    @Update
    fun update(m: Medicine)

    @Delete
    fun delete(m: Medicine)

    @Query("SELECT * FROM MEDICINES")
    fun getAllMedicines(): LiveData<List<Medicine>>

    @Query("SELECT * FROM GEN")
    fun getAllGenericOfRedBook(): LiveData<List<GenericRB>>

//    @Query("SELECT * FROM 'public.pe_catalog'")
//    fun getAllMedicinesOfRedBook(): LiveData<List<Medicine1>>

    @Query("SELECT * FROM MEDICINES")
    fun getAllPagedMedicines(): PagingSource<Int, Medicine>

    @Query("SELECT * FROM MEDICINES WHERE LOWER(name) LIKE :name LIMIT 500")
//    @Query("SELECT * FROM MEDICINES WHERE :name = :name")
    fun getMedicinesContains(name: String): List<Medicine>

    @Query("UPDATE MEDICINES SET stock = :stock WHERE id = :medicineId")
    fun updateMedicineStock(stock: Int, medicineId: Int)
}