package main;

import drivingManagement.DriverAssignment;
import util.CollectionUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SortandDistance {
    public void sortDrivingTable() {
        if (CollectionUtil.isEmpty(MainRun.drivings)) {
            System.out.println("Nhập bảng phân công trước khi sắp xếp");
            return;
        }
        do {
            System.out.println("-----Sắp xếp danh sách phân công-------");
            System.out.println("1.Sắp xếp theo họ tên lái xe");
            System.out.println("2.Sắp xếp theo số lượng tuyến");
            System.out.println("3.Thoát");
            System.out.println("Hãy nhập sự lựa chọn của bạn: ");
            int choice = 0;
            boolean checkchoice = true;
            do {
                try {
                    choice = new Scanner(System.in).nextInt();
                    checkchoice = true;
                } catch (Exception e) {
                    System.out.println("Không được chứa ký tự khác ngoài số! Nhập lại");
                    checkchoice = false;
                    continue;
                }
                if (choice <= 0 || choice > 3) {
                    System.out.println("Nhập số trong khoảng từ 1 đến 3! Nhập lại: ");
                    checkchoice = false;
                }
            } while (!checkchoice);
            switch (choice) {
                case 1:
                    sortByDriverName();
                    break;
                case 2:
                    sortByBusLineNumber();
                    break;
                case 3:
                    return;
            }
        } while (true);
    }

    public void sortByDriverName() {
        for (int i = 0; i < MainRun.drivings.size(); i++) {
            for (int j = i + 1; j < MainRun.drivings.size(); j++) {
                if (MainRun.drivings.get(i).getDriver().getName().compareTo(MainRun.drivings.get(j).getDriver().getName()) > 0) {
                    DriverAssignment tmp = MainRun.drivings.get(i);
                    MainRun.drivings.set(i, MainRun.drivings.get(j));
                    MainRun.drivings.set(j, tmp);
                }
            }
        }
        for (DriverAssignment driverAssignment : MainRun.drivings) {
            System.out.println(driverAssignment);
        }

    }

    public void sortByBusLineNumber() {
        for (int i = 0; i < MainRun.drivings.size(); i++) {
            for (int j = i + 1; j < MainRun.drivings.size(); j++) {
                if (MainRun.drivings.get(i).getBusLineSum() < MainRun.drivings.get(j).getBusLineSum()) {
                    DriverAssignment tmp = MainRun.drivings.get(i);
                    MainRun.drivings.set(i, MainRun.drivings.get(j));
                    MainRun.drivings.set(j, tmp);
                }
            }
        }
        for (DriverAssignment driverAssignment : MainRun.drivings) {
            System.out.println(driverAssignment);
        }

    }

    public void distanceDriving() {
        if (CollectionUtil.isEmpty(MainRun.drivings)) {
            System.out.println("Nhập bảng phân công trước khi sắp xếp");
            return;
        }
        for (int i = 0; i < MainRun.drivings.size()-1; i++) {
            System.out.println("-------Tính tổng khoảng cách cho lái xe " + MainRun.drivers.get(i).getName() + "--------");
            List<Float> distanceTotal = new ArrayList<>();
            for (int j = 0; j < MainRun.drivings.get(i).getBusLineLists().size(); j++) {
                float tmp = MainRun.drivings.get(i).getBusLineLists().get(j).getRoute().getDistance() *
                        MainRun.drivings.get(i).getBusLineLists().get(j).getDrivingTurnNumber();
                distanceTotal.add(tmp);
            }
            int tempTotal = 0;
            for (Float aFloat : distanceTotal) {
                tempTotal += aFloat;
            }
            MainRun.drivings.get(i).setDistanceSum(tempTotal);
            System.out.println(tempTotal);
        }
    }
}
