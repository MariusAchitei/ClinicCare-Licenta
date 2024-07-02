package ro.uaic.clinic_care.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.uaic.clinic_care.dto.review.clinic.ClinicReviewDto;
import ro.uaic.clinic_care.services.ReviewService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewsController {

    private final ReviewService reviewService;

    @GetMapping
    public List<ClinicReviewDto> getReviews(@RequestParam(required = false) Long clinicId) {
        return reviewService.getReviewsByFilter(clinicId);
    }
}
