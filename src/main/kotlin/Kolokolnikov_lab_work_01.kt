import kotlin.collections.mutableMapOf as mutableMapOf

const val X = "X"
const val O = "O"
val EMPTY_NUMBERS = listOf(
    "11", "12", "13",
    "21", "22", "23",
    "31", "32", "33",
)
const val TIE = "Ничья"

fun registration(): MutableMap<String, String>{
    /**Собирает карту с логинами и паролями игроков.*/
    val mapForReg = mutableMapOf<String, String>()
    var login: String
    var password: String

    for(numPlayer in 1..4){
        println("\nИгрок №$numPlayer зарегистрируйтесь, пожалуйста.")
        login = getLoginOrPassword("Логин", mapForReg)
        password = getLoginOrPassword("Пароль", mapForReg)
        mapForReg += Pair(login, password)
    }
    return mapForReg
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

        // Указания для пользователя при наличии ошибок в введённом пароле.
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

fun askYes(mes: String){
    /**Задаёт вопрос, на который должен прийти ответ 'y'.*/
    var userInput: String
    do {
        print(mes)
        userInput = readLine().toString()
    }
    while(userInput !in listOf("y", "Y"))
}

fun divideIntoGroups(playersList: MutableList<String>): MutableMap<String, MutableList<String>> {
    /**Распределяет игроков по группам.*/
    val groupMap = mutableMapOf<String, MutableList<String>>()
    var index = 0
    for(i in 1.. playersList.size/2){
        groupMap.put(i.toString(), playersList.subList(index, index+2))
        index += 2
    }
    return groupMap
}

fun gameEntry(accountMap: MutableMap<String, String>, groupMap: MutableMap<String, MutableList<String>>){
    /**Ведёт счёт раундов, требует пройти авторизацию перед игрой, даёт доступ к игре.*/
    val rounds = listOf("первый", "второй", "финальный")
    var pair: MutableList<String> // Переменная для пары игроков, которые пройдут авторизацию
    var nameWinner: String        // Переменная для имени(логина) игрока, который победил в одном раунде
    var finalists = mutableListOf<String>() // Принимает 2 nameWinner для создания 3 группы - финалистов
    for(numRound in 1..3){
        pair = authorization(rounds[numRound-1], accountMap, groupMap) //4-5
        nameWinner = round(pair, numRound) //6-7
        finalists += nameWinner
        // Если прошёл 2 раунд, то создаётся 3-я группа для финала
        if (numRound == 2)
            groupMap["3"] = finalists
    }
    println("Игра окончена.")
}

fun authorization(round: String, accountMap: MutableMap<String, String>, groupMap: MutableMap<String, MutableList<String>>):
        MutableList<String> {
    /**Производит авторизацию текущей пары.*/
    // 4. Подтверждение начала раунда

    // Взятие пары, что соответствует раунду, для авторизации.
    var pair = mutableListOf<String>()
    when (round){
        "первый" -> pair = groupMap["1"]!!
        "второй" -> pair = groupMap["2"]!!
        "финальный" -> pair = groupMap["3"]!!
    }

    askYes("Чтобы начать $round раунд игроков пары ${pair[0]} - ${pair[1]}, впишите \"y\" в консоли: ")
    println("\nДалее Вам необходимо будет подтвердить свою личность, введя пароль.")

    // 5. Вход игроков(авторизация)
    var userInput: String
    for(i in 0..1)
        do {
            print("\nПользователь ${pair[i]}, введите пароль, пожалуйста: ")
            userInput = readLine().toString()
            if (userInput != accountMap[pair[i]])
                println("Пароль указан неверно.")
        } while(userInput != accountMap[pair[i]])
    println("Игроки успешно авторизованы, начинаем игру!")
    return pair
}

fun round(pair: MutableList<String>, numFinishedRound: Int): String{
    /**Проводит один раунд игры.*/
    //6-7. Игра/финальная игра
    var turn = X // Определяет, какая сторона ходит первой
    var move: String
    var moveCountX = 0 // Счётчики ходов сторон
    var moveCountO = 0
    var board = newBoard() // Создание чистой доски
    pair.shuffle()         // Перемешивает пару для случайного распределения на стороны
    var sideWinner: String // сторона победитель (X || O)
    var nameWinner = ""    // игрок победитель (user1 || user2)
    var playerX = mapOf(pair[0] to X) // карты, что связывают игрока со стороной
    var playerO = mapOf(pair[1] to O)
    println("${pair[0]} — играет крестиками\n${pair[1]} — играет ноликами")
    displayBoard(board)
    do {
        // Определяет кто должен ходить
        if (turn == playerX[pair[0]]) {
            move = playerMove(board, playerX)
            board[board.indexOf(move)] = X
            moveCountX += 1
        } else {
            move = playerMove(board, playerO)
            board[board.indexOf(move)] = O
            moveCountO += 1
        }
        displayBoard(board)
        turn = nextTurn(turn)
        sideWinner = whoWinner(board)
        if (sideWinner != "") {
            nameWinner = roundResult(sideWinner, pair, moveCountX, moveCountO, numFinishedRound)
        }
        // При ничьей обнуляет все значения для повтора раунда
        if (sideWinner == TIE){
            turn = X
            sideWinner = ""
            moveCountX = 0
            moveCountO = 0
            board = newBoard()
            pair.shuffle()
            playerX = mapOf(pair[0] to X)
            playerO = mapOf(pair[1] to O)
            println("${pair[0]} — играет крестиками\n${pair[1]} — играет ноликами")
            displayBoard(board)
        }
    } while (sideWinner == "")
    return nameWinner
}

fun newBoard(): MutableList<String>{
    /**Создаёт новую игровую доску в виде списка.*/
    val board = mutableListOf<String>()
    for (square in 0 until 9)
        board += EMPTY_NUMBERS[square]
    return board
}

fun displayBoard(board: MutableList<String>){
    /**Отображает игровую доску на экране.*/
    println("\n\t| ${board[0]} | ${board[1]} | ${board[2]} |")
    println("\t----------------")
    println("\t| ${board[3]} | ${board[4]} | ${board[5]} |")
    println("\t----------------")
    println("\t| ${board[6]} | ${board[7]} | ${board[8]} |")
}

fun legalMoves(board: MutableList<String>): MutableList<String>{
    /**Создаёт список доступных ходов.*/
    var moves = mutableListOf<String>()
    for (square in 0 until 9){
        if (board[square] in EMPTY_NUMBERS)
            moves += board[square]
    }
    return moves
}

fun whoWinner(board: MutableList<String>): String {
    /**Определяет победителя в игре.*/
    var winner = ""
    val waysToWin = listOf( // Все варианты побед, цифры - это индексы доски board
        listOf(0, 1, 2),    // "11", "12", "13"
        listOf(3, 4, 5),    // "21", "22", "23"
        listOf(6, 7, 8),    // "31", "32", "33"
        listOf(0, 3, 6),    // "11", "21", "31"
        listOf(1, 4, 7),    // "12", "22", "32"
        listOf(2, 5, 8),    // "13", "23", "33"
        listOf(0, 4, 8),    // "11", "22", "33"
        listOf(2, 4, 6),    // "13", "22", "31"
    )

    // Провека на наличие выиграшного варианта
    for (row in waysToWin) {
        if (board[row[0]] == board[row[1]] && board[row[0]] == board[row[2]] && board[row[0]] !in EMPTY_NUMBERS) {
            winner = board[row[0]]
            return winner
        }
    }
    // Проверка на наличие незаполненных клеток
    for(i in EMPTY_NUMBERS) {
        if (i in board)
            return ""
    }
    // Возврат ничьей, если нет выиграшной комбинации и незаполненных клеток
    return TIE
}

fun playerMove(board: MutableList<String>, player: Map<String, String>): String{
    /**Получает ход от игрока.*/
    val legal = legalMoves(board)
    var move = ""
    move = getStrNumber("Игрок ${player.keys.toString().removeSurrounding("[", "]")}, " +
            "пожалуйста, выберите и впишите ниже код клетки для своего хода\n", legal)
    return move
}

fun getStrNumber(mes: String, legal: MutableList<String>): String{
    /**Получение от пользователя имени (не индекса) клекти, поэтому *Str*.*/
    var userInput: String
    print("\n$mes")
    do {
        userInput = readLine().toString()
        if(userInput !in EMPTY_NUMBERS)
            println("Вы ввели невалидный номер клетки, попробуйте снова.")
        else if (userInput !in legal)
            println("Выбранное Вами поле занято, попробуйте другое")
    } while(userInput !in EMPTY_NUMBERS || userInput !in legal)
    return userInput
}

fun nextTurn(turn: String):String{
    /**Определяет какая сторона должна ходить.*/
    if (turn == X)
        return O
    else
        return X
}

fun roundResult(sideWinner: String, pair: MutableList<String>, moveCountX: Int, moveCountO: Int, numFinishedRound: Int):
        String{
    /**Оглашает победу или ничью по завершению раунда.*/
    // Определение типа раунда (полуфинальный/финальный)
    var nameWinner: String
    var nameRound = "финальную"
    if (numFinishedRound in 1..2) nameRound = "полу$nameRound"

    // Определение имени победившего, если ничья, то имя пустое.
    nameWinner = when (sideWinner == TIE){
        true -> {println("\nНичья. Игроки ${pair[0]} и ${pair[1]} должны сыграть повторно\n")
            ""
        }
        false -> if (sideWinner == X){
            println("\nИгрок ${pair[0]} выиграл $nameRound игру за $moveCountX ходов.\n")
            pair[0]
        } else {
            println("\nИгрок ${pair[1]} выиграл $nameRound игру за $moveCountO ходов.\n")
            pair[1]
        }
    }
    return nameWinner
}

fun main(){
    println("\nКолокольников лабораторная работа №1. (07.04.2023) \n")

    // 1. Регистрация
    val accountMap = registration()
    // Для пропуска регистрации
    //val accountMap = mutableMapOf<String, String>("l_one" to "1", "l_two" to "2", "l_three" to "3", "l_four" to "4")

    // 2. Начало полуфинала игры
    askYes("Начать игру? Если да, пропишите в консоли \"y\": ")
    println("\nНачинаем игру!\nУчастники будут распределены на группы по 2 человека.\n" +
            "Сначала состоится полуфинал, после чего победители будут сражаться в финальной игре.")

    // 3. Распределение участников для полуфинала
    val playersList = accountMap.keys.toMutableList()
    playersList.shuffle()
    val groupMap = divideIntoGroups(playersList)
    println("""
Пара 1
${playersList[0]}
${playersList[1]}

Пара 2
${playersList[2]}
${playersList[3]}
""")

    // 4-7.
    gameEntry(accountMap, groupMap)

}

