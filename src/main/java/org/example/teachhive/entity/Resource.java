package org.example.teachhive.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.teachhive.entity.base.BaseUUIDEntity;
import org.example.teachhive.enums.Position;
import org.example.teachhive.enums.ResourceType;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "resources")
public class Resource extends BaseUUIDEntity {

    @Column(nullable = false)
    private String name;

    private byte[] photo;

    private String about;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Position position;

    private String downloadLink;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResourceType resourceType;

    @ManyToOne
    private User author;

}
