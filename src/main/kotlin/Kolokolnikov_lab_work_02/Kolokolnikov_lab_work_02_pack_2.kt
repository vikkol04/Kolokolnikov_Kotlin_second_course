package Kolokolnikov_lab_work_02_pack

object Consts{
    /**
     * Объект, содержащий константы для игры в крестики нолики.
     * */
    const val X = "X"
    const val O = "O"
    val EMPTY_NUMBERS = listOf(
        "11", "12", "13",
        "21", "22", "23",
        "31", "32", "33",
    )

    const val TIE = "Ничья"
}

// Функции, для общего использования.
fun registration(playersList: MutableList<Player>){
    /**Собирает карту с логинами и паролями игроков.*/
    val mapForReg = mutableMapOf<String, String>()
    var login: String
    var password: String
    var count = 1

    for(numPlayer in playersList){
        println("\nИгрок №${count} зарегистрируйтесь, пожалуйста.")
        login = getLoginOrPassword("Логин", mapForReg)
        password = getLoginOrPassword("Пароль", mapForReg)
        numPlayer.login = login
        numPlayer.password = password
        mapForReg += Pair(login, password)
        count += 1
    }
}

fun getLoginOrPassword(name: String, registeredMap: MutableMap<String, String>): String{
    /**Запрашивает и проверяет ввод пользователем логина или пароля.*/
    // Определение начальных переменных для получения и проверки пароля.
    var userInput: String
    var len: Boolean
    var digit: Boolean
    var letter: Boolean
    var registeredInput: Boolean

    // Основной цикл для получения от пользователя правильного пароля при регистрации.
    do{
        // Назначение начальных значений переменным для каждой итерации.
        len = false
        digit = false
        letter = false
        registeredInput = false

        // Запрос на ввод пароля пользователем.
        print("\nВведите $name: ")
        userInput = readln()

        // Условия для проверки правильности введённого пароля.
        when(name){
            "Логин" -> {if(userInput.isNotEmpty()) len = true
                if(userInput !in registeredMap.keys) registeredInput = true}
            "Пароль" -> {if(userInput.length >= 8) len = true
                registeredInput = true}
        }
        userInput.forEach { i -> if(i.isDigit()) digit = true; if(i.uppercase() != i.lowercase()) letter = true}

        // Указания для пользователя при наличии ошибок во введённом пароле.
        if(!len){
            when(name){
                "Логин" -> println("$name должен быть не менее 1 символа.")
                "Пароль" -> println("$name должен быть не менее 8 символов.")
            }
        }
        else if(!digit)
            println("$name должен содержать хотя бы одну цифру.")
        else if(!letter)
            println("$name должен содержать хотя бы одну букву.")
        else if(!registeredInput)
            println("$name уже зарегистрирован, введите, пожалуйста, другой.")
    }
    // Результирующая строка цикла, которая определит, что пароль указан правильно или нужно затребоать новый.
    while(!len || !digit || !letter || !registeredInput)

    // Возвращает то, что ввёл пользователь (логин или пароль)
    return userInput
}

fun authorization(round: String, groupMap: MutableMap<String, MutableList<Player>>):
        MutableList<Player> {
    /**Производит авторизацию текущей пары.*/
    // 4. Подтверждение начала раунда

    // Взятие пары, что соответствует раунду, для авторизации.
    var pair = mutableListOf<Player>()
    when (round) {
        "первый" -> pair = groupMap["1"]!!
        "второй" -> pair = groupMap["2"]!!
        "финальный" -> pair = groupMap["3"]!!
    }

    askYes("Чтобы начать $round раунд игроков пары ${pair[0].login} - ${pair[1].login}, впишите \"y\" в консоли: ")
    println("\nДалее Вам необходимо будет подтвердить свою личность, введя пароль.")

    // 5. Вход игроков(авторизация)
    var userInput: String
    for (i in 0..1)
        do {
            print("\nПользователь ${pair[i].login}, введите пароль, пожалуйста: ")
            userInput = readLine().toString()
            if (userInput != pair[i].password)
                println("Пароль указан неверно.")
        } while (userInput != pair[i].password)
    println("Игроки успешно авторизованы, начинаем игру!")
    return pair
}

fun askYes(mes: String){
    /**Задаёт вопрос, на который должен прийти ответ 'y'.*/
    var userInput: String
    do {
        print(mes)
        userInput = readLine().toString()
    }
    while(userInput !in listOf("y", "Y"))
}