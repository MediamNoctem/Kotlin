import kotlin.math.absoluteValue
import kotlin.math.sqrt
import kotlin.math.floor
fun main(){
    val y = 36
    val x = y.absoluteValue
    println("Сумма цифр = " + sumDigitsNum(x))
    println("Произведение цифр = " + mulDigitNum(x))
    println("Максимальная цифра = " + maxDigit(x))
    println("Минимальная цифра = " + minDigit(x))
    println("Сумма непростых делителей = " + sumNonPrimeDiv(x))
}
// 6
fun sumDigitsNum(Number: Int): Int {
    if (Number == 0) return 0
    return (Number % 10 + sumDigitsNum(Number / 10))
}
// 7
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
// 8_1
// Проверка числа на простоту.
fun isPrime(Number: Int): Boolean {
    return if (Number < 3) true else isPrime(2,floor(sqrt(Number.toFloat())),Number)
}
fun isPrime(I: Int, T: Float, Number: Int): Boolean {
    return if (I <= T) {
        if (Number % 2 != 0) {
            if (Number % I != 0) isPrime(I+1,T,Number)
            else false
        } else false
    } else true
}
// Ищем сумму непростых делителей числа.
fun sumNonPrimeDiv(Number: Int): Int = sumNonPrimeDiv(Number,0,Number)
fun sumNonPrimeDiv(CurDiv: Int, CurSum: Int, Number: Int): Int {
    if (CurDiv == 0) return CurSum
    val newSum: Int = if (Number % CurDiv == 0 && !(isPrime(CurDiv))) CurSum + CurDiv
    else CurSum
    return sumNonPrimeDiv(CurDiv-1, newSum, Number)
}