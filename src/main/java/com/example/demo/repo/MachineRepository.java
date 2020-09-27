package com.example.demo.repo;

import com.example.demo.entity.MachineDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineRepository extends JpaRepository<MachineDetailsEntity, Long> {

    public MachineDetailsEntity findBySerialNumber(Long serialNumber);
}
