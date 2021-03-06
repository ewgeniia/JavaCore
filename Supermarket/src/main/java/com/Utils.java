package com;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Utils {
    static int[] range(int from, int to) {
        int[] range = new int[to - from];
        for (int i = from; i < to; ++i) {
            range[i] = i;
        }
        return range;
    }

    static int random(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    static void sleep(int millis) {
        try { Thread.sleep(millis); } catch (InterruptedException ex) { /* skip */ }
    }

    static PrintStream stringPrintStream() {
        StringWriter sw = new StringWriter();
        OutputStream os = new OutputStream() {
            @Override
            public void write(int b) {
                sw.write(b);
            }
        };
        return new PrintStream(os) {
            @Override
            public String toString() {
                return sw.toString();
            }
        };
    }
}
