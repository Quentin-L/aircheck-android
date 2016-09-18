package greenhunan.aircheck.presenter

import greenhunan.aircheck.GlobalConfig
import greenhunan.aircheck.service.LoginRequest
import greenhunan.aircheck.views.LoginCallback

/**
 * Created by Quentin on 8/13/16.
 */
class LoginPresenter(private val request: LoginRequest, private val loginCallback: LoginCallback) {

    fun login(username: String, password: String) {
        if (request.login(username, password)) {
            loginCallback.onSuccess(request.requestInfo)
            GlobalConfig.setUser(request.user)
        } else
            loginCallback.onFailure(request.requestInfo)
    }

}
