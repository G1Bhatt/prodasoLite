package com.example.demo.request;

import com.example.demo.enums.MachineStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class MachineAdditionRequest implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5374919733382058937L;

    @NotNull
    @JsonProperty("machine_name")
    private String name;

    @NotNull
    @JsonProperty("status")
    private MachineStatus machineStatus;

    @NotNull
    @JsonProperty("serial_no")
    private Long serialNumber;

    @Override
    public String toString() {
        return "CashInCreateRequest [machine_name=" + name + ",status=" + machineStatus
                + ",seraial_no=" + serialNumber + "]";
    }
}
