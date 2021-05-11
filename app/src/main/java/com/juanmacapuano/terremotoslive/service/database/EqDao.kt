package com.juanmacapuano.terremotoslive.service.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.juanmacapuano.terremotoslive.service.data.COLUMN_EARTHQUAKE_TIME
import com.juanmacapuano.terremotoslive.service.data.EarthQuake
import com.juanmacapuano.terremotoslive.service.data.TABLE_NAME_EARTHQUAKE

@Dao
interface EqDao {

    @Insert
    fun insert(earthQuake: EarthQuake): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(earthQuake: MutableList<EarthQuake>)

    @Update
    fun update(earthQuake: MutableList<EarthQuake>): Int

    @Query("SELECT * FROM $TABLE_NAME_EARTHQUAKE ORDER BY $COLUMN_EARTHQUAKE_TIME ASC")
    fun getAllEarthQuake() : LiveData<MutableList<EarthQuake>>
}