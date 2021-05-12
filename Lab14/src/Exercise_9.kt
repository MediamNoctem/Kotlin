import java.io.File
fun main() {
    /*val file1 = File("C:\\Users\\Anastasia\\Documents\\GitHub\\Kotlin\\Lab13\\names.txt").readLines()
    println("22: " + p1(file1[0]))
    val file2 = File("C:\\Users\\Anastasia\\Documents\\GitHub\\Kotlin\\Lab13\\words.txt").readLines()
    println("42: " + p2(file2[0]))*/
    println("62: " + p3())
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
fun p1(s0: String): Long =
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

fun p2(s0: String): Int =
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
// Перестановка.
fun cond1(a: Array<Byte>,n: Int): Int = cond1(a,n,n-2)
tailrec fun cond1(a: Array<Byte>,n: Int,j: Int): Int =
    if (j > -1 && a[j] >= a[j+1]) {
        val j1 = j - 1
        cond1(a,n,j1)
    } else j
fun cond2(a: Array<Byte>,n: Int,j: Int): Int = cond2(a,n,n-1,j)
tailrec fun cond2(a: Array<Byte>,n: Int,k: Int,j: Int): Int =
    if (a[j] >= a[k]) {
        val k1 = k - 1
        cond2(a,n,k1,j)
    } else k
tailrec fun cond3(a: Array<Byte>,l: Int,r: Int): Array<Byte> =
    if (l < r) {
        val tmp = a[l]
        a[l] = a[r]
        a[r] = tmp
        val l1 = l + 1
        val r1 = r - 1
        cond3(a,l1,r1)
    } else a
fun nextPermutation(a: Array<Byte>, n: Int): Array<Byte> =
    run {
        val j = cond1(a, n)
        if (j <= -1) emptyArray<Byte>() else {
            val k = cond2(a, n, j)
            var tmp = a[j]
            a[j] = a[k]
            a[k] = tmp
            var l = j + 1
            var r = n - 1
            cond3(a, l, r)
        }
    }
// Число по массиву.
fun numByArray(a: Array<Byte>): Long = numByArray(a,a.size - 1,1,0)
tailrec fun numByArray(a: Array<Byte>, i: Int, p: Long, cur: Long): Long =
    if (i < 0) cur else {
        val p1 = p * 10
        val i1 = i - 1
        val cur1 = cur + a[i] * p
        numByArray(a,i1,p1,cur1)
    }
// Считаем количество цифр в числе.
fun countDigits(n: Long): Int = countDigits(n,0)
tailrec fun countDigits(n: Long, c: Int): Int =
    if (n <= 0) c else {
        val c1 = c + 1
        val n1 = n / 10
        countDigits(n1,c1)
    }
// Массив по числу.
fun arrayByNum(num: Long): Array<Byte> =
    when {
        num >= 0 -> {
            val size = countDigits(num)
            var a = Array<Byte>(size){0}
            a = arrayByNum(a,num,size,0)
            a.reversedArray()
        }
        else -> throw IllegalArgumentException("Ошибка!")
    }
tailrec fun arrayByNum(a: Array<Byte>,num: Long,size: Int, i: Int): Array<Byte> =
    when (i) {
        in 0 until size -> {
            a[i] = (num % 10).toByte()
            val num1 = num / 10
            val i1 = i + 1
            arrayByNum(a,num1,size,i1)
        }
        else -> a
    }
// Проверка, является ли число кубом.
fun checkCube(n: Long): Boolean = checkCube(n,1,1)
tailrec fun checkCube(n: Long,i: Long,cube: Long): Boolean =
    if (cube < n) {
        val i1 = i + 1
        val cube1 = i1*i1*i1
        checkCube(n,i1,cube1)
    }
    else cube == n
tailrec fun p3_1(perm: Array<Byte>,num: Long, count: Int): Int =
    if (perm.isNotEmpty()) {
        val count1 = if (checkCube(num)) count + 1 else count
        val perm1 = nextPermutation(perm, perm.size)
        val num1 = numByArray(perm1)
        if (count1 > 5) count1 else p3_1(perm1,num1,count1)
    } else count
tailrec fun p3_2(count: Int,i:Long): Long =
    if (count != 5) {
        val i1: Long = i + 1
        val t = i1*i1*i1
        val perm = arrayByNum(t)
        perm.sort()
        val num: Long = numByArray(perm)
        val count1 = p3_1(perm,num,0)
        p3_2(count1,i1)
    } else i*i*i
fun p3(): Long = p3_2(0,4)