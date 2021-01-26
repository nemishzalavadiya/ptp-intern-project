package com.pirimidtech.ptp.entity;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

    @Getter
    @Setter
    @NoArgsConstructor
    @Entity
    @Table(name = "mutualFundStatistic")
    public class MutualFundStatistic {
        @Id
        public UUID MutualFundID;


        private String risk;
        private float minSIP;
        private boolean SIPallowed;
        private float expenseRatio;
        private float NAV;
        private Date fundStarted;
        private float fundSize;

        @OneToOne(targetEntity = MutualFundDetail.class)
        @JoinColumn(name = "mutualFundID")
        private MutualFundDetail mutualFundDetail;


    }
