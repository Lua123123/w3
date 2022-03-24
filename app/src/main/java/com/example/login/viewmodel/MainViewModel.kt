package com.example.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.login.model.DataStore
import kotlinx.coroutines.*

class Resp(val isSuccess: Boolean, val error: Error?)
class MainViewModel : ViewModel() {



    private var _isSuccessEvent: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccessEvent: LiveData<Boolean>
        get() = _isSuccessEvent

    private var _isErrorEvent: MutableLiveData<String> = MutableLiveData()
    val isErrorEvent: LiveData<String>
        get() = _isErrorEvent

    var job: Job? = null

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun registerUser(email: String, passWord: String, fullName: String) {
        val isValidEmail = isEmailValid(email)
        if (!isValidEmail) {
            _isErrorEvent.postValue("email không hợp lệ")
            return
        }

        val isValidPassword = isPasswordValid(passWord)
        if (!isValidPassword) {
            _isErrorEvent.postValue("password không hợp lệ")
            return
        }

        val isFullnameValid = isFullnameValid(fullName)
        if (!isFullnameValid) {
            _isErrorEvent.postValue("password không hợp lệ")
            return
        }

        _isSuccessEvent.postValue(true)
    }

    fun LoginUser(email: String, passWord: String) {
        val isValidEmail = isEmailValid(email)
        if (!isValidEmail) {
            _isErrorEvent.postValue("email không hợp lệ")
            return
        }

        val isValidPassword = isPasswordValid(passWord)
        if (!isValidPassword) {
            _isErrorEvent.postValue("password không hợp lệ")
            return
        }
        _isSuccessEvent.postValue(true)
    }



    private fun isEmailValid(emailFun: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(emailFun).matches()
    }

    private fun isPasswordValid(passWordFun: String): Boolean {
        return passWordFun.length in 7..10
    }

    private fun isFullnameValid(fullNameFun: String): Boolean {
        return fullNameFun.length in 1..30
    }

}
