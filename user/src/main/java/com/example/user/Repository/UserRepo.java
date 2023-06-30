package com.example.user.Repository;

import com.example.user.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {


    User findByUid(int uid);
}
