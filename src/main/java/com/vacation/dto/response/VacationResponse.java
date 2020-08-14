package com.vacation.dto.response;

import com.vacation.domain.Vacation;
import com.vacation.model.VacationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VacationResponse {
    private long vacationNo;
    private float totalVacationCount;
    private float leftVacationCount;
    private float useVacationCount;
    private VacationType vacationType;

    public static VacationResponse of(Vacation vacation) {
        VacationResponse vacationResponse = new VacationResponse();
        vacationResponse.setVacationNo(vacation.getVacationNo().getVacationNo());
        vacationResponse.setTotalVacationCount(vacation.getTotalVacationCount().getTotalVacationCount());
        vacationResponse.setLeftVacationCount(vacation.getLeftVacationCount().getLeftVacationCount());
        vacationResponse.setUseVacationCount(vacation.getUseVacationCount().getUseVacationCount());
        vacationResponse.setVacationType(vacation.getVacationType());
        return vacationResponse;
    }
}
