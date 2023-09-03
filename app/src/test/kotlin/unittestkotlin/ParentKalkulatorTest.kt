package unittestkotlin

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import unittestkotlin.resolver.RandomParameterResolver


/**
 * Pewarisan di Test
 *
 * JUnit mendukung pewarisan di test, artinya jika kita membuat class atau interface dan menambahkan informasi test disitu, maka ketika kita membuat turunannya, secara otomatis semua fitur test nya dimiliki oleh turunannya
 * Biasanya dibuat untuk kode yang berulang-ulang seperti before all ataupun beforeach
 * Ini sangat cocok ketika kita misal contohnya sering membuat code sebelum dan setelah test yang berulang-ulang, sehingga dibanding dibuat di semua test class, lebih baik dibuat sekali di parent test class, dan test class tinggal menjadi child class dari parent test class
 *
 */

@Extensions(value = [
    ExtendWith(RandomParameterResolver::class)
])
abstract class ParentKalkulatorTest {

    val calculator = Kalkulator()

    @BeforeEach
    fun beforeEach() {
        println("before each")
    }
}
