import kotlin.math.absoluteValue

fun main(){
    val x = 914158
    println(sumDigitsNum(x.absoluteValue))
    println(mulDigitNum(x.absoluteValue))
    println(maxDigit(x.absoluteValue))
}
fun sumDigitsNum(Number: Int): Int {
    if (Number == 0) return 0
    return (Number % 10 + sumDigitsNum(Number / 10))
}
fun maxDigit(Number: Int): Int = maxDigit(Number/10,Number % 10)
fun maxDigit(Number: Int, CurMax: Int): Int {
    if (Number == 0) return CurMax
    return if (Number % 10 > CurMax) maxDigit(Number/10,Number % 10)
    else maxDigit(Number/10,CurMax)
}
fun mulDigitNum(Number: Int): Int =
    when(Number) {
        in 0..9 -> Number
        else -> mulDigitNum1(Number)
    }
fun mulDigitNum1(Number: Int): Int {
    if (Number == 0) return 1
    return ((Number % 10) * mulDigitNum1(Number / 10))
}