package com.alekseysamoylov.spock.repository;

import com.alekseysamoylov.spock.entity.User;

public interface UserRepository {
    User findOneByName(String name);
}
