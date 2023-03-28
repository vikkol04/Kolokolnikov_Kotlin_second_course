import java.lang.Math.pow
import kotlin.random.Random

fun main() {
    println("\n28.03.2023 \n")

    println("\n(1)")
    var arr1 = intArrayOf(1, 3, 5)

    println("\n(2)")
    var arr1_2 = IntArray(3)
    println(arr1_2.joinToString())
    println(arr1_2.contentToString())

    println("\n(3)")
    var arr1_3 = IntArray(3) {i -> i+1}
    println(arr1_3.contentToString())
    var arr1_4 = IntArray(3) {i -> i*1}
    println(arr1_4.contentToString())

    println("\n(4)")
    val arr1_5 = intArrayOf(1, 2, 3, 4, 5)
    for ( (index, value ) in arr1_5.withIndex()){
        println("$index $value")
    }

    println("\n(5)")
    val arr1_6 = intArrayOf(1, 2, 3, 4, 5)
    var reverseArr1_6 = arr1_6.reversedArray()
    println(reverseArr1_6.contentToString())

    println("\n(6)")
    val arr1_7 = intArrayOf(1, 2, 3, 4, 5)
    var reverseArr1_7 = arr1_6.reversedArray()
    println(reverseArr1_7.contentToString())

    println("\n(7)")
    val arr1_8 = intArrayOf(1, 5, 7, 9, 3)
    arr1_8.sort()
    println(arr1_8.contentToString())

    println("\n(8)")
    val arr1_9 = intArrayOf(1, 5, 7, 9, 3)
    arr1_9.sortDescending()
    println(arr1_9.contentToString())

    println("\n(9)")
    val arr1_10 = intArrayOf(1, 5, 7, 9, 3)
    var sortedArr1_10 = arr1_10.sortedArray()
    var DescendingsoredArr1_10 = arr1_10.sortDescending()

    println("\n(10)")
    data class Car(
        val name: String,
        val age: Int,
        val hp: Int
    )
    val carsArr = arrayOf(Car("gtr r34", 20, 290), Car("supra", 25, 310), Car("silvia", 35, 210))
    carsArr.forEach { println(it) }
    carsArr.sortWith(Comparator { c1: Car, c2: Car -> c1.hp - c2.hp })
    carsArr.forEach { println(it) }

    println("\n(11)")
    val arrayOfArrs = Array(4){i -> arrayOf(i+i, i+i+1)}
    arrayOfArrs.forEach { e -> println(e.contentToString()) }

    println("\n(12)")
    arrayOfArrs.sortWith(Comparator {o1, o2 -> o2[0] - o1[0] })
    arrayOfArrs.forEach { e -> println(e.contentToString())}

    println("\n(13)")
    val arrayOfArrs_2 = Array(4){i -> arrayOf(i+i, i+i+1)}
    arrayOfArrs_2.onEach { e -> e+2 }

    println("\n(14)")
    var str = ""
    val arrayOfArrs_3 = arrayOf(1, 3, 5, 7)
    arrayOfArrs_3.onEach { str += it+1 }
    println(str)

    println("\n(15)")
    val array1 = arrayOf(1, 3, 5, 7)
    println(array1.contains(9))

    println("\n(16)")
    val array1_2 = arrayOf(1, 3, 5, 7)
    println(array1_2.average())

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

