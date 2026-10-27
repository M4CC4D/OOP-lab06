package it.unibo.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    public static int FROM = 1000;
    public static int TO = 2000;
    public static int TESTADDFIRST = 100000;
    public static int TESTGETMIDLE = 1000;

    public static long POPULATIONAFRICA = 1110635000;
    public static long POPULATIONAMERICAS = 972005000;
    public static long POPULATIONANTARCTICA = 0;
    public static long POPULATIONASIA = 429872300;
    public static long POPULATIONEUROPE = 742452000;
    public static long POPULATIONOCEANIA = 38304000;

    private UseListsAndMaps() {
    }

    public static void testAddFirst(List<Integer> l) {
        Time t = new Time();
        for(int i = TO; i < TESTADDFIRST + TO; i++) {
            l.addFirst(i);
        }
        t.<Integer>stmpTimeOfList(l);
    }

    public static void testGetMidle(List<Integer> l) {
        Time t = new Time();
        for(int i = 0; i < TESTGETMIDLE; i++) {
            l.get(l.size() / 2);
        }
        t.<Integer>stmpTimeOfList(l);
    }

    /**
     * Program entry point.
     */
    public static void main() {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        ArrayList<Integer> ar = new ArrayList<>();

        for(int i = FROM; i <= TO; i++ ){
            ar.add(i);
        }
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        LinkedList<Integer> ll = new LinkedList<>(ar);
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        var previous = ar.getFirst();
        var last = ar.getLast();
        ar.set(0, last);
        ar.set(ar.size() - 1, previous);
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        for(Integer value : ar) {
            IO.println(value);
        }
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        testAddFirst(ar);
        testAddFirst(ll);
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */
        testGetMidle(ar);
        testGetMidle(ll);
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        Map<String, Long> m = new HashMap<>();
        m.put("Africa", POPULATIONAFRICA);
        m.put("Americas", POPULATIONAMERICAS);
        m.put("Antarctica", POPULATIONANTARCTICA);
        m.put("Asia", POPULATIONASIA);
        m.put("Europe", POPULATIONEUROPE);
        m.put("Oceania", POPULATIONOCEANIA);
        
        /*
         * 8) Compute the population of the world
         */
    }
}
