package com.example.mym_posdemomvvm.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Parcel")
data class Parcel(
    @PrimaryKey(autoGenerate = true)
    var event_id: Int,
    var tracking_num: Int,
    var status: String? = "",
    var date_time: Int,
    var location: String? = ""
)
