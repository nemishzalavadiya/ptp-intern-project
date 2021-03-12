package com.pirimidtech.ptp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KYCResponsePOJO implements Serializable {
    private float cosineDistance;
    private float distance;
    private String message;
}
