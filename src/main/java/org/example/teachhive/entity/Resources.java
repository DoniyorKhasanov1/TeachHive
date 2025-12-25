package org.example.teachhive.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "resources")
public class Resources {

    private UUID id;

    private String name;

    private byte[] photo;

    private String about;

    private Position position;

    private String downloadLink;

    private ResourceType resourceType;

    private User author;

}
