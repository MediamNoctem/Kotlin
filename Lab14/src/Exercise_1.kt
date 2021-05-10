import java.lang.Integer.max
import java.lang.Integer.min
import kotlin.math.absoluteValue
import kotlin.math.floor
import kotlin.math.sqrt

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
        val n1 = n / 10
        val accum1 = if (pr(n % 10)) f(accum,n % 10) else accum
        digitsDown(n1,accum1,f,pr)
    }
// 6
// Произведение цифр числа, меньших 3.
fun mulDigitsLess3(n: Int): Int = digitsDown(n,1,{ a,b -> a*b }, { a -> a < 3 })
// Сумма цифр числа, кратных 5.
fun sumDigitsMult5(n: Int): Int = digitsDown(n,0,{ a,b -> a + b }, { a -> (a % 5 == 0) })
// Максимальная цифра числа, не превосходящая 6.
fun maxDigitNotExceeding6(n: Int): Int = digitsDown(n,-1,{ a,b -> max(a,b) }, { a -> a <= 6 })
// 7
// 13_8_1
// Проверка числа на простоту.
fun isPrime(number: Int): Boolean = if (number < 3) true else isPrime(2, floor(sqrt(number.toFloat())),number)
tailrec fun isPrime(i: Int, t: Float, number: Int): Boolean =
    if (i <= t) {
        if (number % 2 != 0) {
            if (number % i != 0) {
                val i1 = i + 1
                isPrime(i1,t,number)
            }
            else false
        } else false
    } else true
// Ищем сумму непростых делителей числа.
fun sumNonPrimeDiv(number: Int): Int = sumNonPrimeDiv(number,0,number)
tailrec fun sumNonPrimeDiv(curDiv: Int, curSum: Int, number: Int): Int =
    if (curDiv == 0) curSum else {
        val newSum: Int = if (number % curDiv == 0 && !(isPrime(curDiv))) curSum + curDiv else curSum
        sumNonPrimeDiv(curDiv - 1, newSum, number)
    }
// 13_8_2
fun countDigitsNumLess3(number: Int): Int =
    when(number) {
        in 0..2 -> 1
        in 3..9 -> 0
        else -> countDigitsLess3(number, 0)
    }
tailrec fun countDigitsLess3(number: Int, curCount: Int): Int =
    if (number == 0) curCount else {
        if (number % 10 < 3) {
            val curCount1 = curCount + 1
            countDigitsLess3(number / 10, curCount1)
        }
        else countDigitsLess3(number / 10, curCount)
    }
// 13_8_3
// НОД.
tailrec fun nod(x: Int, y: Int): Int =
    if (x == y) x else {
        val d: Int
        if (x < y) {
            d = y - x
            nod(x,d)
        } else {
            d = x - y
            nod(d,y)
        }
    }
// Сумма простых цифр числа.
fun sumPrimeDigit(number: Int): Int = sumPrimeDigit(number,0)
tailrec fun sumPrimeDigit(number: Int, curSum: Int): Int =
    if (number == 0) curSum else {
        val newSum: Int = if (isPrime(number % 10)) curSum + number % 10
        else curSum
        sumPrimeDigit(number / 10, newSum)
    }
fun p3(number: Int): Int =
    when (sumPrimeDigit(number)) {
        0 -> 0
        else -> p3(number,0,number,sumPrimeDigit(number))
    }
tailrec fun p3(curN: Int, curQuantity: Int, number: Int, sum: Int): Int =
    if (curN == 0) curQuantity else {
        val newQuantity: Int = if (number % curN != 0 && nod(curN, number) != 1 && nod(curN, sum) == 1) curQuantity + 1
        else curQuantity
        p3(curN - 1, newQuantity, number, sum)
    }
fun main() {
    val y = 10
    val x = y.absoluteValue
    /*println("Exercise 1:")
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
    println("Exercise 6:")
    println("Произведение цифр числа, меньших 3 = " + mulDigitsLess3(x))
    println("Сумма цифр числа, кратных 5 = " + sumDigitsMult5(x))
    println("Максимальная цифра числа, не превосходящая 6 = " + maxDigitNotExceeding6(x))*/
    println("Exercise 7:")
    println("Сумма непростых делителей = " + sumNonPrimeDiv(x))
    println("Количество цифр, меньших 3 = " + countDigitsNumLess3(x))
    println("Количество чисел, не являющихся делителями исходного числа,\n" +
            "не взамно простых с ним и взаимно простых с суммой простых\n" +
            "цифр этого числа = " + p3(x))
}