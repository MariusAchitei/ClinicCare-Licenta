package ro.uaic.clinic_care.services.impl;

import ro.uaic.clinic_care.repository.AppointmentRepository;
import ro.uaic.clinic_care.repository.TreatmentPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TreatmentPlanServiceImpl {
    private final TreatmentPlanRepository treatmentPlanRepository;
    private final AppointmentRepository appointmentRepository;
    private final AppointmentServiceImpl appointmentService;



}
