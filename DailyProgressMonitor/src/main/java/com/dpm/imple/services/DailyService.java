package com.dpm.imple.services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.dpm.interfaces.services.ServiceInterface;
import com.dpm.modals.DailyDetails;
import com.dpm.modals.SaveStatus;
import com.dpm.repos.DailyRepository;

@Component
public class DailyService implements ServiceInterface {

	@Autowired
	private DailyRepository repository;
//	@Autowired
	private SaveStatus saveStatus;

	@Override
	public SaveStatus saveDetails(DailyDetails dailyDetails) {

//		int exception = 1/Integer.parseInt(dailyDetails.getEndTime());

		String startTime = dailyDetails.getStartTime();
		String endTime = dailyDetails.getEndTime();

		LocalTime parse = LocalTime.parse(startTime);
		LocalTime parse1 = LocalTime.parse(endTime);

		Duration duration = Duration.between(parse, parse1);

		dailyDetails.setMinutes(duration.toMinutes());
		
		dailyDetails.setDate(LocalDate.now());
		
		repository.save(dailyDetails);
		saveStatus = new SaveStatus();
		saveStatus.setErrors(null);
		saveStatus.setMessage("SAVED SUCCESSFULL...");
		saveStatus.setStatus(HttpStatus.OK);
		return saveStatus;
	}

	@Override
	public List<DailyDetails> getDetails(int days) {
		LocalDate date1 = LocalDate.now();
		LocalDate date2 = LocalDate.now().minusDays(days);
		
		List<DailyDetails> details = repository.getDetails(date2, date1);
		
		return details;
	}
	
	

}
