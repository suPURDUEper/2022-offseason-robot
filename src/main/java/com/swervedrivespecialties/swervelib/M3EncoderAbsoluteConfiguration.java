package com.swervedrivespecialties.swervelib;

public class M3EncoderAbsoluteConfiguration {
    private final int id;
    private final double offset;

    

    public M3EncoderAbsoluteConfiguration(int id, double offset) {
        this.id = id;
        this.offset = offset;
    }

    public int getId() {
        return id;
    }

    public double getOffset() {
        return offset;
    }

}
