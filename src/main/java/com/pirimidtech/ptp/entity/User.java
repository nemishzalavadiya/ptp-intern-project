package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.EnumType;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="userDetail")
public class User {
    @Id
    private UUID id;

    private String name;

    private String email;

    private String panCard;

    private String mobileNo;

    private String signatureUrl;

    private String dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String dpURL;
}

