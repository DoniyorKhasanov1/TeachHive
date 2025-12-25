package org.example.teachhive.enums;


import jakarta.persistence.Table;

@Table(name = "notificationtype")
public enum NotificationType {

    COMMENT,
    FEEDBACK
}
