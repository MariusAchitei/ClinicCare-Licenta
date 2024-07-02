package ro.uaic.clinic_care.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.uaic.clinic_care.dto.review.clinic.ClinicReviewDto;
import ro.uaic.clinic_care.dto.review.clinic.CreateClinicReviewDto;
import ro.uaic.clinic_care.exceptions.ServiceException;
import ro.uaic.clinic_care.models.administrative.Clinic;
import ro.uaic.clinic_care.repository.ClinicRepository;
import ro.uaic.clinic_care.repository.ClinicReviewRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ClinicReviewRepository clinicReviewRepository;

    private final ClinicRepository clinicRepository;

    public List<ClinicReviewDto> getReviewsByFilter(Long clinicId) {
        if (clinicId == null){
            return clinicReviewRepository.getAllReviews();
    }
        return clinicReviewRepository.getReviewsByClinicId(clinicId);
    }

    public void createClinicReview(CreateClinicReviewDto dto) {
        Clinic clinic = clinicRepository.findById(dto.getClinicId()).orElseThrow(() -> new ServiceException("Clinic not found"));
        

    }
}
