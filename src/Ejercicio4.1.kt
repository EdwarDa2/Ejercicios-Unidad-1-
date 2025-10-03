import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    val a = obtenerCoeficiente("a")
    val b = obtenerCoeficiente("b")
    val c = obtenerCoeficiente("c")

    val ecuacion = Raices(a, b, c)
    ecuacion.calcular()
}
class Raices(private val a: Double, private val b: Double, private val c: Double) {

    fun getDiscriminante(): Double {
        return b.pow(2) - 4 * a * c
    }
    fun tieneRaices(): Boolean {
        return getDiscriminante() >= 0
    }
    fun tieneRaiz(): Boolean {
        return getDiscriminante() == 0.0
    }
    fun obtenerRaices() {
        val discriminante = getDiscriminante()
        val raiz1 = (-b + sqrt(discriminante)) / (2 * a)
        val raiz2 = (-b - sqrt(discriminante)) / (2 * a)
        println("Las dos soluciones son:")
        println("Raíz 1: $raiz1")
        println("Raíz 2: $raiz2")
    }
    fun obtenerRaiz() {
        val raiz = -b / (2 * a)
        println("La ecuación tiene una única solución:")
        println("Raíz única: $raiz")
    }
    fun calcular() {
        println("Calculando soluciones para la ecuación: ${a}x^2 + ${b}x + ${c} = 0")
        val discriminante = getDiscriminante()

        if (a == 0.0) {
            println("El coeficiente 'a' es 0, por lo que no es una ecuación cuadrática.")
            if (b == 0.0) {
                println("La ecuación es una constante. No tiene solución (a menos que c=0).")
            } else {
                val solucion = -c / b
                println("Es una ecuación lineal. La solución es x = $solucion")
            }
            return
        }
        when {
            discriminante > 0 -> {
                println("El discriminante es positivo ($discriminante), hay dos soluciones reales distintas.")
                obtenerRaices()
            }
            discriminante == 0.0 -> {
                println("El discriminante es cero, hay una única solución real.")
                obtenerRaiz()
            }
            else -> {
                println("El discriminante es negativo ($discriminante), no hay soluciones reales.")
            }
        }
    }
}

fun obtenerCoeficiente(nombre: String): Double {
    while (true) {
        print("Introduce el coeficiente $nombre: ")
        val input = readLine()
        val coeficiente = input?.toDoubleOrNull()
        if (coeficiente != null) {
            return coeficiente
        } else {
            println("Entrada inválida. Por favor, introduce un número.")
        }
    }
}