
package com.glofox.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudioClass {

	private String className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Map<LocalDate, Integer> getDailyBookings() {
		return dailyBookings;
	}

	public void setDailyBookings(Map<LocalDate, Integer> dailyBookings) {
		this.dailyBookings = dailyBookings;
	}

	private LocalDate startDate;
	private LocalDate endDate;
	private int capacity;

	public StudioClass(String className, LocalDate startDate, LocalDate endDate, int capacity) {
		super();
		this.className = className;
		this.startDate = startDate;
		this.endDate = endDate;
		this.capacity = capacity;
	}

	private Map<LocalDate, Integer> dailyBookings = new HashMap<>();

}
