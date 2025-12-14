package pt.iade.ei.aquapoint.data

import android.util.Log
import pt.iade.ei.aquapoint.ui.classes.UserData

object UserCoordinates {
    private var startLatitude: Double = 38.78166399699406 // usado para restaurar coordenadas facilmente
    private var startLongitude: Double = -9.102570032326907 // usado para restaurar coordenadas facilmente
    private var latitude: Double = 38.78166399699406
    private var longitude: Double = -9.102570032326907

    fun getLatitude(): Double {
        return latitude
    }

    fun getLongitude(): Double {
        return longitude
    }

    fun setLatitude(lat: Double) {
        latitude = lat
    }

    fun setLongitude(long: Double) {
        longitude = long
    }

    fun setStartLatitude(lat: Double) {
        startLatitude = lat
    }

    fun setStartLongitude(long: Double) {
        startLongitude = long
    }

    fun restoreDefaults() {
        latitude = startLatitude
        longitude = startLongitude
    }
}
