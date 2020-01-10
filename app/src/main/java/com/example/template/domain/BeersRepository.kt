package com.example.template.domain

import com.example.template.commons.datatype.Result
import com.example.template.repository.model.entity.BeersEntity

interface BeersRepository {

    suspend fun getAllBeers(): Result<BeersEntity>
}