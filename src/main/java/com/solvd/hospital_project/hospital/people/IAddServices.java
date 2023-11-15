package com.solvd.hospital_project.hospital.people;

import com.solvd.hospital_project.hospital.structure.Service;
import com.solvd.hospital_project.hospital.structure.VipService;

public interface IAddServices {
    void addService(Service service);
    void addVipService(VipService vipService);
}
