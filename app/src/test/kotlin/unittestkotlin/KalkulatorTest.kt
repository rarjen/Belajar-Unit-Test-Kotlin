package unittestkotlin

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.*
import unittestkotlin.generator.SimpleDisplayNameGenerator

@DisplayNameGeneration(SimpleDisplayNameGenerator::class) // Jarang digunakan!
//@DisplayName("Test for Calculator Class") //Digunakan untuk mengubah nama/keterangan nama test dan memberikan deskripsi
class KalkulatorTest {

    val kalkulator = Kalkulator();

    // BeforeEach & AfterEach
    /**
     * Digunakan untuk membuat kode yang sama berulang-ulang sebelum dan sesudah unit test
     * Digunakan lebih tepatnya misal Before nya menyimpan data ke db dahulu setelah itu menghapus data di db nya
     */
    @BeforeEach
    fun beforeEach() {
        println("Sebelum Unit test")
    }

    @AfterEach
    fun afterEach() {
        println("Setelah Unit test")
    }

    // BeforeAll & AfterAll
    /**
     * Case misal saat hanya ingin melakukan eksekusi 1x saja yaitu membuka dan menutup koneksi ke db
     *
     * Namun hanya static function (object function di kotlin) yg bisa menggunakan @BeforeAll dan @AfterAll
     */
    companion object {

        @BeforeAll
        @JvmStatic // Berfungsi untuk memberitahu compiler kotlin untuk di generate mjd static method nya java
        fun beforeAll() {
            println("Sebelum semua unit test")
        }

        @AfterAll
        @JvmStatic
        fun afterAll() {
            println("Setelah semua unit test")
        }
    }

    // Test add
    @Test
//    @DisplayName("Test for fun Calculator.add(10, 10)")
    fun testAddSuccess() {
        println("Unit test testAddSuccess")
        val result = kalkulator.add(10, 10)

        //Assertions (Param message menampilkan error message apabila hasilnya tidak 20)
        assertEquals(20, result, "Hasil harusnya 20")
    }

    @Test
//    @DisplayName("Test for fun Calculator.add(20, 20)")
    fun testSuccess2() {
        println("Unit test testAddSuccess2")
        val result = kalkulator.add(20, 20)
        assertEquals(40, result, "Hasil harus 40")
    }

    // Test Divide
    @Test
//    @DisplayName("Test for fun Calculator.divide(100, 10)")
    fun testDivideSuccess() {
        println("Unit test testDivideSuccess")
        val result = kalkulator.divide(100, 10)
        assertEquals(10, result)
    }

    @Test
//    @DisplayName("Test throw exception for fun Calculator.add(100, 0)")
    fun testDivideError() {
        println("Unit test testDivideError")
        // Test gagal (Memastikan throw exception pada kondisi tertentu)
        assertThrows<IllegalArgumentException> {
            kalkulator.divide(100, 0)
        }
    }

    @Disabled("Coming Soon!") // Menonaktifkan unit test
    @Test
    // Melakukan nonaktif unit test
    fun testComingSoon() {
        // not finished yet
    }





}