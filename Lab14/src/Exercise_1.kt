// 1
fun sumDigitsNumUp(number: Int): Int =
    if (number == 0) 0
    else number % 10 + sumDigitsNumUp(number / 10)

tailrec fun sumDigitsNumDown(number: Int, sum: Int): Int =
    if (number == 0) sum else {
        val num1 = number / 10
        val sum1 = sum + (number % 10)
        sumDigitsNumDown(num1,sum1)
    }
fun main() {
    val x = 915148
    println(sumDigitsNumUp(x))
    println(sumDigitsNumDown(x,0))
}