package com.vacation.domain;

import com.vacation.dto.VacationDTO;
import lombok.Getter;

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


}
