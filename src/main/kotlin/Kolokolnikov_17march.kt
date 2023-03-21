import java.lang.Math.pow

fun main(){
    println("\nКолокольніков на 17.03.2023 \n")
    /*
        Дано номер телефону: "+38(099)519-54-70"
        1. Вивести в консоль номер без коду країни
            (у такому форматі: (ххх)ххх-хх-хх ): "Номер без коду країни: [результат]"
        2. Вивести в консоль код оператора (099) без дужок: "Номер коду оператора: [результат]"
        3. Вивести в консоль номер без коду країни та оператора
            (у такому форматі: ххххххх - без розділових дефісів): "Номер без коду країни та оператора: [результат]"
        4. Порахувати кількість цифр у номері(попередньо) видаливши усі зайві символи зі строки,
            а саме "+", "(", ")", "-". Результат вивести в консоль: "Кількість цифр у номері: [результат]"
        5. Порівняти першу та передостанню цифру номера телефону, результат вивести у консоль
        6. Знайти середнє арифметичне усіх чисел номера та піднести отримане число до степеня "2/3".
            Отриманий результат вивести у консоль
     */

    var ph_num = "+38(099)519-54-70"
    println("Дано номер телефона ${ph_num}\n")

    println("1. Номер без коду країни: ${ph_num.removePrefix("+38")}\n")
    // Або: println("1. Номер без коду країни: ${ph_num.substring(3)}\n")

    println("2. Номер коду оператора: ${ph_num.substringAfter("(").substringBefore(")")}\n")
    // Або: println("2. Номер коду оператора: ${ph_num.substring(4, 7)}\n")

    println("3. Номер без коду країни та оператора: ${ph_num.drop(8).replace("-", "")}\n")

    println("4. Кількість цифр у номері: ${(ph_num.substring(1, 3) + ph_num.substring(4, 7) + ph_num.substring(8).replace("-", "")).length}\n")

    var first: Int = ph_num.drop(1).dropLast(ph_num.length-2).toInt()
    var al_last: Int = ph_num.drop(ph_num.length-2)[0].toString().toInt()
    var comparison: String
    when (first > al_last) {
        true -> comparison = "більше"
        else -> { if (first < al_last) {
                      comparison = "менше"
                  } else {
                        comparison = "дорівнює"
                    }
        }
    }
    println("5. Перша цифра ${first} ${comparison} ${al_last}, яка передостання.\n")

    var num = ph_num.substring(1, 3) + ph_num.substring(4, 7) + ph_num.substring(8).replace("-", "")
    var len_num = num.length
    var sum = 0
    for( i in num) {
        sum += i.toString().toInt()
    }
    // Або: num.forEach { char -> sum += char.toString().toInt() }
    var average: Double = (sum / len_num).toDouble()
    var result = pow(average, 2.0/3)

    println("6. Середнє арифметичне номера у степені 2/3: ${result}\n")
    println("(Сума ${sum}, середнє. арифм. ${average})")

}