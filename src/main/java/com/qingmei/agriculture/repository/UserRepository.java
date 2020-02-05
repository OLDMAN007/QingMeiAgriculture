package com.qingmei.agriculture.repository;

import com.qingmei.agriculture.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    User findByUserNameAndPassWord(String userName, String passWord);
}
