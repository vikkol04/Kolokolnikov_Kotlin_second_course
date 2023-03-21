import java.lang.Math.pow

fun main() {
    println("\nКолокольніков на 21.03.2023 \n")
    var sfu = readLine()
    println(sfu)

    var num = 10
    if (num == 9){
        println(2)
    }
    else if(num == 10){
        println("10")
    }
    else{
        println("3")
    }

    var num1 = 10
    var num2 = 9
    val num3 = if(num1 > num2) num1 else num2
    println(num3)

    /*
    val num3 = if(num1 > num2){
        num1 else num2
    }
    println(num3)
    */

    val IsOn = true
    when(IsOn){
        true -> println("On")
        false -> println("Off")
    }

    val IsOn2 = 16
    when(IsOn2){
        20 -> println("On")
        30 -> println("Off")
        else -> println("error")
    }

    val IsOn3 = 3
    when(IsOn3){
        20, 3 -> println("On")
        30 -> println("Off")
        else -> println("error")
    }

    val x = 5
    var y = 10
    var z = 5
    when(x){
        y-z -> println("x is equal to 5")
        y+z -> println("x is equal to 15")
    }
    when{
        (x < y) -> println("x<y")
        (y > z) -> println("x>y")
    }

    for (i in 1 .. 4){
        for(j in 1..4){
            print("${i*j} \t")
        }
        println()
    }

    var i = 0
    while (i < 4){
        println(i)
        i++
    }

    i = 0
    do{
        println("hello")
        i++
    } while (i<-1)

    i = 0
    var i1 = i++
    println(i1)

    for (n in 1..9){
        if (n==3) continue
        println(n*2)
    }


    val arr1 = intArrayOf(1, 2, 3)
    println(arr1[0])
    println(arr1.size)

    for (index in arr1.indices){
        println(arr1[index])
    }

    val arr2 = intArrayOf(4, 5, 6)
    val arr3 = arr1 + arr2
    println(arr3[5])

    var arr4 = arrayOfNulls<String>(3)//[null, null, null]

    var arr5 = emptyArray<String>()
    arr5 += "1"

    var arr6 = Array(5, {i -> i*2})
    for (el in arr6){
        println(el)
    }
    println(arr6[2])

    println(arr6.contentToString())

    var arr7 = arrayOf(4, 8, 2, 1, 9)
    arr7.contentToString()
    var arr8 = arr7.sort()
    println(arr8)

}

