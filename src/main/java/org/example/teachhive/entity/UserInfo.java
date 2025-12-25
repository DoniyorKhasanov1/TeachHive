package org.example.teachhive.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.teachhive.enums.Position;
import org.example.teachhive.enums.UserGoals;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "userinfo")
public class UserInfo {

    private UUID id;

    private User user;

    private String schoolOrInsName;

    private Position subjactType;

    private int classGrade;

    private UserGoals userGoals;

}
