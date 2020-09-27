package com.example.demo.request;

import com.example.demo.enums.MachineStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
@Builder
public class MachineAdditionRequest implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5374919733382058937L;


    @JsonProperty("machine_name")
    private String name;

    @JsonProperty("status")
    private MachineStatus machineStatus;

    @JsonProperty("serial_no")
    private Long serialNumber;

    @Override
    public String toString() {
        return "CashInCreateRequest [machine_name=" + name + ",status=" + machineStatus
                + ",seraial_no=" + serialNumber + "]";
    }
}
