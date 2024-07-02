package ro.uaic.clinic_care.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.uaic.clinic_care.dto.review.clinic.ClinicReviewDto;
import ro.uaic.clinic_care.models.review.ClinicReview;

import java.util.List;

public interface ClinicReviewRepository extends JpaRepository<ClinicReview, Long>{

    @Query("SELECT AVG(r.rating) FROM ClinicReview r WHERE r.clinic.id = ?1")
    Double getAverageRatingByClinicId(Long clinicId);

    @Query("SELECT COUNT(r) FROM ClinicReview r WHERE r.clinic.id = ?1")
    Integer getReviewCountByClinicId(Long clinicId);

    List<ClinicReviewDto> getAllReviews();

    List<ClinicReviewDto> getReviewsByClinicId(Long clinicId);
}
