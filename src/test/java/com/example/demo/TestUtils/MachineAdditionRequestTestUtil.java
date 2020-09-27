package com.example.demo.TestUtils;

import com.example.demo.enums.MachineStatus;
import com.example.demo.request.MachineAdditionRequest;

public class MachineAdditionRequestTestUtil {

    public static MachineAdditionRequest getValidMachineAdditionRequest() {
        MachineAdditionRequest
                machineAdditionRequest =
                MachineAdditionRequest.builder().machineStatus(MachineStatus.STANDBY)
                        .name("Tap_machine").serialNumber(123L).build();
        return machineAdditionRequest;


    }
}
