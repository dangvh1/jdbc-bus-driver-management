package main;

import entity.BusLine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BusLineService {
    public void createNewBusLine(){
        System.out.println("Nhập số lượng tuyến muốn thêm mới: ");
        int countBusLine = 0;
        boolean check = true;
        do {
            try {
                countBusLine = new Scanner(System.in).nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                check = false;
                continue;
            }
            if (countBusLine <= 0) {
                System.out.print("Số lượng tuyến đường không được nhỏ hơn 0! Nhập lại: ");
                check = false;
            }
        } while (!check);

        List<BusLine> tempBusLine = new ArrayList<>();
        for (int i = 0; i < countBusLine; i++) {
            BusLine busLine = new BusLine();
            busLine.inputInfo();
            tempBusLine.add(busLine);
        }
        MainRun.busLines.addAll(tempBusLine);
        MainRun.buslineDAO.insertNewRoute(tempBusLine);
    }
}
