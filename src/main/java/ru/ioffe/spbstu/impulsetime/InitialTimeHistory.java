package ru.ioffe.spbstu.impulsetime;

public class InitialTimeHistory {
    public static final int SECONDS = 86400;
    public static final int COLUMNS = 26;
    int timeHistory[][];

    InitialTimeHistory () {
        timeHistory = new int[SECONDS][COLUMNS];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < SECONDS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                stringBuilder.append(timeHistory[i][j]).append("\t");
            }
            stringBuilder.append("\n");
        }
        return "InitialTimeHistory{" + stringBuilder + '}';
    }
}
