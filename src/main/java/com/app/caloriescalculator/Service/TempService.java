package com.app.caloriescalculator.Service;
import java.util.Collection;

import com.app.caloriescalculator.Model.Temp;
import com.app.caloriescalculator.Validator.TempDto;

public interface TempService {

    Temp add(TempDto tempDto);

    Collection <Temp> findAllTemp();

    Temp TempfindById(Integer id);

    void deleteTemp(Temp temp);

}