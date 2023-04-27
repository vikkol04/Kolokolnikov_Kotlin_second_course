import org.intellij.lang.annotations.JdkConstants.InputEventMask
// import user.Const - это подключается, если создан пакет user и object Const (отдельный файл)
import java.lang.IllegalArgumentException
import user.User10

fun main() {
    println("\n11.04.2023 \n")
    var ch = readLine()

    //
    if (ch == "1"){
        class User1
        val user1: User1 = User1()
        println(user1::class.simpleName)
    }//
    else if(ch == "2"){
        class User2 {
            var name = "hhh"
        }

        val user2: User2 = User2()
        user2.name = "ttt"
        println(user2.name)
    }//
    else if(ch == "3"){
        class User3 {
            var name = "name"
        }

        val user3: User3 = User3()
        user3.name = "Mariia"
        println(user3.name)
    }//
    else if(ch == "4"){
        class User4 {
            var name = "name"
            fun sayHello(message: String){
                println("$message")
            }
        }

        val user4: User4 = User4()
        user4.name = "Mariia"
        println(user4.name)
        user4.sayHello("hello kotlin")
    }//
    else if(ch == "5"){
        class User5 {
            var name = "name"
            fun sayHello(message: String){
                println("$message")
                name = "Tom"
                println(name)
            }
        }

        val user5: User5 = User5()
        user5.name = "Mariia"
        println(user5.name)
        user5.sayHello("hello kotlin")
    }// для Java
    else if(ch == "6"){
        class Person6{
            var name: String
            var age: Int
            constructor(name: String, age: Int){
                this.name = name
                this.age = age
            }
        }
    }//
    else if(ch == "7"){
        class Person7 constructor(name: String, age: Int){
            val name: String
            var age: Int

            init{
                this.name = name
                this.age = age
            }
        }

        val person7 = Person7("person7", 12)
        val person7_2 = Person7("person7_2", 100)
        println(person7.name)
    }//
    else if(ch == "8"){
        class Person8 constructor(val name: String = "Name", var age: Int)

        val person8 = Person8("person7", 12)
        val person8_2 = Person8(age = 100)
        println(person8_2.age)
    }//
    else if(ch == "9"){
        class User9(val name: String){
            var email = "email"
                set(value) {
                    if (!value.isNotEmpty()) {
                        throw IllegalArgumentException("field is empty")
                    }
                    field = value
                }
            var pass = "vsdf"
                get(){
                    return field.toUpperCase()
                }
        }

        val user9 = User9("Tom")
        user9.email = "email"
        println(user9.email)
        println(user9.pass)

    }// этот же класс User10 ещё импортируется из из User_11april.kt
    else if(ch == "10"){
        class User10(val name: String){
            var email = ""
            var isOnline: Boolean? = null

            constructor(name: String, email: String): this(name){
                this.email = email
            }
            constructor(name: String, email: String, isOnline: Boolean): this(name, email){
                this.isOnline = isOnline
            }
        }

        val us10 = User10("Tom")
        val us10_2 = User10("Nika", "email")
        val us10_3 = User10("Nika", "email", true)
        println(us10.email) //ничего
        println(us10_2.email)
        println(us10_3.isOnline)



    }//
    else if(ch == "11"){
    //    println(Const.baseEndpoint) смотреть вверху

    }

    else{
        println("Выбор не осуществлён.")
    }


}

