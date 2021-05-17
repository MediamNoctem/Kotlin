import java.io.File
import kotlin.math.pow

fun main() {
    /*val file1 = File("C:\\Users\\Anastasia\\Documents\\GitHub\\Kotlin\\Lab13\\names.txt").readLines()
    println("22: " + p22(file1[0]))
    val file2 = File("C:\\Users\\Anastasia\\Documents\\GitHub\\Kotlin\\Lab13\\words.txt").readLines()
    println("42: " + p42(file2[0]))*/
    println("62: " + p62())
}
// 22
// Считаем количество запятых.
tailrec fun countCommas(s: String, ind: Int, count: Int): Int =
    if (ind == s.length) count else {
        if (s[ind] == ',') {
            val ind1 = ind + 1
            val count1 = count + 1
            countCommas(s, ind1, count1)
        } else {
            val ind1 = ind + 1
            countCommas(s, ind1, count)
        }
    }
// Выделяем слово из строки.
tailrec fun getWord(s: String, i: Int, w: String): String =
    when(s[i]) {
        ',' -> w
        else -> if(s[i] != '"' && s[i] != ',') {
            val w1 = w + s[i]
            val i1 = i + 1
            getWord(s,i1,w1)
        }
        else {
            val i1 = i + 1
            getWord(s,i1,w)
        }
    }
// Добавляем к массиву еще один элемент.
tailrec fun fillArray(arr1: Array<String>, arr2: Array<String>, i: Int, n: Int, s: String): Unit =
    if (n - i == 1) {
        arr2[i] = s
    } else {
        arr2[i] = arr1[i]
        val i1 = i + 1
        fillArray(arr1,arr2,i1,n,s)
    }
// Создаем массив слов по строке.
tailrec fun makeArrayString(arr: Array<String>, i: Int, n: Int, s: String): Array<String> =
    if (i == n) arr else {
        val i1 = i + 1
        var arr1 = Array<String>(i1){""}
        val w = getWord(s,0,"")
        val s1 = s.drop(w.length + 3)
        fillArray(arr,arr1,0,i1,w)
        makeArrayString(arr1,i1,n,s1)
    }
// Вычислим алфавитное значение для имени.
tailrec fun alphabeticalValue(s: String, i: Int, cur: Int): Int =
    if (i == s.length) cur else {
        val i1 = i + 1
        val cur1 = cur + s.codePointAt(i) - 64
        alphabeticalValue(s, i1, cur1)
    }
tailrec fun sumAlphabetValue(arr: Array<String>, i: Int, n: Int, cur: Long): Long =
    if (n == i) cur else {
        val cur1 = cur + alphabeticalValue(arr[i],0,0)*(i + 1)
        val i1 = i + 1
        sumAlphabetValue(arr,i1,n,cur1)
    }
fun p22(s0: String): Long =
    when (s0) {
        "" -> 0
        else -> {
            val s = "$s0,"
            val n = countCommas(s, 0, 0)
            var arr = Array<String>(n) { "" }
            arr = makeArrayString(arr, 0, n, s)
            arr.sort()
            sumAlphabetValue(arr, 0, n, 0)
        }
    }
// 42
// Вычислим треугольное число по номеру.
fun triangleNumber(n: Int): Int =
    if (n <= 0) throw IllegalArgumentException("Ошибка!") else {
        val t = 0.5 * n * (n + 1)
        t.toInt()
    }
fun checkTriangleNum(t: Int) = checkTriangleNum(t,1)
tailrec fun checkTriangleNum(t: Int, i: Int): Boolean =
    if (triangleNumber(i) < t) {
        val i1 = i + 1
        checkTriangleNum(t,i1)
    } else t == triangleNumber(i)

tailrec fun countTriangleNum(arr: Array<String>, i: Int, n: Int, cur: Int): Int =
    if (i == n) cur else {
        val t = alphabeticalValue(arr[i],0,0)
        val cur1 = if (checkTriangleNum(t)) cur + 1 else cur
        val i1 = i + 1
        countTriangleNum(arr,i1,n,cur1)
    }

fun p42(s0: String): Int =
    when(s0) {
        "" -> 0
        else -> {
            val s = "$s0,"
            val n = countCommas(s, 0, 0)
            var arr = Array<String>(n) { "" }
            arr = makeArrayString(arr, 0, n, s)
            countTriangleNum(arr, 0, n, 0)
        }
    }

// 62
fun cubeRoot(n: Long): Long = Math.cbrt(n.toDouble()).toLong()
// Составляем массив, где номер элемента - цифра числа,
// а значение элемента - сколько раз встречается эта цифра в числе.
fun arrayByNum(n: Long): Array<Byte> =
    run {
        val a = Array<Byte>(10){0}
        arrayByNum(n,a)
    }
tailrec fun arrayByNum(n: Long, a: Array<Byte>): Array<Byte> =
    if (n == 0L) a else {
        val i = (n % 10).toInt()
        a[i]++
        val n1 = n / 10
        arrayByNum(n1, a)
    }
// Составляем минимальное число, которое только можно
// составить из данного массива цифр.
fun makeMinNum(a: Array<Byte>): Long =
    run {
        val a1 = a.clone()
        makeMinNum(a1,0,1,9)
    }
tailrec fun makeMinNum(a: Array<Byte>, n: Long, p: Long, i: Int): Long =
    if (i == -1) n else {
        if (a[i] != 0.toByte()) {
            val n1 = n + p * i
            a[i]--
            val p1 = p * 10
            makeMinNum(a, n1, p1, i)
        } else {
            val i1 = i - 1
            makeMinNum(a, n, p, i1)
        }
    }
// Составляем максимальное число, которое только можно
// составить из данного массива цифр.
fun makeMaxNum(a: Array<Byte>): Long =
    run {
        val a1 = a.clone()
        makeMaxNum(a1,0,1,0)
    }
tailrec fun makeMaxNum(a: Array<Byte>, n: Long, p: Long, i: Int): Long =
    if (i == a.size) n else {
        if (a[i] != 0.toByte()) {
            val n1 = n + p * i
            a[i]--
            val p1 = p * 10
            makeMaxNum(a, n1, p1, i)
        } else {
            val i1 = i + 1
            makeMaxNum(a, n, p, i1)
        }
    }
// Проверяем равенство массивов.
tailrec fun checkEqualityArrays(a: Array<Byte>, b: Array<Byte>, i: Int): Boolean =
    if (i == 10) true else {
        if (a[i] != b[i]) false else {
            val i1 = i + 1
            checkEqualityArrays(a,b,i1)
        }
    }
// Считаем количество перестановок.
tailrec fun countPerm(start: Long, end: Long,a: Array<Byte>, count: Int): Int =
    if (start > end) count else {
        val n = start * start * start
        val b = arrayByNum(n)
        val c = if (checkEqualityArrays(a,b,0)) count + 1 else count
        val s1 = start + 1
        countPerm(s1, end, a, c)
    }
fun p62(): Long = p62(4,0)
tailrec fun p62(i: Long, count: Int): Long =
    if (count == 5) i*i*i else {
        val i1 = i + 1
        val n = i1*i1*i1
        val a = arrayByNum(n)
        val min = cubeRoot(makeMinNum(a))
        val max = cubeRoot(makeMaxNum(a))
        val c = countPerm(min,max,a,0)
        p62(i1,c)
    }