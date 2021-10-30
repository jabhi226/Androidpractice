package com.example.mym_posdemomvvm.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Manufacture(
    @ColumnInfo(name = "manufacturer_name")
    var manufacturerName: String,

    @ColumnInfo(name = "manufacturer_email")
    var manufacturerEmail: String,

    @ColumnInfo(name = "manufacturer_website")
    var manufacturer_website: String,

    @ColumnInfo(name = "is_global")
    var isGlobal: Boolean = false,

    @ColumnInfo(name = "is_active")
    var isActive: Boolean = false
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "manufacture_id")
    var manufactureId: Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte()
    ) {
        manufactureId = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(manufacturerName)
        parcel.writeString(manufacturerEmail)
        parcel.writeString(manufacturer_website)
        parcel.writeByte(if (isGlobal) 1 else 0)
        parcel.writeByte(if (isActive) 1 else 0)
        parcel.writeInt(manufactureId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Manufacture> {
        override fun createFromParcel(parcel: Parcel): Manufacture {
            return Manufacture(parcel)
        }

        override fun newArray(size: Int): Array<Manufacture?> {
            return arrayOfNulls(size)
        }
    }
}