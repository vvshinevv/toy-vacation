package com.vacation.controller;

import com.vacation.annotation.VacationRestController;
import com.vacation.domain.Member;
import com.vacation.domain.Vacation;
import com.vacation.dto.request.VacationRequest;
import com.vacation.dto.response.VacationDomainResponse;
import com.vacation.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@VacationRestController
@RequestMapping("/api")
public class VacationApiController {

    @Autowired
    private VacationService vacationService;

    @RequestMapping(value = "/assign", method = RequestMethod.POST)
    public boolean assignVacation() {
        vacationService.assignAnnualVacationToAllMember();
        return true;
    }

    @RequestMapping(value = "/vacation", method = RequestMethod.POST)
    public VacationDomainResponse requestVacation(Member member, VacationRequest vacationRequest) {
        Vacation vacation = vacationService.requestVacation(member, vacationRequest);
        return vacationService.makeVacationResponse(vacation, member.getMemberNo());
    }

    @RequestMapping(value = "/vacation/{vacationNo}", method = RequestMethod.DELETE)
    public boolean cancelVacation(Member member, @PathVariable("vacationNo") long vacationNo) {
        return vacationService.cancelVacation(member, vacationNo);
    }
}
