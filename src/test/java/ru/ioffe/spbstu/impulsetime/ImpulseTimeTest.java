package ru.ioffe.spbstu.impulsetime;

import org.junit.Test;

public class ImpulseTimeTest {

    @Test
    public void testLoadFile() {
        String file1 = ClassLoader.getSystemResource("test/krf20090301_1_S1_bg.thr").getFile();
        String file2 = ClassLoader.getSystemResource("test/krf20090301_1_S2_bg.thr").getFile();

        InitialTimeHistory timeHistory = TimeHistoryFileLoader.loadFile(file1, file2);
        //System.err.println(timeHistory);

        PreparedTimeHistory preparedTimeHistory = TimeHistoryPreparator.prepareHistory(timeHistory);
        //System.err.println(preparedTimeHistory);

        TimeHistoryCumulative.cumulateFunction(preparedTimeHistory, 1, 1100, 2200, TimeHistoryCumulative.Type.T90);

    }
}
