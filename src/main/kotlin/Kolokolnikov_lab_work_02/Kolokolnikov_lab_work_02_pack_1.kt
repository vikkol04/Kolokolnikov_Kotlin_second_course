package Kolokolnikov_lab_work_02_pack

import kotlin.math.sqrt


class TicTacToe() {
    /**
     *  Класс по которому создаётся экземпляр игры в крестики-ноликик, тут находятся методы, устанавливающие
     * правила игры, и свойства, определяющие константы в игре и экземпляры игроков.
     * */
    val X: String = Consts.X
    val O: String = Consts.O
    val EMPTY_NUMBERS: List<String> = Consts.EMPTY_NUMBERS
    val TIE: String = Consts.TIE
    val player1: Player = Player()
    val player2: Player = Player()
    val player3: Player = Player()
    val player4: Player = Player()
    val playersList = mutableListOf(this.player1, this.player2, this.player3, this.player4)
    val groupMap: MutableMap<String, MutableList<Player>>
    var pair = mutableListOf<Player>() // Переменная для пары игроков, которые пройдут авторизацию

    init {
        // 1. Регистрация
        registration(playersList)

        // 2. Начало полуфинала игры
        askYes("Начать игру? Если да, пропишите в консоли \"y\": ")
        println("\nНачинаем игру!\nУчастники будут распределены на группы по 2 человека.\n" +
                "Сначала состоится полуфинал, после чего победители будут сражаться в финальной игре.")

        this.playersList.shuffle()
        this.groupMap = divideIntoGroups(this.playersList)
        gameEntry(this.groupMap)

    }

    fun divideIntoGroups(playersList: MutableList<Player>): MutableMap<String, MutableList<Player>> {
        /**Распределяет игроков по группам.*/
        // 3. Распределение участников для полуфинала
        val groupMap = mutableMapOf<String, MutableList<Player>>()
        var index = 0
        for (i in 1..playersList.size / 2) {
            groupMap.put(i.toString(), playersList.subList(index, index + 2))
            index += 2
        }
        println(
            """
Пара 1
${playersList[0].login}
${playersList[1].login}

Пара 2
${playersList[2].login}
${playersList[3].login}
"""
        )
        return groupMap
    }

    fun gameEntry(groupMap: MutableMap<String, MutableList<Player>>) {
        /**Ведёт счёт раундов, требует пройти авторизацию перед игрой, даёт доступ к игре.*/
        val rounds = listOf("первый", "второй", "финальный")
        var roundWinner: Player        // Переменная для имени(логина) игрока, который победил в одном раунде
        var finalists = mutableListOf<Player>() // Принимает 2 nameWinner для создания 3 группы - финалистов
        for (numRound in 1..3) {
            this.pair = authorization(rounds[numRound - 1], groupMap) //4-5
            roundWinner = this.round(this.pair, numRound) //6-7
            roundWinner.winner = false
            finalists += roundWinner
            // Если прошёл 2-й раунд, то создаётся 3-я группа для финала
            if (numRound == 2)
                groupMap["3"] = finalists
        }
        println("Игра окончена.")
    }

    fun round(pair: MutableList<Player>, numFinishedRound: Int): Player {
        /**Проводит один раунд игры.*/
        //6-7. Игра/финальная игра
        var turn = X           // Определяет, какая сторона ходит первой
        var move: String       // Содержит ход игрока
        val board = Board()    // Создаёт доску
        board.newBoard()       // Создание чистой доски
        pair.shuffle()         // Перемешивает пару для случайного распределения на стороны
        pair[0].side = X       // Назначает стороны игрокам
        pair[1].side = O
        var moveResult = ""
        println("${pair[0].login} — играет крестиками\n${pair[1].login} — играет ноликами")
        board.displayBoard()
        do {
            // Определяет кто должен ходить
            if (turn == this.pair[0].side) {
                move = this.pair[0].playerMove(board)
                board.cellState[board.cellState.indexOf(move)] = X
                pair[0].moveCount += 1
            } else {
                move = this.pair[1].playerMove(board)
                board.cellState[board.cellState.indexOf(move)] = O
                this.pair[1].moveCount += 1
            }
            board.displayBoard()
            turn = nextTurn(turn)
            moveResult = checkMoveResult(board.cellState)
            if (moveResult != "") {
                roundResult(pair, numFinishedRound)
            }
            // При ничьей обнуляет все значения для повтора раунда
            if (moveResult == TIE) {
                turn = X
                this.pair[0].moveCount = 0
                this.pair[1].moveCount = 0
                board.newBoard()
                pair.shuffle()
                println("${this.pair[0].login} — играет крестиками\n${this.pair[1].login} — играет ноликами")
                board.displayBoard()
            }
        } while (!pair[0].winner && !pair[1].winner)
        this.pair[0].moveCount = 0
        this.pair[1].moveCount = 0
        if (pair[0].winner) return pair[0]
        else return pair[1]
    }

