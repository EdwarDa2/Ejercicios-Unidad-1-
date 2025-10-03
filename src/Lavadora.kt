package models

class Lavadora : Electrodomestico {
    private val DEFAULT_LOAD = 5.0
    val load: Double

    constructor() : super() {
        this.load = DEFAULT_LOAD
    }

    constructor(precioBase: Double, peso: Double) : super(precioBase, peso) {
        this.load = DEFAULT_LOAD
    }

    constructor(precioBase: Double, color: String, consumoEnergetico: Char, peso: Double, load: Double) :
            super(precioBase, color, consumoEnergetico, peso) {
        this.load = load
    }

    override fun precioFinal(): Double {
        var price = super.precioFinal()
        if (load > 30) {
            price += 50
        }
        return price
    }
}