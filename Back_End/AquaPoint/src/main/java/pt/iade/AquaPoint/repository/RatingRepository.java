package pt.iade.AquaPoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pt.iade.AquaPoint.models.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
}