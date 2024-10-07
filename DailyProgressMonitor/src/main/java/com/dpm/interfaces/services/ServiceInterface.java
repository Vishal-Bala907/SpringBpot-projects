package com.dpm.interfaces.services;

import java.util.List;

import com.dpm.modals.DailyDetails;
import com.dpm.modals.SaveStatus;

public interface ServiceInterface {
	public SaveStatus saveDetails(DailyDetails dailyDetails);
	
	public List<DailyDetails> getDetails(int date);
}
