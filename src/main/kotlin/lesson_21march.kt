import java.lang.Math.pow

fun main() {
    println("\n21.03.2023 \n")
    println("\n(1)")
    var sfu = readLine()
    println(sfu)

    println("\n(2)")
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

    println("\n(3)")
    var num1 = 10
    var num2 = 9
    val num3 = if(num1 > num2) num1 else num2
    println(num3)

    println("\n(4)")
    val num4 = if(num1 > num2){
        num1 } else {
            num2
        }
    println(num4)

    println("\n(5)")
    val IsOn = true
    when(IsOn){
        true -> println("On")
        false -> println("Off")
    }

    println("\n(6)")
    val IsOn2 = 16
    when(IsOn2){
        20 -> println("On")
        30 -> println("Off")
        else -> println("error")
    }

    println("\n(7)")
    val IsOn3 = 3
    when(IsOn3){
        20, 3 -> println("On")
        30 -> println("Off")
        else -> println("error")
    }

    println("\n(8)")
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

    println("\n(9)")
    var i = 0
    while (i < 4){
        println(i)
        i++
    }

    println("\n(10)")
    i = 0
    do{
        println("hello")
        i++
    } while (i<-1)

    println("\n(11)")
    i = 0
    var i1 = i++
    println(i1)

    println("\n(12)")
    for (n in 1..9){
        if (n==3) continue
        println(n*2)
    }


    println("\n(13)")
    val arr1 = intArrayOf(1, 2, 3)
    println(arr1[0])
    println(arr1.size)

    println("\n(14)")
    for (index in arr1.indices){
        println(arr1[index])
    }

    println("\n(15)")
    val arr2 = intArrayOf(4, 5, 6)
    val arr3 = arr1 + arr2
    println(arr3[5])

    println("\n(16)")
    var arr4 = arrayOfNulls<String>(3)//[null, null, null]

    println("\n(17)")
    var arr5 = emptyArray<String>()
    arr5 += "1"

    println("\n(18)")
    var arr6 = Array(5, {i -> i*2})
    for (el in arr6){
        println(el)
    }
    println(arr6[2])

    println("\n(19)")
    println(arr6.contentToString())

    println("\n(20)")
    var arr7 = arrayOf(4, 8, 2, 1, 9)
    arr7.contentToString()
    var arr8 = arr7.sort()
    println(arr8)

}

