package com.vacation.domain;

import com.vacation.dto.VacationDTO;
import com.vacation.util.Streams;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Vacations {

    private List<Vacation> vacations;

    private Vacations(List<Vacation> vacations) {
        this.vacations = new ArrayList<>(vacations);
    }

    public static Vacations of(List<VacationDTO> vacationDTOs) {
        List<Vacation> vacations = new ArrayList<>();
        for (VacationDTO vacationDTO : vacationDTOs) {
            Vacation vacation = Vacation.of(vacationDTO);
            vacations.add(vacation);
        }
        return new Vacations(vacations);
    }

    public float getLeftTotalVacations() {
        LocalDateTime now = LocalDateTime.now();
        return Streams.ofNullable(vacations)
                .filter(vacation -> now.isBefore(vacation.getExpireVacationDate()))
                .map(Vacation::getLeftVacationCount)
                .map(LeftVacationCount::getLeftVacationCount)
                .reduce(0.0f, (a, b) -> a + b);
    }
}
