package com.alekseysamoylov.spock.repository;

import org.springframework.stereotype.Repository;

import com.alekseysamoylov.spock.entity.User;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public User findOneByName(String name) {
        throw new UnsupportedOperationException("You should implement this, if you want");
    }
}
