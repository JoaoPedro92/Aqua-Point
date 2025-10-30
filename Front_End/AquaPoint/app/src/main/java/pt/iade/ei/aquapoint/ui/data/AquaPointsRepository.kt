package pt.iade.ei.aquapoint.data

import pt.iade.ei.aquapoint.AquaPoint

object AquaPointsRepository {
    private var cache: List<AquaPoint>? = null

    fun getCached(): List<AquaPoint>? {
        return cache
    }

    fun setCache(list: List<AquaPoint>) {
        cache = list
    }

    fun clearCache() {
        cache = null
    }

    fun hasCache(): Boolean {
        return cache != null
    }
}
