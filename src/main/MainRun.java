package main;

import drivingManagement.DriverAssignment;
import entity.BusLine;
import entity.Driver;
import repository.DriverDAO;
import repository.DrivingDAO;
import repository.BuslineDAO;
import util.CollectionUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MainRun {
    public static List<Driver> drivers = new ArrayList<>();
    public static List<BusLine> busLines = new ArrayList<>();
    public static List<DriverAssignment> drivings = new ArrayList<>();
    public static List<Integer> checkDriverID = new ArrayList<>();
    public static List<Integer> checkBusLineID = new ArrayList<>();

    public static final DriverDAO driverDAO = new DriverDAO();
    public static final BuslineDAO buslineDAO = new BuslineDAO();
    public static final DrivingDAO drivingDAO = new DrivingDAO();

    private static final DriverService driverService = new DriverService();
    private static final BusLineService busLineService = new BusLineService();
    private static final DriveAssignmentService driveAssignmentService = new DriveAssignmentService();
    private static final SortandDistance sortandDistance = new SortandDistance();

    public static void main(String[] args) {
        System.out.println("Program is running");
        init();
        System.out.println("Program is ready!");
        menu();
    }

    public static void menu() {
        do {
            int functionChoice = functionChoice();
            switch (functionChoice) {
                case 1:
                    driverService.createNewDriver();
                    printDriver();
                    break;
                case 2:
                    busLineService.createNewBusLine();
                    printRoute();
                    break;
                case 3:
                    driveAssignmentService.createDrivingTable();
                    printDriving();
                    break;
                case 4:
                    sortandDistance.sortDrivingTable();
                    break;
                case 5:
                    sortandDistance.distanceDriving();
                    break;
                case 6:
                    System.exit(0);
            }
        } while (true);
    }

    private static void init() {
        drivers = !CollectionUtil.isEmpty(driverDAO.getDrivers()) ? driverDAO.getDrivers() : new ArrayList<>();
        if (CollectionUtil.isEmpty(drivers)) {
            Driver.AUTO_ID = 10000;
        } else {
            drivers.sort(Comparator.comparing(Driver::getId));
            Driver.AUTO_ID = drivers.get(drivers.size() - 1).getId() + 1;
        }
        busLines = !CollectionUtil.isEmpty(buslineDAO.getRoute()) ? buslineDAO.getRoute() : new ArrayList<>();
        if (CollectionUtil.isEmpty(busLines)) {
            BusLine.AUTO_ID = 100;
        } else {
            busLines.sort(Comparator.comparing(BusLine::getId));
            BusLine.AUTO_ID = busLines.get(drivers.size() - 1).getId() + 1;
        }
        drivings = !CollectionUtil.isEmpty(drivingDAO.getDrivingTimeSheet()) ? drivingDAO.getDrivingTimeSheet() : new ArrayList<>();
    }

    public static int functionChoice() {
        System.out.println("-----QUẢN LÝ PHÂN CÔNG LÁI XE BUS-------");
        System.out.println("1. Nhập danh sách lái xe");
        System.out.println("2. Nhập danh sách tuyến");
        System.out.println("3. Nhập danh sách phân công lái xe");
        System.out.println("4. Sắp xếp danh sách phân công ");
        System.out.println("5. Lập bảng thống kê tổng khoảng cách");
        System.out.println("6. Thoát");
        System.out.print("Nhập sự lựa chọn của bạn: ");
        int functionChoice = 0;
        boolean checkFunction = true;
        do {
            try {
                functionChoice = new Scanner(System.in).nextInt();
                checkFunction = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                continue;
            }
            if (functionChoice <= 0 || functionChoice > 6) {
                System.out.print("Nhập số trong khoảng từ 1 đến 6! Nhập lại: ");
                checkFunction = false;
            } else break;
        } while (!checkFunction);
        return functionChoice;
    }

    public static void printDriver() {
        drivers.forEach(System.out::println);
    }
    public static void printRoute() {
        busLines.forEach(System.out::println);
    }
    public static void printDriving() {
        drivings.forEach(System.out::println);
    }
}
