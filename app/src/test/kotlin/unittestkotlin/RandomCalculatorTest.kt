package unittestkotlin

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
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

class RandomCalculatorTest: ParentKalkulatorTest() { //(17)

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


    /**
     * Test Berulang (18)
     *
     * JUnit mendukung eksekusi unit test berulang kali sesuai dengan jumlah yang kita tentukan
     * Untuk mengulang eksekusi unit test, kita bisa menggunakan annotation @RepeatedTest di function unit test nya
     * @RepeatedTest juga bisa digunakan untuk mengubah detail nama test nya, dan kita bisa menggunakan value {displayName} untuk mendapatkan display name, {currentRepetition} untuk mendapatkan perulangan ke berapa saat ini,  dan {totalRepetitions} untuk mendapatkan total perulangan nya
     *
     * Informasi Perulangan
     *
     * @RepeatedTest juga memiliki object RepetitionInfo yang di inject oleh class RepetitionInfoParameterResolver, sehingga kita bisa mendapatkan informasi RepetitionInfo melalui parameter function unit test
     */
    @DisplayName("Test kalkulator random")
    @RepeatedTest(
        value = 10,
        name = "{displayName} ke {currentRepetition} dari {totalRepetitions}"
    )
    fun testRandomRpeated(random: Random, repetitionInfo: RepetitionInfo, testInfo: TestInfo) { //(18)

        println("${testInfo.displayName} ke ${repetitionInfo.currentRepetition} dari ${repetitionInfo.totalRepetitions}")

        val first = random.nextInt(1000)
        println(first)

        val second = random.nextInt(1000)
        println(second)

        val result = calculator.add(first, second)
        assertEquals(first + second, result)
    }

    /**
     * Test With Parameter (19)
     *
     * Sebelumnya kita sudah tau jika ingin menambahkan parameter di function unit test, maka kita perlu membuat ParameterResolver
     * Namun jika terlalu banyak membuat ParameterResolver juga agak menyulitkan kita
     * JUnit memiliki fitur yang bernama @ParameterizedTest, dimana jenis unit test ini memang khusus dibuat agar dapat menerima parameter
     * Yang perlu kita lakukan adalah dengan mengganti @Test menjadi @ParameterizedTest
     *
     * Sumber Parameter
     *
     * @ParameterizedTest mendukung beberapa sumber parameter, yaitu :
     *
     * @ValueSource, untuk sumber Number, Char, Boolean dan String
     * @EnumSource, untuk sumber berupa enum
     * @MethodSource, untuk sumber dari function object (static)
     * @CsvSource, untuk sumber beruba data CSV
     * @CsvFileSource, untuk sumber beruba file CSV
     * @ArgumentSource, untuk data dari class ArgumentProvider
     */

    @DisplayName("Test calculator with parameter")
    @ParameterizedTest(name = "{displayName} with data {0}")
    @ValueSource(ints = [10, 20, 30, 40, 50])
    fun testWithParameter(value: Int) {
        val result = calculator.add(value, value)
        assertEquals(value + value, result)
        println(result)
    }

    // Jika ada yang lebih sulit dan kompleks dan diluar kapasistas dari tipe dara ValueSource kita bisa menggunakan:
    // @MethodSource => Parameter diambil dari function object (function static di java) (19)
    @DisplayName("Test with Method Source")
    @ParameterizedTest
    @MethodSource(value = ["parameterSource"])
    fun testWithMethodSource(value: Int) {
        assertEquals(value + value, calculator.add(value, value))
        println(calculator.add(value, value))
    }
    companion object {
        @JvmStatic //Agar membuat function nya menjadi static function di java
        fun parameterSource(): List<Int> {
            return listOf(1, 2, 3, 4, 5)
        }
    }

}