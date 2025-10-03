fun main() {

    do {
        println("Ingresa un numero:")
        var num = readLine()!!.toInt()
        if (num <= 0) {
            println("el numero debe ser positivo")
        }

        for (i in 1..num) {
            if (i % 2 == 1) {
                println("El numero $i es impar")
            } else {
                println("El numero $i es par")
            }
        }
        println()
    } while (num < 0)

}
