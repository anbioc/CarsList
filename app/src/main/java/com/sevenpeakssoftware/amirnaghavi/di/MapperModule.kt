package com.sevenpeakssoftware.amirnaghavi.di

import com.sevenpeakssoftware.amirnaghavi.base.Mapper
import com.sevenpeakssoftware.amirnaghavi.base.TwoWayMapper
import com.sevenpeakssoftware.amirnaghavi.data.dto.CarsDTO
import com.sevenpeakssoftware.amirnaghavi.data.local.data.CarItemLocalEntity
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import com.sevenpeakssoftware.amirnaghavi.mapper.DomainToLocalMapper
import com.sevenpeakssoftware.amirnaghavi.mapper.RemoteToDomainMapper
import dagger.Module
import dagger.Provides

@Module
class MapperModule {
    @Provides
    fun bindRemoteToDomainMapper(): Mapper<CarsDTO, List<CarEntity>> = RemoteToDomainMapper()

    @Provides
    fun bindDomainToLocalMapper(): TwoWayMapper<List<CarEntity>, List<CarItemLocalEntity>> = DomainToLocalMapper()
}