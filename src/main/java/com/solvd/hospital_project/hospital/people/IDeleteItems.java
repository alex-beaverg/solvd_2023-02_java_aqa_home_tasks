package com.solvd.hospital_project.hospital.people;

import com.solvd.hospital_project.hospital.structure.Service;
import com.solvd.hospital_project.hospital.structure.VipService;

public interface IDeleteItems {
    void deleteService(Service service);
    void deleteVipService(VipService vipService);
    void deletePatient(Patient patient);
}
