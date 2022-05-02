package mx.edu.ittepic.ladm_u3_practica1_arrendamientodeautomoviles_aaronalejandrorivasluna

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteException

class Automovil (este : Context) {
    var idAuto = 0
    var modelo =""
    var marca = ""
    var kilometraje = 0
    private var este = este
    private var err=""

    //INSERCCION
    fun insertar () : Boolean{
        val basedatos= BaseDatos(este,"RentaDeAutos",null,1)
        err = ""
        try {
            val tabla = basedatos.writableDatabase
            var datos = ContentValues()

            datos.put("MODELO", modelo)
            datos.put("MARCA",marca)
            datos.put("KILOMETRAJE",kilometraje)

            val respuesta = tabla.insert("AUTOMOVIL",null,datos)

            if(respuesta == -1L){
                return false
            }

        }catch (err: SQLiteException){
            this.err = err.message!!
            return false
        }finally {
            basedatos.close()
        }
        return true
    }

    //ELIMINACIÓN
    fun eliminar():Boolean{
        val basedatos= BaseDatos(este,"RentaDeAutos",null,1)
        err = ""
        try {
            var tabla = basedatos.writableDatabase
            val resultado = tabla.delete("AUTOMOVIL", "IDAUTO=?",arrayOf(idAuto.toString()))

            if (resultado==0){
                return false
            }

        }catch (err:SQLiteException){
            this.err = err.message!!
            return false
        }finally {
            basedatos.close()
        }
        return true
    }

    //ACTUALIZACIÓN
    fun actualizar():Boolean{
        val basedatos= BaseDatos(este,"RentaDeAutos",null,1)
        err = ""
        try {
            val tabla = basedatos.writableDatabase
            val datosActualizados = ContentValues()

            datosActualizados.put("MARCA",marca)
            datosActualizados.put("MODELO", modelo)
            datosActualizados.put("KILOMETRAJE", kilometraje)

            val respuesta = tabla.update("AUTOMOVIL",datosActualizados,"IDAUTO=?", arrayOf(idAuto.toString()))

            if(respuesta==0){
                return false
            }

        }catch (err:SQLiteException){
            this.err = err.message!!
            return false
        }finally {
            basedatos.close()
        }
        return true
    }

    //CONSULTAS
    fun mostrarAutomovilModelo(modelo:String): ArrayList<Automovil>{
        val basedatos= BaseDatos(este,"RentaDeAutos",null,1)
        err = ""
        val arreglo = ArrayList<Automovil>()
        var automovil = Automovil(este)

        try {
            var tabla = basedatos.readableDatabase
            val SQLSELECT = "SELECT * FROM AUTOMOVIL WHERE MODELO = ?"

            var cursor = tabla.rawQuery(SQLSELECT, arrayOf(modelo))

            if(cursor.moveToFirst()){
                do{
                    automovil.idAuto =cursor.getInt(0)
                    automovil.marca = cursor.getString(1)
                    automovil.modelo = cursor.getString(2)
                    automovil.kilometraje = cursor.getInt(3)
                    arreglo.add(automovil)
                } while(cursor.moveToNext())
            }

        }catch (err:SQLiteException){
            this.err = err.message!!
        }finally {
            basedatos.close()
        }
        return arreglo
    }

    fun mostrarAutomovilMarca(marca:String): ArrayList<Automovil>{
        val basedatos= BaseDatos(este,"RentaDeAutos",null,1)
        err = ""
        val arreglo = ArrayList<Automovil>()
        var automovil = Automovil(este)

        try {
            var tabla = basedatos.readableDatabase
            val SQLSELECT = "SELECT * FROM AUTOMOVIL WHERE MARCA = ?"

            var cursor = tabla.rawQuery(SQLSELECT, arrayOf(marca))

            if(cursor.moveToFirst()){
               do{
                   automovil.idAuto =cursor.getInt(0)
                   automovil.marca = cursor.getString(1)
                   automovil.modelo = cursor.getString(2)
                   automovil.kilometraje = cursor.getInt(3)
                   arreglo.add(automovil)
               } while(cursor.moveToNext())
            }
        }catch (err:SQLiteException){
            this.err = err.message!!
        }finally {
            basedatos.close()
        }
        return arreglo
    }

    fun mostrarAutomovilKilometraje(kilometraje:String): ArrayList<Automovil>{
        val basedatos= BaseDatos(este,"RentaDeAutos",null,1)
        err = ""
        val arreglo = ArrayList<Automovil>()
        var automovil = Automovil(este)

        try {
            var tabla = basedatos.readableDatabase
            val SQLSELECT = "SELECT * FROM AUTOMOVIL WHERE KILOMETRAJE <= ?"

            var cursor = tabla.rawQuery(SQLSELECT, arrayOf(kilometraje))

            if(cursor.moveToFirst()){
                do{
                    automovil.idAuto =cursor.getInt(0)
                    automovil.marca = cursor.getString(1)
                    automovil.modelo = cursor.getString(2)
                    automovil.kilometraje = cursor.getInt(3)
                    arreglo.add(automovil)
                } while(cursor.moveToNext())
            }

        }catch (err:SQLiteException){
            this.err = err.message!!
        }finally {
            basedatos.close()
        }
        return arreglo
    }

}