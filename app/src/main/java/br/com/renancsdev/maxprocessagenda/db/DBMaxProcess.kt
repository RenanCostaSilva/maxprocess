package br.com.renancsdev.maxprocessagenda.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.renancsdev.maxprocessagenda.dao.ClienteDao
import br.com.renancsdev.maxprocessagenda.model.Cliente

@Database(entities = [Cliente::class] , version = 1 , exportSchema = false)
abstract class DBMaxProcess : RoomDatabase(){

    abstract fun clienteDao(): ClienteDao

    companion object{


        /*private var INSTANCE: DBMaxProcess? = null
        fun getInstance(context: Context): DBMaxProcess =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DBMaxProcess::class.java,
                "maxprocess.db"
            ).build()*/

        @Volatile
        private var INSTANCE: DBMaxProcess? = null

        fun getInstance(context: Context): DBMaxProcess{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DBMaxProcess::class.java,
                    "maxprocess.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }

}