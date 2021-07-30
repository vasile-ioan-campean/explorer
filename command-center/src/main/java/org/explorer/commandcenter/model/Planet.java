package org.explorer.commandcenter.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String planetId;
    @Column(unique = true)
    private String name;
    private String image;
    private PlanetStatus status;
    @OneToOne
    private Crew crew;
}
