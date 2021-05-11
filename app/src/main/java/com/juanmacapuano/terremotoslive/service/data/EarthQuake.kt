package com.juanmacapuano.terremotoslive.service.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val TABLE_NAME_EARTHQUAKE: String = "earthquake"
const val COLUMN_EARTHQUAKE_PLACE: String = "place"
const val COLUMN_EARTHQUAKE_MAGNITUDE: String = "magnitude"
const val COLUMN_EARTHQUAKE_TIME: String = "time"
const val COLUMN_EARTHQUAKE_LONGITUDE: String = "longitude"
const val COLUMN_EARTHQUAKE_LATITUDE: String = "latitude"

@Entity(tableName = TABLE_NAME_EARTHQUAKE)
data class EarthQuake(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = "",
    @ColumnInfo(name = COLUMN_EARTHQUAKE_PLACE)
    var place: String = "",
    @ColumnInfo(name = COLUMN_EARTHQUAKE_MAGNITUDE)
    var magnitude: Double = 0.0,
    @ColumnInfo(name = COLUMN_EARTHQUAKE_TIME)
    var time: Long = 0,
    @ColumnInfo(name = COLUMN_EARTHQUAKE_LONGITUDE)
    var longitude: Double = 0.0,
    @ColumnInfo(name = COLUMN_EARTHQUAKE_LATITUDE)
    var latitude: Double = 0.0
    )
