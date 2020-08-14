package com.vacation.service;

import com.vacation.domain.Member;
import com.vacation.domain.MemberNo;
import com.vacation.domain.Vacation;
import com.vacation.domain.VacationApplicationNo;
import com.vacation.domain.VacationNo;
import com.vacation.domain.Vacations;
import com.vacation.dto.MemberDTO;
import com.vacation.dto.VacationApplicationDTO;
import com.vacation.dto.VacationDTO;
import com.vacation.dto.request.VacationRequest;
import com.vacation.dto.response.VacationDomainResponse;
import com.vacation.repository.VacationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
            int result = vacationRepository.insertVacationPerMember(vacationDTO);
            log.debug("insertVacationPerMember() :: {}", result);
        }
    }

    @Transactional
    public Vacation requestVacation(Member member, VacationRequest vacationRequest) {

        if (Objects.isNull(member)) {
            throw new IllegalArgumentException("인증되지 않은 회원입니다.");
        }

        VacationNo vacationNo = VacationNo.of(vacationRequest.getVacationNo());
        MemberNo memberNo = member.getMemberNo();
        VacationDTO vacationDTO = vacationRepository.selectVacation(vacationNo, memberNo);

        Vacation vacation = Vacation.of(vacationDTO);
        if (!vacation.haveAvailableVacation(vacationRequest.getUseVacationCount())) {
            throw new RuntimeException("신청할 휴가가 없습니다.");
        }

        vacation.useVacation(vacationRequest.getUseVacationCount());
        VacationDTO manipulatedVacationDTO = VacationDTO.of(memberNo.getMemberNo(), vacationNo.getVacationNo(), vacation);
        int updateVacationForVacationRequestResult = vacationRepository.updateVacationForVacationRequest(manipulatedVacationDTO);
        log.debug("updateVacationForVacationRequest() :: {}", updateVacationForVacationRequestResult);

        VacationApplicationDTO vacationApplicationDTO = VacationApplicationDTO.of(vacationRequest, member);
        int insertVacationApplicationResult = vacationRepository.insertVacationApplication(vacationApplicationDTO);
        log.debug("insertVacationApplication() :: {}", insertVacationApplicationResult);

        return vacation;
    }

    public VacationDomainResponse makeVacationResponse(Vacation vacation, MemberNo memberNo) {
        VacationDTO vacationDTO = VacationDTO.of(memberNo.getMemberNo(), vacation);
        Vacation manipulatedVacation = Vacation.of(vacationDTO);
        List<VacationDTO> manipulatedVacationDTOs = vacationRepository.selectVacationsPerMemberNo(memberNo);
        Vacations manipulatedVacations = Vacations.of(manipulatedVacationDTOs);
        return VacationDomainResponse.of(manipulatedVacation, manipulatedVacations);
    }

    public boolean cancelVacation(Member member, long vacationApplicationNo) {
        VacationApplicationNo manipulatedVacationApplicationNo = VacationApplicationNo.of(vacationApplicationNo);
        MemberNo memberNo = member.getMemberNo();
        VacationApplicationDTO vacationApplicationDTO = vacationRepository.selectVacationApplication(manipulatedVacationApplicationNo);

        if (Objects.isNull(vacationApplicationDTO)) {
            throw new IllegalArgumentException("취소할 휴가가 없습니다.");
        }

        MemberNo selectedMemberNo = MemberNo.of(vacationApplicationDTO.getMemberNo());
        if (!memberNo.equals(selectedMemberNo)) {
            throw new IllegalArgumentException("휴가를 취소할 수 있는 권한이 없습니다.");
        }

        VacationNo vacationNo = VacationNo.of(vacationApplicationDTO.getVacationNo());
        VacationDTO vacationDTO = vacationRepository.selectVacation(vacationNo, memberNo);

        Vacation vacation = Vacation.of(vacationDTO);
        vacation.cancelVacation(vacationApplicationDTO.getUseVacationCount());

        VacationDTO manipulatedVacationDTO = VacationDTO.of(memberNo.getMemberNo(), vacation);
        int updateVacationForVacationRequestResult = vacationRepository.updateVacationForVacationRequest(manipulatedVacationDTO);
        log.debug("updateVacationForVacationRequest() :: {}", updateVacationForVacationRequestResult);

        vacationApplicationDTO.setCancelYN("Y");
        vacationApplicationDTO.setCancelDate(LocalDateTime.now());
        int updateVacationApplicationForCancelResult = vacationRepository.updateVacationApplicationForCancel(vacationApplicationDTO);
        log.debug("updateVacationApplicationForCancel() :: {}", updateVacationApplicationForCancelResult);

        return true;
    }
}
