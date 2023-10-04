package com.example.mym_posdemomvvm.moduls.mposPoc.ui.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mym_posdemomvvm.moduls.mposPoc.data.db.daos.MedicineDao
import com.example.mym_posdemomvvm.moduls.mposPoc.data.models.Medicine1
import com.example.mym_posdemomvvm.utils.Utils
import kotlin.coroutines.coroutineContext

class SearchMedicinePagingSource(private val medicineDao: MedicineDao, val name: String): PagingSource<Int, Medicine1>() {
    companion object {
        const val DEFAULT_PAGE_INDEX = 1
    }
    override fun getRefreshKey(state: PagingState<Int, Medicine1>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Medicine1> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
//        val response = medicineDao.getMedicinesContainsOfRedBookPaging("%cro%")
        val response = medicineDao.getMedicinesContainsOfRedBook("%$name%")

        Log.d("RESPONSE_SIZE -> ", "${response.size}")
        return LoadResult.Page(
                response,
                prevKey = page - 1,
                nextKey = page + 1
            )
    }
}