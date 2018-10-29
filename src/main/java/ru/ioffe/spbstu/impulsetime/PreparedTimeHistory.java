package ru.ioffe.spbstu.impulsetime;

public class PreparedTimeHistory {
    public static final int SECONDS = 86400;
    public static final int COLUMNS = 8;

    int timeHistory[][];

    PreparedTimeHistory () {
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
        return "PreparedTimeHistory{" + stringBuilder + '}';
    }
}
