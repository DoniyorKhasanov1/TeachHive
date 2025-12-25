package org.example.teachhive.repository;

import org.example.teachhive.entity.Resources;
import org.example.teachhive.enums.Position;
import org.example.teachhive.enums.ResourceType;
import org.example.teachhive.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ResourcesRepository extends JpaRepository<Resources, UUID> {

    List<Resources> findByPosition(Position position);

    List<Resources> findByResourceType(ResourceType resourceType);

    List<Resources> findByAuthor(User author);

    List<Resources> findByPositionAndResourceType(Position position, ResourceType resourceType);
}
