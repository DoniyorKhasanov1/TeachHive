package org.example.teachhive.enums;

import jakarta.persistence.Table;

@Table(name = "position")
public enum Position {
    ENGLISH,
    MATH,
    SCIENCE,
    PHYSICS,
    CHEMISTRY
}
