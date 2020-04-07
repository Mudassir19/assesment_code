package com.uxpsystems.assignment.app.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uxpsystems.assignment.app.model.AssignmentEntities;
import com.uxpsystems.assignment.app.service.AssignmentService;

/**
 * @author Mudassir
 *
 */
@RestController
@RequestMapping(value = "/assignement/records")
public class AssignmentController {

	private static final String CLASS_NAME = AssignmentController.class.getName();

	private static final Logger LOGGER = LoggerFactory.getLogger(CLASS_NAME);

	@Autowired
	private AssignmentService assignmentService;

	/**
	 * @param entities
	 * @return
	 */
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public AssignmentEntities createRecords(@RequestBody AssignmentEntities entities) {

		LOGGER.info("inside createRecords method of " + CLASS_NAME);
		return assignmentService.createRecords(entities);
		
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<AssignmentEntities> getRecordById(@PathVariable("id") Long id) {

		LOGGER.info("inside getRecordById method of " + CLASS_NAME);
		return assignmentService.getRecordById(id);
	}

	/**
	 * @return
	 */
	@GetMapping(value = "/allrecords", produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<AssignmentEntities> getAllRecords() {

		LOGGER.info("inside getAllRecords method of " + CLASS_NAME);
		return assignmentService.getAllRecords();

	}

	/**
	 * @param id
	 */
	@DeleteMapping(value = "delete/{id}")
	public void deleteRecord(@PathVariable("id") Long id) {

		LOGGER.info("inside deleteRecords method of " + CLASS_NAME);
		assignmentService.deleteRecord(id);
		
		
	}

	/**
	 * @param id
	 * @param newStatus
	 * @return
	 */
	@PutMapping(value = "update/{id}/{newStatus}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AssignmentEntities updateRecord(@PathVariable("id") Long id, @PathVariable("newStatus") String newStatus) {

		LOGGER.info("inside updateRecord method of " + CLASS_NAME);
		return assignmentService.updateRecord(id, newStatus);
	}

}
