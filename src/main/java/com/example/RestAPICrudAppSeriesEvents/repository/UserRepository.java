package com.example.RestAPICrudAppSeriesEvents.repository;

import com.example.RestAPICrudAppSeriesEvents.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
}
