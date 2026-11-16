package gutierrez.ruben.mipokedexcompose.model.data.sqllite

import android.provider.BaseColumns

object Contract {
    object Pokemons: BaseColumns{
        const val TABLE_NAME = "pokemons"
        const val COLUMN_ID = "_id"
        const val COLUMN_NAME_NUMBER = "number"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_TYPE = "type"
        const val COLUMN_NAME_DESCRIPTION = "description"
    }
}