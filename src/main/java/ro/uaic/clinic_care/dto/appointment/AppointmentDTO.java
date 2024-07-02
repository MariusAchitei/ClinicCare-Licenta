package ro.uaic.clinic_care.dto.appointment;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class AppointmentDTO {
    private Long id;
    private String photo;
    private String reason;
    private LocalDate date;
    private String startTime;
    private String endTime;
    private String duration;
    private Date markedDate;
    private String recommendations;
    private String clinic;
    private String parentAppointment;
    private TreatmentPlanDTO plan;
    private MedicDTO medic;
    private String status;
    private ServicesDTO services;
    private List<FileDTO> files;

    @Data
    public static class TreatmentPlanDTO {
        private Long id;
        private String title;
        private String diagnostic;
        private Date startDate;
        private Date endDate;
        private Date nextAppointment;
        private int appointmentCount;
        private String status;
    }

    @Data
    public static class MedicDTO {
        private String name;
        private String specialty;
        private String location;
        private int experience;
        private int rating;
        private int reviews;
        private String image;
        private ClinicDTO clinic;
    }

    @Data
    public static class ClinicDTO {
        private String name;
        private String logo;
        private String location;
        private CoordinatesDTO coordinates;
    }

    @Data
    public static class CoordinatesDTO {
        private double lat;
        private double lng;
    }

    @Data
    public static class ServicesDTO {
        private String description;
        private List<MedicationDTO> medication;
        private List<DiagnosticDTO> diagnostic;
    }

    @Data
    public static class MedicationDTO {
        private Long id;
        private String name;
        private String dose;
    }

    @Data
    public static class DiagnosticDTO {
        private Long id;
        private Date date;
        private String diagnostic;
    }

    @Data
    public static class FileDTO {
        private Long id;
        private String name;
        private String type;
        private String path;

        public FileDTO(Long id, String name, String type, String path) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.path = path;
        }
    }
}
