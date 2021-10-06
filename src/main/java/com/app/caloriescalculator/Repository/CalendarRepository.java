package com.app.caloriescalculator.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.caloriescalculator.Model.Calendar;;
@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {

}