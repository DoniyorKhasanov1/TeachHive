package org.example.teachhive.repository;

import org.example.teachhive.entity.Resource;
import org.example.teachhive.enums.Position;
import org.example.teachhive.enums.ResourceType;
import org.example.teachhive.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ResourcesRepository extends JpaRepository<Resource, UUID> {

    List<Resource> findByPosition(Position position);

    List<Resource> findByResourceType(ResourceType resourceType);

    List<Resource> findByAuthor(User author);

    List<Resource> findByPositionAndResourceType(Position position, ResourceType resourceType);
}
