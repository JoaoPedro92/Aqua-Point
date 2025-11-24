package pt.iade.AquaPoint.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pt.iade.AquaPoint.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}