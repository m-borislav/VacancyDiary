package com.vacancydiary.inmost.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@ApiModel(value = "Vacancy entity")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ApiModelProperty(value = "Name of company", example = "Inmost")
    @Column(name = "companyName", nullable = false, length = 32)
    private String companyName;
    @ApiModelProperty(value = "Position in company", example = "Junior Java Developer")
    @Column(name = "position", nullable = false, length = 32)
    private String position;
    @ApiModelProperty(value = "Expected salary", example = "500")
    @Column(name = "expectedSalary", nullable = false, length = 10)
    private int expectedSalary;
    @ApiModelProperty(value = "Link to vacancy")
    @Column(name = "linkToVacancy", nullable = false)
    private String linkToVacancy;
    @ApiModelProperty(value = "Contact of recruiter")
    @Column(name = "recruiterContact", nullable = false)
    private String recruiterContact;
    @ApiModelProperty(value = "Date of updating status")
    @Column(name = "statusUpdate", nullable = false)
    private LocalDate statusUpdate = LocalDate.now();
    @ApiModelProperty(value = "Status of vacancy")
    @Column(name = "vacancy_status")
    @Enumerated(EnumType.STRING)
    private VacancyStatus vacancyStatus = VacancyStatus.SENT_CV;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "Vacancy{" +
                "statusUpdate=" + statusUpdate +
                ", vacancyStatus=" + vacancyStatus +
                '}';
    }


}
