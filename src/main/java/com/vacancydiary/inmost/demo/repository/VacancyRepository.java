package com.vacancydiary.inmost.demo.repository;

import com.vacancydiary.inmost.demo.model.User;
import com.vacancydiary.inmost.demo.model.Vacancy;
import com.vacancydiary.inmost.demo.model.VacancyStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface VacancyRepository extends CrudRepository<Vacancy, Long> {
    List<Vacancy> findByUser(User user);
    List<Vacancy> findByCompanyName(String companyName);
    List<Vacancy> findByVacancyStatus(VacancyStatus vacancyStatus);
}
