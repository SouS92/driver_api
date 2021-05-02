package io.housedevinci.service.driver;

import io.housedevinci.datatransferobject.DriverSearch;
import io.housedevinci.domainobject.DriverDO;
import io.housedevinci.domainvalue.OnlineStatus;
import io.housedevinci.exception.CarAlreadyInUseException;
import io.housedevinci.exception.ConstraintsViolationException;
import io.housedevinci.exception.EntityNotFoundException;

import java.util.List;

public interface DriverService {

    DriverDO find(Long driverId) throws EntityNotFoundException;

    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    List<DriverDO> find(OnlineStatus onlineStatus);

    void rentCar(Long driverId, String licensePlate) throws EntityNotFoundException, CarAlreadyInUseException;

    void releaseCar(Long driverId) throws EntityNotFoundException;

    List<DriverDO> findByCriteriaBuilder(DriverSearch driverSearch);

}
