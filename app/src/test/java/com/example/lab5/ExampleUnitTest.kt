package com.example.lab5

import org.junit.Test
import org.junit.Assert.*

class ExampleUnitTest {
    private val basePrices = mapOf(
        "9x13" to 50.0,
        "10x15" to 60.0,
        "11x15" to 70.0,
        "13x18" to 80.0,
        "15x20" to 90.0,
        "15x21" to 95.0,
        "15x22" to 100.0,
        "15x23" to 105.0,
        "18x24" to 110.0,
        "20x30" to 120.0,
        "21x30" to 125.0,
        "23x30" to 130.0,
        "30x30" to 140.0,
        "30x40" to 150.0,
        "30x42" to 160.0,
        "30x45" to 170.0,
        "30x60" to 180.0,
        "30x90" to 190.0
    )

    private val paperTypeMultipliers = mapOf(
        "Матовая" to 1.0,
        "Глянцевая" to 1.2,
        "Металлик" to 1.5,
        "Шелк" to 1.3
    )

    private fun calculatePrice(quantity: Int, size: String, paperType: String): Double {
        val basePrice = basePrices[size] ?: 0.0
        val paperTypeMultiplier = paperTypeMultipliers[paperType] ?: 1.0
        return basePrice * paperTypeMultiplier * quantity
    }

    @Test
    fun testPriceCalculation() {
        val quantity = 10
        val size = "10x15"
        val paperType = "Глянцевая"

        val result = calculatePrice(quantity, size, paperType)
        assertEquals(720.0, result, 0.01)
    }

    @Test
    fun testInvalidPriceCalculation() {
        val quantity = 0
        val size = "10x15"
        val paperType = "Глянцевая"

        val result = calculatePrice(quantity, size, paperType)
        assertEquals(0.0, result, 0.01)
    }
}
