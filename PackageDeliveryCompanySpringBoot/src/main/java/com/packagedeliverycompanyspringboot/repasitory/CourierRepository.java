package com.packagedeliverycompanyspringboot.repasitory;

import com.mysql.cj.jdbc.Driver;
import com.packagedeliverycompanyspringboot.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierRepository extends JpaRepository<Courier, Integer> {
    Courier findCourierByLoginAndPassword(String login, String password);
}
