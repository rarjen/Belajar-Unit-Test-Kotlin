package unittestkotlin

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import java.util.concurrent.TimeUnit


/**
 * Timeout di Test (20)
 *
 * Kadang kita ingin memastikan bahwa sebuah unit test berjalan tidak lebih dari sekian detik
 * Misal ketika kasus kita ingin memastikan kode program kita mempunyai performa bagus dan cepat
 * JUnit memiliki fitur timeout, yaitu memastikan bahwa unit test berjalan tidak lebih dari waktu yang ditentukan, jika melebihi waktu yang ditentukan, secara otomatis unit test tersebut akan gagal
 * Kita bisa menggunakan annotation @Timeout untuk melakukan hal tersebut
 *
 */

/**
 * Eksekusi Test Secara Paralel
 *
 * Secara default, JUnit tidak mendukung eksekusi unit test secara paralel, artinya unit test akan dijalankan secara sequential satu per satu
 * Namun kadang ada kasus kita ingin mempercepat proses unit test sehingga dijalankan secara paralel, hal ini bisa kita lakukan di JUnit, namun perlu beberapa langkah
 * Tapi ingat, pastikan unit test kita aman ketika dijalankan secara paralel
 *
 * Menambah Konfigurasi Paralel
 *
 * Hal pertama yang perlu kita lakukan adalah membuat file junit-platform.properties di resource
 * Lalu menambah value :
 * junit.jupiter.execution.parallel.enabled = true
 *
 * Menggunakan @Execution
 *
 * Walaupun sudah mengaktifkan fitur paralel, tapi bukan berarti secara otomatis semua unit test berjalan paralel, agar unit test berjalan paralel, kita perlu menggunakan annotation @Execution
 * Lalu memilih jenis execution nya, misal untuk paralel bisa menggunakan ExecutionMode.CONCURRENT
 *
 *
 * Catatan
 * Jangan sampai sharing attribute yang dapat meyebabkan rest condition (1 function mengubah value dari function yg lainnya) harus aman bila mau running scr paralel
 */

@Execution(ExecutionMode.CONCURRENT)
class SlowTest {
    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    fun slowTest1() {
        Thread.sleep(3000)
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    fun slowTest2() {
        Thread.sleep(3000)
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    fun slowTest3() {
        Thread.sleep(3000)
    }
}