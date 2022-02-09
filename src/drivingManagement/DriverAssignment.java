package drivingManagement;

import entity.Driver;
import java.util.ArrayList;
import java.util.List;

public class DriverAssignment   {
    private Driver driver;
    private List<BuslineList> busLineLists = new ArrayList<>();
    private int busLineSum;
    private float distanceSum;

    public DriverAssignment() {
    }

    public DriverAssignment(Driver driver, List<BuslineList> busLineLists) {
        this.driver = driver;
        this.busLineLists = busLineLists;
    }

    public float getDistanceSum() {
        return distanceSum;
    }

    public void setDistanceSum(float distanceSum) {
        this.distanceSum = distanceSum;
    }

    public int getBusLineSum() {
        return busLineSum;
    }

    public void setBusLineSum(int busLineSum) {
        this.busLineSum = busLineSum;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public List<BuslineList> getBusLineLists() {
        return busLineLists;
    }

    public void setBusLineLists(List<BuslineList> busLineLists) {
        this.busLineLists = busLineLists;
    }

    @Override
    public String toString() {
        return "DriverAssignment{" +
                "driver=" + driver +
                ", busLineLists=" + busLineLists +
                '}';
    }
}
