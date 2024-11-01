package com.glofox.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.glofox.exception.ClassCreationException;
import com.glofox.model.StudioClass;

@Service
public class ClassService {

	private final List<StudioClass> classes = new ArrayList<>(); // In-memory storage

	/**
	 * Adds a new class to the list if it meets validation criteria.
	 *
	 * @param studioClass the class to be added
	 * @throws ClassCreationException if class data is invalid or a duplicate exists
	 */
	public void createClass(StudioClass studioClass) {
		validateClassData(studioClass);
		if (isDuplicateClass(studioClass)) {
			throw new ClassCreationException("A class with the same name and dates already exists.");
		}
		classes.add(studioClass);
	}

	/**
	 * Retrieves all created classes.
	 *
	 * @return a list of all classes
	 */
	public List<StudioClass> getAllClasses() {
		return new ArrayList<>(classes); // Returning a copy to avoid external modification
	}

	/**
	 * Retrieves classes by name and updates their daily bookings.
	 *
	 * @param className     the name of the class to search for
	 * @param dailyBookings the number of bookings to update
	 * @return a list of classes matching the specified name
	 */
	public List<StudioClass> getClassByName(String className, int dailyBookings, LocalDate todaysDate) {
		List<StudioClass> foundClasses = classes.stream()
				.filter(existingClass -> existingClass.getClassName().equalsIgnoreCase(className))
				.collect(Collectors.toList());

		for (StudioClass studioClass : foundClasses) {
			updateDailyBookings(studioClass, dailyBookings, todaysDate);
		}

		return foundClasses;
	}

	/**
	 * Updates the daily bookings for a specific class.
	 *
	 * @param studioClass   the class to update
	 * @param dailyBookings the number of bookings to add
	 * @param todaysDate
	 */
	private void updateDailyBookings(StudioClass studioClass, int dailyBookings, LocalDate todaysDate) {

		studioClass.getDailyBookings().put(todaysDate, dailyBookings);

	}

	/**
	 * Validates class data.
	 *
	 * @param studioClass the class to validate
	 * @throws ClassCreationException if validation fails
	 */
	private void validateClassData(StudioClass studioClass) {
		if (studioClass.getClassName() == null || studioClass.getClassName().trim().isEmpty()) {
			throw new ClassCreationException("Class name cannot be empty");
		}
		if (studioClass.getStartDate() == null || studioClass.getEndDate() == null) {
			throw new ClassCreationException("Start date and end date must be provided");
		}
		if (studioClass.getStartDate().isAfter(studioClass.getEndDate())) {
			throw new ClassCreationException("Start date cannot be after end date");
		}
		if (studioClass.getCapacity() <= 0) {
			throw new ClassCreationException("Capacity must be greater than zero");
		}
	}

	/**
	 * Checks if a class with the same name and date range already exists.
	 *
	 * @param studioClass the class to check for duplicates
	 * @return true if a duplicate exists, false otherwise
	 */
	private boolean isDuplicateClass(StudioClass studioClass) {
		return classes.stream()
				.anyMatch(existingClass -> existingClass.getClassName().equalsIgnoreCase(studioClass.getClassName())
						&& existingClass.getStartDate().equals(studioClass.getStartDate())
						&& existingClass.getEndDate().equals(studioClass.getEndDate()));
	}
}
