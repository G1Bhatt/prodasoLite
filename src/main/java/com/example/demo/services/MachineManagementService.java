package com.example.demo.services;

import com.example.demo.entity.MachineDetailsEntity;
import com.example.demo.enums.MachineStatus;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repo.reposervice.MachineManagementRepoService;
import com.example.demo.request.MachineAdditionRequest;
import com.example.demo.response.MachineDetailsResponse;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j
public class MachineManagementService {

    @Autowired
    private MachineManagementRepoService machineManagementRepoService;

    /**
     * method to add a new machine into the database;
     *
     * @param machineAdditionRequest
     * @return
     */
    public MachineDetailsResponse addMachine(MachineAdditionRequest machineAdditionRequest) {
        Long serialNumber = machineAdditionRequest.getSerialNumber();
        MachineDetailsEntity
                machineDetailsEntity =
                machineManagementRepoService.getMachineBySerialNumber(serialNumber);
        if (machineDetailsEntity == null) {
            log.info("adding machine to the database " + machineAdditionRequest);
            machineDetailsEntity = createMachineDetailsEntity(machineAdditionRequest);
            log.info("machine successfully added to the database ");
        } else {
            log.info("machine already exists :" + machineDetailsEntity);
        }
        MachineDetailsResponse
                machineDetailsResponse =
                new MachineDetailsResponse(machineDetailsEntity);
        return machineDetailsResponse;
    }

    /**
     * @param machineAdditionRequest
     * @return
     */
    private MachineDetailsEntity createMachineDetailsEntity(
            MachineAdditionRequest machineAdditionRequest) {
        MachineDetailsEntity machineDetailsEntity;
        machineDetailsEntity = new MachineDetailsEntity();
        machineDetailsEntity.setMachineName(machineAdditionRequest.getName());
        machineDetailsEntity.setSerialNumber(machineAdditionRequest.getSerialNumber());
        machineDetailsEntity = machineManagementRepoService.createEntity(machineDetailsEntity);
        return machineDetailsEntity;
    }

    /**
     * mmethod to get the details about all the machine from the database;
     *
     * @return
     */
    public List<MachineDetailsResponse> getAllMachines() {
        List<MachineDetailsResponse> machineDetailsResponseList = new ArrayList<>();
        List<MachineDetailsEntity>
                machineDetailsEntities =
                machineManagementRepoService.getAllEntities();
        for (MachineDetailsEntity machineDetailsEntity : machineDetailsEntities) {
            MachineDetailsResponse machineDetailsResponse = new MachineDetailsResponse();
            machineDetailsResponse.setId(machineDetailsEntity.getId());
            machineDetailsResponse.setMachineStatus(machineDetailsEntity.getMachineStatus());
            machineDetailsResponse.setName(machineDetailsEntity.getMachineName());
            machineDetailsResponse.setSerialNumber(machineDetailsEntity.getSerialNumber());
            machineDetailsResponseList.add(machineDetailsResponse);
        }
        return machineDetailsResponseList;
    }

    /**
     * method to get the details of a perticular machine from serial number.
     *
     * @param serialId
     * @return
     */
    public MachineDetailsResponse getMachineInfo(Long serialId) throws NotFoundException {
        MachineDetailsEntity
                machineDetailsEntity =
                machineManagementRepoService.getMachineBySerialNumber(serialId);
        if (machineDetailsEntity == null) {
            throw new NotFoundException("Machine  does not exist");
        } else
            return new MachineDetailsResponse(machineDetailsEntity);
    }

    /**
     * method to update the info of the machine.
     *
     * @param machineAdditionRequest
     * @return
     * @throws NotFoundException
     */
    public MachineDetailsResponse updateMachineInfo(MachineAdditionRequest machineAdditionRequest)
            throws NotFoundException {
        MachineDetailsEntity
                machineDetailsEntity =
                machineManagementRepoService
                        .getMachineBySerialNumber(machineAdditionRequest.getSerialNumber());
        if (machineDetailsEntity == null) {
            throw new NotFoundException("Machine does not exist");
        } else {
            machineDetailsEntity.setMachineName(machineAdditionRequest.getName());
            if (machineAdditionRequest.getMachineStatus() == null) {
                machineDetailsEntity.setMachineStatus(MachineStatus.STANDBY);
            } else {
                machineDetailsEntity.setMachineStatus(machineAdditionRequest.getMachineStatus());
            }
            machineDetailsEntity = machineManagementRepoService.updateEntity(machineDetailsEntity);
            log.info("Machine data updated successfully : " + machineDetailsEntity);
        }
        return new MachineDetailsResponse(machineDetailsEntity);
    }

    /**
     * method to delete the machine from the database;
     *
     * @param serialId
     * @throws NotFoundException
     */
    public void deleteMachineWithId(Long serialId) throws NotFoundException {
        MachineDetailsEntity
                machineDetailsEntity =
                machineManagementRepoService.getMachineBySerialNumber(serialId);
        if (machineDetailsEntity == null) {
            throw new NotFoundException("Machine does not exist");
        }
        machineManagementRepoService.deleteEntity(machineDetailsEntity);
    }
}
