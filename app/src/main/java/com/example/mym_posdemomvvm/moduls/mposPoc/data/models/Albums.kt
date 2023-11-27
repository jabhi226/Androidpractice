package com.example.mym_posdemomvvm.moduls.mposPoc.data.models

import androidx.room.PrimaryKey

//@Entity(tableName = "Parcel")
data class Parcel(
    @PrimaryKey(autoGenerate = true)
    var event_id: Int,
    var tracking_num: Int,
    var status: String? = "",
    var date_time: Int,
    var location: String? = ""
)
