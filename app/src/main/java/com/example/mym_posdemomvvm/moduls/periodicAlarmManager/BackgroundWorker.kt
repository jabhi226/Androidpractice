package com.example.mym_posdemomvvm.moduls.periodicAlarmManager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.mym_posdemomvvm.utils.SharedPrefs
import com.example.mym_posdemomvvm.utils.Utils
import com.example.mym_posdemomvvm.utils.Utils.getFormattedDate
import java.util.Date

class BackgroundWorker(
    private val context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {

        val s = SharedPrefs.getString(context, "WORK_MANAGER") + "|" + Date().getFormattedDate(
            Utils.TIMESTAMP
        )
        SharedPrefs.setString(context, "WORK_MANAGER", s)
        return Result.success()
    }

}