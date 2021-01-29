package com.sevenpeakssoftware.amirnaghavi.di

import com.sevenpeakssoftware.amirnaghavi.base.Mapper
import com.sevenpeakssoftware.amirnaghavi.base.TwoWayMapper
import com.sevenpeakssoftware.amirnaghavi.data.dto.CarsDTO
import com.sevenpeakssoftware.amirnaghavi.data.local.data.CarItemLocalEntity
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import com.sevenpeakssoftware.amirnaghavi.mapper.DomainToLocalMapper
import dagger.Binds
import dagger.Module

@Module
interface MapperModule {
    @Binds
    fun bindRemoteToDomainMapper(mapper: DomainToLocalMapper): Mapper<CarsDTO, List<CarEntity>>

    @Binds
    fun bindDomainToLocalMapper(mapper: DomainToLocalMapper): TwoWayMapper<List<CarEntity>, List<CarItemLocalEntity>>
}