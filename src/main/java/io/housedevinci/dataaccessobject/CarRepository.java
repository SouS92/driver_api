package io.housedevinci.dataaccessobject;

import io.housedevinci.domainobject.CarDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<CarDO,Long> {

    CarDO getBylicensePlate(String licensePlate);
}
