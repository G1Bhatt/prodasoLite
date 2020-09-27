package com.example.demo.request;

import com.example.demo.enums.MachineStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
public class MachineAdditionRequest implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5374919733382058937L;

    @NonNull
    @JsonProperty("machine_name")
    private String name;

    @NonNull
    @JsonProperty("status")
    private MachineStatus machineStatus;

    @NonNull
    @JsonProperty("serial_no")
    private Long serialNumber;

    @Override
    public String toString() {
        return "CashInCreateRequest [machine_name=" + name + ",status=" + machineStatus
                + ",seraial_no=" + serialNumber + "]";
    }
}
