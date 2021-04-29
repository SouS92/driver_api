package com.freenow.service.driver;

import com.freenow.dataaccessobject.DriverRepository;
import com.freenow.datatransferobject.DriverSearch;
import com.freenow.domainobject.CarDO;
import com.freenow.domainobject.DriverDO;
import com.freenow.domainvalue.GeoCoordinate;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.exception.CarAlreadyInUseException;
import com.freenow.exception.ConstraintsViolationException;
import com.freenow.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.SerializationUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverService implements DriverService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    private final DriverRepository driverRepository;

    private final CarService carService;

    private final EntityManager entityManager;

    public DefaultDriverService(final DriverRepository driverRepository, CarService carService, EntityManager entityManager) {
        this.driverRepository = driverRepository;
        this.carService = carService;
        this.entityManager = entityManager;
    }


    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDO find(Long driverId) throws EntityNotFoundException {
        return findDriverChecked(driverId);
    }


    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    public DriverDO create(DriverDO driverDO) throws ConstraintsViolationException {
        DriverDO driver;
        try {
            driver = driverRepository.save(driverDO);
        } catch (DataIntegrityViolationException e) {
            LOG.warn("ConstraintsViolationException while creating a driver: {}", driverDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setDeleted(true);
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverDO> find(OnlineStatus onlineStatus) {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }


    private DriverDO findDriverChecked(Long driverId) throws EntityNotFoundException {
        return driverRepository.findById(driverId)
                .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + driverId));
    }

    @Override
    @Transactional
    public void rentCar(Long driverId, String licensePlate) throws EntityNotFoundException, CarAlreadyInUseException {
        synchronized (this) {
            DriverDO driverDO = findDriverChecked(driverId);
            CarDO carDO = carService.getCarByLicensePlate(licensePlate);

            if (carDO.getDriver() != null)
                throw new CarAlreadyInUseException(String.format("Car %s already linked", carDO.getLicensePlate()));

            driverDO.setCar(carDO);
        }

    }

    @Override
    @Transactional
    public void releaseCar(Long driverId) throws EntityNotFoundException {
        synchronized (this) {
            DriverDO driverDO = findDriverChecked(driverId);
            driverDO.setCar(null);
        }
    }

    @Override
    public List<DriverDO> findByCriteriaBuilder(DriverSearch driverSearch) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<DriverDO> query = cb.createQuery(DriverDO.class);
        Root<DriverDO> root = query.from(DriverDO.class);
        Join<DriverDO, CarDO> joinUser = root.join("car", JoinType.LEFT);

        if (!StringUtils.isEmpty(driverSearch.getUsername()))
            query.where(cb.equal(root.get("username"), driverSearch.getUsername()));

        if (driverSearch.getOnlineStatus() != null)
            query.where(cb.equal(root.get("onlineStatus"), driverSearch.getOnlineStatus()));

        if (!StringUtils.isEmpty(driverSearch.getLicensePlate()))
            query.where(cb.like(joinUser.get("licensePlate"), driverSearch.getLicensePlate()));

        if (driverSearch.getManufacturer() != null)
            query.where(cb.equal(joinUser.get("manufacturer"), driverSearch.getManufacturer()));

        if (!StringUtils.isEmpty(driverSearch.getRating()))
            query.where(cb.equal(joinUser.get("rating"), driverSearch.getRating()));


        return entityManager.createQuery(query).getResultList();

    }

}
