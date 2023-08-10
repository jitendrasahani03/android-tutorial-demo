package com.example.roomdatabase3

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
@Dao
interface PersonDao {
    @Query("SELECT * FROM personTb")
    fun getAllPerson():LiveData<List<Person>>

    @Insert
    fun insertPerson(person: Person)

    @Delete
    fun deletePerson(person: Person)

    @Update
    fun updatePerson(person: Person)
}