package unittestkotlin.repository

import unittestkotlin.model.Person


// Sebuah kontrak dari data model person ke database
// Anggap saja adalah kontrak untuk integrasi dg database, jadi tempat untuk definisikan function" yg berinteraksi ke database
// Tinggal buat implementasi class nya utk terkoneksi ke database
interface PersonRepository {

    // Bisa null karena misal kita tidak dpt datanya di db
    fun selectById(id: String): Person?

    //(24)
    fun insert(person: Person)
}