package org.example.teachhive.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.teachhive.entity.base.BaseUUIDEntity;
import org.example.teachhive.enums.Position;
import org.example.teachhive.enums.UserGoals;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "userinfo")
public class UserInfo extends BaseUUIDEntity {

    @OneToOne
    private User user;

    private String schoolOrInsName;

    @Enumerated(EnumType.STRING)
    private Position subjectType;

    private int classGrade;

    @ElementCollection(targetClass = UserGoals.class)
    @Enumerated(EnumType.STRING)
    private Set<UserGoals> userGoals = new HashSet<>();

}
