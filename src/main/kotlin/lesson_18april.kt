fun main() {
    println("\n18.04.2023 \n")
    var ch = readLine()
    //
    if (ch == "1"){
        open class Person1(val name: String){
            fun printName1(){
                println(name)
            }
        }

        class Employee1(empName: String): Person1(empName)

    }//
    else if(ch == "2"){
        open class Person2(val name: String){
            fun printName2(){
                println(name)
            }
        }

        class Employee2: Person2{
            constructor(empName: String): super(empName){}
        }

    }//
    else if(ch == "3"){

        open class Person3{
            val name: String
            constructor(persName: String){
                name = persName
            }
            fun printName(){
                println(name)
            }
        }
        class Employee3(empName: String): Person3(empName)
        val emp3 = Employee3("Name3")
        emp3.printName()

    }//
    else if(ch == "4"){
        open class Person4{
            val name: String
            constructor(persName: String){
                name = persName
            }
            fun printName(){
                println(name)
            }
        }
        class Employee4(empName: String, var position: String): Person4(empName){
            fun printPosition(){
                println(position)
            }
        }
        val emp4 = Employee4("Name4", "manager")
        emp4.printName()
        emp4.printPosition()
    }//
    else if(ch == "5"){
        open class Person5(val name: String){
            open var age: Int = 1
                set(value){
                    if (value in 1..123){
                        field = value
                    }
                }
            open fun showInfo(){
                println("Person Info: $name $age")
            }
        }
        open class Employee5(name: String): Person5(name){
            final override var age: Int = 20
                set(value){
                    if (value in 19..64){
                        field = value
                    }
                }
            override fun showInfo(){
                println("Employee Info: $name $age")
                println(super.age)
            }
        }

        val pers5 = Person5("Mariia")
        val empl5 = Employee5("Anna")
        pers5.age = 14
        empl5.age = 14
        println(pers5.age)
        println(empl5.age)
        pers5.showInfo()
        empl5.showInfo()


    }//
    else if(ch == "6"){
        abstract class Person6(val name: String){
            fun showInfo(){
                println("Info: $name")
            }
        }

        class Employee6(name: String): Person6(name)

        val em6: Employee6 = Employee6("Anna")
        em6.showInfo()

    }//
    else if(ch == "7"){
        abstract class Person7(val name: String){
            abstract var age: Int
            abstract fun showInfo()
        }

        class Employee7(name: String, override var age: Int): Person7(name){
            override fun showInfo(){
                println("Info: $name $age")
            }
        }

        val em7: Employee7 = Employee7("Anna", 23)
        em7.showInfo()
    }//
    else if(ch == "8"){
        val car8 = Vechicle()
        val plain8 = Aviation()
        car8.showInfo()
        plain8.showInfo()
        car8.move()

    }//
    else if(ch == "9"){
    val telegram = InstantChat("Telegram")
    val samsung = SmartPhone("S10+", telegram)
    samsung.send("my message")

    }//
    else if(ch == "10"){


    }//
    else if(ch == "11"){


    }//
    else if(ch == "12"){


    }//
    else if(ch == "13"){


    }//
    else if(ch == "14"){


    }//
    else if(ch == "15"){

    }

    else{
        println("Выбор не осуществлён.")
    }
}

//8
interface Transport{
    var speed: Int
    fun move(){
        println("Driving")
    }
    fun stop()
    fun showInfo()
}
class Vechicle: Transport{
    override var speed: Int = 120
    override fun stop(){
        println("Vehicle is stopped")
    }

    override fun showInfo() {
        println("Vehicle Info: $speed")
    }
}
class Aviation: Transport{
    override var speed: Int = 450
    override fun stop(){
        println("Plain is stopped")
    }

    override fun move() {
        println("Plain is flying")
    }
    override fun showInfo() {
        println("Aviation Info: $speed")
    }
}//8

//9
interface Messenger{
    fun send(message: String)
}

class InstantChat(val name: String): Messenger{
    override fun send(message: String){
        println("$message has been sent")
    }
}

class SmartPhone(val name: String, mes: Messenger): Messenger by mes

