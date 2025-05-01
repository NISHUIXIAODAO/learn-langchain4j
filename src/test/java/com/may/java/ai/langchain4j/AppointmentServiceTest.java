package com.may.java.ai.langchain4j;

import com.may.java.ai.langchain4j.entity.Appointment;
import com.may.java.ai.langchain4j.service.IAppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppointmentServiceTest {

    @Autowired
    private IAppointmentService appointmentService;

    @Test
    void testGetOne() {
        Appointment appointment = new Appointment()
                .setUsername("张三")
                .setIdCard("123456789012345678")
                .setDepartment("内科")
                .setDate("2025-04-14")
                .setTime("上午");

        Appointment appointmentDB = appointmentService.getOne(appointment);
        System.out.println(appointmentDB);
    }

    @Test
    void testSave() {
        Appointment appointment = new Appointment()
                .setUsername("张三")
                .setIdCard("123456789012345678")
                .setDepartment("内科")
                .setDate("2025-04-14")
                .setTime("上午")
                .setDoctorName("张医生");

        appointmentService.save(appointment);
    }

    @Test
    void testRemoveById() {
        appointmentService.removeById(1L);
    }
}