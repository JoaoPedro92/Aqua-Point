package pt.iade.ei.aquapoint.ui.backEndFunctions

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.serialization.json.Json
import pt.iade.ei.aquapoint.AquaPoint

object NetworkService {

    fun getAquaPoints(onResult: (String) -> Unit) {
        "http://10.0.2.2:8080/api/java/aquapoints/getAllAquaPoints/"
            .httpGet()
            .responseString { _, _, result ->
                val output = when (result) {
                    is Result.Success -> result.get()
                    is Result.Failure -> "Erro: ${result.error}"
                }
                onResult(output)
            }
    }

    fun parseAquaPoints(jsonString: String): List<AquaPoint> {
        return Json.decodeFromString(jsonString)
    }

    /*fun getUsers(onResult: (String) -> Unit) {
        "http://10.0.2.2:8080/api/java/users/getAllUsers/"
            .httpGet()
            .responseString { _, _, result ->
                val output = when (result) {
                    is Result.Success -> result.get()
                    is Result.Failure -> "Erro: ${result.error}"
                }
                onResult(output)
            }
    }*/
}
