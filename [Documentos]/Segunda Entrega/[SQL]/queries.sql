SELECT ap.id, ap.point_name, ap.point_type, ap.local_id, ap.latitude, ap.longitude, ps.state_id FROM aqua_points ap 
LEFT JOIN points_state ps ON ap.id = ps.point_id;

SELECT * FROM aqua_points 
WHERE id = :pointId;

SELECT * FROM users;

SELECT * FROM users
WHERE name = :name;

select interaction.id, interaction.date, users.name, rating.rating, comment.comment from interaction 
INNER JOIN users ON users.id = interaction.user_id 
INNER JOIN rating ON rating.id = interaction.rating_id 
INNER JOIN comment ON comment.id = interaction.comment_id 
WHERE interaction.point_id = :pointId;