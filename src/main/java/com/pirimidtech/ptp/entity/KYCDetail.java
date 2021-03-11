package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "KYCDetail")
public class KYCDetail {

    @Id
    @GeneratedValue
    private UUID id;
    private String panNumber;
    private String occupation;
    private int annualIncome;
    private String motherName;
    private String fatherName;

    private String panUrl;
    private String signatureUrl;
    private String profileUrl;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(nullable = false)
    private User user;

}
