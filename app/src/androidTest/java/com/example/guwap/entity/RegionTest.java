package com.example.guwap.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegionTest {

    @Test
    public void distanceTo() {
        Region reg1 = new Region(35.667222, -105.964444, "Santa Fe");
        Region reg2 = new Region(38.746149, -105.184042,"Cripple Creek");

        double dist = reg1.distanceTo(reg2);
        assertEquals(dist, 349000, 500);
    }
}