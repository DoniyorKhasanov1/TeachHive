package org.example.teachhive.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.teachhive.enums.NotificationType;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity


@Table(name = "notifications")
public class Notifications {

    private UUID id;

    private String title;

    private NotificationType type;

    private boolean isRead;

    private boolean isDeleted;


}
