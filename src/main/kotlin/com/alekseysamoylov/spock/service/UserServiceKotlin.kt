package com.alekseysamoylov.spock.service

import com.alekseysamoylov.spock.entity.Role
import com.alekseysamoylov.spock.entity.User
import com.alekseysamoylov.spock.exception.AuthenticationException
import com.alekseysamoylov.spock.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class UserServiceKotlin @Autowired constructor(private val userRepository: UserRepository) {

  fun processUser(name: String): Boolean {
    val user = userRepository.findOneByName(name)
        ?: throw AuthenticationException("User is not found")
    return true
  }

  fun sum(a: Int, b: Int): Int {
    return a + b
  }

  fun setAdminRole(user: User): User {
    user.role = Role.ADMIN
    return user
  }
}