    fun checkMoveResult(cellState: MutableList<String>): String {
        /**Определяет победителя в игре.*/
        var moveResult = ""
        val cellStateRC = mutableListOf<MutableList<String>>()
        val rowOrCol = sqrt(cellState.size.toFloat()).toInt()
        // Создание временной доски с колонками и рядами для проверки досок любой величины
        var minLimit = 0
        for (k in 0 until rowOrCol) {
            cellStateRC.add(k, mutableListOf())
            for (j in 0 until rowOrCol) {
                cellStateRC[k].add(j, cellState[minLimit + j])
            }
            minLimit += rowOrCol
        }

        // Проверка рядов
        for (row in cellStateRC) {
            if (row[0] == row[1] && row[1] == row[2]) {
                moveResult = row[0]
            }
        }

        // Проверка колонок
        for (col in 0..2) {
            if (cellStateRC[0][col] == cellStateRC[1][col] && cellStateRC[1][col] == cellStateRC[2][col]) {
                moveResult = cellStateRC[0][col]
            }
        }

        // Проверка диагоналей
        if (cellStateRC[0][0] == cellStateRC[1][1] && cellStateRC[1][1] == cellStateRC[2][2]) {
            moveResult = cellStateRC[1][1]
        }
        if (cellStateRC[0][2] == cellStateRC[1][1] && cellStateRC[1][1] == cellStateRC[2][0]) {
            moveResult = cellStateRC[1][1]
        }

        if (moveResult != "") {
            if (moveResult == pair[0].side) this.pair[0].winner = true
            if (moveResult == pair[1].side) this.pair[1].winner = true
            return moveResult
        }

        // Проверка на наличие незаполненных клеток
        if ((this.pair[0].moveCount + this.pair[1].moveCount) == EMPTY_NUMBERS.size)
            return TIE

        return moveResult

    }

    fun nextTurn(turn: String): String {
        /**Определяет какая сторона должна ходить.*/
        if (turn == X)
            return O
        else
            return X
    }

    fun roundResult(pair: MutableList<Player>, numFinishedRound: Int) {
        /**Оглашает победу или ничью по завершению раунда.*/
        // Определение типа раунда (полуфинальный/финальный)
        var nameRound = "финальную"
        if (numFinishedRound in 1..2) nameRound = "полу$nameRound"

        // Определение имени победившего, если ничья, то имя пустое.
        if (pair[0].winner) {
            println("\nИгрок ${pair[0].login} выиграл $nameRound игру за ${pair[0].moveCount} ходов.\n")
        } else if (pair[1].winner) {
            println("\nИгрок ${pair[1].login} выиграл $nameRound игру за ${pair[0].moveCount} ходов.\n")
        } else {
            println("\nНичья. Игроки ${pair[0].login} и ${pair[1].login} должны сыграть повторно\n")
        }
    }
}

class Board {
    /**
     * Класс по которому создаётся экземпляр доски для игры в крестики-нолики.
     * Тут находятся методы, устанавливающие взаимодействия с доской,
     * и свойства, определяющие расположение объектов на доске.
     * */
    val EMPTY_NUMBERS: List<String> = Consts.EMPTY_NUMBERS
    var cellState = mutableListOf<String>()

    fun newBoard(){
        /**Создаёт новую игровую доску в виде списка.*/
        for (square in 0 until 9) {
            this.cellState.add(square, EMPTY_NUMBERS[square])
        }
    }

    fun displayBoard() {
        /**Отображает игровую доску на экране.*/
        println("\n\t| ${this.cellState[0]} | ${this.cellState[1]} | ${this.cellState[2]} |")
        println("\t----------------")
        println("\t| ${this.cellState[3]} | ${this.cellState[4]} | ${this.cellState[5]} |")
        println("\t----------------")
        println("\t| ${this.cellState[6]} | ${this.cellState[7]} | ${this.cellState[8]} |")
    }

    fun legalMoves(cellState: MutableList<String> = this.cellState): MutableList<String>{
        /**Создаёт список доступных ходов.*/
        var moves = mutableListOf<String>()
        for (square in 0 until 9){
            if (cellState[square] in EMPTY_NUMBERS)
                moves += cellState[square]
        }
        return moves
    }
}

class Player {
    /**
     * Класс по которому создаётся экземпляры игроков для игры в крестики-нолики.
     * Тут находятся методы, устанавливающие действия игроков,
     * и свойства, определяющие положение игрока в игре и другое.
     * */
    var login: String = ""
    var password: String? = ""
    var side: String = ""
    var moveCount: Int = 0 // Счётчик ходов
    var winner: Boolean = false

    fun playerMove(board: Board): String {
        /**Получает ход от игрока.*/
        val legal = board.legalMoves()
        var move = ""
        move = this.getStrNumber(
            "Игрок ${this.login}, " +
                    "пожалуйста, выберите и впишите ниже код клетки для своего хода\n", legal
        )
        return move
    }

    private fun getStrNumber(mes: String, legal: MutableList<String>): String {
        /**Получение от пользователя имени (не индекса) клекти, поэтому *Str*.*/
        var userInput: String
        print("\n$mes")
        do {
            userInput = readLine().toString()
            if (userInput !in Consts.EMPTY_NUMBERS)
                println("Вы ввели невалидный номер клетки, попробуйте снова.")
            else if (userInput !in legal)
                println("Выбранное Вами поле занято, попробуйте другое")
        } while (userInput !in Consts.EMPTY_NUMBERS || userInput !in legal)
        return userInput
    }
}
