package com.example.demo.service;

import com.example.demo.TestUtils.MachineAdditionRequestTestUtil;
import com.example.demo.TestUtils.MachineDetailsEntityTestUtils;
import com.example.demo.TestUtils.MachineDetailsResponseTestUtils;
import com.example.demo.entity.MachineDetailsEntity;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repo.reposervice.MachineManagementRepoService;
import com.example.demo.request.MachineAdditionRequest;
import com.example.demo.response.MachineDetailsResponse;
import com.example.demo.services.MachineManagementService;
import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@Log4j
@RunWith(MockitoJUnitRunner.class)
public class MachineManagementServiceTest {

    @Mock
    private MachineManagementRepoService machineManagementRepoServiceMock;

    @InjectMocks
    private MachineManagementService machineManagementService;

    @Test
    public void testAddMachineForSuccess() {
        MachineAdditionRequest
                machineAdditionRequest =
                MachineAdditionRequestTestUtil.getValidMachineAdditionRequest();

        MachineDetailsResponse
                expectedMachineDetailsResponse =
                MachineDetailsResponseTestUtils.getValidMachineDetailsResponse(123L);

        MachineDetailsEntity
                inputMachineDetailsEntity =
                MachineDetailsEntityTestUtils.getInputMachineDetailsEntity(123L);

        MachineDetailsEntity
                outputMachineDetailsEntity =
                MachineDetailsEntityTestUtils.getOutputMachineDetailsEntity(123L);

        Mockito.when(machineManagementRepoServiceMock
                .getMachineBySerialNumber(machineAdditionRequest.getSerialNumber()))
                .thenReturn(null);

        Mockito.when(machineManagementRepoServiceMock.createEntity(inputMachineDetailsEntity))
                .thenReturn(outputMachineDetailsEntity);

        MachineDetailsResponse
                actualMachineDetailsResponse =
                machineManagementService.addMachine(machineAdditionRequest);
        Mockito.verify(machineManagementRepoServiceMock, Mockito.times(1))
                .getMachineBySerialNumber(123L);
        Mockito.verify(machineManagementRepoServiceMock, Mockito.times(1))
                .createEntity(inputMachineDetailsEntity);
        Assert.assertEquals(expectedMachineDetailsResponse, actualMachineDetailsResponse);
    }

    @Test
    public void getMachineInfoForSuccess() {
        MachineDetailsEntity
                expectedOutputMachineDetailsEntity =
                MachineDetailsEntityTestUtils.getOutputMachineDetailsEntity(123L);

        Mockito.when(machineManagementRepoServiceMock.getMachineBySerialNumber(123L))
                .thenReturn(expectedOutputMachineDetailsEntity);
        MachineDetailsResponse
                expectedMachineDetailsResponse =
                MachineDetailsResponseTestUtils.getValidMachineDetailsResponse(123L);
        MachineDetailsResponse
                actualMachineDetailsResponse =
                machineManagementService.getMachineInfo(123L);

        Mockito.verify(machineManagementRepoServiceMock, Mockito.times(1))
                .getMachineBySerialNumber(123L);
        Assert.assertEquals(actualMachineDetailsResponse, expectedMachineDetailsResponse);

    }

    @Test(expected = NotFoundException.class)
    public void testGetMachineInfoForException() {
        Mockito.when(machineManagementRepoServiceMock.getMachineBySerialNumber(123L))
                .thenReturn(null);
        machineManagementService.getMachineInfo(123L);
        Mockito.verify(machineManagementRepoServiceMock, Mockito.times(1))
                .getMachineBySerialNumber(123L);
    }

    @Test
    public void testGetAllMachinesForSuccessForNoMachine() {

        Mockito.when(machineManagementRepoServiceMock.getAllEntities())
                .thenReturn(new ArrayList<>());
        List<MachineDetailsResponse> machineDetailsResponseList = new ArrayList<>();
        machineManagementService.getAllMachines();
        Mockito.verify(machineManagementRepoServiceMock, Mockito.times(1)).getAllEntities();
        Assert.assertEquals(new ArrayList<>(), machineDetailsResponseList);
    }

