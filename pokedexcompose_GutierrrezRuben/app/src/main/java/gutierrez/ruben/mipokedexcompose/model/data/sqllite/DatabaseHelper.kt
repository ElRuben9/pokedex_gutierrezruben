package gutierrez.ruben.mipokedexcompose.model.data.sqllite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createPokemons = "CREATE TABLE ${Contract.Pokemons.TABLE_NAME}(" +
                "${Contract.Pokemons.COLUMN_ID} INTEGER PRIMARY KEY," +
                "${Contract.Pokemons.COLUMN_NAME_NUMBER} INTEGER," +
                "${Contract.Pokemons.COLUMN_NAME_NAME} TEXT," +
                "${Contract.Pokemons.COLUMN_NAME_DESCRIPTION} TEXT," +
                "${Contract.Pokemons.COLUMN_NAME_TYPE} TEXT)"
        db?.execSQL(createPokemons)
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        val dropTablePokemons = "DROP TABLE IF EXISTS ${Contract.Pokemons.TABLE_NAME}"
        db?.execSQL(dropTablePokemons)
        onCreate(db)
    }

    companion object{
        private const val  DATABASE_NAME = "pokedex.db"
        private const val DATABASE_VERSION = 1
    }
}