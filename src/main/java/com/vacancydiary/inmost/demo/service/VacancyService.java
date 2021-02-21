package com.vacancydiary.inmost.demo.service;

import com.vacancydiary.inmost.demo.model.User;
import com.vacancydiary.inmost.demo.model.Vacancy;
import com.vacancydiary.inmost.demo.repository.UserRepository;
import com.vacancydiary.inmost.demo.repository.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacancyService {

    private final VacancyRepository vacancyRepository;
    private final UserRepository userRepository;

    @Autowired

    public VacancyService(VacancyRepository vacancyRepository, UserRepository userRepository) {
        this.vacancyRepository = vacancyRepository;
        this.userRepository = userRepository;
    }

    public List<Vacancy> findByUser (User user){
        return vacancyRepository.findByUser(user);
    }

    public Vacancy addVacancy (Vacancy vacancy, Long user_id){
        User user = userRepository.findById(user_id).get();
        vacancy.setUser(user);
        vacancyRepository.save(vacancy);
        return vacancy;
    }


    public Vacancy editVacancyById (Long id, Vacancy vacancy){
        Vacancy vacancyFromDb = vacancyRepository.findById(id).get();
        vacancyFromDb.setCompanyName(vacancy.getCompanyName());
        vacancyFromDb.setLinkToVacancy(vacancy.getLinkToVacancy());
        vacancyFromDb.setExpectedSalary(vacancy.getExpectedSalary());
        vacancyFromDb.setPosition(vacancy.getPosition());
        vacancyFromDb.setRecruiterContact(vacancy.getRecruiterContact());
        vacancyFromDb.setVacancyStatus(vacancy.getVacancyStatus());
        if (!vacancyFromDb.getVacancyStatus().equals(vacancy.getVacancyStatus())) {
            vacancyFromDb.setStatusUpdate(LocalDate.now());
        } else {
            vacancyFromDb.setStatusUpdate(vacancy.getStatusUpdate());
        }
        vacancyRepository.save(vacancyFromDb);
        return vacancyFromDb;

    }


    public List<Vacancy> filterByVacancyStatus(String vacancyStatus, Long user_id){
        User user = userRepository.findById(user_id).get();
        return vacancyRepository.findByUser(user)
                .stream()
                .filter(vacancy -> vacancy.getVacancyStatus().toString().equals(vacancyStatus))
                .collect(Collectors.toList());
    }
}
