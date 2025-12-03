package pt.iade.AquaPoint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pt.iade.AquaPoint.models.AquaPoint;

@Repository
public interface AquaPointRepository extends JpaRepository<AquaPoint, Integer> {
    /*@Query(
        value = "SELECT ap.id, ap.point_name, ap.point_type, ap.local_id, ap.latitude, ap.longitude, ps.state_id "+
        "FROM aqua_points ap "+
        "LEFT JOIN points_state ps ON ap.id = ps.point_id",
        nativeQuery = true
    )
    List<AquaPoint> getAquaPoints();*/ /* método antigo */

    @Query(
        value = "SELECT DISTINCT ap.id, ap.point_name, ap.point_type, ap.local_id, ap.latitude, ap.longitude, ps.state_id " +
                "FROM aqua_points ap "+
                "LEFT JOIN points_state ps ON ap.id = ps.point_id "+
                "INNER JOIN favorites fav ON ap.id  = fav.point_id " +
                "WHERE fav.user_id = :userId",
        nativeQuery = true
    )
    List<AquaPoint> getFavoriteAquaPointsByUserId(@Param("userId") int userId);

    @Modifying
    @Transactional
    @Query(
        value = "DELETE FROM favorites " +
                "WHERE favorites.user_id = :userId and favorites.point_id = :pointId",
        nativeQuery = true
    )
    int removeAquaPointFromFavorites(@Param("pointId") int pointId, @Param("userId") int userId);

    @Modifying
    @Transactional
    @Query(
        value = "INSERT INTO favorites (user_id, point_id, date) "+
            "VALUES (:userId, :pointId, CURRENT_DATE())",
        nativeQuery = true
    )
    int addAquaPointToFavorite(@Param("pointId") int pointId, @Param("userId") int userId);

    @Modifying
    @Transactional
    @Query(
        value = "INSERT INTO points_state (point_id, state_id) "+
            "VALUES (:pointId, :state_id)",
        nativeQuery = true
    )
    int AddAquaPointToStateList(@Param("pointId") int pointId, @Param("state_id") int state_id);
}