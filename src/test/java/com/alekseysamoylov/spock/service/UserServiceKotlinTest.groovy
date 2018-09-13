package com.alekseysamoylov.spock.service

import com.alekseysamoylov.spock.entity.Role
import com.alekseysamoylov.spock.entity.User
import com.alekseysamoylov.spock.exception.AuthenticationException
import com.alekseysamoylov.spock.repository.UserRepository
import spock.lang.Specification
import spock.lang.Unroll

class UserServiceKotlinTest extends Specification {

    def "Should not throw AuthenticationException"() {
        setup:
        def userName = 'John'
        def user = new User(id: 1, name: userName)
        def userRepositoryMock = Spy(UserRepository)
        userRepositoryMock.findOneByName(userName) >> user
        def userService = new UserServiceKotlin(userRepositoryMock)

        when:
        userService.processUser(userName)

        then:
        notThrown(AuthenticationException)
    }

    def "Should throw AuthenticationException"() {
        setup:
        def userName = 'John'
        def userRepositoryMock = Spy(UserRepository)
        userRepositoryMock.findOneByName(userName) >> null
        def userService = new UserServiceKotlin(userRepositoryMock)

        when:
        userService.processUser(userName)

        then:
        thrown(AuthenticationException)
    }

    def "Should summarize values simple"() {
        setup:
        def userRepository = Spy(UserRepository)
        def userService = new UserServiceKotlin(userRepository);

        when:
        def result = userService.sum(1, 1)

        then:
        result == 2
    }

    def "Should summarize values"() {
        setup:
        def userRepository = Spy(UserRepository)
        def userService = new UserServiceKotlin(userRepository);

        expect:
        result == userService.sum(firstArg, lastArg)

        where:
        firstArg << [1, 2, 3]
        lastArg << [1, 2, 3]
        result << [2, 4, 6]
    }

    @Unroll("first: #firstArg plus last: #lastArg equals #result")
    def "Should summarize values with table"() {
        setup:
        def userRepository = Spy(UserRepository)
        def userService = new UserServiceKotlin(userRepository);

        expect:
        result == userService.sum(firstArg, lastArg)

        where:
        firstArg | lastArg || result
        1        | 1       || 2
        2        | 2       || 4
        3        | 3       || 6
    }

    def "Should set admin role to user"() {
        setup:
        def user = new User()
        def userRepository = Spy(UserRepository)
        def userService = new UserServiceKotlin(userRepository)

        when:
        def resultUser = userService.setAdminRole(user)

        then:
        resultUser.role == Role.ADMIN
    }
}
