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

    private String orgao;

    @EqualsAndHashCode.Include
    @Column(name = "codigo_uasg")
    private String codigoUASG;

    @Column(name = "tipo_licitacao")
    private String tipoLicitacao;

    private String objeto;

    @Column(name = "data_edital")
    private String dataEdital;

    private String endereco;

    private String telefone;

    private String fax;

    @Column(name = "entrega_proposta")
    private String entregaProposta;

}
