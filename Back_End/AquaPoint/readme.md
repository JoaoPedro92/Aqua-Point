Exemplos para android studio:

fun createUser() {
    val json = JSONObject()
    json.put("name", "testeUser")
    json.put("email", "teste@example.com")
    json.put("password", "1234")

    "http://10.0.2.2:8080/api/java/users/createNewUser/".httpPost()
        .header(Headers.CONTENT_TYPE, "application/json")
        .body(json.toString())
        .response { request, response, result ->
            Log.d("TESTES", String(response.data))
        }
}



fun updateUser() {
    val json = JSONObject()
    json.put("id", "16")
    json.put("name", "testeUser")
    json.put("email", "teste@example.com")
    json.put("password", "12345")
    json.put("joined", "2025-12-12")

    "http://10.0.2.2:8080/api/java/users/editUserData/".httpPost()
        .header(Headers.CONTENT_TYPE, "application/json")
        .body(json.toString())
        .response { request, response, result ->
            Log.d("TESTES", String(response.data))
        }
}


fun getUser() {

    "http://10.0.2.2:8080/api/java/users/getUserDataByName/".httpPost()
        .header(Headers.CONTENT_TYPE, "application/json")
        .body("testeUser")
        .response { request, response, result ->
            Log.d("TESTES", String(response.data))
        }
}


fun getAquaPoint() {

    "http://10.0.2.2:8080/api/java/aquapoints/getAquaPointById/".httpPost()
        .header(Headers.CONTENT_TYPE, "application/json")
        .body("2")
        .response { request, response, result ->
            Log.d("TESTES", String(response.data))
        }
}

fun createAquaPoint() {
    val json = JSONObject()

    json.put("point_name", "testAquaPoint")
    json.put("point_type", "1")
    json.put("local_id", "1")
    json.put("latitude", "32")
    json.put("longitude", "16")

    "http://10.0.2.2:8080/api/java/aquapoints/createNewAquaPoint/".httpPost()
        .header(Headers.CONTENT_TYPE, "application/json")
        .body(json.toString())
        .response { request, response, result ->
            Log.d("TESTES", String(response.data))
        }
}

fun editAquaPoint() {
    val json = JSONObject()
    json.put("id", "25")
    json.put("point_name", "testAquaPoint")
    json.put("point_type", "1")
    json.put("local_id", "1")
    json.put("latitude", "32")
    json.put("longitude", "16")

    "http://10.0.2.2:8080/api/java/aquapoints/editAquaPointData/".httpPost()
        .header(Headers.CONTENT_TYPE, "application/json")
        .body(json.toString())
        .response { request, response, result ->
            Log.d("TESTES", String(response.data))
        }
}