package com.app.caloriescalculator.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.caloriescalculator.Model.Temp;

@Repository
public interface TempRepository extends JpaRepository<Temp, Long> {

	Temp save(Temp temp);

	Temp findById(Integer id);

}