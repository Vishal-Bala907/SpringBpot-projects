package com.dpm.interfaces.services;

import java.time.LocalDate;
import java.util.List;

import com.dpm.modals.ChartData;
import com.dpm.modals.DailyDetails;
import com.dpm.modals.SaveStatus;

public interface ServiceInterface {
	public SaveStatus saveDetails(DailyDetails dailyDetails);
	
	public List<DailyDetails> getDetails(int date);
	
	public List<ChartData> getChartData(int data);
	
	public List<DailyDetails> getByDate(LocalDate date);
	
	public boolean deleteDataById(int id);
}
