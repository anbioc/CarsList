package com.multithread.car.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.multithread.car.data.local.data.CarItemLocalEntity
import com.multithread.car.data.local.type_converter.CarContentTypeConverter

@Database(
    entities = [CarItemLocalEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(CarContentTypeConverter::class)
abstract class CarsDataBase : RoomDatabase() {
    companion object {
        fun create(context: Context): CarsDataBase =
            Room.databaseBuilder(
                context,
                CarsDataBase::class.java,
                "cars.db"
            ).allowMainThreadQueries().build()
    }

    abstract fun provideCarDao(): CarDao
}