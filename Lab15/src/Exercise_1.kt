import java.io.File
import java.lang.Integer.min
import java.lang.Integer.max


fun main() {
    // 1-2
    /*val a = enterArray()
    val n = a.size
    println("Сумма элементов массива: " + sumElementsArray(a,n) + "   " + sumElArray(a))
    println("Произведение элементов массива: " + multElementsArray(a,n) + "   " + multElArray(a))
    println("Минимальный элемент массива: " + minElementArray(a,n) + "   " + minElArray(a))
    println("Максимальный элемент массива: " + maxElementArray(a,n) + "   " + maxElArray(a))
    // 3
    var b = enterArrayFrom()
    b.forEach { print("$it ") }*/
    // 4_11
    val a = arrayOf(55,55)
    println("Отличающийся элемент: " + findDiffElArray(a))
}
// 1-2
// Вводим массив через клавиатуру.
fun enterArray(): Array<Int> =
    run {
        print("Введите размер массива: ")
        val n = readLine()!!.toInt()
        var a = Array(n){0}
        enterArrayElements(a,0,n)
        a
    }
tailrec fun enterArrayElements(a: Array<Int>, i: Int, n: Int): Unit =
    if (i < n - 1) {
        val i1 = i + 1
        println("Введите $i1 элемент массива:")
        val element = readLine()!!.toInt()
        a[i] = element
        enterArrayElements(a,i1,n)
    } else {
        val i1 = i + 1
        println("Введите $i1 элемент массива:")
        val element = readLine()!!.toInt()
        a[i] = element
    }
// Сумма элементов массива.
fun sumElementsArray(a: Array<Int>, n: Int): Int = sumElementsArray(a,0,0,n)
tailrec fun sumElementsArray(a: Array<Int>, sum: Int, i: Int, n: Int): Int =
    if (i == n) sum else {
        val sum1 = sum + a[i]
        val i1 = i + 1
        sumElementsArray(a,sum1,i1,n)
    }
// Произведение элементов.
fun multElementsArray(a: Array<Int>, n: Int): Int = multElementsArray(a,1,0,n)
tailrec fun multElementsArray(a: Array<Int>, p: Int, i: Int, n: Int): Int =
    if (i == n) p else {
        val p1 = p * a[i]
        val i1 = i + 1
        multElementsArray(a,p1,i1,n)
    }
// Минимальный элемент.
fun minElementArray(a: Array<Int>, n: Int): Int = minElementArray(a,a[0],0,n)
tailrec fun minElementArray(a: Array<Int>, min: Int, i: Int, n: Int): Int =
    if (i == n) min else {
        val min1 = if (a[i] < min) a[i] else min
        val i1 = i + 1
        minElementArray(a,min1,i1,n)
    }
// Максимальный элемент.
fun maxElementArray(a: Array<Int>, n: Int): Int = maxElementArray(a,a[0],0,n)
tailrec fun maxElementArray(a: Array<Int>, max: Int, i: Int, n: Int): Int =
    if (i == n) max else {
        val max1 = if (a[i] > max) a[i] else max
        val i1 = i + 1
        maxElementArray(a,max1,i1,n)
    }

tailrec fun arrayOp(a: Array<Int>, accum: Int, f: (Int,Int) -> Int): Int =
    if (a.isEmpty()) accum else {
        val a1: Array<Int> = a.dropLast(1).toTypedArray()
        val accum1 = f(accum,a.last())
        arrayOp(a1,accum1,f)
    }
fun sumElArray(a: Array<Int>): Int = arrayOp(a,0,{ a,b -> a + b })
fun multElArray(a: Array<Int>): Int = arrayOp(a,1,{ a,b -> a*b })
fun minElArray(a: Array<Int>): Int = arrayOp(a,a[0],{ a,b -> min(a,b) })
fun maxElArray(a: Array<Int>): Int = arrayOp(a,a[0],{ a,b -> max(a,b) })

// 3
fun enterArrayFromFile(path: String): Array<Int> =
    run {
        var a: Array<Int> = emptyArray()
        File(path).forEachLine { a += it.toInt() }
        a
    }
fun enterArrayFrom(): Array<Int> =
    run {
        var b = emptyArray<Int>()
        println("1. Ввести массив с клавиатуры.")
        println("2. Ввести массив из файла.")
        when (readLine()) {
            "1" -> b = enterArray()
            "2" -> {
                println("Введите полный путь к файлу, из которого нужно считать массив (использовать двойной обратный слэш): ")
                b = enterArrayFromFile(readLine()!!.toString())
            }
            else -> println("Неизвестная команда!")
        }
        b
    }

// 4_11
fun findDiffElArray(a: Array<Int>): Int =
    when {
        a.size < 3 -> throw IllegalArgumentException("Ошибка!")
        else -> findDiffElArray(a,0)
    }
fun findDiffElArray(a: Array<Int>, i: Int): Int =
    if (a[i] == a[i + 1]) {
        if (a[i + 1] == a[i + 2]) {
            val i1 = i + 1
            findDiffElArray(a, i1)
        } else a[i + 2]
    } else {
        if (a[i] == a[i + 2]) a[i + 1] else a[i]
    }