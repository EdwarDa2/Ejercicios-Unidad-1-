package models

import java.util.*

open class Electrodomestico {

    companion object {
        const val DEFAULT_COLOR = "BLANCO"
        const val DEFAULT_ENERGY_CONSUMPTION = 'F'
        const val DEFAULT_BASE_PRICE = 100.0
        const val DEFAULT_WEIGHT = 5.0
    }

    protected var color: String
    protected var weight: Double
    protected var energyConsumption: Char
    protected var basePrice: Double

    constructor() {
        this.color = DEFAULT_COLOR
        this.energyConsumption = DEFAULT_ENERGY_CONSUMPTION
        this.basePrice = DEFAULT_BASE_PRICE
        this.weight = DEFAULT_WEIGHT
    }

    constructor(precioBase: Double, peso: Double) {
        this.color = DEFAULT_COLOR
        this.energyConsumption = DEFAULT_ENERGY_CONSUMPTION
        this.basePrice = precioBase
        this.weight = peso
    }

    constructor(precioBase: Double, color: String, consumoEnergetico: Char, peso: Double) {
        this.color = comprobarColor(color)
        this.weight = peso
        this.energyConsumption = comprobarConsumoEnergetico(consumoEnergetico)
        this.basePrice = precioBase
    }

    private fun comprobarConsumoEnergetico(letter: Char): Char {
        val letters = charArrayOf('A', 'B', 'C', 'D', 'E', 'F')
        return if (letters.contains(letter.uppercaseChar())) letter.uppercaseChar() else DEFAULT_ENERGY_CONSUMPTION
    }

    private fun comprobarColor(color: String): String {
        val validColors = arrayOf("BLANCO", "NEGRO", "ROJO", "AZUL", "GRIS")
        val uppercaseColor = color.uppercase(Locale.getDefault())
        return if (validColors.contains(uppercaseColor)) uppercaseColor else DEFAULT_COLOR
    }

    open fun precioFinal(): Double {
        var finalPrice = this.basePrice
        when (this.energyConsumption) {
            'A' -> finalPrice += 100
            'B' -> finalPrice += 80
            'C' -> finalPrice += 60
            'D' -> finalPrice += 50
            'E' -> finalPrice += 30
            'F' -> finalPrice += 10
        }

        if (this.weight in 0.0..19.9) {
            finalPrice += 10
        } else if (this.weight in 20.0..49.9) {
            finalPrice += 50
        } else if (this.weight in 50.0..79.9) {
            finalPrice += 80
        } else if (this.weight >= 80) {
            finalPrice += 100
        }

        return finalPrice
    }
}