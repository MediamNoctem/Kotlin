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
fun main() {
    val file1 = File("C:\\Users\\Anastasia\\Documents\\GitHub\\Kotlin\\Lab13\\names.txt").readLines()
    println("10.1: " + p1(file1[0]))
    val file2 = File("C:\\Users\\Anastasia\\Documents\\GitHub\\Kotlin\\Lab13\\words.txt").readLines()
    println("10.2: " + p2(file2[0]))
}