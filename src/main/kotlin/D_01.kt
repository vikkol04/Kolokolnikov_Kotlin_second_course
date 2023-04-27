import java.nio.file.attribute.UserPrincipal
import java.security.MessageDigest

val users = mutableMapOf<String, String>() // зберігає імена користувачів та паролі
var pairs = listOf<List<String>>()

fun main() {
    for (i in 1..4) {
        registration(i)
    }
    starting_game()
    shuffle_users()
    game()
}

// Перевіряє пароль на літери та цифри
fun isValidPassword(pass: String): Boolean {
    val hasLetter = pass.any { it.isLetter() }
    val hasDigit = pass.any { it.isDigit() }
    return pass.length > 8 && hasLetter && hasDigit
}

// Регістрація
fun registration(player_number: Int) {
    while (true) {
        println("Гравець $player_number зареєструйтесь, будь ласка.")
        val username = readLine()


        // Перевірка, чи логін користувача відповідає вимогам

        if (username != null && username.any { it.isLetter() }) {
            if (users.containsKey(username)){
                println("Your login is already taken")
                return
            }
            println("Введіть пароль (щонайменше 8 символів, містить хоча б одну букву та цифру):")
            val password = readLine()

            // Перевірка, чи пароль відповідає вимогам
            if (password != null && isValidPassword(password)) {
                users[username] = password
                println("Реєстрація пройшла успішно!")
                break
            } else {
                println("Пароль не відповідає вимогам.")
            }
        } else {
            println("Ім'я користувача не відповідає вимогам або вже зайняте.")
        }
    }
}

// Підтвердження початку гри
fun starting_game() {

    do {
        println("Почати гру? Якщо так, пропишіть в консолі \"y\" ")
        val ready = readLine()
    } while (ready != "y")

}

// Перемішка гравців
fun shuffle_users() {
    val players = users.keys
    val shuffledPlayers = players.shuffled()
    pairs = shuffledPlayers.chunked(2)
    println("Пара 1\n${pairs.joinToString(" \nПара 2\n")}")
    do {
        println("Щоб почати перший раунд гравців пари ${pairs.joinToString()} впишіть \"у\" в консолі. Далі Вам необхідно буде підтвердити свою особистість, ввівши пароль")
        val ready = readLine()
    } while (ready != "y")
}

// Веріфікація гравців
fun verification(pairs: List<String>) {
    for (user in pairs) {
        println("Please $user enter your password")
        val password = users.get(user)
        val verify_pass = readlnOrNull()
        if (verify_pass != user && password != verify_pass) {
            println("Ваш пароль невірний. Введіть ще раз")
            return game()
        } else {
            println("Ваш пароль правильний")
            continue
        }
    }
}

// Створює поле
fun CreateBoard(): Array<Array<String>> {
    val board = arrayOf(
        arrayOf("11", "12", "13"),
        arrayOf("21", "22", "23"),
        arrayOf("31", "32", "33")
    )
    return board
}

// Малює поле
fun printBoard(board: Array<Array<String>>) {
    for (row in board) {
        println("| ${row[0]} | ${row[1]} | ${row[2]} |")
        println("----------------")
    }
}

fun game() {
    for (pair in pairs) {
        verification(pair)
        val board = CreateBoard()
        fun winningVariants(row: Int, col: Int, player: String): Boolean {
            // Перевірка на виїграш горизонтальний
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                return true
            }
            // Перевірка на виїграш вертикальний
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true
            }
            // Перевірка на виїграш діагональний (Верхній лівій кут до правого нижнього кута)
            if (row == col && board[0][0] == player && board[1][1] == player && board[2][2] == player) {
                return true
            }
            // Перевірка на виїграш діагональний (Правий верхній кут до нижнього лівого кута)
            if (row + col == 2 && board[0][2] == player && board[1][1] == player && board[2][0] == player) {
                return true
            }
            return false
        }


        val firstPlayer = pair.get(0)
        val secondPlayer = pair.get(1)
        val firstChar = "X"
        val secondChar = "0"
        var firstPlayerNumMoves = 0
        var secondPlayerNumMoves = 0
        var gameOver = false

        // Реалізація самої гри
        while (!gameOver) {
            var currentChar: String
            printBoard(board)
            var currentPlayer: String
            if (firstPlayerNumMoves > secondPlayerNumMoves) {
                currentChar = secondChar
                currentPlayer = secondPlayer
                secondPlayerNumMoves++
            } else {
                currentChar = firstChar
                currentPlayer = firstPlayer
                firstPlayerNumMoves++
            }
            print("Гравець $currentPlayer, будь ласка, оберіть та впишіть нижче код клітинки для свого ходу ")
            val position = readLine()?.toIntOrNull() ?: continue

            // Заміна дефолтних значень для вводу на ці
            val row = when (position) {
                11, 12, 13 -> 0
                21, 22, 23 -> 1
                31, 32, 33 -> 2
                else -> continue
            }
            val col = when (position) {
                11, 21, 31 -> 0
                12, 22, 32 -> 1
                13, 23, 33 -> 2
                else -> continue
            }

            // Встановити символ на місце
            board[row][col] = currentChar

            // Перевірка на можливість встановлення символа на місце


            // Перевірка на виїграш конкретного гравця
            if (winningVariants(row, col, currentChar)) {
                println("Гравець $currentPlayer виграв!")
                gameOver = true
            } else if (firstPlayerNumMoves + secondPlayerNumMoves == 9) {
                // Перевірка на нічию
                println("Нічия")
                gameOver = true
            }
        }
    }
}

