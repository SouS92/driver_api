package com.freenow.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.freenow.domainobject.EngineType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private String licensePlate;

    private int seatCount;

    private boolean convertible;

    private float rating;

    private EngineType engineType;

    private boolean carSelected;


}
