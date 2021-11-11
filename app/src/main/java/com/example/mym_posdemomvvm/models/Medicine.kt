package com.example.mym_posdemomvvm.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

//@Entity(tableName = "Medicines")
//@Entity(foreignKeys = [ForeignKey(
//    entity = Manufacture::class,
//    parentColumns = ["manufacture_id"],
//    childColumns = ["manufactureId"],
//    onDelete = CASCADE
//)], tableName = "Medicines"
//)
class Medicine(
    var name: String,
    var isH1: Boolean,
    var divisor: Int,
    var stock: Int = 0,
    var manufactureId: Int?
) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
        id = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeByte(if (isH1) 1 else 0)
        parcel.writeInt(divisor)
        parcel.writeInt(stock)
        parcel.writeValue(manufactureId)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Medicine> {
        override fun createFromParcel(parcel: Parcel): Medicine {
            return Medicine(parcel)
        }

        override fun newArray(size: Int): Array<Medicine?> {
            return arrayOfNulls(size)
        }
    }
}