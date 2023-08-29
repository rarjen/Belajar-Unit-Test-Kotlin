package unittestkotlin.testingDepedencyInjection

class EnginX {
    fun start() {
        println("Engine Start")
    }
}

class CarX {
    private val enginX = EnginX();

    fun start() {
        enginX.start();
    }
}

fun main() {
    val carX = CarX();
    carX.start()
}

/**
 * Alasan kenapa tidak dikatakan sebagai DI
 *
 * Car dan Engine disandingkan erat dengan instance/object Car. Jika Car menyusun Engine nya sendiri maka kita harus membuat 2 jenis Car
 * bukan hanya menggunakan kembali Car yang sama utk mesin berjenis Gas dan Electric
 */