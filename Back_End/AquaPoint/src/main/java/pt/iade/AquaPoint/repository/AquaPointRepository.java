package pt.iade.AquaPoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pt.iade.AquaPoint.models.AquaPoint;

@Repository
public interface AquaPointRepository extends JpaRepository<AquaPoint, Integer> {
    //
}