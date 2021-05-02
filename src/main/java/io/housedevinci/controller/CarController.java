package io.housedevinci.controller;

import io.housedevinci.controller.mapper.CarMapper;
import io.housedevinci.datatransferobject.Car;
import io.housedevinci.service.driver.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public Car getCars() {
        return CarMapper.convertCarsToDTO(carService.getCars());
    }
}
