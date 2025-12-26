package org.example.teachhive.enums;

import jakarta.persistence.Table;

@Table(name = "usergoals")
public enum UserGoals {
    SHARE_RESOURCES,
    FIND_QUALITY_CONTENT,
    COLLABORATE_WITH_TEACHERS,
    SAVE_TIME_ON_PLANNING,
    TRY_AI_TOOLS,
    BUILD_LESSON_PACKS
}
