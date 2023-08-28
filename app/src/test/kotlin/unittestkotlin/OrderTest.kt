package unittestkotlin

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestMethodOrder

/**
 * Urutan Eksekusi Test (12)
 * Secara default, urutan eksekusi unit test tidak ditentukan, jadi jangan berharap  jika sebuah function berada diatas function lainnya, maka akan dijalankan lebih dulu
 * Hal ini karena memang sebaiknya function unit test itu harus independen, tidak saling ketergantungan
 * Secara default pun, object class unit test akan selalu dibuat ulang tiap function, jadi jangan berharap kita bisa menyimpan data di variable untuk digunakan di unit test function selanjutnya
 *
 * Mengubah Urutan Eksekusi Test
 * JUnit mendukung perubahan urutan eksekusi test jika kita mau menggunakan annotation @TestMethodOrder, ada beberapa cara yang bisa kita lakukan
 * Alphanumeric, artinya urutan eksekusi unit test akan diurutkan berdasarkan alphanumeric
 * Random, artinya urutan urutan eksekusi unit test akan dieksekusi secara random
 * OrderAnnotation, artinya urutan eksekusi  unit  test akan mengikuti annotation @Order yang ada di tiap function unit test
 *
 * Memembuat Urutan Sendiri
 * Jika kita ingin membuat cara mengurutkan urutan unit test function sendiri, kita bisa dengan mudah tinggal membuat class baru turunan dari MethodOrderer interface
 */

/**
 * Sikuls Hidup Test (13)
 *
 * Secara default, lifecycle (siklus hidup) object test adalah independent per function test, artinya object unit test  akan selalu dibuat baru per function unit test, oleh karena itu kita tidak bisa bergantung dengan function test lain
 * Cara pembuatan object test di JUnit ditentukan oleh annotation @TestInstance, dimana defaultnya adalah Lifecycle.PER_METHOD, artinya tiap function akan dibuat sebuah instance / object baru
 * Kita bisa merubahnya menjado Lifecycle.PER_CLASS jika mau, dengan demikian instance / object test haya dibuat sekali per class, dan function test akan menggunakan object test yang sama
 * Hal ini bisa kita manfaatkan ketika membuat test yang tergantung dengan test lain
 *
 * Keuntungan Instance Per Class
 *
 * Salah satu keuntungan saat menggunakan Lifecycle.PER_CLASS adalah, kita bisa menggunakan @BeforeAll dan @AfterAll di function biasa, tidak harus menggunakan function object  / static
 *
 */

//@TestMethodOrder(value = MethodOrderer.OrderAnnotation::class) //sesuai dengan antotasi order yg diberikan
//@TestMethodOrder(value = MethodOrderer.Alphanumeric::class) //urut alphabet atau angka
@TestMethodOrder(value = MethodOrderer.MethodName::class) //pengganti AlphaNumeric
//@TestMethodOrder(value = MethodOrderer.Random::class)

// CATATAN, arti ::class adalah mengambil referensi kelas tanpa perlu menginisialisasi Object

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS) //(13) LifeCycle Per_class
class OrderTest {
    var counter: Int = 0;

    @BeforeAll
    fun beforeAll() {

    }

    @AfterAll
    fun afterAll() {

    }

    // By Default ordernya adalah alphanumeric (A-Z, 0-9)
    @Test
    @Order(3)
    fun testA(){
        println("testA")

        counter++
        println(counter) //1
        println(this.hashCode())
    }

    @Test
    @Order(1)
    fun testC(){
        println("testC")

        counter++
        println(counter) //1
        println(this.hashCode())
    }

    @Test
    @Order(2)
    fun testB(){
        println("testB")

        counter++
        println(counter) //1 (Tetap 1 karena object nya dibuat terpisah dalam 1 class sehingga counter akan ttp 1)
        println(this.hashCode())
    }
}