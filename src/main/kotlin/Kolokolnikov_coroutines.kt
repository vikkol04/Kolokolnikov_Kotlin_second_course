package main.kotlin
import kotlinx.coroutines.*
import java.lang.NullPointerException
import kotlin.system.measureTimeMillis

fun main() {
    println("\n27.04.2023 \n")
    var ch = readLine()
    /**
     * [  ] - своими словами.
     */
    //1. Понятие корутин в котлине, для чего используются.
    if (ch == "1"){
        /**
         *  Процесс — это экземпляр исполняемой программы, а также текущие значения счетчика команд, регистров
         * и переменных.
         *  Поток выполнения (тред; от англ. thread — нить) — наименьшая единица обработки, исполнение
         * которой может быть назначено ядром операционной системы. Реализация потоков выполнения и процессов
         * в разных операционных системах отличается друг от друга, но в большинстве случаев поток выполнения
         * находится внутри процесса.
         *  Сопрограмма(корутин, легковесный поток) — это экземпляр приостанавливаемых вычислений.
         *  [ Используется для асинхронного написания кода, уменьшения его количества , классов
         *  и т.п., создания меньшего количества потоков и перемещения между ними. ]
         *  В синхронном коде выполнение функций происходит в том же месте, где они были вызваны, и в тот момент,
         * когда происходит вызов. В асинхронном коде всё по-другому. Вызов функции не означает, что она
         * отработает прямо здесь и сейчас.
        */
    }//2. Основной синтаксис и правила использования корутина в коде (здесь с примерами к теории)
     //3. Запуск нескольких корутин в пределах одной функции, вложенные корутины (coroutine scope, runBlocking)
    else if(ch == "2" || ch == "3"){
        /**
         *  launch — это строитель сопрограмм. Он запускает новую сопрограмму одновременно с остальным кодом,
         * которая продолжает работать независимо.
         *  delay — это специальная функция приостановки. Она приостанавливает сопрограмму на определенное время.
         * Приостановка сопрограммы не блокирует базовый поток, но позволяет запускать другие сопрограммы и
         * использовать базовый поток для своего кода.
         *
         *   runBlocking — это сборщик сопрограммы, который соединяет несопрограммный мир
         *  обычного fun main() и код с сопрограммами внутри runBlocking { ... }. [ Без него при написании
         *  сопрограмм будут возникать исключения.] Имя runBlocking означает, что поток, который его запускает
         *  (в данном случае — основной поток), блокируется на время вызова, пока все сопрограммы
         *  внутри runBlocking { ... } не закончат своё исполнение.
         *   runBlocking часто используется на верхнем уровне приложения и редко внутри реального кода,
         *  т.к. потоки являются дорогостоящими ресурсами, и блокировать их неэффективно и часто нежелательно.
         *
         *   CoroutineScope — интерфейс, определяющий область для новых сопрограмм.
         *   Сoroutine builders (построители спрограмм) — это расширения для CoroutineScope, например
         *   launch, async, runBlocking.
         *   Structured concurrency (структурированный параллелизм) — принцип, которому следуют сопрограммы, при
         *  котором новые сопрограммы могут запускаться только в определенной CoroutineScope , которая ограничивает
         *  время жизни сопрограммы.
         */
        println("\nПример №1: Простая сопрограмма")
        fun coroutines1() = runBlocking {
            launch {
                for (i in 1..3) {
                    delay(1000L)
                    println("$i-я секунда")
                }
            }
            println("0-я секунда")
        }
        coroutines1()

        /**
         *  suspend(приостановка) — [ модификатор функции, который позволяет произвести рефакторинг или
         *  извлечение этой функции. При исполнении функции с эти модификатором она может, например приостановить
         *  сопрограмму используя delay(). ]
         */
        println("\nПример №2: рефакторинг функции извлечения")
        suspend fun coroutines2S(){
                for (i in 1..3) {
                    delay(1000L)
                    println("$i-я секунда")
            }
        }
        fun coroutines2() = runBlocking{
            launch{
                coroutines2S()
            }
            println("0-я секунда")
        }
        coroutines2()

        /**
         *   coroutineScope — сборщик сопрограммы, который позволяет объявить собственную область действия сопрограммы
         *  и не завершается до тех пор, пока не будут завершены все запущенные дочерние элементы.
         *   Сборщики сопрограмм runBlocking и coroutineScope могут выглядеть одинаково, потому что они оба ждут завершения
         *  своего тела и всех его дочерних элементов. Основное отличие состоит в том, что runBlocking метод
         *  блокирует текущий поток для ожидания, в то время как coroutineScope просто приостанавливает работу,
         *  освобождая базовый поток для других целей. Из-за этой разницы runBlocking является обычной функцией,
         *  а coroutineScope — функцией приостановки.
         *  В одной области видимости все launch могут работать одновременно.
         */
        println("\nПример №3: Конструктор области; построитель области действия и параллелизм")
        suspend fun coroutines3_R1() = runBlocking {
            delay(6000L)
            println("R1")
        }
        suspend fun coroutines3_S1() = coroutineScope {
            launch {
                for (i in 1..3) {
                    delay(1000L)
                    println("CS1 $i-я секунда")
                }
            }
            println("CS1 0-я секунда")
        }
        suspend fun coroutines3_S2() = coroutineScope {
            launch {
                println("CS2 Последняя секунда")
            }
        }
        fun coroutines3() = runBlocking {
            launch {
                for (i in 1..3) {
                    delay(1000L)
                    println("R $i-я секунда")
                }
            }
            launch {
                for (i in 1..3) {
                    delay(1000L)
                    println("R Параллельная $i-я секунда")
                }
            }
            coroutines3_R1()
            println("я был блокирован R1")
            coroutines3_S1()
            println("я не был блокирован S1")
            coroutines3_S2()
        }
        coroutines3()


        println("\nПример №4: Явная работа")
        fun coroutines4() = runBlocking {
            val stopwatch = launch { // объявляем новую сопрограмму и сохраняем ссылку на неё в stopwatch
                for (i in 1..3) {
                    delay(1000L)
                    println("$i-я секунда")
                }
            }
            println("0-я секунда")
            stopwatch.join() // ждём завершения дочерней сопрограммы
            println("Последняя секунда")
        }
        coroutines4()

        println("\nПример №5: лёгкость корутин относительно потоков")
        fun coroutines5() = runBlocking {
            repeat(5000) {
                launch {
                        delay(1000L)
                        println(".")
                    }
                }
            println("Счёт окончен.")
            }
        coroutines5()

        /**
         * [ Если вместо сопрограмм всегда использовать потоки, то программа будет работать гораздо медленнее.
         * Тогда не используется runBlocking, вместо launch - thread, вместо delay - Thread.sleep ]
         */



    }//
    else if(ch == "3"){


    }//4. Строительство корутин: launch и async(async/await())
    else if(ch == "4"){
        /**
         *  job(работа) — это интерфейс, а концептуально это вещь, которую можно отменить, жизненный цикл которой
         * заканчивается её окончанием. [ Не имеет результата ]
         *  Deferred value(отложенное значение) — это неблокирующее отменяемое будущее — это Job с результатом.
         * Он создаётся с помощью построителя асинхронной (async) сопрограммы или с помощью конструктора класса
         * CompletableDeferred. Он находится в активном состоянии, пока вычисляется значение. Результат отложенного
         * выполнения доступен после его завершения и может быть получен методом await, который выдает исключение
         * в случае сбоя отложенного выполнения.
         *  launch смотреть пункт 2-3.
         *  async — строитель сопрограмм, который создаёт сопрограмму и возвращает её будущий результат как
         * реализацию Deferred. Результирующая работающая сопрограмма отменяется, когда отменяется отложенная.
         *  await() — метод, который применяется для получения результата из объекта Deferred.
         */
        println("\nПример №6: структурированный параллелизм с асинхронностью")
        suspend fun coroutines6_S(): String{
            delay(1000L)
            return "Suspend String"
        }
        fun coroutines6(): String = runBlocking {
            val message: Deferred<String> = async { coroutines6_S() }
            return@runBlocking message.await()
        }
        val stopwatch6 = measureTimeMillis {
            println("Строка из suspend функции:")
            println(coroutines6())
        }
        println("Получено за $stopwatch6 мс")


    }//5. Отмена выполнения корутины
    else if(ch == "5"){
        /**
         * Отмена выполнения сопрограммы всегда распространяется через иерархию сопрограмм
         * [ try берёт любое значение или функцию и находит исключение. Если нет исключений код внутри try
         * будет исполнен.
         * catch - если сработал try, то будет проверка на заранее указанные в скобочках исключения. Если эти исключения
         * определены, то выполняется код, который находится catch, иначе программа выдаст исключение
         * finally - всегда выполняется код, который находится под finally
         * После try должен быть либо catch, либо finally или всё вместе. ]
         *
         */
        suspend fun coroutines7_S(): Int = coroutineScope {
            val a1 = async<Int> {
                try {
                    42
                } finally {
                    2+2
                }
            }
            println("В a1 = ${a1.await()}")
            throw ArithmeticException()
        }
        fun coroutines7() = runBlocking {
            try {
                coroutines7_S()
            } catch (e: ArithmeticException){
                println("Если сработал catch отобразится.")
            } finally {
                println("Всегда отображается.")
            }
        }
        coroutines7()
    }

    else{
        println("Выбор не осуществлён.")
    }
}

