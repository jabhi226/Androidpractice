package com.example.mym_posdemomvvm.testes

import android.text.InputFilter
import android.text.Spanned
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.mym_posdemomvvm.daos.MedicineDao
import com.example.mym_posdemomvvm.getOrAwaitValue
import com.example.mym_posdemomvvm.models.Medicine
import com.example.mym_posdemomvvm.roomDb.RetailerDb
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalMultiplatform
@RunWith(AndroidJUnit4::class)
@SmallTest
class AndroidTests {

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    private lateinit var db: RetailerDb
    private lateinit var medDao: MedicineDao

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RetailerDb::class.java
        ).allowMainThreadQueries()
            .build()
        medDao = db.medicineDao
    }

    @After
    fun tearUp() {
        db.close()
    }

//    @Test
//    fun getProperSearch()  {
//        CoroutineScope(Dispatchers.IO).launch {
//            val names = arrayListOf(
//                "CROCIN ADVANCE 500MG STRIP OF 20 TABLETS",
//                "AIAMICRON XR 60MG STRIP OF 14 TABLETS"
//            )
//            names.forEach {
//                val m = Medicine(
//                    it,
//                    false,
//                    0,
//                    0,
//                    1
//                )
//                medDao.insert(m)
//            }
//
//            val a = medDao.getAllMedicineList("cro").getOrAwaitValue()
//
//            assertThat(a).containsExactly(names)
//        }
//    }

    @Test
    fun NoSpaceFilter(string: String) {
        val o = object: InputFilter {
            override fun filter(
                source: CharSequence?,
                start: Int,
                end: Int,
                dest: Spanned?,
                dstart: Int,
                dend: Int
            ): CharSequence {
                return source?.filter {
                    it.isWhitespace()
                }.toString()
            }
        }
    }

}