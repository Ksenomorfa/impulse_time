package ru.ioffe.spbstu.impulsetime;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TimeHistoryFileLoader {

    public static InitialTimeHistory loadFile(String filePath1, String filePath2) {
        File file1 = new File(filePath1);
        File file2 = new File(filePath2);
        InitialTimeHistory timeHistory = new InitialTimeHistory();

        try {
            int i = 0;
            Scanner scanner = new Scanner(file1);
            while (scanner.hasNext()) {
                String[] split = scanner.nextLine().trim().split("\\s+");
                timeHistory.timeHistory[i][0] = (int) Double.parseDouble(split[0]);
                timeHistory.timeHistory[i][1] = (int) Double.parseDouble(split[1]);
                for (int j = 2; j < 14; j++) {
                    timeHistory.timeHistory[i][j] = Integer.parseInt(split[j]);
                }
                i++;
            }
            i = 0;
            scanner = new Scanner(file2);
            while (scanner.hasNext()) {
                String[] split = scanner.nextLine().trim().split("\\s+");
                for (int j = 2; j < 14; j++) {
                    timeHistory.timeHistory[i][j+12] = Integer.parseInt(split[j]);
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return timeHistory;
    }

}
