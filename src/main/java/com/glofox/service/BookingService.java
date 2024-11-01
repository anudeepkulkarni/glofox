package com.glofox.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glofox.exception.BookingException;
import com.glofox.exception.ClassCreationException;
import com.glofox.model.Booking;

@Service
public class BookingService {

    @Autowired
    ClassService classService;

    private final List<Booking> bookings = new ArrayList<>();

    /**
     * Adds a booking if it meets validation criteria.
     *
     * @param booking the booking details
     * @throws BookingException if booking data is invalid
     */
    public void bookClass(Booking booking) {
        validateBookingData(booking);
        
        try {
            // Attempting to get the class and update bookings
            classService.getClassByName(booking.getClassName(), 1, booking.getBookingDate());
            // If successful, adding the booking
            bookings.add(booking);
        } catch (ClassCreationException e) {
            throw new BookingException("Failed to book class: " + e.getMessage());
        }
    }

    /**
     * Retrieves all bookings.
     *
     * @return a list of all bookings
     */
    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings); // Returning a copy to avoid external modification
    }

    /**
     * Validates booking data.
     *
     * @param booking the booking to validate
     * @throws BookingException if validation fails
     */
    private void validateBookingData(Booking booking) {
        if (booking.getMemberName() == null || booking.getMemberName().trim().isEmpty()) {
            throw new BookingException("Member name cannot be empty");
        }
        if (booking.getBookingDate() == null) {
            throw new BookingException("Booking date must be provided");
        }
    }
}
