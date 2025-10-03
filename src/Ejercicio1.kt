fun main() {
    println("Ingrese el numero suma:")
    var num: Int
    num = readLine()!!.toInt()
    var sum: Int = 0;
    for(i in 1..num) {
        sum = i + sum;
    }
    println("la suma es: $sum")
    var  facto = 1;
    var j =1
    while(j <= num ) {
        facto *= j
        j++
    }
    println("El factorial es: $facto")

}