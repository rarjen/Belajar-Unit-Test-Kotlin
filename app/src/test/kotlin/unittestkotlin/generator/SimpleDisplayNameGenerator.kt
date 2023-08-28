package unittestkotlin.generator

import org.junit.jupiter.api.DisplayNameGenerator
import java.lang.reflect.Method


/**
 * Mengubah Nama Test
 *
 * Kadang agak sulit membuat nama function yang merepresentasikan kasus test nya
 * Jika kita ingin menambahkan deskripsi untuk tiap test, kita bisa menggunakan annotation @DisplayName
 * Dengan menggunakan annotation @DisplayName, kita bisa menambahkan deskripsi unit testnya
 *
 */

/**
 * Display Name Generator
 *
 * JUnit mendukung pembuatan DisplayName secara otomatis menggunakan generator
 * Yang perlu kita lakukan adalah membuat class turunan dari interface DisplayNameGenerator, lalu menambahkan annotation @DisplayNameGeneration di test class nya
 *
 */

// Display name generator
class SimpleDisplayNameGenerator : DisplayNameGenerator{
    override fun generateDisplayNameForClass(p0: Class<*>): String {
        return "Test ${p0.simpleName}"
    }

    override fun generateDisplayNameForNestedClass(p0: Class<*>): String {
        return "Test ${p0.simpleName}"
    }

    override fun generateDisplayNameForMethod(p0: Class<*>, p1: Method): String {
        return "Test ${p0.simpleName}.${p1.name}"
    }

}