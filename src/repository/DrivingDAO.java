package repository;

import constant.DatabaseConstant;
import drivingManagement.BuslineList;
import drivingManagement.DriverAssignment;
import entity.BusLine;
import entity.Driver;
import util.CollectionUtil;
import util.DatabaseConnection;
import util.ObjectUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DrivingDAO {
    private static final String DRIVING_TABLE_NAME = "driving_assignment";

    private static final String DRIVER_ID = "driver_id";
    private static final String ROUTE_ID = "bus_line_id";
    private static final String DRIVING_TURN_NUMBER = "driving_turn_number";

    public static final Connection connection;

    static {
        connection = DatabaseConnection.openConnection(DatabaseConstant.DRIVER_STRING, DatabaseConstant.URL, DatabaseConstant.USERNAME, DatabaseConstant.PASSWORD);
    }

    public List<DriverAssignment> getDrivingTimeSheet() {
        List<DriverAssignment> drivings = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "select d.id driver_id, d.name, d.address, d.phone_number, d.driver_level, bl.id bus_line_id, bl.distance, bl.bus_stop_number, da.driving_turn_number " +
                    "from " + DRIVING_TABLE_NAME + " da join " + DriverDAO.DRIVER_TABLE_NAME + " d on da.driver_Id = d.id join " + BuslineDAO.ROUTE_TABLE_NAME + " bl on da.bus_line_id = bl.id";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            drivings = new ArrayList<>();
            while (resultSet.next()) {
                int driverID = resultSet.getInt(DRIVER_ID);
                String name = resultSet.getString(DriverDAO.NAME);
                String address = resultSet.getString(DriverDAO.ADDRESS);
                String phoneNumber = resultSet.getString(DriverDAO.PHONE_NUMBER);
                String level = resultSet.getString(DriverDAO.LEVEL);
                Driver driver = new Driver(driverID, name, address, phoneNumber, level);

                int routeID = resultSet.getInt(ROUTE_ID);
                float range = resultSet.getFloat(BuslineDAO.RANGE);
                int stopNumber = resultSet.getInt(BuslineDAO.STOP_NUMBER);
                BusLine busLine = new BusLine(routeID, range, stopNumber);

                int turn = resultSet.getInt(DRIVING_TURN_NUMBER);

                BuslineList buslineList = new BuslineList(busLine, turn);

                DriverAssignment driverAssignment = searchDriver(drivings, driverID);

                if (ObjectUtil.isEmpty(driverAssignment)) {
                    DriverAssignment driving = new DriverAssignment(driver, Arrays.asList(buslineList));
                    drivings.add(driving);
                } else {
                    int index = drivings.indexOf(driverAssignment);
                    drivings.get(index).getBusLineLists().add(buslineList);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(resultSet, preparedStatement, null);
        }
        return drivings;
    }

    private DriverAssignment searchDriver(List<DriverAssignment> drivings, int driverId) {

        List<DriverAssignment> collect = drivings.stream().filter(driving -> driving.getDriver().getId() == driverId).collect(Collectors.toList());
        if (!CollectionUtil.isEmpty(collect)) {
            collect.get(0);
        }
        return null;
    }

    public void insertNewDriving(DriverAssignment driving) {
        if (ObjectUtil.isEmpty(driving)) {
            return;
        }
        int driverID = driving.getDriver().getId();
        driving.getBusLineLists().forEach(timesheet -> {
            PreparedStatement preparedStatement = null;
            try {
                String query = "INSERT INTO " + DRIVING_TABLE_NAME + " VALUES (?, ?, ?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, driverID);
                preparedStatement.setInt(2, timesheet.getRoute().getId());
                preparedStatement.setInt(3, timesheet.getDrivingTurnNumber());
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                DatabaseConnection.closeConnection(null, preparedStatement, null);
            }
        });
    }

    public void insertNewDrivingTimeSheet(List<DriverAssignment> drivings) {
        if (CollectionUtil.isEmpty(drivings)) {
            return;
        }
        drivings.forEach(this::insertNewDriving);
    }
}
