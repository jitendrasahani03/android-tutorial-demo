package com.example.databaseexample

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//extends SQLiteOpenHelper class
class MyDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "MyContact"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "Contact"

        //create table column name here
        private const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_PHONE_NO = "phone"
    }

    override fun onCreate(db: SQLiteDatabase) {
        //create Query here
        val createTable =
            "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,$COLUMN_NAME TEXT, $COLUMN_PHONE_NO TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
// this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addContact(name: String, phone: String) {
        val values = ContentValues()
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_PHONE_NO, phone)

        //calling database object
        val database = this.writableDatabase
        database.insert(TABLE_NAME, null, values)
        //database.close()
    }

    fun updateContact() {
        val updateQuery = "UPDATE $TABLE_NAME SET $COLUMN_PHONE_NO='123456123' WHERE $COLUMN_ID=1"
        val database = this.writableDatabase
        database.execSQL(updateQuery)
    }

    fun fetchAllContacts(): Cursor {


        return readableDatabase.rawQuery(
            "SELECT * FROM $TABLE_NAME WHERE $COLUMN_NAME = ? AND $COLUMN_PHONE_NO = ?",
            arrayOf("Jitendra", "9800198909")
        )

    }

    public fun deleteContact() {
        //val deleteQuery = "DELETE FROM $TABLE_NAME"
        val database = this.writableDatabase
        // database.execSQL(deleteQuery)
        database.delete(TABLE_NAME, "$COLUMN_PHONE_NO= ?", arrayOf("9800198909"))
    }
}