package com.example.template.repository.model.entity

data class BeerEntity(
        val id: Int,
        val name: String,
        val tagline: String,
        val image: String,
        val abv: Double
)