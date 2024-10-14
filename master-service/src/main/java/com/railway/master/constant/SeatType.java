package com.railway.master.constant;

public enum SeatType {

    LowerBerth("LB"),
    MiddleBerth("MB"),
    UpperBerth("UB"),
    SideLowerBerth("SLB"),
    SideUpperBerth("SUB"),
    SideMiddleBerth("SMB");

    private String code;

    SeatType(String code) {
        this.code = code;
    }

    public static SeatType fromCode(String code) {
        for (SeatType seatType : values()) {
            if (seatType.getCode().equalsIgnoreCase(code)) {
                return seatType;
            }
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }
}

