package main.Util;

import static main.Util.Common.infinity;

public class Interval {
    final static Interval empty = new Interval(+infinity, -infinity);
    final static Interval universe = new Interval(-infinity, +infinity);
    public double min, max;

    public Interval(double _min, double _max){
        min = _min;
        max = _max;
    }

    public boolean contains(double x) {
        return min <= x && x <= max;
    }

    public boolean surrounds(double x) {
        return min < x && x < max;
    }
}
