package com.onebooming.frastructure.model;
import com.onebooming.frastructure.dto.InternetEquipmentDto;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-04-18 15:53
 * @ApiNote
 */
public class ConvertUtil {
    public static InternetEquipment convertToInternetEquipment(InternetEquipmentDto dto){
        InternetEquipment ie = new InternetEquipment();
        ie.setId(dto.getId());
        ie.setName(dto.getName());
        ie.setRole(dto.getRole());
        ie.setMgmtIp(dto.getMgmtIp());
        ie.setAssetStatus(dto.getAssetStatus());
        ie.setDescription(dto.getDescription());
        ie.setCity(dto.getCity());
        ie.setArea(dto.getArea());
        ie.setPosition(dto.getPosition());
        ie.setRackPosition(dto.getRackPosition());
        ie.setBrand(dto.getBrand());
        ie.setType(dto.getType());
        ie.setOSVersion(dto.getOSVersion());
        ie.setCPUFrequency(dto.getCPUFrequency());
        ie.setMemory(dto.getMemory());
        ie.setSerialNumber(dto.getSerialNumber());
        ie.setPowerSuppliesNumber(dto.getPowerSuppliesNumber());
        ie.setFanNumber(dto.getFanNumber());
        ie.setLicenseTerm(dto.getLicenseTerm());
        ie.setMonitor(dto.getMonitor());
        ie.setPortSpeed(dto.getPortSpeed());
        ie.setPortType(dto.getPortType());
        ie.setPortNumber(dto.getPortNumber());
        ie.setListDate(dto.getListDate());
        ie.setWarrantyPeriod(dto.getWarrantyPeriod());
        ie.setManufacturer(dto.getManufacturer());
        ie.setAgent(dto.getAgent());
        ie.setMaintenanceManufacturers(dto.getMaintenanceManufacturers());
        ie.setOMDepartment(dto.getOMDepartment());
        ie.setAdministrator(dto.getAdministrator());

        return ie;
    }

    public static void main(String[] args) {
        String s = "id,name,type,mgmtIp,bIp,city,area,position,usagew,brand,serialNumber,role,fcPortsNumber,\n" +
                "fcPortsVelocity,ipPortsNumber,ipPortsVelocity,SASspecification,SASnumber,SAScapacity,\n" +
                "SATAspecification,SATAnumber,SATAcapacity,SSDspecification,SSDnumber,SSDcapacity,assetStatus,description,\n" +
                "listDate,warrantyPeriod,manufacturer,maintenanceManufacturers,\n" +
                "agent,OMDepartment,administrator";
        String[] split = s.split(",");
        StringBuilder sb = new StringBuilder();
        for (String s1 : split) {
            sb.append(s1).append("=#{").append(s1).append("},");
        }
        System.out.println(sb.toString());
    }

}
