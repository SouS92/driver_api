package io.housedevinci.dataaccessobject;

import io.housedevinci.domainobject.DriverDO;
import io.housedevinci.domainvalue.OnlineStatus;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverDO, Long>
{

    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);
}
