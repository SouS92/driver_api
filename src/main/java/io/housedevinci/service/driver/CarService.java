package io.housedevinci.service.driver;

import io.housedevinci.dataaccessobject.CarRepository;
import io.housedevinci.domainobject.CarDO;
import io.housedevinci.exception.EntityNotFoundException;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<CarDO> getCars() {
        return Lists.newArrayList(carRepository.findAll());
    }

    public CarDO getCarByLicensePlate(String licensePlate) throws EntityNotFoundException{
        CarDO car = carRepository.getBylicensePlate(licensePlate);

        if (car == null) {
            throw new EntityNotFoundException(String.format("Car not found with id ",licensePlate));
        }
        return car;
    }
}
