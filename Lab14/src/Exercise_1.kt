import java.lang.Integer.max
import java.lang.Integer.min
import kotlin.math.absoluteValue
// 1
fun sumDigitsNumUp(number: Int): Int =
    if (number == 0) 0
    else number % 10 + sumDigitsNumUp(number / 10)
// 2
tailrec fun sumDigitsNumDown(number: Int, sum: Int): Int =
    if (number == 0) sum else {
        val num1 = number / 10
        val sum1 = sum + (number % 10)
        sumDigitsNumDown(num1,sum1)
    }
// 3
fun mulDigitNumUp(number: Int): Int =
    when(number) {
        in 0..9 -> number
        else -> mulDigitUp(number)
    }
fun mulDigitUp(number: Int): Int =
    if (number == 0) 1
    else (number % 10) * mulDigitUp(number / 10)
fun mulDigitNumDown(number: Int): Int =
    when(number) {
        in 0..9 -> number
        else -> mulDigitNumDown(number / 10, number % 10)
    }
tailrec fun mulDigitNumDown(number: Int, curMul: Int): Int =
    if (number == 0) curMul
    else {
        val curMul1 = curMul*(number % 10)
        mulDigitNumDown(number/10, curMul1)
    }
fun minDigitUp(number: Int): Int =
    if (number.div(10) == 0) number % 10
    else {
        val curMin2 = minDigitUp(number / 10)
        val curMin1 = number % 10
        min(curMin1,curMin2)
    }
fun minDigitDown(number: Int): Int = minDigitDown(number/10,number % 10)
tailrec fun minDigitDown(number: Int, curMin: Int): Int =
    if (number == 0) curMin else {
        if (number % 10 < curMin) minDigitDown(number / 10, number % 10)
        else minDigitDown(number / 10, curMin)
    }
fun maxDigitUp(number: Int): Int =
    if (number / 10 == 0) number % 10
    else {
        val curMax2 = maxDigitUp(number / 10)
        val curMax1 = number % 10
        max(curMax1,curMax2)
    }
fun maxDigitDown(number: Int): Int = maxDigitDown(number/10,number % 10)
tailrec fun maxDigitDown(number: Int, curMax: Int): Int =
    if (number == 0) curMax else {
        if (number % 10 > curMax) maxDigitDown(number / 10, number % 10)
        else maxDigitDown(number / 10, curMax)
    }
// 4
tailrec fun digitsDown(n: Int, accum: Int, f:(Int,Int) -> Int): Int =
    if (n == 0) accum else {
        val n1 = n / 10
        val accum1 = f(accum,n % 10)
        digitsDown(n1,accum1,f)
    }
fun sumDigits(n: Int): Int = digitsDown(n,0, { a, b -> a + b })
fun mulDigits(n: Int): Int = digitsDown(n,1,{ a,b -> a*b })
fun minDigit(n: Int): Int = digitsDown(n,n % 10, { a,b -> min(a,b) })
fun maxDigit(n: Int): Int = digitsDown(n, n % 10, { a,b -> max(a,b) })
// 5
tailrec fun digitsDown(n: Int, accum: Int, f:(Int,Int)->Int, pr:(Int)->Boolean): Int =
    if (n == 0) accum else {
        val n1 = n/10
        val accum1 = if (pr(n/10)) accum + (n%10) else accum
        digitsDown(n1,accum1,f,pr)
    }
fun main() {
    val y = 915148
    val x = y.absoluteValue
    println("Exercise 1:")
    println("Сумма цифр (Up) = " + sumDigitsNumUp(x))
    println("Exercise 2:")
    println("Сумма цифр (down) = " + sumDigitsNumDown(x,0))
    println("Exercise 3:")
    println("Произведение цифр (Up) = " + mulDigitNumUp(x))
    println("Произведение цифр (down) = " + mulDigitNumDown(x))
    println("Минимальная цифра (Up) = " + minDigitUp(x))
    println("Минимальная цифра (down) = " + minDigitDown(x))
    println("Максимальная цифра (Up) = " + maxDigitUp(x))
    println("Максимальная цифра (down) = " + maxDigitDown(x))
    println("Exercise 4:")
    println("Сумма цифр = " + sumDigits(x))
    println("Произведение цифр = " + mulDigits(x))
    println("Минимальная цифра = " + minDigit(x))
    println("Максимальная цифра = " + maxDigit(x))
}