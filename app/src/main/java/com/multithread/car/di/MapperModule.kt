package com.multithread.car.di

import com.multithread.car.base.Mapper
import com.multithread.car.base.TwoWayMapper
import com.multithread.car.data.dto.CarDTO
import com.multithread.car.data.local.data.CarItemLocalEntity
import com.multithread.car.domain.entity.CarEntity
import com.multithread.car.mapper.DomainToLocalMapper
import com.multithread.car.mapper.RemoteToDomainMapper
import dagger.Module
import dagger.Provides

@Module
class MapperModule {
    @Provides
    fun bindRemoteToDomainMapper(): Mapper<CarDTO, List<CarEntity>> = RemoteToDomainMapper()

    @Provides
    fun bindDomainToLocalMapper(): TwoWayMapper<List<CarEntity>, List<CarItemLocalEntity>> = DomainToLocalMapper()
}