// 1
fun sumDigitsNumUp(number: Int): Int =
    if (number == 0) 0
    else (number % 10 + sumDigitsNumUp(number / 10))
fun main() {
    val x = 915148
    println(sumDigitsNumUp(x))
}