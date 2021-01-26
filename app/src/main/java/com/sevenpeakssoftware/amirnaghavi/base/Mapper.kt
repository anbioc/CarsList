package com.sevenpeakssoftware.amirnaghavi.base

interface Mapper<LEFT, RIGHT> {
    fun map(t: LEFT): RIGHT
}

interface TwoWayMapper<LEFT, RIGHT> {
    fun mapLeftToRight(t: LEFT): RIGHT
    fun mapRightToLeft(t: RIGHT): LEFT
}