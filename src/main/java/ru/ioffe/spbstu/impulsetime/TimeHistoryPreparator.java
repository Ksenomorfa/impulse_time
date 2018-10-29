package ru.ioffe.spbstu.impulsetime;


public class TimeHistoryPreparator {

    public static PreparedTimeHistory prepareHistory(InitialTimeHistory initialTimeHistory) {

        PreparedTimeHistory timeHistory = new PreparedTimeHistory();
        int a[][] = initialTimeHistory.timeHistory;
        for (int i = 0; i < 86400; i ++) {
            timeHistory.timeHistory[i][0] = a[i][0];
            timeHistory.timeHistory[i][1]= a[i][2] + a[i][3] + a[i][4] +a[i][5];
            timeHistory.timeHistory[i][2]= a[i][5] + a[i][6] + a[i][7] +a[i][8];
            timeHistory.timeHistory[i][3]= a[i][8] + a[i][9] + a[i][10] +a[i][12];
            timeHistory.timeHistory[i][4]= a[i][13] + a[i][14] + a[i][15] + a[i][16];
            timeHistory.timeHistory[i][5]= a[i][16] + a[i][17] + a[i][18] + a[i][19];
            timeHistory.timeHistory[i][6]= a[i][19] + a[i][20] + a[i][21] + a[i][22];
            timeHistory.timeHistory[i][7]= a[i][22] + a[i][23] + a[i][24] + a[i][25];
            //33858	33859	1404	1252	1844	2296	2125	1360	489	170	109	112	79	1438	1326	1417	1853	2353	2114	1304	525	220	142	102	82	1455
            //33859	33860	1331	1353	1784	2419	2114	1350	488	186	108	89	76	1378	1267	1465	2034	2316	2122	1225	461	189	107	129	66	1518
        }
        return timeHistory;
    }

}
