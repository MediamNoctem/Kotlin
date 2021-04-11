fun hello(name: String){
    println("Hello, $name!")
}
fun main(){
    val name = readLine()
    hello("$name")
    println("What is your favorite language?")
    println("1. C++")
    println("2. C#")
    println("3. Java")
    println("4. Kotlin")
    println("5. Prolog")
    val i = readLine()

    when (i) {
        "1","2","3" -> println("Okay, you aren't toady.")
    }
    if (i == "4" || i == "5") println("You are toady!")
}