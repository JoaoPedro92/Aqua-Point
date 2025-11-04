package pt.iade.AquaPoint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pt.iade.AquaPoint.models.AquaPoint;

@Repository
public interface AquaPointRepository extends JpaRepository<AquaPoint, Integer> {
    @Query(
        value = "SELECT ap.id, ap.point_name, ap.point_type, ap.local_id, ap.latitude, ap.longitude, ps.state_id "+
        "FROM aqua_points ap "+
        "LEFT JOIN points_state ps ON ap.id = ps.point_id",
        nativeQuery = true
    )
    List<AquaPoint> getAquaPoints();
}