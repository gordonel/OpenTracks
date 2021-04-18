package de.dennisguse.opentracks.content.data;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DistanceTest {

    @Test
    public void ofMilimeter() {
        assertEquals(Distance.of(0.1), Distance.ofMilimeter(100));
    }

    @Test
    public void ofMile() {
        assertEquals(Distance.of(1609.344).toM(), Distance.ofMile(1).toM(), 0.01);
    }

    @Test
    public void ofKilometer() {
        assertEquals(Distance.of(1000), Distance.ofKilometer(1));
    }

    @Test
    public void one() {
        assertEquals(Distance.ofKilometer(1), Distance.one(true));
        assertEquals(Distance.ofMile(1), Distance.one(false));
    }

    @Test
    public void plus() {
        assertEquals(Distance.of(1000), Distance.of(1).plus(Distance.of(999)));
    }

    @Test
    public void minus() {
        assertEquals(Distance.of(1), Distance.of(1000).minus(Distance.of(999)));
    }

    @Test
    public void multipliedBy() {
        assertEquals(Distance.of(1000), Distance.of(2).multipliedBy(500));
    }

    @Test
    public void dividedBy() {
        assertEquals(Distance.of(100), Distance.of(1000).dividedBy(10));
    }

    @Test
    public void isZero() {
        assertEquals(Distance.of(0), Distance.of(2).multipliedBy(0));
    }

    @Test
    public void lessThan() {
        assertTrue(Distance.of(0).lessThan(Distance.of(1)));
        assertFalse(Distance.of(1).lessThan(Distance.of(0)));

        assertFalse(Distance.of(1).lessThan(Distance.of(1)));
    }

    @Test
    public void greaterThan() {
        assertTrue(Distance.of(1).greaterThan(Distance.of(0)));
        assertFalse(Distance.of(0).greaterThan(Distance.of(1)));

        assertFalse(Distance.of(1).greaterThan(Distance.of(1)));
    }

    @Test
    public void greaterOrEqualThan() {
        assertTrue(Distance.of(1).greaterOrEqualThan(Distance.of(0)));
        assertFalse(Distance.of(0).greaterOrEqualThan(Distance.of(1)));

        assertTrue(Distance.of(1).greaterOrEqualThan(Distance.of(1)));
    }

    @Test
    public void toM() {
        assertEquals(100.5, Distance.of(100.5).toM(), 0.01);
    }

    @Test
    public void toKM() {
        assertEquals(2.001, Distance.of(2001).toKM(), 0.01);
    }

    @Test
    public void toFT() {
        assertEquals(329.724, Distance.of(100.5).toFT(), 0.01);
    }

    @Test
    public void toMI() {
        assertEquals(1.24, Distance.of(2000.5).toMI(), 0.01);
    }

    @Test
    public void toM_or_FT() {
        assertEquals(100.5, Distance.of(100.5).toM_or_FT(true), 0.01);
        assertEquals(329.72, Distance.of(100.5).toM_or_FT(false), 0.01);
    }

    @Test
    public void to() {
        assertEquals(1.005, Distance.of(1000.5).to(true), 0.01);
        assertEquals(1.24, Distance.of(2000.5).to(false), 0.01);
    }

    @Test
    public void testEquals() {
        assertEquals(Distance.of(1), Distance.of(0).plus(Distance.of(1)));
        assertEquals(Distance.of(2), Distance.of(1).multipliedBy(2));
    }
}