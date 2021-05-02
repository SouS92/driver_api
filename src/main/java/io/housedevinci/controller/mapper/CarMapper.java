package io.housedevinci.controller.mapper;

import io.housedevinci.datatransferobject.Car;
import io.housedevinci.datatransferobject.CarDTO;
import io.housedevinci.domainobject.CarDO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {

    public static Car convertCarsToDTO(List<CarDO> source) {
        Car result = new Car();
        result.setCars(source.stream().map(CarMapper::convertCarToDTO).collect(Collectors.toList()));

        return result;
    }

    public static CarDTO convertCarToDTO(CarDO source) {
        CarDTO result = new CarDTO();
        BeanUtils.copyProperties(source, result);
        if(source.getDriver() != null)
            result.setCarSelected(true);
        return result;
    }
}
