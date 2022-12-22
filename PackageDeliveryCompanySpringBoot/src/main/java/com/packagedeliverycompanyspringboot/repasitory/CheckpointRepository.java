package com.packagedeliverycompanyspringboot.repasitory;

import com.packagedeliverycompanyspringboot.model.Checkpoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckpointRepository extends JpaRepository<Checkpoint, Integer> {
    Checkpoint findCheckpointByAddress(String address);
}
