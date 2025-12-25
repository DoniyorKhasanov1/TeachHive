package org.example.teachhive.entity;


import jakarta.persistence.Table;

@Table(name = "notificationtype")
public enum NotificationType {

    COMMENT,
    FEEDBACK
}
