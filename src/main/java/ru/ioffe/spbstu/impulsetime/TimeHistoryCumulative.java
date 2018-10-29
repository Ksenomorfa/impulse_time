package ru.ioffe.spbstu.impulsetime;

public class TimeHistoryCumulative {
    public static final int TIME_MINUS = -240;

    public enum Type {
        T90(0.9),
        T50(0.5);

        public final double percent;

        Type(double percent) {
            this.percent = percent;
        }
    }

    public static void cumulateFunction(PreparedTimeHistory prepareHistory, int range, int from, int to, Type type) {
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
            cumulative[i] = channelValues[i] + cumulative[i - 1];
        }

        int maxOnInterval = 0;
        for (int i = 0; i < measurementTime; i++) {
            if (maxOnInterval < cumulative[i]) {
                maxOnInterval = cumulative[i];
            }
        }
        System.err.println("MAX: " + maxOnInterval);
        double limitLower = limitLowerT * maxOnInterval;
        double limitUpper = limitUpperT * maxOnInterval;

        int start = 0;
        int end = 0;
        boolean flag = false;
        for (int i = 0; i < measurementTime; i++) {
            if (cumulative[i] > limitLower && !flag) {
                start = i;
                flag = true;
                continue;
            }
            if (cumulative[i] > limitUpper && flag) {
                end = i;
                break;
            }
        }
        int measuredTime = end - start;
        System.err.println("measuredTime from: " + (start + from) + " to: " + (end + from) + " range: " + measuredTime);

    }
}
