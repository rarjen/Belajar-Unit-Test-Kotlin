package unittestkotlin.service

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import unittestkotlin.model.Person
import unittestkotlin.repository.PersonRepository
import java.lang.Exception

@Extensions(value = [
    ExtendWith(MockitoExtension::class)
])
class PersonServiceTest {
    // Keuntungan MockitoExtension adalah kita tidak perlu membuat mock secara manual dan akan dibuat secara otomatis

    // lateinit adalah suatu perintah agar variable tidak wajib di inisialisasi terlebih dahulu
    @Mock
    lateinit var personRepository: PersonRepository

    // Tidak perlu di mock karena harus tetap dibuat manual
    lateinit var personService: PersonService

    @BeforeEach
    fun beforeEach() {
        personService = PersonService(personRepository)
    }

    @Test
    fun testPersonidIsNotValid(){
        assertThrows<IllegalArgumentException> {
            personService.get("     ")
        }
    }

    @Test
    fun testPersonNotFound(){
        assertThrows<Exception> {
            personService.get("not found")
        }
    }

    @Test
    fun testPersonSuccess(){
        Mockito.`when`(personRepository.selectById("otniel")).thenReturn(Person("otniel", "Otniel Kevin"))

        val person = personService.get("otniel");
        assertEquals("otniel", person.id)
        assertEquals("Otniel Kevin", person.name)
    }

    @Test
    fun testRegisterPersonNameIsBlank(){
        assertThrows<IllegalArgumentException> {
            personService.register("    ")
        }
    }

    @Test
    fun testRegsiterSuccess() {
        val person = personService.register("Otniel")

        assertEquals("Otniel", person.name)
        assertNotNull(person.id)

        Mockito.verify(personRepository, Mockito.times(1)).insert(Person(person.id, "Otniel"))
    }
}