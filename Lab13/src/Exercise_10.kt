import java.io.File
// 10_1
// Считаем количество запятых.
tailrec fun countCommas(s: String, ind: Int, count: Int): Int {
    if (ind == s.length) return count
    return if (s[ind] == ',') {
        val ind1 = ind + 1
        val count1 = count + 1
        countCommas(s, ind1, count1)
    }
    else {
        val ind1 = ind + 1
        countCommas(s, ind1, count)
    }
}
// Создаем массив слов по строке.
fun makeArray(s0: String): Array<String> {
    val s = "$s0,"
    var t = 0
    var str: String
    val n = countCommas(s,0, 0) - 1
    val arr = Array<String>(n+1){""}
    for (i in 0..n) {
        str = ""
        // Собираем слово.
        while(s[t] != ',') {
            if (s[t] != '"' && s[t] != ',') str += s[t]
            t++
        }
        t++
        // Добавляем слово в массив.
        arr[i] = str
    }
    return arr
}
// Вычислим алфавитное значение для имени.
fun alphabeticalValue(s: String, i: Int): Int {
    if (i == s.length - 1) return s.codePointAt(i) - 64
    val i1 = i + 1
    return s.codePointAt(i) - 64 + alphabeticalValue(s,i1)
}
fun p1(s: String): Long {
    var sum: Long = 0
    var arr = makeArray(s)
    arr.sort()
    arr.forEach {
        sum += alphabeticalValue(it,0)*(arr.indexOf(it) + 1)
    }
    return sum
}
// 10_2
// Вычислим треугольное число по номеру.
fun triangleNumber(n: Int): Int {
    val t = 0.5*n*(n+1)
    return t.toInt()
}
fun checkTriangleNum(t: Int): Boolean {
    var i = 1
    while (triangleNumber(i) < t) i++
    return t == triangleNumber(i)
}
fun p2(s: String): Int {
    var c = 0
    val arr = makeArray(s)
    arr.forEach {
        val t = alphabeticalValue(it,0)
        if (checkTriangleNum(t)) c++
    }
    return c
}
// 10_3
// Перестановка.
fun nextPermutation(a: Array<Int>, n: Int): Array<Int> {
    var j = n-2
    while (j > -1 && a[j] >= a[j+1]) j--
    if (j <= -1) return emptyArray()
    var k = n - 1
    while (a[j] >= a[k]) k--
    var tmp = a[j]
    a[j] = a[k]
    a[k] = tmp
    var l = j+1
    var r = n - 1
    while (l < r) {
        tmp = a[l]
        a[l] = a[r]
        a[r] = tmp
        l++
        r--
    }
    return a
}
// Число по массиву.
fun numByArray(a: Array<Int>): Long {
    val size = a.size
    var i = size - 1
    var p = 1
    var num: Long = 0
    while (i >= 0) {
        num += a[i]*p
        p*=10
        i--
    }
    return num
}
// Массив по числу.
fun arrayByNum(num: Int): Array<Int> {
    var size = 0
    var n = num
    while (n > 0) {
        size++
        n = n.div(10)
    }
    var a = Array<Int>(size){0}
    n = num
    for (i in 0 until size) {
        a[i] = n.mod(10)
        n = n.div(10)
    }
    return a.reversedArray()
}
// Проверка, является ли число кубом.
fun checkCube(n: Long): Boolean {
    var i: Long = 0
    var cube: Long = 0
    while (cube < n) {
        i++
        cube = i*i*i
    }
    return cube == n
}
fun p3(): Int {
    var count = 0
    var i = 4
    while (count != 5) {
        i++
        var perm = arrayByNum(i * i * i)
        perm.sort()
        var num: Long = numByArray(perm)
        count = 0
        while (perm.isNotEmpty()) {
            if (checkCube(num)) {
                count++
            }
            perm = nextPermutation(perm, perm.size)
            num = numByArray(perm)
            if (count > 5) break
        }
    }
    return i
}
fun main() {
    val file1 = File("C:\\Users\\Anastasia\\Documents\\GitHub\\Kotlin\\Lab13\\names.txt").readLines()
    println("10.1: " + p1(file1[0]))
    val file2 = File("C:\\Users\\Anastasia\\Documents\\GitHub\\Kotlin\\Lab13\\words.txt").readLines()
    println("10.2: " + p2(file2[0]))
    println("10.3: " + p3())
}