package org.example.teachhive.enums;

import jakarta.persistence.Table;

@Table(name = "resourcetype")
public enum ResourceType {
    PDF,
    XLSX,
    PPTX,
    MP3,
    MP4,
    JPG
}
