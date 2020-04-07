package com.uxpsystems.assignment.app.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.uxpsystems.assignment.app.dao.AssignmentDao;
import com.uxpsystems.assignment.app.exception.AssignmentCustomException;
import com.uxpsystems.assignment.app.model.AssignmentEntities;

/**
 * @author xeadmin
 *
 */
@Service
public class AssignmentService {

	private static final String CLASS_NAME = AssignmentService.class.getName();

	private static final Logger LOGGER = LoggerFactory.getLogger(CLASS_NAME);

	@Autowired
	private AssignmentDao assignmentDao;

	/**
	 * @param entities
	 * @return
	 */
	public AssignmentEntities createRecords(AssignmentEntities entities) {

		LOGGER.info("inside createRecrods Method of " + CLASS_NAME);
		try {
			return assignmentDao.save(entities);
		} catch (Exception e) {
			LOGGER.error("Exception occured in createRecords() method " + e);
			throw new AssignmentCustomException("Exception Occured while creating records:");
		}

	}

	/**
	 * @param ticketId
	 * @return
	 */
	@Cacheable(value = "recordsCache", key = "#id")
	public Optional<AssignmentEntities> getRecordById(long ticketId) {

		LOGGER.info("inside getRecordById Method of " + CLASS_NAME);
		try {
			return assignmentDao.findById(ticketId);
		} catch (Exception e) {
			LOGGER.error("Exception occured in getRecordById() " + e);
			throw new AssignmentCustomException("Exception Occured while retriving records by id:");
		}

	}

	/**
	 * @return
	 */
	public Iterable<AssignmentEntities> getAllRecords() {

		LOGGER.info("inside getAllRecords Method of " + CLASS_NAME);
		try {
			return assignmentDao.findAll();
		} catch (Exception e) {
			LOGGER.error("Exception occured in getAllRecords() " + e);
			throw new AssignmentCustomException("Exception Occured while retriving all the records:");
		}

	}

	/**
	 * @param id
	 */
	@CacheEvict(value = "recordsCache", key = "#id")
	public void deleteRecord(long id) {

		LOGGER.info("inside deleteRecord Method of " + CLASS_NAME);
		try {
			assignmentDao.deleteById(id);
		} catch (Exception e) {
			LOGGER.error("Exception occured in deleteRecord() " + e);
			throw new AssignmentCustomException("Exception Occured while deleting the records:");
		}

	}

	/**
	 * @param id
	 * @param newStatus
	 * @return
	 */
	@CachePut(value = "recordsCache", key = "#id")
	public AssignmentEntities updateRecord(long id, String newStatus) {

		LOGGER.info("inside updateRecord Method of " + CLASS_NAME);
		try {
			AssignmentEntities entities = assignmentDao.findById(id).get();
			entities.setStatus(newStatus);
			AssignmentEntities upadedRecords = assignmentDao.save(entities);
			return upadedRecords;
		} catch (Exception e) {
			LOGGER.error("Exception occured in updateRecord() " + e);
			throw new AssignmentCustomException("Exception Occured while updating the records:");
		}
	}
}
