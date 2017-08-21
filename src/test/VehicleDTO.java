/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seleniumtestscript;

/**
 *
 * @author user
 */
public class VehicleDTO {
    private String id;
    private String vehicleName;
    private String vehicleColor;
 
    public String getId() {
        return id;
    }
 
    public void setId(String id) {
        this.id = id;
    }
 
    public String getVehicleName() {
        return vehicleName;
    }
 
    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }
 
    public String getVehicleColor() {
        return vehicleColor;
    }
 
    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }
 
    @Override
    public String toString() {
        return "Employee [id=" + id + ", vehicleName=" + vehicleName
                + ", vehicleColor=" + vehicleColor + "]";
    }
}
