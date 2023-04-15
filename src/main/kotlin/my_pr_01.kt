import java.lang.Math.pow
import kotlin.random.Random


fun main() {
    println("\n07.04.2023 \n")
    var ch = readLine()

    //as7
    if (ch == "1"){
        var f1:Float = 120.8f
        var f2 = 120.8
        var d1:Double = 120.8
        var d2 = 120.8
    //as8
    }else if(ch == "2"){
        var testBoolean: Boolean = true
        var testChar: Char = '/'
        var testBoolean2: Boolean = "Привет, как дела?".startsWith("Рарара") //false
        var testBoolean3 = "Привет, как дела?".endsWith("дела?") //false
        println(testBoolean2)
        println(testBoolean3)
    //as10
    }else if(ch == "3") {
        var v1 = 90
        var v2 = 100
        if (v1 > v2)
            println("$v1 > $v2")
        else
            println("$v1 !> $v2")
        when (v1) {
            in 0..v2 -> println("0 <= $v1 <= $v2")
            else -> println("Предел превышен")
        }
    }else if(ch == "4"){
            var v1 = 90

    }else{
        println("Выбор не осуществлён.")
    }


}

