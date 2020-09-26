package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "machine_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MachineDetailsEntity extends AbstractEntity<Integer> {

    @Column(name = "name")
    private String machineName;
}
