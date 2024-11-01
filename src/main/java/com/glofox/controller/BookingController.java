package com.glofox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glofox.constants.Constants;
import com.glofox.exception.BookingException;
import com.glofox.model.Booking;
import com.glofox.service.BookingService;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

	// Fine tuning the project has endless possibilities excluding things such as
	// adding loggers, adding swagger endpoint, constant package, entity classes etc..

	@Autowired
	private BookingService bookingService;

	@PostMapping
	public ResponseEntity<String> bookClass(@RequestBody Booking booking) {
		try {
			if (booking.getMemberName() == null || booking.getMemberName().isEmpty()) {
				throw new BookingException(Constants.ERROR_MEMBER_NAME_EMPTY);
			}
			if (booking.getBookingDate() == null) {
				throw new BookingException(Constants.ERROR_BOOKING_DATE_REQUIRED);
			}
			bookingService.bookClass(booking);
			return ResponseEntity.status(HttpStatus.CREATED).body("Booking successful.");
		} catch (BookingException e) {
			throw e;
		} catch (Exception e) {
			throw new BookingException("An error occurred while processing the booking");
		}
	}
}
