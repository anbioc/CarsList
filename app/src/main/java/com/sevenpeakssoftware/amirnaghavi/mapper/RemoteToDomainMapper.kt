package com.sevenpeakssoftware.amirnaghavi.mapper

import com.sevenpeakssoftware.amirnaghavi.base.Mapper
import com.sevenpeakssoftware.amirnaghavi.data.dto.CarsDTO
import com.sevenpeakssoftware.amirnaghavi.data.dto.ContentDTO
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarContentEntity
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import javax.inject.Inject

class RemoteToDomainMapper @Inject constructor(): Mapper<CarsDTO, List<CarEntity>>{
    override fun map(left: CarsDTO): List<CarEntity> = mutableListOf<CarEntity>().apply {
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
                    tags = it.tags,
                    title = it.title
                )
            )
        }
    }

    private fun createContent(content: List<ContentDTO>): List<CarContentEntity> =
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