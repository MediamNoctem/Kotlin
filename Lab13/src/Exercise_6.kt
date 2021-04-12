import kotlin.math.absoluteValue

fun main(){
    val y = 914158
    val x = y.absoluteValue
    println(sumDigitsNum(x))
    println(mulDigitNum(x))
    println(maxDigit(x))
    println(minDigit(x))
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
fun minDigit(Number: Int): Int = minDigit(Number/10,Number % 10)
fun minDigit(Number: Int, CurMin: Int): Int {
    if (Number == 0) return CurMin
    return if (Number % 10 < CurMin) minDigit(Number/10,Number % 10)
    else minDigit(Number/10,CurMin)
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