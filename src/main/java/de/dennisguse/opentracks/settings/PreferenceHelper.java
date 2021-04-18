package de.dennisguse.opentracks.settings;

import android.content.Context;

import de.dennisguse.opentracks.R;
import de.dennisguse.opentracks.content.data.Distance;
import de.dennisguse.opentracks.util.PreferencesUtils;

final class PreferenceHelper {

    static String[] getMinRecordingIntervalEntries(Context context) {
        String[] entryValues = context.getResources().getStringArray(R.array.min_recording_interval_values);
        String[] entries = new String[entryValues.length];
        for (int i = 0; i < entryValues.length; i++) {
            int value = Integer.parseInt(entryValues[i]);

            if (value == PreferencesUtils.getMinRecordingIntervalAdaptAccuracy(context)) {
                entries[i] = context.getString(R.string.value_adapt_accuracy);
            } else if (value == PreferencesUtils.getMinRecordingIntervalAdaptBatteryLife(context)) {
                entries[i] = context.getString(R.string.value_adapt_battery_life);
            } else if (value == PreferencesUtils.getMinRecordingIntervalDefault(context)) {
                entries[i] = context.getString(R.string.value_smallest_recommended);
            } else {
                entries[i] = value < 60 ? context.getString(R.string.value_integer_second, value) : context.getString(R.string.value_integer_minute, value / 60);
            }
        }

        return entries;
    }

    static String[] getRecordingDistanceIntervalEntries(Context context, boolean metricUnits) {
        String[] entryValues = context.getResources().getStringArray(R.array.recording_distance_interval_values);
        String[] entries = new String[entryValues.length];

        final int recordingDistanceIntervalDefault = (int) PreferencesUtils.getRecordingDistanceIntervalDefault(context).toM();

        for (int i = 0; i < entryValues.length; i++) {
            int value = Integer.parseInt(entryValues[i]);
            if (metricUnits) {
                if (value == recordingDistanceIntervalDefault) {
                    entries[i] = context.getString(R.string.value_integer_meter_recommended, value);
                } else {
                    entries[i] = context.getString(R.string.value_integer_meter, value);
                }
            } else {
                Distance distance = Distance.of(value);
                int feet = (int) distance.toFT();
                if (value == recordingDistanceIntervalDefault) {
                    entries[i] = context.getString(R.string.value_integer_feet_recommended, feet);
                } else {
                    entries[i] = context.getString(R.string.value_integer_feet, feet);
                }
            }
        }

        return entries;
    }

    static String[] getMaxRecordingDistanceEntries(Context context, boolean metricUnits) {
        String[] entryValues = context.getResources().getStringArray(R.array.max_recording_distance_values);
        String[] entries = new String[entryValues.length];

        final int maxRecordingDistanceDefault = Integer.parseInt(context.getResources().getString(R.string.max_recording_distance_default));

        for (int i = 0; i < entryValues.length; i++) {
            int value = Integer.parseInt(entryValues[i]);
            if (metricUnits) {
                if (value == maxRecordingDistanceDefault) {
                    entries[i] = context.getString(R.string.value_integer_meter_recommended, value);
                } else {
                    entries[i] = context.getString(R.string.value_integer_meter, value);
                }
            } else {
                Distance distance = Distance.of(value);
                int feet = (int) distance.toFT();
                if (feet < 2000) {
                    if (value == maxRecordingDistanceDefault) {
                        entries[i] = context.getString(R.string.value_integer_feet_recommended, feet);
                    } else {
                        entries[i] = context.getString(R.string.value_integer_feet, feet);
                    }
                } else {
                    entries[i] = context.getString(R.string.value_float_mile, distance.toMI());
                }
            }
        }

        return entries;
    }

    static String[] getRecordingGpsAccuracyEntries(Context context, boolean metricUnits) {
        String[] entryValues = context.getResources().getStringArray(R.array.recording_gps_accuracy_values);
        String[] entries = new String[entryValues.length];

        final int recordingGPSAccuracyDefault = Integer.parseInt(context.getResources().getString(R.string.recording_gps_accuracy_default));
        final int recordingGPSAccuracyExcellent = Integer.parseInt(context.getResources().getString(R.string.recording_gps_accuracy_excellent));
        final int recordingGPSAccuracyPoor = Integer.parseInt(context.getResources().getString(R.string.recording_gps_accuracy_poor));

        for (int i = 0; i < entryValues.length; i++) {
            int value = Integer.parseInt(entryValues[i]);
            if (metricUnits) {
                if (value == recordingGPSAccuracyDefault) {
                    entries[i] = context.getString(R.string.value_integer_meter_recommended, value);
                } else if (value == recordingGPSAccuracyExcellent) {
                    entries[i] = context.getString(R.string.value_integer_meter_excellent_gps, value);
                } else if (value == recordingGPSAccuracyPoor) {
                    entries[i] = context.getString(R.string.value_integer_meter_poor_gps, value);
                } else {
                    entries[i] = context.getString(R.string.value_integer_meter, value);
                }
            } else {
                Distance distance = Distance.of(value);
                int feet = (int) distance.toFT();
                if (feet < 2000) {
                    if (value == recordingGPSAccuracyDefault) {
                        entries[i] = context.getString(R.string.value_integer_feet_recommended, feet);
                    } else if (value == recordingGPSAccuracyExcellent) {
                        entries[i] = context.getString(R.string.value_integer_feet_excellent_gps, feet);
                    } else {
                        entries[i] = context.getString(R.string.value_integer_feet, feet);
                    }
                } else {
                    double mile = distance.toMI();
                    if (value == recordingGPSAccuracyPoor) {
                        entries[i] = context.getString(R.string.value_float_mile_poor_gps, mile);
                    } else {
                        entries[i] = context.getString(R.string.value_float_mile, mile);
                    }
                }
            }
        }

        return entries;
    }
}
