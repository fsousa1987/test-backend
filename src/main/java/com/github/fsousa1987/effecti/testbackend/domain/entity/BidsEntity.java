package com.github.fsousa1987.effecti.testbackend.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BidsEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 5175171890280255216L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String agency;

    @EqualsAndHashCode.Include
    @Column(name = "codigo_uasg")
    private String uasgCode;

    @Column(name = "tipo_licitacao")
    private String biddingType;

    private String object;

    @Column(name = "data_edital")
    private String noticeDate;

    private String address;

    private String telephone;

    private String fax;

    @Column(name = "entrega_proposta")
    private String deliveryProposal;

}
