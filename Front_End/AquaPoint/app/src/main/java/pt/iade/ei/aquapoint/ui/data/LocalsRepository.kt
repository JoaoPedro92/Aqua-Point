package pt.iade.ei.aquapoint.data

import pt.iade.ei.aquapoint.ui.backEndFunctions.NetworkService
import pt.iade.ei.aquapoint.ui.classes.AquaPoint
import pt.iade.ei.aquapoint.ui.classes.LocalsDataClass


object LocalsRepository {
    private var cache: List<LocalsDataClass>? = null

    fun getCached(): List<LocalsDataClass>? {
        return LocalsRepository.cache
    }

    fun setCache(list: List<LocalsDataClass>) {
        LocalsRepository.cache = list
    }

    fun clearCache() {
        LocalsRepository.cache = null
    }

    fun updateLocals(onComplete: (List<LocalsDataClass>) -> Unit) {
        NetworkService.getLocalsData { jsonString ->
            try {
                val locals = NetworkService.parseLocalsData(jsonString)
                setCache(locals)
                onComplete(locals)
            } catch (e: Exception) {
                onComplete(emptyList())
            }
        }
    }

    fun hasCache(): Boolean {
        return LocalsRepository.cache != null
    }
}
