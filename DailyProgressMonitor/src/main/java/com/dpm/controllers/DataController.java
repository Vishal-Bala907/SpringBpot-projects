package com.dpm.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dpm.errors.ValidationErrorBuilder;
import com.dpm.imple.services.DailyService;
import com.dpm.modals.DailyDetails;
import com.dpm.modals.SaveStatus;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class DataController {
	@Autowired
	private ValidationErrorBuilder validationErrorBuilder;
	@Autowired
	private DailyService dailyService;

	@CrossOrigin(origins = "http://localhost:5173" , methods = RequestMethod.POST)
	@PostMapping("/save")
	public ResponseEntity<SaveStatus> saveProgress(@Valid @RequestBody DailyDetails details,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body(validationErrorBuilder.fromBindingErrors(bindingResult));
		} else {

			SaveStatus saveDetails = dailyService.saveDetails(details);
			return new ResponseEntity<SaveStatus>(saveDetails, HttpStatus.CREATED);
		}

	}
	@CrossOrigin(origins = "http://localhost:5173/" , methods = RequestMethod.GET)
	@GetMapping("/get/{days}")
	public ResponseEntity<List<DailyDetails>> getDetails(@PathVariable int days) {
		List<DailyDetails> details = dailyService.getDetails(days);
		if(details == null) {
			return new ResponseEntity<List<DailyDetails>>(details,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<DailyDetails>>(details,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:5173/" , methods = RequestMethod.GET)
	@GetMapping("/get/by-date/{date}")
	public ResponseEntity<List<DailyDetails>> getDetails(@PathVariable LocalDate date) {
		List<DailyDetails> details = dailyService.getByDate(date);
		if(details == null) {
			return new ResponseEntity<List<DailyDetails>>(details,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<DailyDetails>>(details,HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:5173/" , methods = RequestMethod.DELETE)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTask(@PathVariable int id) {
		dailyService.deleteDataById(id);
		return new ResponseEntity<String>("Deleted...",HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:5173/" , methods = RequestMethod.GET)
	@GetMapping("/get/days/{days}")
	public ResponseEntity<Map<LocalDate, List<DailyDetails>>> getDataOfDays(@PathVariable int days) {
		List<DailyDetails> details = dailyService.getDetails(days);
		Map<LocalDate, List<DailyDetails>> dataOfDays = dailyService.getDataOfDays(details, days);
		System.out.println(dataOfDays);
		return new ResponseEntity<Map<LocalDate,List<DailyDetails>>>(dataOfDays,HttpStatus.OK);
	
	}
	
	

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public SaveStatus handleException(MethodArgumentNotValidException exception) {
		return createValidationError(exception);
	}

	private SaveStatus createValidationError(MethodArgumentNotValidException e) {
		return validationErrorBuilder.fromBindingErrors(e.getBindingResult());
	}

}
