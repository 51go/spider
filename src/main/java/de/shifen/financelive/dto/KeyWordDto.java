package de.shifen.financelive.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author ms404
 */
@Entity
@Table
@Data
@NoArgsConstructor
public class KeyWordDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column
    long dtoId;

    String term;

    @Column(name="score",columnDefinition = "double(12,8)")
    Double score;
    @Column
    int freq;
}
