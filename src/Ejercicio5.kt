package App

import models.Electrodomestico
import models.Lavadora
import models.Television

fun main() {
    val list: Array<Electrodomestico?> = arrayOfNulls(10)

    list[0] = Lavadora(9000.0, "BLANCO", 'F', 100.0, 20.0)
    list[1] = Electrodomestico(1500.0, "ROJO", 'A', 40.0)
    list[2] = Television(100.0, "BLANCO", 'A', 80.0, 100.0, false)
    list[3] = Electrodomestico(2100.0, "NEGRO", 'C', 50.0)
    list[4] = Lavadora(12000.0, "GRIS", 'B', 70.0, 9.0)
    list[5] = Electrodomestico(4500.0, "AZUL", 'B', 100.0)
    list[6] = Television(13500.0, "AZUL", 'F', 40.0, 300.0, true)
    list[7] = Lavadora(1900.0, "BLANCO", 'F', 120.0, 12.0)
    list[8] = Electrodomestico(1800.0, "ROJO", 'A', 40.0)
    list[9] = Television(7500.0, "GRIS", 'E', 90.0, 780.0, false)

    var totalAppliances = 0.0
    var totalWashers = 0.0
    var totalTelevisions = 0.0

    for (appliance in list) {
        if (appliance != null) {
            val finalPrice = appliance.precioFinal()
            totalAppliances += finalPrice

            if (appliance is Lavadora) {
                totalWashers += finalPrice
            } else if (appliance is Television) {
                totalTelevisions += finalPrice
            }
        }
    }

    println("==============================")
    println("Total Washers: $totalWashers €")
    println("Total Televisions: $totalTelevisions €")
    println("Total Appliances: $totalAppliances €")
}