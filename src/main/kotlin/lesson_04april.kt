import java.lang.Math.pow
import kotlin.random.Random

fun main() {
    println("\n04.04.2023 \n")

    println("\n(1)")
    fun sum12(x: Int, y: Int): Int{
        return x+y
    }
    fun sum(x: String, y: String): String{
        return x+y
    }
    fun sum(x: Int, y: Int, z: Int): Int{
        return x+y+z
    }
    println(sum12(2, 4))
    println(sum("2", "3"))
    println(sum(3, 4, 5))

    println("\n(2)")
    val greeting = { println("jello")}
    greeting()

    println("\n(3)")
    val greeting_3: () -> Unit = { println("hello_3")}
    greeting_3()


    println("\n(5)"); //точка с запятой отделяет лямбду
    { println("hello_5") }()

    println("\n(6)")
    val message = {mes: String -> println(mes)}
    message("hello!")

    println("\n(7)")
    val sum_7 = {x: Int, y: Int ->
        val res = x+y
        println(res)
    }


    println("\n(8)")
    val sum_8 = {x: Int, y: Int -> x+y}
    println(sum_8(2, 3))


    println("\n(9)")
    val sum_9 = {x: Int, y: Int ->
        x+y
        println("result")
    }
    println(sum_9(2, 3))


    println("\n(10)")
    val sum_10 = {x: Int, y: Int -> x*y}
    fun doMathOperation(x: Int, y: Int, op: (Int, Int) -> Int){
        val result = op(x, y)
        println(result)
    }


    println("\n(11)")
    doMathOperation(2, 3, {x, y -> x*y})
    doMathOperation(2, 3){x, y -> x*y}

    println("\n(12)")
    fun doMathOperation12(key: String): (Int, Int) -> Int{
        when(key){
            "sum12" -> return {x, y -> x+y}
            "multiply12" -> return {x, y -> x*y }
            else -> return {x, y -> 0}
        }
    }
    val op12 = doMathOperation12("sum12")
    val res12 = op12(2, 3)

    println("\n(13)")
    // let, run, with, apply, also
    data class User(val name: String, var email: String?)
    val user13 = User("User13", "email@gmail.com")
    if(user13.email!=null){
        println(user13.email)
    }
    user13.email?.let{ println(it)}


    println("\n(14)")
    user13?.let{ println(it.email)}



    println("\n(15)")
    user13.email?.let( ::println )

    println("\n(16)")
    user13?.let( ::println )

    println("\n(17)")
    val user17 = User("User17", null)
    val user17Email = with(user17){
        if(email == null){
            email = "$name@gmail.com"
        }
        email
    }
    println(user17.email)
    println(user17Email)

    println("\n(18)")
    data class User2(var name: String ="", var email: String="", var age: Int = 0){
        fun name(_name: String): User2 = apply { name = _name }

        fun email(_email: String): User2 = apply { email = _email }
        fun age(_age: Int): User2 = apply { age = _age }
    }
    val user18 = User("User18", null)
    val u18 = User2()
    u18.name("Boris")


}

