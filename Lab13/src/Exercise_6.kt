import kotlin.math.absoluteValue
import kotlin.math.sqrt
import kotlin.math.floor
fun main(){
    val y = 10
    val x = y.absoluteValue
    println("Сумма цифр = " + sumDigitsNum(x))
    println("Произведение цифр = " + mulDigitNum(x))
    println("Максимальная цифра = " + maxDigit(x))
    println("Минимальная цифра = " + minDigit(x))
    println("Сумма непростых делителей = " + sumNonPrimeDiv(x))
    println("Количество цифр, меньших 3 = " + countDigitsNumLess3(x))
    println("Количество чисел, не являющихся делителями исходного числа,\n" +
            "не взамно простых с ним и взаимно простых с суммой простых\n" +
            "цифр этого числа = " + p3(x))
}
// 6
fun sumDigitsNum(number: Int): Int {
    if (number == 0) return 0
    return (number % 10 + sumDigitsNum(number / 10))
}
// 7
fun maxDigit(number: Int): Int = maxDigit(number/10,number % 10)
fun maxDigit(number: Int, curMax: Int): Int {
    if (number == 0) return curMax
    return if (number % 10 > curMax) maxDigit(number/10,number % 10)
    else maxDigit(number/10,curMax)
}
fun minDigit(number: Int): Int = minDigit(number/10,number % 10)
fun minDigit(number: Int, curMin: Int): Int {
    if (number == 0) return curMin
    return if (number % 10 < curMin) minDigit(number/10,number % 10)
    else minDigit(number/10,curMin)
}
fun mulDigitNum(number: Int): Int =
    when(number) {
        in 0..9 -> number
        else -> mulDigitNum1(number)
    }
fun mulDigitNum1(number: Int): Int {
    if (number == 0) return 1
    return ((number % 10) * mulDigitNum1(number / 10))
}
// 8_1
// Проверка числа на простоту.
fun isPrime(number: Int): Boolean {
    return if (number < 3) true else isPrime(2,floor(sqrt(number.toFloat())),number)
}
fun isPrime(i: Int, t: Float, number: Int): Boolean {
    return if (i <= t) {
        if (number % 2 != 0) {
            if (number % i != 0) isPrime(i+1,t,number)
            else false
        } else false
    } else true
}
// Ищем сумму непростых делителей числа.
fun sumNonPrimeDiv(number: Int): Int = sumNonPrimeDiv(number,0,number)
fun sumNonPrimeDiv(curDiv: Int, curSum: Int, number: Int): Int {
    if (curDiv == 0) return curSum
    val newSum: Int = if (number % curDiv == 0 && !(isPrime(curDiv))) curSum + curDiv
    else curSum
    return sumNonPrimeDiv(curDiv-1, newSum, number)
}
// 8_2
fun countDigitsNumLess3(number: Int): Int =
    when(number) {
        in 0..9 -> 1
        else -> countDigitsLess3(number)
    }
fun countDigitsLess3(number: Int): Int {
    if (number == 0) return 0
    return if (number % 10 < 3) 1 + countDigitsLess3(number/10)
    else countDigitsLess3(number/10)
}
// 8_3
// НОД.
fun nod(x: Int, y: Int): Int {
    return if (x == y) x
    else {
        val d: Int
        if (x < y) {
            d = y - x
            nod(x,d)
        } else {
            d = x - y
            nod(d,y)
        }
    }
}
// Сумма простых цифр числа.
fun sumPrimeDigit(number: Int): Int = sumPrimeDigit(number,0)
fun sumPrimeDigit(number: Int, curSum: Int): Int {
    if (number == 0) return curSum
    val newSum: Int = if (isPrime(number % 10)) curSum + number % 10
    else curSum
    return sumPrimeDigit(number/10,newSum)
}
fun p3(number: Int): Int =
    when (sumPrimeDigit(number)) {
        0 -> 0
        else -> p3(number,0,number,sumPrimeDigit(number))
    }
fun p3(curN: Int, curQuantity: Int, number: Int, sum: Int): Int {
    if (curN == 0) return curQuantity
    val newQuantity: Int = if (number % curN != 0 && nod(curN,number) != 1 && nod(curN,sum) == 1) curQuantity + 1
    else curQuantity
    return p3(curN-1,newQuantity,number,sum)
}