import kotlin.math.absoluteValue

fun main(){
    val x = 914058
    val sum = sumDigitsNum(x.absoluteValue)
    println(sum)
}
fun sumDigitsNum(Number: Int): Int {
    if (Number == 0) return 0
    return (Number % 10 + sumDigitsNum(Number / 10))
}