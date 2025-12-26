package org.example.teachhive.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.teachhive.entity.base.BaseUUIDEntity;
import org.example.teachhive.enums.NotificationType;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity


@Table(name = "notifications")
public class Notification extends BaseUUIDEntity {

    @Column(nullable = false,columnDefinition = "text")
    private String title;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private boolean isRead;

    private boolean isDeleted;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String message;

}
