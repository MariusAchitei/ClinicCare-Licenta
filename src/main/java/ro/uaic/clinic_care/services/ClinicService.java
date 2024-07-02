package ro.uaic.clinic_care.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.uaic.clinic_care.dto.clinic.ClinicDto;
import ro.uaic.clinic_care.dto.clinic.ClinicListDto;
import ro.uaic.clinic_care.dto.clinic.CreateClinicDTO;
import ro.uaic.clinic_care.models.administrative.Clinic;
import ro.uaic.clinic_care.models.location.City;
import ro.uaic.clinic_care.repository.ClinicRepository;
import ro.uaic.clinic_care.repository.ClinicReviewRepository;
import ro.uaic.clinic_care.repository.MedicRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClinicService {

    private final AmazonS3Service amazonS3Service;

    private final ClinicRepository clinicRepository;

    private final LocationService locationService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ClinicReviewRepository clinicReviewRepository;
    private final MedicRepository medicRepository;

    public void saveClinic(CreateClinicDTO dto, MultipartFile mainPhoto, MultipartFile[] files) {

        City city = locationService.getCityByNameAndCountyName(dto.getCity(), dto.getCounty());
        try {
            Clinic clinic = Clinic.builder().name(dto.getName()).address(dto.getAddress()).description(dto.getDescription()).phone(dto.getPhone()).email(dto.getEmail()).website(dto.getWebsite()).latitude(dto.getLat()).longitude(dto.getLng()).city(city)
//                    .gallery(objectMapper.writeValueAsString(fileUrls))
//                    .options(objectMapper.writeValueAsString(dto.getSpecialties()))
//                    .services(objectMapper.writeValueAsString(dto.getServices()))
                    .options(dto.getSpecialties())
                    .services(dto.getServices())
                    .build();
            Clinic newClinic = clinicRepository.save(clinic);
            List<String> fileUrls = List.of(files).stream().map(file -> amazonS3Service.uploadClinicFile(newClinic.getId(), file)).collect(Collectors.toList());
            String mainImageUrl = amazonS3Service.uploadClinicFile(newClinic.getId(), mainPhoto);
            newClinic.setGallery(objectMapper.writeValueAsString(fileUrls));
            newClinic.setMainImage(mainImageUrl);
            clinicRepository.save(newClinic);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Error saving clinic", e);
        }
    }

    public List<ClinicListDto> getAllClinics() {
        return clinicRepository
                .findAll()
                .stream()
                .map(clinic ->
                {
                    try {
                        return ClinicListDto.builder()
                                .id(clinic.getId())
                                .name(clinic.getName())
                                .address(clinic.getAddress())
                                .city(clinic.getCity().getName())
                                .county(clinic.getCity()
                                        .getCounty()
                                        .getName())
                                .longitude(clinic.getLongitude())
                                .latitude(clinic.getLatitude())
                                .phone(clinic.getPhone())
                                .email(clinic.getEmail())
                                .website(clinic.getWebsite())
                                .description(clinic.getDescription())
                                .mainImage(clinic.getMainImage())
                                .options(objectMapper.readValue(clinic.getOptions(), new TypeReference<List<String>>() {}))
                                .rating(clinicReviewRepository.getAverageRatingByClinicId(clinic.getId()))
                                .reviews(clinicReviewRepository.getReviewCountByClinicId(clinic.getId()))
                                .medicCount(medicRepository.countMedicsByClinicId(clinic.getId()))
                                .build();
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    public void updateClinic(Long clinicId, MultipartFile mainPhoto, MultipartFile[] files, CreateClinicDTO dto) {
        City city = locationService.getCityByNameAndCountyName(dto.getCity(), dto.getCounty());
        try {
            Clinic clinic = Clinic
                    .builder()
                    .name(dto.getName())
                    .address(dto.getAddress())
                    .description(dto.getDescription())
                    .phone(dto.getPhone())
                    .email(dto.getEmail())
                    .website(dto.getWebsite())
                    .latitude(dto.getLat())
                    .longitude(dto.getLng())
                    .city(city)
                    .options(objectMapper.writeValueAsString(dto.getSpecialties()))
                    .services(objectMapper.writeValueAsString(dto.getServices()))
                    .build();
            clinic.setId(clinicId);
            Clinic newClinic = clinicRepository.save(clinic);
            List<String> fileUrls = List.of(files).stream().map(file -> amazonS3Service.uploadClinicFile(newClinic.getId(), file)).collect(Collectors.toList());
            String mainImageUrl = amazonS3Service.uploadClinicFile(newClinic.getId(), mainPhoto);
            newClinic.setGallery(objectMapper.writeValueAsString(fileUrls));
            newClinic.setMainImage(mainImageUrl);
            clinicRepository.save(newClinic);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Error saving clinic", e);
        }
    }

    public ClinicDto getClinicById(Long clinicId) {
        Clinic clinic = clinicRepository.findById(clinicId).orElseThrow(() -> new RuntimeException("Clinic not found"));
        try {
            return ClinicDto.builder()
                    .id(clinic.getId())
                    .name(clinic.getName())
                    .address(clinic.getAddress())
                    .city(clinic.getCity().getName())
                    .county(clinic.getCity().getCounty().getName())
                    .latitude(clinic.getLatitude())
                    .longitude(clinic.getLongitude())
                    .phone(clinic.getPhone())
                    .email(clinic.getEmail())
                    .website(clinic.getWebsite())
                    .description(clinic.getDescription())
                    .mainImage(clinic.getMainImage())
                    .options(objectMapper.readValue(clinic.getOptions(), new TypeReference<List<String>>() {}))
                    .services(objectMapper.readValue(clinic.getServices(), new TypeReference<List<String>>() {}))
                    .gallery(objectMapper.readValue(clinic.getGallery(), new TypeReference<List<String>>() {}))
                    .rating(clinicReviewRepository.getAverageRatingByClinicId(clinic.getId()))
                    .reviews(clinicReviewRepository.getReviewCountByClinicId(clinic.getId()))
                    .medicCount(medicRepository.countMedicsByClinicId(clinic.getId()))
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
