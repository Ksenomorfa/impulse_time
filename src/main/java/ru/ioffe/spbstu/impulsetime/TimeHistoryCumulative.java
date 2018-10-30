package ru.ioffe.spbstu.impulsetime;

public class TimeHistoryCumulative {
    public enum Type {
        T90(0.9),
        T50(0.5);
        public final double percent;
        Type(double percent) {
            this.percent = percent;
        }
    }

    public Result cumulateFunction(PreparedTimeHistory prepareHistory, int range, int from, int to, Type type, int phone) {
        int measurementTime = to - from + 1;
        double limitLowerT = (1 - type.percent) / 2;
        double limitUpperT = 1 - (1 - type.percent) / 2;

        int channelValues[] = new int[measurementTime];
        for (int i = 0; i < measurementTime; i++) {
            channelValues[i] = prepareHistory.timeHistory[i + from][range];
        }
        int cumulative[] = new int[measurementTime];
        cumulative[0] = channelValues[0];
        for (int i = 1; i < measurementTime; i++) {
            cumulative[i] = cumulative[i - 1] + channelValues[i] - phone;
        }

        int maxOnInterval = 0;
        for (int i = 0; i < measurementTime; i++) {
            if (maxOnInterval < cumulative[i]) {
                maxOnInterval = cumulative[i];
            }
        }
        double limitLower = limitLowerT * maxOnInterval;
        System.err.println("limitlower: " + limitLower);
        double limitUpper = limitUpperT * maxOnInterval;
        System.err.println("limit upper: " + limitUpper);

        int start = 0;
        int end = 0;
        boolean flag = false;
        for (int i = 0; i < measurementTime; i++) {
            if (cumulative[i] >= limitLower && !flag) {
                start = i;
                flag = true;
                //System.err.println("start: " + (i + from) + " " +cumulative[i]);
                continue;
            }
            if (cumulative[i] >= limitUpper && flag) {
                end = i;
                //System.err.println("end: " + (i + from)+ " " +cumulative[i]);
                break;
            }
        }
        int measuredTime = end - start;
        System.err.println(type + " measuredTime from: " + (start + from) + " to: " + (end + from) + " range: " + measuredTime);
        return new Result(start + from, end +from);
    }

    class Result {
        int start;
        int end;
        Result(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
