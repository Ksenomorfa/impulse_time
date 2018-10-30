package ru.ioffe.spbstu.impulsetime;

import org.junit.Test;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class ImpulseTimeTest {

    @Test
    public void testLoadFile() {
        String file1 = ClassLoader.getSystemResource("test/krf20090301_1_S1_bg.thr").getFile();
        String file2 = ClassLoader.getSystemResource("test/krf20090301_1_S2_bg.thr").getFile();

        InitialTimeHistory timeHistory = TimeHistoryFileLoader.loadFile(file1, file2);
        //System.err.println(timeHistory);

        PreparedTimeHistory preparedTimeHistory = TimeHistoryPreparator.prepareHistory(timeHistory);
        //System.err.println(preparedTimeHistory);

        new TimeHistoryCumulative().cumulateFunction(preparedTimeHistory, 1, 1100, 2200, TimeHistoryCumulative.Type.T90, 1000);

        double xData[] = new double[2200-1100];
        for(int i=0; i < 1100; i++) {
            xData[i] = 1100 + i;
        }
        double yData[] = new double[2200-1100];
        for(int i=0; i < 1100; i++) {
            yData[i] = preparedTimeHistory.timeHistory[1100+i][1];
        }
    }
}
