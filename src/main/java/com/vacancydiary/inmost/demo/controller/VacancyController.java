package com.vacancydiary.inmost.demo.controller;

import com.vacancydiary.inmost.demo.model.User;
import com.vacancydiary.inmost.demo.model.Vacancy;
import com.vacancydiary.inmost.demo.repository.UserRepository;
import com.vacancydiary.inmost.demo.repository.VacancyRepository;
import com.vacancydiary.inmost.demo.service.VacancyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Vacancy controller")
@RestController
public class VacancyController {

    private final VacancyService vacancyService;
    private final UserRepository userRepository;
    private final VacancyRepository vacancyRepository;

    @Autowired
    public VacancyController(VacancyService vacancyService,
                             UserRepository userRepository,
                             VacancyRepository vacancyRepository) {
        this.vacancyService = vacancyService;
        this.userRepository = userRepository;
        this.vacancyRepository = vacancyRepository;
    }


    @ApiOperation(value = "Creat new note about vacancy")
    @PostMapping(value = "/{user_id}/vacancy/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Vacancy> addNewVacancy(@RequestBody Vacancy vacancy, @PathVariable Long user_id) {

        vacancyService.addVacancy(vacancy, user_id);
        return ResponseEntity.ok(vacancy);
    }

    @ApiOperation(value = "Show all user`s vacancies")
    @GetMapping(value = "/{user_id}/vacancy")
    public ResponseEntity<List<Vacancy>> showAllUserVacancy(@PathVariable Long user_id) {
        User user = userRepository.findById(user_id).get();
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(vacancyService.findByUser(user), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Search for vacancies by company name")
    @GetMapping(value = "/{user_id}/vacancy/{companyName}")
    public ResponseEntity<List<Vacancy>> showAllVacanciesByCompanyName(@PathVariable String companyName) {
        List<Vacancy> vacancy = vacancyRepository.findByCompanyName(companyName);
        if (vacancy == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(vacancy, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Get vacancy by id")
    @GetMapping(value = "/vacancy/{vacancy_id}")
    public ResponseEntity<Vacancy> getVacancyById(@PathVariable Long vacancy_id) {
        Vacancy vacancy = vacancyRepository.findById(vacancy_id).get();
        if (vacancy == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(vacancy, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Edit vacancy")
    @PostMapping(value = "/{userId}/vacancy/{vacancy_id}/edit", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Vacancy> editVacancy(@PathVariable Long vacancy_id,
                                               @PathVariable Long userId,
                                               @RequestBody Vacancy vacancy) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userFromDb = userRepository.findById(userId).get();
        if (userFromDb.getUsername().equals(userDetails.getUsername())) {
            return new ResponseEntity(vacancyService.editVacancyById(vacancy_id, vacancy), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // return vacancyService.editVacancyById(vacancy_id);
    }

    /*@GetMapping(value = "/vacancy/{vacancyStatus}")
    public ResponseEntity<List<Vacancy>> filterVacancyByStatus(@PathVariable VacancyStatus vacancyStatus, @RequestBody User user){
        List<Vacancy> allVacancies = vacancyRepository.findByUser(user);

    }*/

    @ApiOperation(value = "Filter vacancies by status")
    @PostMapping(value = "/vacancy")
    public List<Vacancy> filterVacanciesByStatus(@RequestParam Long user_id,
                                                 @RequestParam String vacancyStatus) {

        return vacancyService.filterByVacancyStatus(vacancyStatus, user_id);
        //return null;
    }
}
