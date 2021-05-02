package io.housedevinci.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.housedevinci.domainobject.Manufacturer;
import io.housedevinci.domainvalue.OnlineStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DriverSearch {

    private String username;

    private OnlineStatus onlineStatus;

    private String licensePlate;

    private Float rating;

    private Manufacturer manufacturer;


}
