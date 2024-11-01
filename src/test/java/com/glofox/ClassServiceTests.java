package com.glofox;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.glofox.exception.ClassCreationException;
import com.glofox.model.StudioClass;
import com.glofox.service.ClassService;

@SpringBootTest
public class ClassServiceTests {

	private final ClassService classService = new ClassService();

	@Test
	public void testCreateClass() {
		StudioClass studioClass = new StudioClass("Yoga", LocalDate.now(), LocalDate.now().plusDays(10), 20);
		classService.createClass(studioClass);
		List<StudioClass> classes = classService.getAllClasses();
		assertEquals(1, classes.size());
	}

	@Test
	public void testCreateClassWithEmptyName() {
		StudioClass studioClass = new StudioClass("", LocalDate.now(), LocalDate.now().plusDays(10), 20);
		assertThrows(ClassCreationException.class, () -> classService.createClass(studioClass),
				"Class name cannot be empty");
	}

	@Test
	public void testCreateClassWithInvalidDates() {
		StudioClass studioClass = new StudioClass("Pilates", LocalDate.now().plusDays(10), LocalDate.now(), 15);
		assertThrows(ClassCreationException.class, () -> classService.createClass(studioClass),
				"Start date cannot be after end date");
	}

}
