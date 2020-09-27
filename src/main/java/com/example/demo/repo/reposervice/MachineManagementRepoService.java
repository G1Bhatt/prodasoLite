package com.example.demo.repo.reposervice;

import com.example.demo.entity.MachineDetailsEntity;
import com.example.demo.repo.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MachineManagementRepoService extends AbstractRepoService<MachineDetailsEntity, Long> {

    @Autowired
    private MachineRepository repository;

    @Override
    protected JpaRepository<MachineDetailsEntity, Long> getRepo() {
        return repository;
    }

    public MachineDetailsEntity getMachineBySerialNumber(Long serialNumber){
        return repository.findBySerialNumber(serialNumber);
    }

}
