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
    b.forEach { print("$it ") }
    // 4_11
    val a = arrayOf(55,55,85)
    println("Отличающийся элемент: " + findDiffElArray(a))*/
    // 4_24
    val a = arrayOf(5,0,0,1,9,5,10)
    var max: Array<Int> = findTwoMax(a)
    max.forEach { print("$it ") }
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
tailrec fun findDiffElArray(a: Array<Int>, i: Int): Int =
    if (a[i] == a[i + 1]) {
        if (a[i + 1] == a[i + 2]) {
            val i1 = i + 1
            findDiffElArray(a, i1)
        } else a[i + 2]
    } else {
        if (a[i] == a[i + 2]) a[i + 1] else a[i]
    }

// 4_12
// Ищем индекс элемента массива, который больше i и равен t.
tailrec fun findIndex(a: Array<Int>, i: Int, t: Int, j: Int): Int =
    if (j >= a.size) -1 else {
        if (a[j] == t && j >= i) j else {
            val j1 = j + 1
            findIndex(a, i, t, j1)
        }
    }
// Заменяем в массиве a элементы с индексами от start до end
// элементами массива b.
fun editElementsArrayByArray(a: Array<Int>, b: Array<Int>, i: Int, start: Int, end: Int): Unit =
    if (i < start) {
        val i1 = i + 1
        editElementsArrayByArray(a,b,i1,start,end)
    } else {
        if (i == end) a[end] = b[i - start] else {
            val i1 = i + 1
            a[i] = b[i - start]
            editElementsArrayByArray(a,b,i1,start,end)
        }
    }
fun reverseElBetweenMinAndMax(a: Array<Int>): Unit =
    run {
        val min = minElArray(a)
        val max = maxElArray(a)
        reverseElBetweenMinAndMax(a,min,max,0)
    }
fun reverseElBetweenMinAndMax(a: Array<Int>, min: Int, max: Int, i: Int): Unit =
    run {
        val indMin = findIndex(a, i, min, 0)
        val indMax = findIndex(a, i, max, 0)
        if (indMin > -1 && indMax > -1) {
            if (indMin < indMax - 2) {
                val b: Array<Int> = a.copyOfRange(indMin + 1, indMax)
                b.reverse()
                editElementsArrayByArray(a, b, 0, indMin + 1, indMax - 1)
                reverseElBetweenMinAndMax(a, min, max, indMax)
            } else {
                val i1 = max(indMin, indMax)
                reverseElBetweenMinAndMax(a, min, max, i1)
            }
        }
    }

// 4_22
// Находим минимум из интервала.
fun minFromInterval(x: Array<Int>, a: Int, b: Int): Int = minFromInterval(x,0,a,b)
tailrec fun minFromInterval(x: Array<Int>, i: Int, a: Int, b: Int): Int =
    if (i == x.size) throw IllegalArgumentException("Ошибка!") else {
        if (x[i] > a && x[i] < b) minFromInterval(x,0,a,b,x[i]) else {
            val i1 = i + 1
            minFromInterval(x,i1,a,b)
        }
    }
tailrec fun minFromInterval(x: Array<Int>, i: Int, a: Int, b: Int, min: Int): Int =
    if (i == x.size) min else {
        if (x[i] > a && x[i] < b) {
            val min1 = min(min,x[i])
            val i1 = i + 1
            minFromInterval(x,i1,a,b,min1)
        } else {
            val i1 = i + 1
            minFromInterval(x,i1,a,b,min)
        }
    }
// Считаем, сколько раз встречается минимум из интервала в массиве.
fun countMinFromInterval(x: Array<Int>, a: Int, b: Int): Int =
    run {
        val min = minFromInterval(x, a, b)
        countMinFromInterval(x, min)
    }
fun countMinFromInterval(x: Array<Int>, min: Int): Int = x.count { it == min }

// 4_24
fun findTwoMax(a: Array<Int>): Array<Int> =
    run {
        val max = Array<Int>(2){0}
        max[0] = a.maxOrNull() ?: 0
        max[1] = a.minOrNull() ?: 0
        findTwoMax(a,max,0)
        max
    }
fun findTwoMax(a: Array<Int>, max: Array<Int>, i: Int): Unit =
    run {
        if (i != a.size) {
            if (a[i] > max[1] && a[i] < max[0]) max[1] = a[i]
            val i1 = i + 1
            findTwoMax(a, max, i1)
        }
    }