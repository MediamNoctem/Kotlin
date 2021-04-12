import kotlin.math.absoluteValue

fun main(){
    val x = 914158
    val sum = sumDigitsNum(x.absoluteValue)
    println(sum)
    val mul = mulDigitNum(x.absoluteValue)
    println(mul)
}
fun sumDigitsNum(Number: Int): Int {
    if (Number == 0) return 0
    return (Number % 10 + sumDigitsNum(Number / 10))
}
fun mulDigitNum(Number: Int): Int {
    if (Number == 0) return 1
    return ((Number % 10) * mulDigitNum(Number / 10))
}