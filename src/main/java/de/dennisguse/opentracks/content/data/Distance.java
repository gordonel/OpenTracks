package de.dennisguse.opentracks.content.data;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Distance {

    private static final double MM_TO_M = 0.001;

    private static final double KM_TO_M = 1000.0;
    static final double M_TO_KM = 1 / KM_TO_M;

    static final double KM_TO_MI = 0.621371192;
    private static final double MI_TO_KM = 1 / KM_TO_MI;
    static final double M_TO_MI = M_TO_KM * KM_TO_MI;

    private static final double MI_TO_FT = 5280.0;


    public static Distance of(double distance_m) {
        return new Distance(distance_m);
    }

    public static Distance of(String distance_m) {
        return of(Float.parseFloat(distance_m));
    }


    public static Distance ofMilimeter(double distance_mm) {
        return of(distance_mm * MM_TO_M);
    }

    public static Distance ofMile(double distance_mile) {
        return of(distance_mile * MI_TO_KM * KM_TO_M);
    }

    public static Distance ofKilometer(double distance_km) {
        return of(distance_km * KM_TO_M);
    }

    public static Distance one(boolean metricUnit) {
        if (metricUnit) {
            return Distance.ofKilometer(1);
        } else {
            return Distance.ofMile(1);
        }
    }

    public static Distance invalid() {
        return of(Double.NaN);
    }

    private final double distance_m;

    private Distance(double distance_m) {
        this.distance_m = distance_m;
    }

    public Distance plus(@NonNull Distance distance) {
        return new Distance(distance_m + distance.distance_m);
    }

    public Distance minus(@NonNull Distance distance) {
        return new Distance(distance_m - distance.distance_m);
    }

    public Distance multipliedBy(double factor) {
        return new Distance(factor * distance_m);
    }

    public Distance dividedBy(double divisor) {
        return multipliedBy(1 / divisor);
    }

    public double dividedBy(@NonNull Distance divisor) {
        return distance_m / divisor.distance_m;
    }

    public boolean isZero() {
        return distance_m == 0;
    }

    public boolean isInvalid() {
        return Double.isNaN(distance_m) || Double.isInfinite(distance_m);
    }

    public boolean lessThan(@NonNull Distance distance) {
        return !greaterOrEqualThan(distance);
    }

    public boolean greaterThan(@NonNull Distance distance) {
        return distance_m > distance.distance_m;
    }

    public boolean greaterOrEqualThan(@NonNull Distance distance) {
        return distance_m >= distance.distance_m;
    }

    public double toM() {
        return distance_m;
    }

    public double toKM() {
        return distance_m * M_TO_KM;
    }

    public double toFT() {
        return distance_m * M_TO_KM * KM_TO_MI * MI_TO_FT;
    }

    public double toMI() {
        return toKM() * KM_TO_MI;
    }

    //TODO Rename to toKM_or_MI
    public double to(boolean metricUnit) {
        return to(metricUnit ? Unit.KM : Unit.MILES);
    }

    public double toM_or_FT(boolean metricUnit) {
        return to(metricUnit ? Unit.M : Unit.FT);
    }

    private double to(Unit unit) {
        switch (unit) {
            case M:
                return toM();
            case KM:
                return toKM();
            case FT:
                return toFT();
            case MILES:
                return toMI();
            default:
                throw new RuntimeException("Not implemented");
        }
    }

    private enum Unit {
        M,
        KM,
        FT,
        MILES
    }

    //TODO We check on exact match of a double. That is probably not the best approach considering multiplication etc.
    //Can we store data as in milimeter as long?
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance = (Distance) o;
        return Double.compare(distance.distance_m, distance_m) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance_m);
    }

    @Override
    public String toString() {
        return "Distance{" +
                "distance_m=" + distance_m +
                '}';
    }
}
