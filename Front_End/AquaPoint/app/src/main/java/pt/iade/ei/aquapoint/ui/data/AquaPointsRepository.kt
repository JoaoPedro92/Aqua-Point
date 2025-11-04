package pt.iade.ei.aquapoint.data

import pt.iade.ei.aquapoint.AquaPoint
import kotlin.collections.List

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

    fun getPointById(id: Int): AquaPoint? {
        val list: List<AquaPoint> = getCached() ?: emptyList()

        return list.find { aquaPoint -> aquaPoint.id == id }
    }

    fun hasCache(): Boolean {
        return cache != null
    }
}
