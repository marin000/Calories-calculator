package com.app.caloriescalculator.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

import com.app.caloriescalculator.Model.Temp;
import com.app.caloriescalculator.Repository.TempRepository;
import com.app.caloriescalculator.Validator.TempDto;

@Service
public class TempServiceImpl implements TempService {

    @Autowired
    private TempRepository tempRepository;

    @Override
    public Temp add(TempDto  TempDto) {
        Temp temp=new Temp();
        temp.setProduct(TempDto.getProduct());
        temp.setMeal(TempDto.getMeal());
        temp.setQuantity(TempDto.getQuantity());
        temp.setCalories(TempDto.getCalories());
        temp.setCarbohydrates(TempDto.getCarbohydrates());
        temp.setProteins(TempDto.getProteins());
        temp.setFats(TempDto.getFats());
        return tempRepository.save(temp);
    }

    @Override
    public Collection<Temp> findAllTemp() {
        return tempRepository.findAll();
    }

    @Override
    public Temp TempfindById(Integer id) {
        return tempRepository.findById(id);
    }

    @Override
    public void deleteTemp(Temp temp) {
        tempRepository.delete(temp);
    }

}