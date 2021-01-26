package com.sevenpeakssoftware.amirnaghavi.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sevenpeakssoftware.amirnaghavi.data.local.data.CarItemLocalEntity
import io.reactivex.Observable


@Dao
interface CarDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCars(cars: List<CarItemLocalEntity>)

    @Query("SELECT * FROM cars")
    fun getCars(): Observable<List<CarItemLocalEntity>>
}