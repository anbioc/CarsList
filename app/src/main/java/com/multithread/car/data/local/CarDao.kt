package com.multithread.car.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.multithread.car.AppConstant
import com.multithread.car.data.local.data.CarItemLocalEntity
import io.reactivex.Observable


@Dao
interface CarDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCars(cars: List<CarItemLocalEntity>)

    @Query("SELECT * FROM ${AppConstant.CARS_TABLE_NAME}")
    fun getCars(): Observable<List<CarItemLocalEntity>>
}