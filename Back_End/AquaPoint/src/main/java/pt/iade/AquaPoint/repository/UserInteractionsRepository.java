package pt.iade.AquaPoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pt.iade.AquaPoint.models.AquaPoint;
import pt.iade.AquaPoint.models.Rating;
import pt.iade.AquaPoint.models.UserInteraction;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

@Repository
public interface UserInteractionsRepository extends JpaRepository<UserInteraction, Integer> {
    @Query(
        value = "select interaction.id, interaction.date, users.name, rating.rating, comment.comment, interaction.user_id from interaction " +
                "INNER JOIN users ON users.id = interaction.user_id " +
                "INNER JOIN rating ON rating.id = interaction.rating_id " +
                "INNER JOIN comment ON comment.id = interaction.comment_id " +
                "WHERE interaction.point_id = :pointId ORDER BY date DESC",
        nativeQuery = true
    )
    List<UserInteraction> getUserReviewByPointId(@Param("pointId") int pointId);

    @Modifying
    @Transactional
    @Query(
        value = "INSERT INTO interaction (user_id, point_id, comment_id, rating_id, date) "+
            "VALUES (:user_id, :point_id, :commentId, :ratingId, CURRENT_DATE())",
        nativeQuery = true
    )
    int addNewReview(
        @Param("user_id") int user_id, 
        @Param("point_id") int point_id, 
        @Param("commentId") int commentId, 
        @Param("ratingId") int ratingId
    );
}