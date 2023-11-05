package org.example.hospital.people;

import org.example.hospital.structure.Service;
import org.example.hospital.structure.VipService;

public interface IAddServices {
    void addService(Service service);
    void addVipService(VipService vipService);
}
