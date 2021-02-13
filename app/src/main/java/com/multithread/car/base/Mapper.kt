package com.multithread.car.base

interface Mapper<LEFT, RIGHT> {
    fun map(left: LEFT): RIGHT
}

interface TwoWayMapper<LEFT, RIGHT> {
    fun mapLeftToRight(left: LEFT): RIGHT
    fun mapRightToLeft(right: RIGHT): LEFT
}