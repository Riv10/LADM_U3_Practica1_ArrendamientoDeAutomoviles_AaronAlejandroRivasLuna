package mx.edu.ittepic.ladm_u3_practica1_arrendamientodeautomoviles_aaronalejandrorivasluna

import android.content.ContentValues
import android.database.sqlite.SQLiteException
import java.text.SimpleDateFormat
import java.util.*

class Arrendamiento (este : MainActivity) {
    var idArrenda = 0
    var nombre = ""
    var domicilio = ""
    var licenciaCond = ""
    var idAuto = 0
    var marca = ""
    var modelo = ""
    var fecha = "YYYY-MM-DD"
    private var este = este
    private var err = ""
    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")

    //INSERCCION
    fun insertar(): Boolean {
        val baseDatos = BaseDatos(este, "RentaDeAutos", null, 1)
        err = ""
        try {
            val tabla = baseDatos.writableDatabase
            var datos = ContentValues()

            var SQL_SELECT = "SELECT IDAUTO FROM AUTOMOVIL WHERE MARCA = ? AND MODELO = ?"

            var cursor = tabla.rawQuery(SQL_SELECT, arrayOf(marca, modelo))
            if (cursor.moveToFirst()) {
                idAuto = cursor.getInt(0)
            } else {
                return false
            }

            fecha = sdf.format(Date())
            datos.put("NOMBRE", nombre)
            datos.put("DOMICILIO", domicilio)
            datos.put("LICENCIACOND", licenciaCond)
            datos.put("IDAUTO", idAuto)
            datos.put("FECHA", fecha)

            var resultado = tabla.insert("ARRENDAMIENTO", "IDARRENDA", datos)
            if (resultado == -1L) {
                return false
            }
        } catch (err: SQLiteException) {
            this.err = err.message!!
            return false
        } finally {
            baseDatos.close()
        }
        return true
    }

    //ELIMINACIÓN
    fun eliminar(): Boolean {
        val basedatos = BaseDatos(este, "RentaDeAutos", null, 1)
        err = ""
        try {
            var tabla = basedatos.writableDatabase
            val resultado = tabla.delete("ARRENDAMIENTO", "IDARRENDA=?", arrayOf(idAuto.toString()))

            if (resultado == 0) {
                return false
            }

        } catch (err: SQLiteException) {
            this.err = err.message!!
            return false
        } finally {
            basedatos.close()
        }
        return true
    }

    //ACTUALIZAR
    fun actualizar(): Boolean {
        val basedatos = BaseDatos(este, "RentaDeAutos", null, 1)
        err = ""
        try {
            val tabla = basedatos.writableDatabase
            val datosActualizados = ContentValues()

            datosActualizados.put("NOMBRE", nombre)
            datosActualizados.put("DOMICILIO", domicilio)
            datosActualizados.put("LICENCIACOND", licenciaCond)
            datosActualizados.put("IDAUTO", idAuto)

            val respuesta = tabla.update(
                "ARRENDAMIENTO",
                datosActualizados,
                "IDARRENDA=?",
                arrayOf(idArrenda.toString())
            )

            if (respuesta == 0) {
                return false
            }

        } catch (err: SQLiteException) {
            this.err = err.message!!
            return false
        } finally {
            basedatos.close()
        }
        return true
    }

    //CONSULTAS
    fun mostrarArrendamientoNombre(nombre: String): ArrayList<Arrendamiento> {
        val basedatos = BaseDatos(este, "RentaDeAutos", null, 1)
        err = ""
        val arreglo = ArrayList<Arrendamiento>()
        var arrendamiento = Arrendamiento(este)

        try {
            var tabla = basedatos.readableDatabase
            val SQLSELECT = "SELECT * FROM ARRENDAMIENTO WHERE NOMBRE = ?"

            var cursor = tabla.rawQuery(SQLSELECT, arrayOf(nombre))

            if (cursor.moveToFirst()) {
                do {
                    arrendamiento.idArrenda = cursor.getInt(0)
                    arrendamiento.nombre = cursor.getString(1)
                    arrendamiento.domicilio = cursor.getString(2)
                    arrendamiento.licenciaCond = cursor.getString(3)
                    arrendamiento.idAuto = cursor.getInt(4)
                    arrendamiento.fecha = cursor.getString(5)
                    arreglo.add(arrendamiento)
                } while (cursor.moveToNext())
            }

        } catch (err: SQLiteException) {
            this.err = err.message!!
        } finally {
            basedatos.close()
        }
        return arreglo
    }

