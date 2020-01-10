package com.example.template.commons.mappers

interface BaseMapper<in A, out B> {

    fun map(type: A?): B
}