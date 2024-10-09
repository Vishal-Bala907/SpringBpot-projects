package com.dpm.repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dpm.modals.DailyDetails;

@Repository
public interface DailyRepository extends JpaRepository<DailyDetails, Integer> {
	
	@Query("SELECT d FROM DailyDetails d WHERE d.date >= :date1 AND d.date <= :date2 ")
	public List<DailyDetails> getDetails(LocalDate date1 ,LocalDate date2);
	
	public List<DailyDetails> findByDate(LocalDate date);
	
}
