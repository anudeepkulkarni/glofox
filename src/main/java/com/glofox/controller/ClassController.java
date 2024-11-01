package com.glofox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glofox.exception.ClassCreationException;
import com.glofox.model.StudioClass;
import com.glofox.service.ClassService;

@RestController
@RequestMapping("/api/v1/classes")
public class ClassController {

	// Fine tuning the project has endless possibilities excluding things such as
	// adding loggers, adding swagger endpoint, adding constant package etc..

	@Autowired
	private ClassService classService;

	@PostMapping
	public ResponseEntity<String> createClass(@RequestBody StudioClass studioClass) {
		try {
			if (studioClass.getClassName() == null || studioClass.getClassName().isEmpty()) {
				throw new ClassCreationException("Class name cannot be empty");
			}
			if (studioClass.getCapacity() <= 0) {
				throw new ClassCreationException("Capacity must be greater than zero");
			}
			classService.createClass(studioClass);
			return ResponseEntity.status(HttpStatus.CREATED).body("Class created successfully.");
		} catch (ClassCreationException classCreateException) {
			throw classCreateException;
		} catch (Exception e) {
			throw new ClassCreationException("An error occurred while creating the class");
		}
	}
}
