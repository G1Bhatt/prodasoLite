package com.example.demo.TestUtils;

import com.example.demo.entity.MachineDetailsEntity;
import com.example.demo.enums.MachineStatus;

import java.util.ArrayList;
import java.util.List;

public class MachineDetailsEntityTestUtils {

    public static MachineDetailsEntity getInputMachineDetailsEntity(long serialNo) {
        MachineDetailsEntity machineDetailsEntity = new MachineDetailsEntity();
        machineDetailsEntity.setMachineStatus(MachineStatus.STANDBY);
        machineDetailsEntity.setMachineName("Tap_machine");
        machineDetailsEntity.setSerialNumber(serialNo);
        return machineDetailsEntity;
    }

    public static MachineDetailsEntity getOutputMachineDetailsEntity(long serialNo) {
        MachineDetailsEntity machineDetailsEntity = new MachineDetailsEntity();
        machineDetailsEntity.setMachineStatus(MachineStatus.STANDBY);
        machineDetailsEntity.setMachineName("Tap_machine");
        machineDetailsEntity.setSerialNumber(serialNo);
        machineDetailsEntity.setId(1L);
        return machineDetailsEntity;
    }

    public static List<MachineDetailsEntity> getListOfValidMachineDetailsEntity() {
        List<MachineDetailsEntity> machineDetailsEntityList = new ArrayList<>();
        machineDetailsEntityList.add(getOutputMachineDetailsEntity(123L));
        machineDetailsEntityList.add(getOutputMachineDetailsEntity(234L));
        return machineDetailsEntityList;
    }
    public static MachineDetailsEntity getUpdatedMachineDetailsEntity(long serialNo) {
        MachineDetailsEntity machineDetailsEntity = new MachineDetailsEntity();
        machineDetailsEntity.setMachineStatus(MachineStatus.PRODUCTION);
        machineDetailsEntity.setMachineName("Swing_machine");
        machineDetailsEntity.setSerialNumber(serialNo);
        machineDetailsEntity.setId(1L);
        return machineDetailsEntity;
    }

}
