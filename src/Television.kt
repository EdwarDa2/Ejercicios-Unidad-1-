package models

class Television : Electrodomestico {
    private val DEFAULT_RESOLUTION = 20.0
    private val DEFAULT_TDT_TUNER = false

    val resolution: Double
    val tdtTuner: Boolean

    constructor() : super() {
        this.resolution = DEFAULT_RESOLUTION
        this.tdtTuner = DEFAULT_TDT_TUNER
    }

    constructor(precioBase: Double, peso: Double) : super(precioBase, peso) {
        this.resolution = DEFAULT_RESOLUTION
        this.tdtTuner = DEFAULT_TDT_TUNER
    }

    constructor(precioBase: Double, color: String, consumoEnergetico: Char, peso: Double, resolution: Double, tdtTuner: Boolean) :
            super(precioBase, color, consumoEnergetico, peso) {
        this.resolution = resolution
        this.tdtTuner = tdtTuner
    }

    override fun precioFinal(): Double {
        var price = super.precioFinal()
        if (resolution > 40) {
            price *= 1.30
        }
        if (tdtTuner) {
            price += 50
        }
        return price
    }
}