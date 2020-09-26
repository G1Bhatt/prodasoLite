package com.example.demo.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public enum MachineStatus {
    @JsonProperty("production") PRODUCTION("production"),

    @JsonProperty("standby") STANDBY("standby");

    private String displayName;

    private static Map<String, MachineStatus> stringMap = new HashMap<>();
    private static Map<Integer, MachineStatus> intMap = new HashMap<>();

    static {
        for (MachineStatus machineStatus : MachineStatus.values()) {
            stringMap.put(machineStatus.displayName, machineStatus);
        }
    }

    public String getValue() {
        return displayName;
    }

    public static MachineStatus getStatus(final String value) {
        return stringMap.get(value);
    }

    public static MachineStatus getStatus(final Integer value) {
        return intMap.get(value);
    }
}
