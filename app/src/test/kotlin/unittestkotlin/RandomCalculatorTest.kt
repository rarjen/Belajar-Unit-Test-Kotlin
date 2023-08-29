package unittestkotlin

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import unittestkotlin.resolver.RandomParameterResolver
import java.util.Random

/**
 * Dependency Injection di Test
 *
 * Tidak ada magic di JUnit, sebenarnya fitur TestInfo yang sebelumnya kita bahas adalah bagian dari dependency injection di JUnit
 * Dependency Injection sederhananya adalah bagaimana kita bisa memasukkan dependency (object/instance) ke dalam unit test secara otomatis tanpa proses manual
 * Saat kita menambah parameter di function unit test, sebenarnya kita bisa secara otomatis memasukkan parameter tersebut dengan bantuan ParameterResolver
 * Contohnya TestInfo yang kita bahas sebelumya, sebenarnya objectnya dibuat oleh TestInfoParameterResolver
 *
 * Menggunakan Parameter Resolver
 *
 * Untuk menggunakan parameter resolver yang sudah kita buat, kita bisa menggunakan annontation @ExtendWith di test class
 * Jika lebih dari 1 parameter resolver, kita bisa menggunakan @Extentions
 *
 */

@Extensions(value = [
    ExtendWith(RandomParameterResolver::class)
])

class RandomCalculatorTest {

    private val calculator = Kalkulator()

    @Test
    // Melakukan inject object random
    fun testRandom(random: Random) {
        val a = random.nextInt()
        println(a)
        val b = random.nextInt()
        println(b)
        println(a + b)
        println(calculator.add(a, b))
        assertEquals(a + b, calculator.add(a, b));
    }
}