package com.packagedeliverycompanyspringboot.repasitory;

import com.packagedeliverycompanyspringboot.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, Integer> {
    Destination findDestinationByAddress(String address);
}
