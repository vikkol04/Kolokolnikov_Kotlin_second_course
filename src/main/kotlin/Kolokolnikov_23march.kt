fun main() {
    println("\nКолокольников на 23.03.2023 \n")
    /*
    Написати валідацію для паролю.
    Користувач має ввести пароль з консолі, який повинен відповідати наступним критеріям:
    1. Довжина паролю має бути більшою за 8 знаків
    2. У паролі має бути хоча б одна буква
    3. У паролі має бути хоча б одна цифра
    При введенні користувачем валідного паролю в консоль має вивестись: "Пароль успішно встановлено. Ваш пароль <пароль,
який встановили>"
    При невідповідності паролю вказаним вище нормам має виводитись підказка: "пароль має бути довшим за 8 символів",
"пароль має містити хоча б одну цифру", "пароль має містити хоча б одну букву".
    Якщо пароль не відповідає кільком правилам валідації - виводимо перше, якому не відповідає.
     */

    // Определение начальных переменных.
    var pw: String
    var len: Boolean
    var digit: Boolean
    var letter: Boolean

    // Инструкция для пользователя.
    println("Пароль должен содержать не менее 9 знаков и включать в себя как минимум по одной цифре и букве.")

    // Основной цикл для получения от пользователя правильного пароля при регистрации.
    do{

        // Назначение начальных значений переменным для каждой итерации.
        len = false
        digit = false
        letter = false

        // Запрос на ввод пароля пользователем.
        print("\nВведите пароль: ")
        pw = readln()

        // Условия для проверки правильности введённого пароля.
        if (pw.length > 8) {
            len = true
        }
        for (i in pw){
            if (i.isDigit()){
                digit = true
            }
        }
        for (i in pw){
            if (i.uppercase() != i.lowercase()){
                letter = true
            }
        }

        // Указания для пользователя при наличии ошибок в введённом пароле.
        if (!len){
            println("Пароль должен быть длиннее 8 символов")
        }else if (!digit){
            println("Пароль должен содержать хотя бы одну цифру")
        }else if (!letter){
            println("Пароль должен содержать хотя бы одну букву")
        }

    // Результирующая строка цикла, которая определит, что пароль указан правильно или нужно затребоать новый.
    }while (!len || !digit || !letter)

    // Ответ пользователю, что пароль установлен.
    print("Пароль успешно установлен. Ваш пароль $pw")
    readln()
}
