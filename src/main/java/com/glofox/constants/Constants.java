package com.glofox.constants;

public final class Constants {

    // ClassService constants
    public static final String ERROR_CLASS_NAME_EMPTY = "Class name cannot be empty";
    public static final String ERROR_START_END_DATE_REQUIRED = "Start date and end date must be provided";
    public static final String ERROR_START_DATE_AFTER_END_DATE = "Start date cannot be after end date";
    public static final String ERROR_CAPACITY_GREATER_THAN_ZERO = "Capacity must be greater than zero";
    public static final String ERROR_DUPLICATE_CLASS = "A class with the same name and dates already exists";

    // BookingService constants
    public static final String ERROR_MEMBER_NAME_EMPTY = "Member name cannot be empty";
    public static final String ERROR_BOOKING_DATE_REQUIRED = "Booking date must be provided";

    private Constants() {
        // Private constructor to prevent instantiation
    }
}
