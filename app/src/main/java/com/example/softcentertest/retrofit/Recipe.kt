package com.example.softcentertest.retrofit

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//класс для получения данных с сервера, а также для работы с базой данных, определяющий таблицы и ее столбцы
@Entity(tableName = "recipe_table")
data class Recipe(

    @ColumnInfo(name ="calories") val calories : String,
    @ColumnInfo(name ="carbos") val carbos : String,
    @ColumnInfo(name ="description") val description : String,
    @ColumnInfo(name ="difficulty") val difficulty : Int,
    @ColumnInfo(name ="fats") val fats : String,
    @ColumnInfo(name ="headline") val headline : String,
    @ColumnInfo(name = "id") val id : String,
    @ColumnInfo(name ="image") val image : String,
    @PrimaryKey val name : String,
    @ColumnInfo(name ="proteins") val proteins : String,
    @ColumnInfo(name ="thumb") val thumb : String,
    @ColumnInfo(name ="time") val time : String
) : Parcelable {



    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(calories)
        parcel.writeString(carbos)
        parcel.writeString(description)
        parcel.writeInt(difficulty)
        parcel.writeString(fats)
        parcel.writeString(headline)
        parcel.writeString(id)
        parcel.writeString(image)
        parcel.writeString(name)
        parcel.writeString(proteins)
        parcel.writeString(thumb)
        parcel.writeString(time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel): Recipe {
            return Recipe(parcel)
        }

        override fun newArray(size: Int): Array<Recipe?> {
            return arrayOfNulls(size)
        }
    }
}
