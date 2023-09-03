package unittestkotlin

import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.junit.jupiter.api.Assertions.*

/**
 * Pengenalan Mocking
 *
 * Ketergantungan Antar Class
 *
 * Saat membuat aplikasi yang besar, source code pun akan semakin besar, struktur class pun akan semakin kompleks
 * Kita tidak bisa memungkiri lagi bahwa akan ada ketergantungan antar class
 * Unit test yang bagus itu bisa terprediksi dan cukup nge test ke satu function, jika harus mengetes interaksi antar class, lebih disarankan integration test
 * Lantas bagaimana jika kita harus mengetest class yang ketergantungan dengan class lain?
 * Solusinya adalah melakukan mocking terhadap dependency yang dibutuhkan class yang akan kita test
 *
 * Pengenalan Mocking
 * Mocking sederhananya adalah membuat object tiruan
 * Hal ini dilakukan agar behavior object tersebut bisa kita tentukan sesuai dengan keinginan kita
 * Dengan mocking, kita bisa membuat request response seolah-olah object tersebut benar dibuat
 *
 * Pengenalan Mockito
 *
 * Ada banyak framework untuk melakukan mocking, namun di materi ini kita akan menggunakan Mockito
 * Mockito adalah salah satu mocking framework paling populer di Java, dan bisa digunakan juga untuk Kotlin
 * Dan Mockito bisa diintegrasikan baik dengan JUnit
 * https://site.mockito.org/
 *
 */
class MockTest {
    @Test
    fun testMock(){
        // Ini merupakan cara biasanya. Namun kita bisa menggunakan mock dari list nya dengan mockito
//        val list: List<String> = listOf("Otniel", "Kevin")

        // Melakukan mockup list string
        val list: List<String> = Mockito.mock(List::class.java) as List<String>

        // akan tetap null krn blm menambahkan behavior
//        println(list.get(0)) //null
//        println(list.get(1)) //null

        // Menambahkan behavior
        Mockito.`when`(list.get(0)).thenReturn("Otniel") // saat mendapat value 0 maka akan mengembalikan Otniel
        Mockito.`when`(list.get(1)).thenReturn("Kevin")

//        println(list.get(0)) //Otniel
//        println(list.get(1)) //Kevin

        assertEquals("Otniel", list.get(0))
        assertEquals("Otniel", list.get(0))
        assertEquals("Kevin", list.get(1))

        // Verifikasi berapa kali mockito dipanggil
        Mockito.verify(list, Mockito.times(2)).get(0); // Melakukan verifikiasi bahwa telah dipanggil 2x dengan functiom get(0)
        Mockito.verify(list).get(1);
    }
}