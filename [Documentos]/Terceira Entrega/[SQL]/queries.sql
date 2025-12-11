/* getFavoriteAquaPointsByUserId */

SELECT DISTINCT ap.id, ap.point_name, ap.point_type, ap.local_id, ap.latitude, ap.longitude, ps.state_id 
FROM aqua_points ap
LEFT JOIN points_state ps ON ap.id = ps.point_id 
INNER JOIN favorites fav ON ap.id  = fav.point_id 
WHERE fav.user_id = :userId

/* removeAquaPointFromFavorites */

DELETE FROM favorites
WHERE favorites.user_id = :userId and favorites.point_id = :pointId

/* addAquaPointToFavorite */
INSERT INTO favorites (user_id, point_id, date)
VALUES (:userId, :pointId, CURRENT_DATE())

/* AddAquaPointToStateList */
INSERT INTO points_state (point_id, state_id)
VALUES (:pointId, :state_id)

/* EditPointState */
UPDATE points_state SET state_id = :state_id WHERE point_id = :pointId

/* getUserReviewByPointId */
select interaction.id, interaction.date, users.name, rating.rating, comment.comment, interaction.user_id from interaction
INNER JOIN users ON users.id = interaction.user_id
INNER JOIN rating ON rating.id = interaction.rating_id
INNER JOIN comment ON comment.id = interaction.comment_id
WHERE interaction.point_id = :pointId ORDER BY date DESC

/* addNewReview */
INSERT INTO interaction (user_id, point_id, comment_id, rating_id, date)
VALUES (:user_id, :point_id, :commentId, :ratingId, CURRENT_DATE())