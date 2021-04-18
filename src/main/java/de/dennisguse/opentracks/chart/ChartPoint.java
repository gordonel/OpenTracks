package de.dennisguse.opentracks.chart;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import de.dennisguse.opentracks.content.data.Distance;
import de.dennisguse.opentracks.content.data.TrackPoint;
import de.dennisguse.opentracks.stats.TrackStatistics;
import de.dennisguse.opentracks.stats.TrackStatisticsUpdater;
import de.dennisguse.opentracks.util.DurationUtils;

public class ChartPoint {
    //X-axis
    private double timeOrDistance;

    //Y-axis
    private final double altitude;
    private double speed;
    private double pace;
    private double heartRate = Double.NaN;
    private double cadence = Double.NaN;
    private double power = Double.NaN;

    @VisibleForTesting
    ChartPoint(double altitude) {
        this.altitude = altitude;
    }

    public ChartPoint(@NonNull TrackStatisticsUpdater trackStatisticsUpdater, TrackPoint trackPoint, boolean chartByDistance, boolean metricUnits) {
        TrackStatistics trackStatistics = trackStatisticsUpdater.getTrackStatistics();

        if (chartByDistance) {
            timeOrDistance = trackStatistics.getTotalDistance().to(metricUnits);
        } else {
            timeOrDistance = trackStatistics.getTotalTime().toMillis();
        }

        altitude = Distance
                .of(trackStatisticsUpdater.getSmoothedAltitude())
                .toM_or_FT(metricUnits);

        speed = trackStatisticsUpdater.getSmoothedSpeed().to(metricUnits);
        pace = DurationUtils.toMin(trackStatisticsUpdater.getSmoothedSpeed().toPace(metricUnits));
        if (trackPoint != null) {
            if (trackPoint.hasHeartRate()) {
                heartRate = trackPoint.getHeartRate_bpm();
            }
            if (trackPoint.hasCyclingCadence()) {
                cadence = trackPoint.getCyclingCadence_rpm();
            }
            if (trackPoint.hasPower()) {
                power = trackPoint.getPower();
            }
        }
    }

    public double getTimeOrDistance() {
        return timeOrDistance;
    }

    public double getAltitude() {
        return altitude;
    }

    public double getSpeed() {
        return speed;
    }

    public double getPace() {
        return pace;
    }

    public boolean hasHeartRate() {
        return Double.isNaN(heartRate);
    }

    public double getHeartRate() {
        return heartRate;
    }

    public boolean hasCadence() {
        return Double.isNaN(cadence);
    }

    public double getCadence() {
        return cadence;
    }

    public boolean hasPower() {
        return Double.isNaN(power);
    }

    public double getPower() {
        return power;
    }
}
