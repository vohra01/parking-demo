package com.jinternal.employee.enums.enums;


public enum VehicleType {
    BIKE(Size.SMALL),
    CAR(Size.MEDIUM),
    BUS(Size.LARGE);

    private final Size size;

    VehicleType(Size size) {
        this.size = size;
    }

    public Size getSize() {
        return size;
    }
}
