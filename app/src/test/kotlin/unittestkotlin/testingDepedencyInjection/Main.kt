package unittestkotlin.testingDepedencyInjection

/**
 * Dependency Injection
 *
 * Ada 2 cara injeksi :
 * 1. Injeksi Konstruktor -> Dengan cara meneruskan dependensi class ke konstruktornya
 * 2. Injeksi Kolom (Field Injection) -> Class framework Android tertentu, seperti aktivitas dan fragmen, dibuat instance-nya oleh sistem, sehingga injeksi konstruktor tidak dapat dilakukan. Dengan injeksi kolom, dependensi akan dibuat instance-nya setelah class dibuat.
 */

/**
 * Namun ada alternatif untuk injeksi depedensi yaitu dependency otomatis
 */


class Engine {
    fun start(){
        println("Engine Start")
    }
}

// Injeksi Konstruktor
class Car (private val engine: Engine) {
    fun start() {
        engine.start();
    }
}

// Field Injection
class ElectricCar {
    lateinit var engine: Engine

    fun start() {
        engine.start()
    }
}

// Dependancy Otomatis

class EngineCutOff {
    fun cutOff() {
        println("Engine has been cutted off")
    }
}

class Break{
    fun breaking() {
        println("Breaking...")
    }
}

object ServiceLocator {
    // Ini seolah olah sebagai penampung semua service yang nantinya akan dipake oleh car
    // Mirip seperti helper atau utility class
    fun getEngine(): Engine = Engine();
    fun cutEngine(): EngineCutOff = EngineCutOff();
    fun breaking(): Break = Break();
}


class SuperCar {
    private val engine = ServiceLocator.getEngine();
    private val cutEngine = ServiceLocator.cutEngine();
    private val breakingCar = ServiceLocator.breaking();

    fun start() {
        engine.start()
    }

    fun cutEngine() {
        cutEngine.cutOff()
    }

    fun breaking() {
        breakingCar.breaking()
    }
}

fun main() {

    val engine = Engine();

    // Injeksi Konstruktor
    val car = Car(engine)
    car.start()

    // Field Injection
    val electricCar = ElectricCar();
    electricCar.engine = Engine()
    electricCar.start()


    // Automatic Injection
    val superCar = SuperCar();
    superCar.start()
    superCar.cutEngine()
    superCar.breaking()
}