    @Test
    public void testGetAllMachinesForSuccessFoMultipleMachines() {
        List<MachineDetailsEntity>
                machineDetailsEntityList =
                MachineDetailsEntityTestUtils.getListOfValidMachineDetailsEntity();
        Mockito.when(machineManagementRepoServiceMock.getAllEntities())
                .thenReturn(machineDetailsEntityList);

        List<MachineDetailsResponse>
                expectedMachineDetailsResponseList =
                MachineDetailsResponseTestUtils.getValidMachineDetailsResponseList();
        List<MachineDetailsResponse>
                actualMachineDetailsResponseList =
                machineManagementService.getAllMachines();
        Mockito.verify(machineManagementRepoServiceMock, Mockito.times(1)).getAllEntities();
        Assert.assertEquals(actualMachineDetailsResponseList, expectedMachineDetailsResponseList);
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateMachineInfoForException() {
        MachineAdditionRequest
                machineAdditionRequest =
                MachineAdditionRequestTestUtil.getValidMachineAdditionRequest();
        Mockito.when(machineManagementRepoServiceMock.getMachineBySerialNumber(123L))
                .thenReturn(null);
        machineManagementService.updateMachineInfo(machineAdditionRequest);
        Mockito.verify(machineManagementRepoServiceMock, Mockito.times(1))
                .getMachineBySerialNumber(123L);
    }

    @Test
    public void testUpdateMachineForSuccess() {
        MachineAdditionRequest
                machineAdditionRequest =
                MachineAdditionRequestTestUtil.getValidMachineAdditionRequest();
        MachineDetailsEntity
                oldMachineDetailsEntity =
                MachineDetailsEntityTestUtils.getOutputMachineDetailsEntity(123L);
        MachineDetailsEntity
                updatedMachineDetailsEntity =
                MachineDetailsEntityTestUtils.getUpdatedMachineDetailsEntity(345L);

        Mockito.when(machineManagementRepoServiceMock
                .getMachineBySerialNumber(machineAdditionRequest.getSerialNumber()))
                .thenReturn(oldMachineDetailsEntity);

        Mockito.when(machineManagementRepoServiceMock.updateEntity(oldMachineDetailsEntity))
                .thenReturn(updatedMachineDetailsEntity);
        MachineDetailsResponse
                expectedMachineDetailsResponse =
                MachineDetailsResponseTestUtils.getUpdatedMachineDetailsResponse(345L);
        MachineDetailsResponse
                actualMachineDetailsResponse =
                machineManagementService.updateMachineInfo(machineAdditionRequest);
        Assert.assertEquals(actualMachineDetailsResponse, expectedMachineDetailsResponse);
        Mockito.verify(machineManagementRepoServiceMock, Mockito.times(1))
                .getMachineBySerialNumber(123L);
        Mockito.verify(machineManagementRepoServiceMock, Mockito.times(1))
                .updateEntity(oldMachineDetailsEntity);
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteMachineWithIdForException() {
        Mockito.when(machineManagementRepoServiceMock.getMachineBySerialNumber(123L))
                .thenReturn(null);
        machineManagementService.deleteMachineWithId(123L);
        Mockito.verify(machineManagementRepoServiceMock, Mockito.times(0)).deleteEntity(null);
    }

    @Test
    public void testDeleteMachineWithIdForSuccess() {
        MachineDetailsEntity
                machineDetailsEntity =
                MachineDetailsEntityTestUtils.getOutputMachineDetailsEntity(123L);
        Mockito.when(machineManagementRepoServiceMock.getMachineBySerialNumber(123L))
                .thenReturn(machineDetailsEntity);
        machineManagementService.deleteMachineWithId(123L);
        Mockito.verify(machineManagementRepoServiceMock, Mockito.times(1))
                .deleteEntity(machineDetailsEntity);
    }

}
