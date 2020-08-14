package com.vacation.dto.response;

import com.vacation.domain.Vacation;
import com.vacation.domain.Vacations;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class VacationDomainResponse {

    private float totalLeftVacationCount;
    private VacationResponse vacation;
    private List<VacationResponse> vacations;

    private VacationDomainResponse(float totalLeftVacationCount, VacationResponse vacation, List<VacationResponse> vacations) {
        this.totalLeftVacationCount = totalLeftVacationCount;
        this.vacation = vacation;
        this.vacations = vacations;
    }

    public static VacationDomainResponse of(Vacation vacation, Vacations vacations) {
        float totalLeftVacationCount = vacations.getLeftTotalVacations();

        VacationResponse requestVacation = VacationResponse.of(vacation);
        List<VacationResponse> vacationResponses = vacationResponses(vacations);

        return new VacationDomainResponse(totalLeftVacationCount, requestVacation, vacationResponses);
    }

    private static List<VacationResponse> vacationResponses(Vacations vacations) {
        List<Vacation> targets = vacations.getVacations();
        List<VacationResponse> results = new ArrayList<>();
        for (Vacation vacation : targets) {
            results.add(VacationResponse.of(vacation));
        }
        return results;
    }

}
