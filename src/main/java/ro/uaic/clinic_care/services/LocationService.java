package ro.uaic.clinic_care.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.uaic.clinic_care.models.location.City;
import ro.uaic.clinic_care.models.location.CityRepository;
import ro.uaic.clinic_care.models.location.County;
import ro.uaic.clinic_care.models.location.CountyRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final CountyRepository countyRepository;

    private final CityRepository cityRepository;

    @Transactional
    public City getCityByNameAndCountyName(String cityName, String countyName) {
        // Check if the county exists, if not, create it
        Optional<County> countyOpt = countyRepository.findByName(countyName);
        County county;
        if (countyOpt.isPresent()) {
            county = countyOpt.get();
        } else {
            county = new County();
            county.setName(countyName);
            county = countyRepository.save(county);
        }

        // Check if the city exists in the county, if not, create it
        Optional<City> cityOpt = cityRepository.findByNameAndCounty(cityName, county);
        City city;
        if (cityOpt.isPresent()) {
            city = cityOpt.get();
        } else {
            city = new City();
            city.setName(cityName);
            city.setCounty(county);
            city = cityRepository.save(city);
        }

        return city;
    }
}
