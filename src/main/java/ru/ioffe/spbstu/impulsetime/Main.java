package ru.ioffe.spbstu.impulsetime;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        String file1 = "C:\\Users\\Polina\\IdeaProjects\\impulse_time\\test\\krf20090301_1_S1_bg.thr";
        String file2 = "C:\\Users\\Polina\\IdeaProjects\\impulse_time\\test\\krf20090301_1_S2_bg.thr";

        InitialTimeHistory timeHistory = TimeHistoryFileLoader.loadFile(file1, file2);
        //System.err.println(timeHistory);

        PreparedTimeHistory preparedTimeHistory = TimeHistoryPreparator.prepareHistory(timeHistory);
        //System.err.println(preparedTimeHistory);

        int timeFrom = 800;
        int timeTo = 2200;
        int phone1 = 1600;
        int phone2 = 1200;
        int phone3 = 700;
        int phone4 = 2000;
        TimeHistoryCumulative.Result t90result = new TimeHistoryCumulative().cumulateFunction(preparedTimeHistory, 1, timeFrom, timeTo, TimeHistoryCumulative.Type.T90, phone1);
        TimeHistoryCumulative.Result t90result2 = new TimeHistoryCumulative().cumulateFunction(preparedTimeHistory, 2, timeFrom, timeTo, TimeHistoryCumulative.Type.T90, phone2);
        TimeHistoryCumulative.Result t90result3 = new TimeHistoryCumulative().cumulateFunction(preparedTimeHistory, 3, timeFrom, timeTo, TimeHistoryCumulative.Type.T90, phone3);
        TimeHistoryCumulative.Result t90result4 = new TimeHistoryCumulative().cumulateFunction(preparedTimeHistory, 4, timeFrom, timeTo, TimeHistoryCumulative.Type.T90, phone4);
        TimeHistoryCumulative.Result t50result = new TimeHistoryCumulative().cumulateFunction(preparedTimeHistory, 1, timeFrom, timeTo, TimeHistoryCumulative.Type.T50, phone1);
        TimeHistoryCumulative.Result t50result2 = new TimeHistoryCumulative().cumulateFunction(preparedTimeHistory, 2, timeFrom, timeTo, TimeHistoryCumulative.Type.T50, phone2);
        TimeHistoryCumulative.Result t50result3 = new TimeHistoryCumulative().cumulateFunction(preparedTimeHistory, 3, timeFrom, timeTo, TimeHistoryCumulative.Type.T50, phone3);
        TimeHistoryCumulative.Result t50result4 = new TimeHistoryCumulative().cumulateFunction(preparedTimeHistory, 4, timeFrom, timeTo, TimeHistoryCumulative.Type.T50, phone4);

        double xData[] = new double[timeTo - timeFrom];
        for (int i = 0; i < timeTo - timeFrom; i++) {
            xData[i] = timeFrom + i;
        }
        double yData1[] = new double[timeTo - timeFrom];
        double yData2[] = new double[timeTo - timeFrom];
        double yData3[] = new double[timeTo - timeFrom];
        double yData4[] = new double[timeTo - timeFrom];
        for (int i = 0; i < timeTo - timeFrom; i++) {
            yData1[i] = preparedTimeHistory.timeHistory[timeFrom + i][1];
            yData2[i] = preparedTimeHistory.timeHistory[timeFrom + i][2];
            yData3[i] = preparedTimeHistory.timeHistory[timeFrom + i][3];
            yData4[i] = preparedTimeHistory.timeHistory[timeFrom + i][4];
        }
        List<XYChart> charts = new ArrayList<>();
        XYChart chart1 = QuickChart.getChart("10-40 kev", "T", "R", "r(t)", xData, yData1);
        XYChart chart2 = QuickChart.getChart("40-160 kev", "T", "R", "r(t)", xData, yData2);
        XYChart chart3 = QuickChart.getChart("160-300 kev", "T", "R", "r(t)", xData, yData3);
        XYChart chart4 = QuickChart.getChart("300-750 kev", "T", "R", "r(t)", xData, yData4);

        int t90x1[] = new int[2];
        int t90x2[] = new int[2];
        int t90x3[] = new int[2];
        int t90x4[] = new int[2];
        t90x1[0] = t90result.start;
        t90x2[0] = t90result2.start;
        t90x3[0] = t90result3.start;
        t90x4[0] = t90result4.start;
        t90x1[1] = t90result.end;
        t90x2[1] = t90result2.end;
        t90x3[1] = t90result3.end;
        t90x4[1] = t90result4.end;
        int t90y[] = new int[2];
        int t50x1[] = new int[2];
        int t50x2[] = new int[2];
        int t50x3[] = new int[2];
        int t50x4[] = new int[2];
        t50x1[0] = t50result.start;
        t50x2[0] = t50result2.start;
        t50x3[0] = t50result3.start;
        t50x4[0] = t50result4.start;
        t50x1[1] = t50result.end;
        t50x2[1] = t50result2.end;
        t50x3[1] = t50result3.end;
        t50x4[1] = t50result4.end;
        int t50y[] = new int[2];

        chart1.addSeries("Points T90: " + (t90x1[1]-t90x1[0]), t90x1, t90y);
        chart2.addSeries("Points T90: " + (t90x2[1]-t90x2[0]), t90x2, t90y);
        chart3.addSeries("Points T90: " + (t90x3[1]-t90x3[0]), t90x3, t90y);
        chart4.addSeries("Points T90: " + (t90x4[1]-t90x4[0]), t90x4, t90y);
        chart1.addSeries("Points T50: " + (t50x1[1]-t50x1[0]), t50x1, t50y);
        chart2.addSeries("Points T50: " + (t50x2[1]-t50x2[0]), t50x2, t50y);
        chart3.addSeries("Points T50: " + (t50x3[1]-t50x3[0]), t50x3, t50y);
        chart4.addSeries("Points T50: " + (t50x4[1]-t50x4[0]), t50x4, t50y);
        charts.add(chart1);
        charts.add(chart2);
        charts.add(chart3);
        charts.add(chart4);
        new SwingWrapper(charts).displayChartMatrix();

    }
}
