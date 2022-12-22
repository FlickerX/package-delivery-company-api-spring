package com.packagedeliverycompanyspringboot.repasitory;

import com.packagedeliverycompanyspringboot.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {
    Cargo findCargoByNaming(String naming);
}
