package unittestkotlin

import  org.junit.jupiter.api.*
import  org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.condition.*

// Test berdasarkan kondisi (10)
/**
 * Fitur yang lebih mudah utk pengecekan beberapa kondisi, sperti kondisi sistem opr, versi java, system prop, env variable
 * Lebih mudah dibandingkan Assumptions
 */

/**
 * Kondisi Sistem Operasi
 * @EnableOnOs -> penanda bahwa unit test boleh berjalan pada OS yg ditentukan
 * @DisableOnOs -> penanda bahwa unit test tdk boleh berjalan pd OS yang ditentukan
 *
 * Kondisi Versi Java
 * @EnableOnJre
 * @DisableOnJre
 * @EnableForJreRange
 * @DisableForJreRange
 *
 * Kondisi System Property
 * @EnabledIfSystemProperty -> akan berjalan jika system property sesuai dgn yg ditentukan
 * @DisableIfSystemProperty -> tdk boleh berjalan jika system property sesuai dg yg ditentukan
 * Jika kondisi lebih dari 1, kita bisa menggunakan @EnableIfSystemProperties dan @DisableIfSystemProperties
 *
 * Kondisi Environtment Variable
 * @EnableIfEnvirontmentVariable -> boleh run jk env variable sesuai dg yg ditentukan
 * @DisableEnvirontmentVariable -> tdk boleh run jk env variable sesuai dg yg ditentukan
 * @EnabledIfEnvirontmentVariables & @DisabledIfEnvirontmentVariables -> untuk kondisi lebih dari 1
 */

class ConditionalTest {

    // OS
    @Test
    @EnabledOnOs(value = [OS.WINDOWS, OS.LINUX])
    fun enableOnWindowsOrLinux(){
        // only run in windows
    }

    @Test
    @DisabledOnOs(value = [OS.WINDOWS])
    fun disabledOnWindows(){
        // cant run in windows
    }

    // Java
    @Test
    @EnabledOnJre(value = [JRE.JAVA_20])
    fun onlyRunOnJava20(){
    }

    @Test
    @DisabledOnJre(value = [JRE.JAVA_20])
    fun disabledRunOnJava20(){
    }

    // System Property

    @Test
    @EnabledIfSystemProperty(named = "java.vendor", matches = "Oracle Corporation")
    fun enableOnOracle(){
        // enable if java from oracle
    }

    // Multi Conditional
    @Test
    @EnabledIfSystemProperties(value = [
        EnabledIfSystemProperty(named = "java.vendor", matches = "Oracle Corporation"),
        EnabledIfSystemProperty(named = "os.version", matches = "10.0")
    ])
    fun enableOnOracleMulti() {
        // Multi
    }

    @Test
    @DisabledIfSystemProperty(named = "java.vendor", matches = "Oracle Corporation")
    fun disabledOnOracle() {
        // Disable if Java from Oracle
    }

    // Print system properties
    @Test fun printSystemProperties(){
        System.getProperties().forEach{key, value ->
            println("$key: $value")
        }
    }

    // Kondisi Environment Variable

    @Test
    @EnabledIfEnvironmentVariable(named = "PROFILE", matches = "DEV")
    fun enableOnDev() {
        // run when profile is dev
    }

    @Test
    @DisabledIfEnvironmentVariable(named = "PROFILE", matches = "DEV")
    fun disabledOnDev() {
        // disable if profile is dev
    }
}