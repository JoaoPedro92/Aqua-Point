package pt.iade.ei.aquapoint.data

import android.util.Log
import pt.iade.ei.aquapoint.ui.classes.UserData

object UserCoordinates {
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

    fun restoreDefaults() {
        latitude = 38.78166399699406
        longitude = -9.102570032326907
    }
}
