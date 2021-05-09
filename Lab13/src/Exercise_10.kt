import java.io.File
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
fun main() {
    val file = File("C:\\Users\\Anastasia\\Documents\\GitHub\\Kotlin\\Lab13\\names.txt").readLines()
    val nameScores = p1(file[0])
    println(nameScores)
}