package it.unibo.collections;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Time {
    private long time;

    public Time() {
        this.time = System.nanoTime();
    }

    public <X> void stmpTimeOfList(List<X> l) {
        time = System.nanoTime() - time;
        final var millis = TimeUnit.NANOSECONDS.toMillis(time);
        IO.println(
            "Converting "
                + l.size()
                + " ints to String and inserting them in a Set took "
                + time
                + "ns ("
                + millis
                + "ms)"
        );
    }
}
