package pt.iade.ei.aquapoint.data

import pt.iade.ei.aquapoint.ui.classes.UserData

object UserDataRepository {
    private var currentLogin: UserData? = null

    fun getUserId(): Int? {
        return currentLogin?.id
    }

    fun getUserName(): String? {
        return currentLogin?.name
    }

    fun getUserPassword(): String? {
        return currentLogin?.password
    }

    fun setUserLogin(data: UserData) {
        currentLogin = data
    }

    fun removeData() {
        currentLogin = null
    }
}
