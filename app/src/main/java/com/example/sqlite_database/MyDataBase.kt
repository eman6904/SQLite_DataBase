package com.example.sqlite_database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDataBase(context: Context,DataBase_name:String, factory: SQLiteDatabase.CursorFactory?,version:Int)
    :SQLiteOpenHelper(context,DataBase_name,factory,version) {

    companion object
   { // here we have defined variables for our database to easily modify
      private val tablename="data"
       private val id="id"
       private val password="password"
       private val name="name"
   }
    override fun onCreate(p0: SQLiteDatabase?) {
        //to create new table
       val table="CREATE TABLE $tablename ($id INTEGER PRIMARY KEY AUTOINCREMENT,$name TEXT,$password TEXT)"
        p0?.execSQL(table)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        // this method is to check if table already exists
        p0?.execSQL("DROP TABLE IF EXISTS $tablename")
        onCreate(p0)
    }
    // This method is for adding data in our database
    //this function take object from class==>table
    //that contain values of columns of table
    //and return number of column if it is added==>long
    //else if not return -1
    fun add(table:table):Boolean{
        //to create object from content_values class
        val values = ContentValues()
        //to add value of column in data base
        values.put(name, table.name)
        values.put(password, table.password)
        //To perform different operations on the data base
        val db = this.writableDatabase
        // all values are inserted into database
      var res:Long=db.insert(tablename, null, values)
        return res!=-1L
        // to  closing our database
        db.close()
    }
    //Returns the number of columns that have been modified==int
    //If it does not modify any column it return 0
    fun update(table:table,column_name:String,s:String):Boolean
    {
        //to create object from content_values class
        val values = ContentValues()
        //To perform different operations on the data base
        val db = this.writableDatabase
        //to add value of column to be modified
        values.put(name, table.name)
        values.put(password, table.password)
        val arr= arrayOf(s)
        var res:Int=db.update(tablename, values,"$column_name=?",arr)
        return res!=0
    }
    //to return number of records
    fun record_number():Long
    {
        val db=this.readableDatabase
        return DatabaseUtils.queryNumEntries(db, tablename)
    }
  //to delete column from table,return number of column that are deleted
    // if return 0 that mean there are not columns deleted
  fun delete(table:table,i:Int):Boolean {
      val db = this.writableDatabase
      val res: Int = db.delete(tablename, "$i", null)
      return res != 0
  }
    //to return values of table we will use function returns arraylist
    //every index of it is object from table class
    fun return_table():ArrayList<table>
    {
      val list=ArrayList<table>()
        val db=this.readableDatabase
        val cursor:Cursor=db.rawQuery("SELECT * FROM $tablename ",null)
        if(cursor.moveToFirst())//To make sure that the cursor stops at the first index
        {
            do {
               val name=cursor.getString(cursor.getColumnIndex(name))
                val password=cursor.getString(cursor.getColumnIndex(Companion.password))
                val t=table(name,password)
                list.add(t)
            }while(cursor.moveToNext())//to check if table finish or not
            cursor.close()
        }
        return list
    }
    //to search in table
    fun search_in_table(colum_name:String,s:String):ArrayList<table>
    {
        var list=ArrayList<table>()
        val db=this.readableDatabase
        val arr= arrayOf(s)
        val cursor:Cursor=db.rawQuery("SELECT * FROM $tablename WHERE $colum_name=?",arr)
        if(cursor.moveToFirst())//To make sure that the cursor stops at the first index
        {
            do {
                val name=cursor.getString(cursor.getColumnIndex(name))
                val password=cursor.getString(cursor.getColumnIndex(Companion.password))
                val t=table(name,password)
                list.add(t)
            }while(cursor.moveToNext())//to check if table finish or not
            cursor.close()
        }
        return list
    }

}