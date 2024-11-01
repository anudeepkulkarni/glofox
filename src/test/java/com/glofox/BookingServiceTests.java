package com.glofox;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.glofox.exception.BookingException;
import com.glofox.model.Booking;
import com.glofox.model.StudioClass;
import com.glofox.service.BookingService;
import com.glofox.service.ClassService;

@SpringBootTest
public class BookingServiceTests {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private ClassService classService;

	@BeforeEach
	public void setUp() {
		classService.createClass(new StudioClass("Yoga", LocalDate.now(), LocalDate.now().plusDays(10), 20));
	}

	@Test
	public void testBookClass() {
		Booking booking = new Booking("John Doe", LocalDate.now(), "Yoga");
		bookingService.bookClass(booking);
		List<Booking> bookings = bookingService.getAllBookings();
		assertEquals(1, bookings.size());
	}

	@Test
	public void testBookClassWithEmptyMemberName() {
		Booking booking = new Booking("", LocalDate.now(), "Yoga");
		assertThrows(BookingException.class, () -> bookingService.bookClass(booking), "Member name cannot be empty");
	}

	@Test
	public void testBookClassWithNullBookingDate() {
		Booking booking = new Booking("Jane Doe", null, "Yoga");
		assertThrows(BookingException.class, () -> bookingService.bookClass(booking), "Booking date must be provided");
	}
}
