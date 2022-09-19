package com.example.sqlite_database.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDataBase(
    context: Context,
    DataBase_name: String,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, DataBase_name, factory, version) {

    companion object { // here we have defined variables for our database to easily modify
        private val tablename = "data"
        private val id = "id"
        private val password = "password"
        private val name = "name"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        //to create new table
        val table =
            "CREATE TABLE $tablename ($id INTEGER PRIMARY KEY AUTOINCREMENT,$name TEXT,$password TEXT)"
        p0?.execSQL(table)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        // this method  to check if table already exists
        p0?.execSQL("DROP TABLE IF EXISTS $tablename")
        onCreate(p0)
    }

    // This method for adding data in our database
    //this function take object from class==>table
    //that contain values of columns of table
    //and return number of column if it is added==>long
    //else return -1
    fun add(table: Table): Boolean {
        //to create object from content_values class
        val values = ContentValues()
        //to add value of record in data base
        values.put(name, table.name)
        values.put(password, table.password)
        //To perform different operations on the data base
        val db = this.writableDatabase
        // all values are inserted into database
        var res: Long = db.insert(tablename, null, values)
        return res != -1L
        // to  closing our database
        db.close()
    }

    //Returns the number of records that have been modified==>int
    //else return 0
    fun update(table: Table, recordNumber: Int): Boolean {
        //to create object from content_values class
        val values = ContentValues()
        //To perform different operations on the data base
        val db = this.writableDatabase
        //to add value of column to be modified
        values.put(name, table.name)
        values.put(password, table.password)
        val idd = recordNumber.toString()
        val arr = arrayOf(idd)
        var res: Int = db.update(tablename, values, "id=?", arr)
        return res != 0
    }

    //to return number of records=rows
    fun recordsNumber(): Long {
        val db = this.readableDatabase
        return DatabaseUtils.queryNumEntries(db, tablename)
    }

    //to delete record from table and return number of record that are deleted
    // if returns 0 that mean there are not records deleted
    fun delete(s: String): Boolean {
        val db = this.writableDatabase
        val arr = arrayOf(s)
        val res: Int = db.delete(tablename, "id=?", arr)
        return res != 0
    }

    //to return values of table we will use function returns arraylist
    //each index in list is object from table class
    fun printTable(): ArrayList<Table> {
        val list = ArrayList<Table>()
        val db = this.readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $tablename ", null)
        if (cursor.moveToFirst())//To make sure that the cursor stops at the first index
        {
            do {
                val name = cursor.getString(cursor.getColumnIndex(name))
                val password = cursor.getString(cursor.getColumnIndex(password))
                val t = Table(name, password)
                list.add(t)
            } while (cursor.moveToNext())//to check if table finish or not
            cursor.close()
        }
        return list
    }

    //to search in table
    fun searchInTable(ColumnName: String, s: String): ArrayList<Table> {
        var list = ArrayList<Table>()
        val db = this.readableDatabase
        val arr = arrayOf(s)
        val cursor: Cursor = db.rawQuery("SELECT * FROM $tablename WHERE $ColumnName=?", arr)
        if (cursor.moveToFirst())//To make sure that the cursor stops at the first index
        {
            do {
                val name = cursor.getString(cursor.getColumnIndex(name))
                val password = cursor.getString(cursor.getColumnIndex(password))
                val t = Table(name, password)
                list.add(t)
            } while (cursor.moveToNext())//to check if table finish or not
            cursor.close()
        }
        return list
    }

}