package com.example.demo.controller;

import com.example.demo.exception.InternalServerException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.request.MachineAdditionRequest;
import com.example.demo.response.BaseMessageResponse;
import com.example.demo.response.MachineDetailsResponse;
import com.example.demo.response.ServiceResponse;
import com.example.demo.services.MachineManagementService;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Log4j2
@RequestMapping(value = "/machine", produces = MediaType.APPLICATION_JSON_VALUE)
public class MachineManagementController {

    @Autowired
    private MachineManagementService machineDetailsService;


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ServiceResponse<?> addMachine(
            @Valid @RequestBody MachineAdditionRequest machineAdditionRequest) {
        log.info("Received request to get expense categories");
        MachineDetailsResponse machineDetailsResponse;
        try {
            machineDetailsResponse = machineDetailsService.addMachine(machineAdditionRequest);
        } catch (InternalServerException ex) {
            log.error("MachineManagementController :add Machine - Exception occurred: {}", ex);
            return new ServiceResponse<BaseMessageResponse>(
                    new BaseMessageResponse(false, ex.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ServiceResponse<MachineDetailsResponse>(machineDetailsResponse);
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ServiceResponse<List<MachineDetailsResponse>> getAllMachines() {
        log.info("Received request to get details of all machines");
        return new ServiceResponse<List<MachineDetailsResponse>>(
                machineDetailsService.getAllMachines());
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ServiceResponse<?> getDetailsOfMachine(@NonNull @PathVariable("id") final Long serialId)
            throws NotFoundException {
        log.info("received request to get the details of machine with id: {} ", serialId);
        MachineDetailsResponse machineDetailsResponse;
        try {
            machineDetailsResponse = machineDetailsService.getMachineInfo(serialId);
        } catch (NotFoundException e) {
            return new ServiceResponse<BaseMessageResponse>(new BaseMessageResponse(false,
                    "Machine with " + "Serial Id :" + serialId + " does not exist"),
                    HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            log.error("MachineManagementController :getDetailsOfMachine - Exception occurred: {}",
                    ex);
            return new ServiceResponse<BaseMessageResponse>(
                    new BaseMessageResponse(false, ex.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ServiceResponse<MachineDetailsResponse>(machineDetailsResponse);
    }


    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ServiceResponse<?> updateMachine(
            @Valid @RequestBody MachineAdditionRequest machineAdditionRequest) {
        log.info("received request to update the details of machine : {} ", machineAdditionRequest);
        MachineDetailsResponse machineDetailsResponse;
        try {
            machineDetailsResponse =
                    machineDetailsService.updateMachineInfo(machineAdditionRequest);
        } catch (NotFoundException e) {
            return new ServiceResponse<BaseMessageResponse>(new BaseMessageResponse(false,
                    "Machine with " + "Serial Id :" + machineAdditionRequest.getSerialNumber()
                            + " does not exist"), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            log.error(
                    "MachineManagementController :updateDetailsOfMachine - Exception occurred: {}",
                    ex);
            return new ServiceResponse<BaseMessageResponse>(
                    new BaseMessageResponse(false, ex.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ServiceResponse<MachineDetailsResponse>(machineDetailsResponse);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ServiceResponse<?> deleteMachine(@NonNull @PathVariable("id") final Long serialId) {
        log.info("received request to delete the machine with id: {} ", serialId);
        Boolean isdeleted;
        try {
            machineDetailsService.deleteMachineWithId(serialId);
        } catch (NotFoundException e) {
            return new ServiceResponse<BaseMessageResponse>(new BaseMessageResponse(false,
                    "Machine with " + "Serial Id :" + serialId + " does not exist"),
                    HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            log.error(
                    "MachineManagementController :updateDetailsOfMachine - Exception occurred: {}",
                    ex);
            return new ServiceResponse<BaseMessageResponse>(
                    new BaseMessageResponse(false, ex.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ServiceResponse<BaseMessageResponse>(new BaseMessageResponse(true,
                "Machine with " + "Serial Id :" + serialId + " deleted successfully"));
    }
}

