package org.example.hospital.people;

import org.example.hospital.structure.Service;
import org.example.hospital.structure.VipService;

public interface IDeleteItems {
    void deleteService(Service service);
    void deleteVipService(VipService vipService);
    void deletePatient(Patient patient);
}
