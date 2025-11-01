package pt.iade.AquaPoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pt.iade.AquaPoint.models.UserInteraction;

import java.util.List;

@Repository
public interface UserInteractionsRepository extends JpaRepository<UserInteraction, Integer> {
    @Query(
        value = "select interaction.id, interaction.date, users.name, rating.rating, comment.comment from interaction " +
                "INNER JOIN users ON users.id = interaction.user_id " +
                "INNER JOIN rating ON rating.id = interaction.rating_id " +
                "INNER JOIN comment ON comment.id = interaction.comment_id " +
                "WHERE interaction.point_id = :pointId",
        nativeQuery = true
    )
    List<UserInteraction> getUserReviewByPointId(@Param("pointId") int pointId);
}