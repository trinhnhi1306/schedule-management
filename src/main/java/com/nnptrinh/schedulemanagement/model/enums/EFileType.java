package com.nnptrinh.schedulemanagement.model.enums;

public enum EFileType {
    USER("users"),
    CLAZZ("clazzes"),
    COURSE("courses"),
    SCHEDULE("schedules");

    private String value;

    // Constructor
    EFileType(String value) {
        this.value  = value ;
    }

    // Getter for value 
    public String getValue () {
        return value ;
    }
}
