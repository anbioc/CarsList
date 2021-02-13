package com.multithread.car.mapper

import com.multithread.car.base.Mapper
import com.multithread.car.data.dto.CarDTO
import com.multithread.car.data.dto.Context
import com.multithread.car.domain.entity.CarContentEntity
import com.multithread.car.domain.entity.CarEntity
import javax.inject.Inject

class RemoteToDomainMapper @Inject constructor() : Mapper<CarDTO, List<CarEntity>> {
    override fun map(left: CarDTO): List<CarEntity> =
        mutableListOf<CarEntity>().apply {
            left.content.forEach {
                add(
                    CarEntity(
                        changed = it.changed,
                        content = createContent(it.content),
                        created = it.created,
                        dateTime = it.dateTime,
                        id = it.id,
                        image = it.image,
                        ingress = it.ingress,
                        title = it.title
                    )
                )
            }
        }

    private fun createContent(content: List<Context>): List<CarContentEntity> =
        mutableListOf<CarContentEntity>().apply {
            content.forEach {
                add(
                    CarContentEntity(
                        description = it.description,
                        subject = it.subject,
                        type = it.type
                    )
                )
            }
        }

}