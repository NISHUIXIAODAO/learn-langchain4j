package com.may.java.ai.langchain4j.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.may.java.ai.langchain4j.entity.Appointment;

public interface IAppointmentService extends IService<Appointment> {
    Appointment getOne(Appointment appointment);
}
