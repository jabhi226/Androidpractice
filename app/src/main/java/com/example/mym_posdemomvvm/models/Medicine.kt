package com.example.mym_posdemomvvm.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Medicines")
class Medicine(
    var name: String,
    var isH1: Boolean,
    var divisor: Int
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}