/*
 * Copyright 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package de.dennisguse.opentracks.util;

import java.time.Duration;

/**
 * Unit conversion constants.
 *
 * @author Sandor Dornbush
 */
public class DurationUtils {

    // multiplication factor to convert milliseconds to seconds
    public static final double MS_TO_S = 1d / Duration.ofSeconds(1).toMillis();
    // multiplication factor to convert seconds to minutes

    public static final double S_TO_MIN = 1 / 60.0;
    // multiplication factor to convert minutes to hours

    public static double toMin(Duration duration) {
        return duration.toMillis() * DurationUtils.MS_TO_S * DurationUtils.S_TO_MIN;
    }

    private DurationUtils() {
    }
}
