package com.example.mym_posdemomvvm.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gen")
data class GenericRB(
@ColumnInfo(name="generic_id")
@PrimaryKey
var genericId: String = "",

@ColumnInfo(name = "created_time")
var createdTime: String? = "",

@ColumnInfo(name = "created_by")
var created_By: String? = "",

@ColumnInfo(name = "deleted")
var deleted: Boolean? = false,

@ColumnInfo(name = "remarks")
var remarks: String? = "",

@ColumnInfo(name = "updated_time")
var updatedTime: String? = "",

@ColumnInfo(name = "updated_by")
var updatedBy: String? = "",

@ColumnInfo(name = "banned_on")
var bannedOn: String? = "",

@ColumnInfo(name = "generic_description")
var genericDescription: String? = "",

@ColumnInfo(name = "generic_dosage")
var genericDosage: String? = "",

@ColumnInfo(name = "generic_dosage_no_space")
var genericDosageNoSpace: String? = "",

@ColumnInfo(name = "generic_name")
var genericName: String? = "",

@ColumnInfo(name = "generic_type")
var genericType: String? = "",

@ColumnInfo(name = "is_banned")
var isBanned: Boolean? = false,

@ColumnInfo(name = "ish1")
var isH1: Boolean? = false,

@ColumnInfo(name = "isTB")
var isTB: Boolean? = false
)
