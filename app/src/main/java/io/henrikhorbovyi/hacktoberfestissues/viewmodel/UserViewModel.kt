package io.henrikhorbovyi.hacktoberfestissues.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.henrikhorbovyi.hacktoberfestissues.data.user.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel() {


    fun authenticate() {
        viewModelScope.launch {
            userRepository.authenticate("CLIENT_ID")
        }
    }
}