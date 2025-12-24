package org.example.teachhive.entity;

import jakarta.persistence.Entity;
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
public class UserInfo {

    private UUID id;

    private User user;

    private String schoolOrInsName;

    private Position subjactType;

    private int classGrade;

}
