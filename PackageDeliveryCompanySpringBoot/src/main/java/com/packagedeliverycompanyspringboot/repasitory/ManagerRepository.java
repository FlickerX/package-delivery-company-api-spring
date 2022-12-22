package com.packagedeliverycompanyspringboot.repasitory;

import com.packagedeliverycompanyspringboot.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    Manager findManagerByLoginAndPassword(String login, String password);
}
