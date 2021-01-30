package com.sevenpeakssoftware.amirnaghavi.mapper

import android.util.Log
import com.sevenpeakssoftware.amirnaghavi.base.Mapper
import com.sevenpeakssoftware.amirnaghavi.data.dto.CarDTO
import com.sevenpeakssoftware.amirnaghavi.data.dto.Context
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarContentEntity
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import javax.inject.Inject

class RemoteToDomainMapper @Inject constructor() : Mapper<CarDTO, List<CarEntity>> {
    override fun map(left: CarDTO): List<CarEntity> =
        mutableListOf<CarEntity>().apply {
            Log.d("dataTag", "map: content length ${left.content}")
            kotlin.runCatching {
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
        }.getOrElse {
            Log.e("errorTag", "map: got problem: ", it)
            it.printStackTrace()
        }
    }

    private fun createContent(content: List<Context>): List<CarContentEntity> =
        mutableListOf<CarContentEntity>().apply {
            kotlin.runCatching {
                content.forEach {
                    add(
                        CarContentEntity(
                            description = it.description,
                            subject = it.subject,
                            type = it.type
                        )
                    )
                }
            }.getOrElse {
                Log.e("errorTag", "createContent: got problem: ", it)
            }
        }

}