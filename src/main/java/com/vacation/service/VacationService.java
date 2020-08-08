package com.vacation.service;

import com.vacation.domain.Vacation;
import com.vacation.dto.MemberDTO;
import com.vacation.dto.VacationDTO;
import com.vacation.repository.VacationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
public class VacationService {

    @Autowired
    private VacationRepository vacationRepository;

    @Transactional
    public void assignAnnualVacationToAllMember() {
        List<MemberDTO> memberDTOs = vacationRepository.selectMembers();
        for (MemberDTO memberDTO : memberDTOs) {
            Vacation vacation = Vacation.annualVacation();
            VacationDTO vacationDTO = VacationDTO.of(memberDTO.getMemberNo(), vacation);
            int result =  vacationRepository.insertVacationPerMember(vacationDTO);
            log.debug("insertVacationPerMember() :: {}", result);
        }
    }
}
