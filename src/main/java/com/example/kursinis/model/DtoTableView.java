package com.example.kursinis.model;

import com.example.kursinis.utilities.CallEndpoints;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoTableView {
    private Integer id;
    private String shipment;
    private String destination;
    private String deliveryDate;
    private String vehicle;
    private String status;

    public DtoTableView(JSONObject jsonObject){
        this.id = (Integer) jsonObject.get("id");
        this.shipment = (String) jsonObject.get("shipment");
        this.destination = (String) jsonObject.get("destination");
        this.deliveryDate = (String) jsonObject.get("deliveryDate");
        this.vehicle = (String) jsonObject.get("vehicle");
        this.status = (String) jsonObject.get("status");
    }

    public static List<DtoTableView> getArray(){
        return getArray(CallEndpoints.Get("http://localhost:8080/api/tableview"));
    }

    public static List<DtoTableView> getArray(String body) {
        List<DtoTableView> table = new ArrayList<>();
        JSONArray responseArray = new JSONArray(body);
        for (int i=0;i<responseArray.length();i++){
            table.add(new DtoTableView(responseArray.getJSONObject(i)));
        }
        return table;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShipment() {
        return shipment;
    }

    public void setShipment(String shipment) {
        this.shipment = shipment;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
