package com.example.mym_posdemomvvm.daos

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagingSource
import androidx.room.*
import com.example.mym_posdemomvvm.models.*

@Dao
interface MedicineDao {
//    @Insert
//    fun insert(m: Medicine)
//
//    @Update
//    fun update(m: Medicine)
//
//    @Delete
//    fun delete(m: Medicine)

//    @Query("SELECT * FROM MEDICINES")
//    fun getAllMedicines(): LiveData<List<Medicine>>
//
//    @Query("SELECT * FROM MEDICINES")
//    fun getAllPagedMedicines(): PagingSource<Int, Medicine>
//
//    @Query("SELECT * FROM MEDICINES WHERE LOWER(name) LIKE :name LIMIT 500")
////    @Query("SELECT * FROM MEDICINES WHERE :name = :name")
//    fun getMedicinesContains(name: String): List<Medicine>
//
//    @Query("UPDATE MEDICINES SET stock = :stock WHERE id = :medicineId")
//    fun updateMedicineStock(stock: Int, medicineId: Int)
//
//    @Query("SELECT * FROM GEN")
//    fun getAllGenericOfRedBook(): LiveData<List<GenericRB>>

//    @Query("SELECT * FROM Parcel")
//    fun getAllParcelOfRedBook(): List<Parcel>

//    @Query("SELECT * FROM `public.demotable`")
//    fun getDEMO(): LiveData<List<Demo>>

//    @Query("SELECT * FROM 'public.pe_catalog'")
//    fun getAllMedicinesOfRedBook(): LiveData<List<Medicine1>>


//    @Query("SELECT * FROM `2021-10-21 02_30_57.688120`")
//    fun getAllMedicinesOfRedBook(): PagingSource<Int, Medicine1>
//
//    @Query("SELECT count(1) FROM `2021-10-21 02_30_57.688120`")
//    fun getAllMedicinesCountOfRedBook(): Int
//
//    @Query("SELECT * FROM `2021-10-21 02_30_57.688120` WHERE LOWER(product_name) LIKE :name")
////    @Query("SELECT * FROM MEDICINES WHERE :name = :name")
//    fun getMedicinesContainsOfRedBook(name: String): List<Medicine1>

    @Insert
    suspend fun insert(m: Medicine1)

    @Insert
    suspend fun insert(m: Medicine)

    @Query("SELECT name FROM med WHERE UPPER(name) like :string ")
    fun getAllMedicineList(string: String): LiveData<List<String>>

    @Query("SELECT * FROM `public.pe_catalog1`")
    fun getAllMedicinesOfRedBook(): DataSource.Factory<Int, Medicine1>

    @Query("SELECT count(1) FROM `public.pe_catalog1`")
    suspend fun getAllMedicinesCountOfRedBook(): Int

    @Query("SELECT * FROM `public.pe_catalog1` WHERE LOWER(product_name) LIKE :name")
    fun getMedicinesContainsOfRedBookPaging(name: String): PagingSource<Int, Medicine1>

    @Query("SELECT * FROM `public.pe_catalog1` WHERE LOWER(product_name) LIKE :name")
    suspend fun getMedicinesContainsOfRedBook(name: String): List<Medicine1>
}