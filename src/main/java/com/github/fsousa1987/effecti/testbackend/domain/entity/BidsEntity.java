package com.github.fsousa1987.effecti.testbackend.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@SuppressWarnings("JpaDataSourceORMInspection")
@Table(name = "tb_bids")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class BidsEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 5175171890280255216L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String agency;

    @EqualsAndHashCode.Include
    @Column(name = "uasg_code")
    private String uasgCode;

    @Column(name = "bidding_type")
    private String biddingType;

    @Column(columnDefinition = "text")
    private String object;

    @Column(name = "notice_date")
    private String noticeDate;

    private String address;

    private String telephone;

    private String fax;

    @Column(name = "delivery_proposal")
    private String deliveryProposal;

}
