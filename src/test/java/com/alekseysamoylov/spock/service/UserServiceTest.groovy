package com.alekseysamoylov.spock.service

import com.alekseysamoylov.spock.entity.User
import com.alekseysamoylov.spock.exception.AuthenticationException
import com.alekseysamoylov.spock.repository.UserRepository
import spock.lang.Specification
import spock.lang.Unroll

class UserServiceTest extends Specification {

    def "Should not throw AuthenticationException"() {
        setup:
        def userName = 'John'
        def user = new User(id: 1, name: userName)
        def userRepositoryMock = Spy(UserRepository)
        userRepositoryMock.findOneByName(userName) >> user
        def userService = new UserService(userRepositoryMock)

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
        def userService = new UserService(userRepositoryMock)

        when:
        userService.processUser(userName)

        then:
        thrown(AuthenticationException)
    }

    def "Should summarize values simple"() {
        setup:
        def userService = new UserService();

        when:
        def result = userService.sum(1, 1)

        then:
        result == 2
    }

    def "Should summarize values"() {
        setup:
        def userService = new UserService();

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
        def userService = new UserService();

        expect:
        result == userService.sum(firstArg, lastArg)

        where:
        firstArg | lastArg || result
        1        | 1       || 2
        2        | 2       || 4
        3        | 3       || 6
    }
}
