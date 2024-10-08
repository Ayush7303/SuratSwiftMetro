/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;


public class RouteStationDTO {
     private static final long serialVersionUID = 1L;

    private Integer routeId;
    private String routeNumber;
    private Integer stationId;
    private String stationName;
        private double longitude;
    private double latitude;


    // Constructors, getters, and setters

    public RouteStationDTO() {}

    public RouteStationDTO(Route route, Station station) {
        this.routeId = route.getRouteid();
        this.routeNumber = route.getRoutenumber();
        this.stationId = station.getStationid();
        this.stationName = station.getStationname();
        this.latitude=station.getLatitude();
        this.longitude=station.getLongitude();
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public String getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(String routeNumber) {
        this.routeNumber = routeNumber;
    }

    public Integer getStationId() {
        return stationId;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
    
}
