package ro.uaic.clinic_care.services.impl;


import ro.uaic.clinic_care.dto.medic_interval.CreateMedicIntervalDto;
import ro.uaic.clinic_care.dto.medic_interval.MedicIntervalFilterDto;
import ro.uaic.clinic_care.exceptions.ServiceException;
import ro.uaic.clinic_care.models.MedicInterval;
import ro.uaic.clinic_care.repository.MedicIntervalRepository;
import ro.uaic.clinic_care.repository.MedicRepository;
import ro.uaic.clinic_care.services.MedicIntervalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicIntervalServiceImpl implements MedicIntervalService {
    private final MedicIntervalRepository medicIntervalRepository;
    private final MedicRepository medicRepository;

    //    @Override
    public MedicInterval createMedicInterval(CreateMedicIntervalDto dto) {
        var medic = medicRepository.findById(dto.getMedicId()).orElseThrow();
        if (medic == null) {
            throw new ServiceException("Medic not found");
        }
        List<MedicInterval> overlappingIntervals = medicIntervalRepository.findMedicIntervalByMedicIdAndStartDateTimeAndEndDateTime(dto.getMedicId(), dto.getStartDateTime(), dto.getEndDateTime());
        if (!overlappingIntervals.isEmpty()) {
            throw new ServiceException("Medic already has an interval in this time slot");
        }
        MedicInterval medicInterval = MedicInterval.builder().medic(medic).startDateTime(dto.getStartDateTime()).endDateTime(dto.getEndDateTime()).build();
        medicIntervalRepository.save(medicInterval);
        return medicInterval;
    }

    //    @Override
    public MedicInterval getMedicIntervalById(Long medicIntervalId) {
        return medicIntervalRepository.findById(medicIntervalId).orElseThrow();
    }

//    @Override

    public List<MedicInterval> getMedicIntervals(Long medicId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (startDateTime == null || endDateTime == null) {
            return medicIntervalRepository.findMedicIntervalByMedicId(medicId);
        }
        return medicIntervalRepository.findMedicIntervalByMedicIdAndStartDateTimeAndEndDateTime(medicId, startDateTime, endDateTime);
    }

    public List<MedicInterval> getMedicIntervalsByFilter(MedicIntervalFilterDto filter) {
        if (filter != null)
            return medicIntervalRepository.findMedicIntervalsByFilter(filter.getMedicIds(), filter.getDepartmentIds(), filter.getStartDateTime(), filter.getEndDateTime());
        return medicIntervalRepository.findAll();
    }
}