    fun mostrarArrendamientoLicencia(licencia: String): ArrayList<Arrendamiento> {
        val basedatos = BaseDatos(este, "RentaDeAutos", null, 1)
        err = ""
        val arreglo = ArrayList<Arrendamiento>()
        var arrendamiento = Arrendamiento(este)

        try {
            var tabla = basedatos.readableDatabase
            val SQLSELECT = "SELECT * FROM ARRENDAMIENTO WHERE LICENCIACOND  = ?"

            var cursor = tabla.rawQuery(SQLSELECT, arrayOf(licenciaCond))

            if (cursor.moveToFirst()) {
                do {
                    arrendamiento.idArrenda = cursor.getInt(0)
                    arrendamiento.nombre = cursor.getString(1)
                    arrendamiento.domicilio = cursor.getString(2)
                    arrendamiento.licenciaCond = cursor.getString(3)
                    arrendamiento.idAuto = cursor.getInt(4)
                    arrendamiento.fecha = cursor.getString(5)
                    arreglo.add(arrendamiento)
                } while (cursor.moveToNext())
            }

        } catch (err: SQLiteException) {
            this.err = err.message!!
        } finally {
            basedatos.close()
        }
        return arreglo
    }

    fun mostrarArrendamientoDomicilio(domicilio: String): ArrayList<Arrendamiento> {
        val basedatos = BaseDatos(este, "RentaDeAutos", null, 1)
        err = ""
        val arreglo = ArrayList<Arrendamiento>()
        var arrendamiento = Arrendamiento(este)

        try {
            var tabla = basedatos.readableDatabase
            val SQLSELECT = "SELECT * FROM ARRENDAMIENTO WHERE DOMICILIO  = ?"

            var cursor = tabla.rawQuery(SQLSELECT, arrayOf(domicilio))

            if (cursor.moveToFirst()) {
                do {
                    arrendamiento.idArrenda = cursor.getInt(0)
                    arrendamiento.nombre = cursor.getString(1)
                    arrendamiento.domicilio = cursor.getString(2)
                    arrendamiento.licenciaCond = cursor.getString(3)
                    arrendamiento.idAuto = cursor.getInt(4)
                    arrendamiento.fecha = cursor.getString(5)
                    arreglo.add(arrendamiento)
                } while (cursor.moveToNext())
            }

        } catch (err: SQLiteException) {
            this.err = err.message!!
        } finally {
            basedatos.close()
        }
        return arreglo
    }

    fun mostrarArrendamientoMarca(marca: String): ArrayList<Arrendamiento> {
        val basedatos = BaseDatos(este, "RentaDeAutos", null, 1)
        err = ""
        val arreglo = ArrayList<Arrendamiento>()
        var arrendamiento = Arrendamiento(este)

        try {
            var tabla = basedatos.readableDatabase
            val SQLSELECT = "SELECT * FROM ARRENDAMIENTO WHERE IDAUTO = (SELECT IDAUTO FROM AUTOMOVIL WHERE MARCA = ?)"

            var cursor = tabla.rawQuery(SQLSELECT, arrayOf(marca))

            if (cursor.moveToFirst()) {
                do {
                    arrendamiento.idArrenda = cursor.getInt(0)
                    arrendamiento.nombre = cursor.getString(1)
                    arrendamiento.domicilio = cursor.getString(2)
                    arrendamiento.licenciaCond = cursor.getString(3)
                    arrendamiento.idAuto = cursor.getInt(4)
                    arrendamiento.fecha = cursor.getString(5)
                    arreglo.add(arrendamiento)
                } while (cursor.moveToNext())
            }

        } catch (err: SQLiteException) {
            this.err = err.message!!
        } finally {
            basedatos.close()
        }
        return arreglo
    }

    fun mostrarArrendamientoModelo(modelo: String): ArrayList<Arrendamiento> {
        val basedatos = BaseDatos(este, "RentaDeAutos", null, 1)
        err = ""
        val arreglo = ArrayList<Arrendamiento>()
        var arrendamiento = Arrendamiento(este)

        try {
            var tabla = basedatos.readableDatabase
            val SQLSELECT = "SELECT * FROM ARRENDAMIENTO WHERE IDAUTO = (SELECT IDAUTO FROM AUTOMOVIL WHERE MODELO = ?)"

            var cursor = tabla.rawQuery(SQLSELECT, arrayOf(modelo))

            if (cursor.moveToFirst()) {
                do {
                    arrendamiento.idArrenda = cursor.getInt(0)
                    arrendamiento.nombre = cursor.getString(1)
                    arrendamiento.domicilio = cursor.getString(2)
                    arrendamiento.licenciaCond = cursor.getString(3)
                    arrendamiento.idAuto = cursor.getInt(4)
                    arrendamiento.fecha = cursor.getString(5)
                    arreglo.add(arrendamiento)
                } while (cursor.moveToNext())
            }

        } catch (err: SQLiteException) {
            this.err = err.message!!
        } finally {
            basedatos.close()
        }
        return arreglo
    }

}
