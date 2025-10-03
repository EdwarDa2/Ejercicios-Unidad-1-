fun main() {
    var kil: Float = 0.0f

    println("Ingresa su distancia")
    kil = readLine()!!.toFloat()

    if (kil <=0.5 ) {
        println("Esta disponle?")
        val disponibilidad: String? = readLine()
        if (disponibilidad == "si") {
            println("Listo para iniciar el viaje")
            println("Conductor en camino")
        }else{
            println("Conductor no disponible")
        }
    }
    if (kil > 0.5) {
        println("Esta disponible?")
        val disponibilidad: String? = readLine()
        if (disponibilidad == "si") {
            println("conductor disponible pero muy lejos, aplicaran tarifas mas altas" )
        }else{
            println("conductor no disponible")
        }
    }

}