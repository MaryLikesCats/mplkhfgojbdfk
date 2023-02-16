package com.example.demo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SensorDataControllerTest {

    @Test
    void getSensorDataById() throws Exception {
        //I'm having trouble getting this to run
        //Here I mock the getSensorReadingsById(103) method. So it shouldn't actually be going into that method and contacting the database.
        //I'm trying to say that it should return the mockAL Arraylist that contains the sensorDataForArrayList.
        //But I'm receiving a SQLite error that there is no such table: SensorReading
        //I'm not sure why it is trying to contact my db.

        SensorData sensorDataForArrayList = new SensorData(28, 2, 3, 103, "13/01/2023");
        ArrayList mockAL = new ArrayList();
        mockAL.add(sensorDataForArrayList);

        SensorData mockSD = Mockito.mock(SensorData.class);
        when(mockSD.getSensorReadingsById(103)).thenReturn(mockAL);
        SensorDataController sdc = new SensorDataController(mockSD);

        assertEquals(mockAL, sdc.getSensorDataById(103));
    }
}