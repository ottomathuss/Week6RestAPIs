package edu.du.week6restapis.model

data class Dog(
    val id: String,
    val name: String,
    val breed: String,
    val weight: String,
    val shelter: String,
    val adoptable: Boolean
)
