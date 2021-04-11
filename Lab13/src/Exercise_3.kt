fun hello(name: String){
    println("Hello, $name!")
}
fun main(){
    val name = readLine()
    hello("$name")
}