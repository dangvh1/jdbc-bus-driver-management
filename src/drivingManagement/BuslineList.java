package drivingManagement;

import entity.BusLine;

public class BuslineList {
    private BusLine busLine;
    private int drivingTurnNumber;

    public BuslineList() {
    }
    public BusLine getRoute() {
        return busLine;
    }

    public BuslineList(BusLine busLine, int drivingTurnNumber) {
        this.busLine = busLine;
        this.drivingTurnNumber = drivingTurnNumber;
    }

    public BusLine getBusLine() {
        return busLine;
    }

    public void setBusLine(BusLine busLine) {
        this.busLine = busLine;
    }

    public int getDrivingTurnNumber() {
        return drivingTurnNumber;
    }

    public void setDrivingTurnNumber(int drivingTurnNumber) {
        this.drivingTurnNumber = drivingTurnNumber;
    }

    @Override
    public String toString() {
        return "BusLineList{" +
                "busLine=" + busLine +
                ", drivingTurnNumber=" + drivingTurnNumber +
                '}';
    }
}
