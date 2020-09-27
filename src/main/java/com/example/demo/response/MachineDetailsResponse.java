package com.example.demo.response;

import com.example.demo.entity.MachineDetailsEntity;
import com.example.demo.enums.MachineStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MachineDetailsResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("machine_name")
    private String name;

    @JsonProperty("serial_no")
    private Long serialNumber;

    @JsonProperty("status")
    private MachineStatus machineStatus;

    public MachineDetailsResponse() {
    }

    public MachineDetailsResponse(MachineDetailsEntity machineDetailsEntity) {
        this.id = machineDetailsEntity.getId();
        this.machineStatus = machineDetailsEntity.getMachineStatus();
        this.name = machineDetailsEntity.getMachineName();
        this.serialNumber = machineDetailsEntity.getSerialNumber();
    }

}
