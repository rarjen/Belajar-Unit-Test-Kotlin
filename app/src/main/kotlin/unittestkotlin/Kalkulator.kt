package unittestkotlin

class Kalkulator {

    fun add(first: Int, second: Int): Int {
        return first + second
    }

    // Menggagalkan sebuah test (digunakan untuk mengecek exception)
    fun divide(first: Int, second: Int): Int {
        if (second == 0) {
            throw IllegalArgumentException("Can't divide by zero")
        } else {
            return first / second;
        }
    }

}