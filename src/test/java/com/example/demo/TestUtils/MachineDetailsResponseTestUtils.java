package com.example.demo.TestUtils;

import com.example.demo.enums.MachineStatus;
import com.example.demo.response.MachineDetailsResponse;

import java.util.ArrayList;
import java.util.List;

public class MachineDetailsResponseTestUtils {

    public static MachineDetailsResponse getValidMachineDetailsResponse(Long serialNo) {
        MachineDetailsResponse machineDetailsResponse = new MachineDetailsResponse();
        machineDetailsResponse.setId(1L);
        machineDetailsResponse.setSerialNumber(serialNo);
        machineDetailsResponse.setName("Tap_machine");
        machineDetailsResponse.setMachineStatus(MachineStatus.STANDBY);
        return machineDetailsResponse;
    }

    public static List<MachineDetailsResponse> getValidMachineDetailsResponseList() {
        List<MachineDetailsResponse> machineDetailsResponseList = new ArrayList<>();
        machineDetailsResponseList.add(getValidMachineDetailsResponse(123L));
        machineDetailsResponseList.add(getValidMachineDetailsResponse(234L));
        return machineDetailsResponseList;
    }

    public static MachineDetailsResponse getUpdatedMachineDetailsResponse(Long serialNo) {
        MachineDetailsResponse machineDetailsResponse = new MachineDetailsResponse();
        machineDetailsResponse.setId(1L);
        machineDetailsResponse.setSerialNumber(serialNo);
        machineDetailsResponse.setName("Swing_machine");
        machineDetailsResponse.setMachineStatus(MachineStatus.PRODUCTION);
        return machineDetailsResponse;
    }
}
