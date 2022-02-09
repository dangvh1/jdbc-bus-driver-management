package main;

import entity.Driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class  DriverService {

    public void createNewDriver() {
        System.out.println("Nhập số lượng lái xe muốn thêm mới: ");
        int countDriver = 0;
        boolean check = true;
        do {
            try {
                countDriver = new Scanner(System.in).nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                check = false;
                continue;
            }
            if (countDriver <= 0) {
                System.out.print("Số lượng lái xe không được nhỏ hơn 0! Nhập lại: ");
                check = false;
            }
        } while (!check);

        List<Driver> tempDriver = new ArrayList<>();
        for (int i = 0; i < countDriver; i++) {
            Driver driver = new Driver();
            driver.inputInfo();
            tempDriver.add(driver);
        }
        MainRun.drivers.addAll(tempDriver);
        MainRun.driverDAO.insertNewDriver(tempDriver);
    }

}
