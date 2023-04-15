import java.lang.Math.pow
import kotlin.random.Random


fun main() {
    println("\n03.04.2023 \n")

    println("\n(1)")
    fun hello() {
        println("hello")
        hello()
    }

    println("\n(2)")
    fun message(mes: String) {
        println("$mes")
    }
    message("hhhh")


    println("\n(3)")
    //fun sum(num1: Int, num2: Int()){
    /// /   prinln($"num1"" + num2)
    //}
    //sum(1, 2)
    ///

    ///println("\n(4)")
    //fun displayInfo(name: String, price: Int, sale: Boolean = false){
    //    println("Name is $name Price is $price")
    //}


    println("\n(5)")
    //

    println("\n(6)")
    fun displayInfo(nums: IntArray) {
        nums[0] = nums[1] + 1
    }

    println("\n(7)")
    fun displayInfo_7() {
        var string1 = "gggg"
        println(string1)
    }
    // println(string1) - нельзя
    displayInfo_7()

    println("\n(8)")
    fun sum_8(vararg numbers: Int) {
        var res = 0
        for (num in numbers) {
            res += num
        }
        println("sum is $res")
        println(res::class.java.typeName)
    }
    sum_8(1, 2, 4, 5, 7)

    println("\n(9)")
    fun sum_9(vararg numbers: Int, str1: String = "", ) {
        var res = 0
        for (num in numbers) {
            res += num
        }
        println("sum is $res")
        println(res::class.java.typeName)
    }
    sum_9(1, 2, 4, 5, 7, str1 = "hh")

    println("\n(10)")
    var numArr_10 = listOf<Int>(1, 3, 5, 8)
    var numArr2_10 = numArr_10.toIntArray()
    fun sum_10(vararg numbers: Int, str1: String = "", ) {
        var res = 0
        for (num in numbers) {
            res += num
        }
        println("sum is $res")
        println(res::class.java.typeName)
    }
    sum_10(*numArr2_10)

    println("\n(11)")
    var numArr_11 = listOf(1, 3, 5, 8)
    var numArr2_11 = numArr_11.toIntArray()
    fun sum_11(vararg numbers: Int, str1: String = "", ): Int {
        var res = 0
        for (num in numbers) {
            res += num
        }
        println("sum is $res")
        println(res::class.java.typeName)
        return res
    }
    sum_11(*numArr2_11)

    println("\n(12)")
    fun compareAge(personAge1: Int, personAge2: Int){
        fun isValid(age: Int): Boolean {
            return age > 0 && age < 124
        }
        if (!isValid(personAge1) || !isValid(personAge2)) {
            println("VIK NEVALIDNUY")
            return
        }
        when {
            personAge1 > personAge2 -> println("1 > 2")
            personAge1 < personAge2 -> println("1 < 2")
            personAge1 == personAge2 -> println("1 = 2")
        }
    }

    println("\n(13)")
    fun sum(x: Int, y: Int) = x+y
        val s1 = sum(2,6)
        println(s1)


    println("\n(14)")
    fun sum14(x: Int, y: Int): Int{
        return x+y
    }
    fun subtact14(x: Int, y: Int): Int{
        return x-y
    }
    var operation: (Int, Int) -> Int
    operation = ::sum
    var res = operation(3, 2)
    operation = ::subtact14
    var res2 = operation(3, 2)


    println("\n(15)")
    fun(x: Int, y: Int): Int {
        return x+y
    }
    val sum = fun(x: Int, y: Int): Int = x+y

    println("\n(16)")
    fun operation16(x: Int, y: Int, oper: (Int, Int) -> Int) {
        val res = oper(x, y)
    }
    println(res)
    val oper = fun(x: Int, y: Int): Int = x*y
    operation16(3, 2, oper)

    println("\n(17)")
    val array1_3 = arrayOf(1, 3, 5, 7)
    println(array1_3.sum()) //min, max

    println("\n(18)")
    val array1_4 = arrayOf(1, 1, 3, 5, 7, 10)
    val array1_5 = arrayOf(1, 1, 6, 8, 9, 10)
    val intersectedArr = array1_4.intersect(array1_5.toList())
    println(intersectedArr) // возврат set

    println("\n(19)")
    val array1_6 = arrayOf(1, 1, 3, 5, 7, 10)
    val array1_6Size = array1_6.size
    val randomNum = Random.nextInt(array1_6Size)
    val randValue = array1_6[randomNum]
    println(randValue)

    println("\n(20)")
    val array1_7 = arrayOf(1, 1, 3, 5, 7, 10)
    array1_7.shuffle()
    println(array1_7.contentToString())

    println("\n(21)")
    val array1_8 = arrayOf(1, 1, 3, 5, 7, 10, 1, 5, 7)
    println(array1_8.toSet())

    println("\n(22)")
    val array1_9 = arrayOf(1, 1, 3, 5, 7, 10, 1, 5, 7)
    val array1_10 = array1_9.distinct()
    println(array1_10)



// Списки
    println("\n(23)")
    var list1 = listOf("car1", "car2", "car3")
    list1 = list1 + "car4"
    println(list1)

    println("\n(24)")
    var list2 = mutableListOf("car1", "car2", "car3")
    list2.add("car4")
    println(list2)

    println("\n(25)")
    var list3 = mutableListOf("car1", "car2", "car3")
    val list4 = list3.toList() // из изменяемого в неизменяемый (ссылка на него)
    println(list3)

    println("\n(26)")
    var list5 = mutableListOf("car1", "car2", "car3")
    println(list5.getOrElse(7){"нету"})
    println(list5.getOrElse(7){list5[2]})
    println(list5.getOrNull(3)?: "нету")
    println(list5.size)
    for (e in list5){
        println(e)
    }

    println("\n(27)")
    var list6 = mutableListOf(8, 9, 7, 5)
    println(list6.any{it%2 == 0})
    println(list6.all{it%2 == 0})
    println(!list6.all{it%2 == 0})
    println(list6.none{it%2 == 0})
    println(list6.count{it%2 == 0})
    println(list6.fold(10){total, next -> total+next}) // 10+8; 18+9 ...
    println(list6.reduce{total, next -> total+next})
    list6.forEach{ println(it) }

    println("\n(28)")
    var list7 = mutableListOf(8, 8, 8)
    println(list7.maxOrNull())

    println("\n(29)")
    var list8 = mutableListOf(1, 2, 3)
    println(list8.sumBy {it +1})

    println("\n(30)")
    var list9 = listOf("167", "278", "Kolokol")
    println(list9.sumByDouble {it.length.toDouble()} )

    println("\n(31)")
    data class Car1(
        val name: String,
        val age: Int,
        val distance: Double
    )
    val cars = listOf(
        Car1("car1", 10, 70.5),
        Car1("car2", 12, 100.5),
        Car1("car3", 18, 23.7),
        Car1("car4", 6, 71.9)
    )
    val carsDistPerYear = cars.sumOf { it.distance/it.age }
    println(carsDistPerYear/cars.size)

    println("\n(32)")
    val list10 = listOf(1, 2, 3, 4, 5, 6)
    println(list10.drop(2))
    println(list10.dropLastWhile{it < 5})
    println(list10.take(3))
    println(list10.takeLast(3))

    println("\n(33)")
    var list11 = mutableListOf("car1", "car2", "car3")
    println(list11.takeIf {
        it.contains("car2")
    }.apply {
        this?.forEach {
            println("$it")
        }
    }
    )

    println("\n(34)")
    var list12 = mutableListOf("car1", "car2", "car3")
    println(list12.takeUnless {
        it.contains("car6")
    }.apply {
        this?.forEach {
            println("$it")
        }
    }
    )

    println("\n(35)")
    var list13 = listOf("car1", "car12", "car3")
    println(list13.filter { it[3] == '1' })

    println("\n(36)")
    // cм. второй data class Car1
    val filteredCars = cars.filter { it.age <= 12 }
    filteredCars.forEach{
        println("Name: ${it.name}, Age: ${it.age}, Distance ${it.distance}")
    }

    println("\n(37)")
    println( cars.first{it.age == 12})

    println("\n(38)")
    println( cars.indexOf(Car1("car1", 10, 70.5)) )

    println("\n(39)")
    println( cars.slice(listOf(1, 3)) )


}

