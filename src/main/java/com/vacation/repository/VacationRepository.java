package com.vacation.repository;

import com.vacation.domain.MemberNo;
import com.vacation.domain.VacationApplicationNo;
import com.vacation.domain.VacationNo;
import com.vacation.dto.MemberDTO;
import com.vacation.dto.VacationApplicationDTO;
import com.vacation.dto.VacationDTO;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class VacationRepository extends SqlSessionDaoSupport {

    @Autowired
    @Qualifier(value = "vacationSqlSessionFactory")
    public void setSessionFactory(SqlSessionFactory sessionFactory) {
        super.setSqlSessionFactory(sessionFactory);
    }

    public List<MemberDTO> selectMembers() {
        return getSqlSession().selectList(getSqlMapperPrefix() + "selectMembers");
    }

    public List<VacationDTO> selectVacationsPerMemberNo(MemberNo memberNo) {
        return getSqlSession().selectList(getSqlMapperPrefix() + "selectVacationsPerMemberNo", memberNo);
    }

    public VacationDTO selectVacation(VacationNo vacationNo, MemberNo memberNo) {
        Map<String, Object> params = new HashMap<>();
        params.put("vacationNo", vacationNo.getVacationNo());
        params.put("memberNo", memberNo.getMemberNo());
        return getSqlSession().selectOne(getSqlMapperPrefix() + "selectVacation", params);
    }

    public VacationApplicationDTO selectVacationApplication(VacationApplicationNo vacationApplicationNo) {
        return getSqlSession().selectOne(getSqlMapperPrefix() + "selectVacationApplication", vacationApplicationNo);
    }

    public int insertVacationPerMember(VacationDTO vacationDTO) {
        return getSqlSession().insert(getSqlMapperPrefix() + "insertVacationPerMember", vacationDTO);
    }

    public int insertVacationApplication(VacationApplicationDTO vacationApplicationDTO) {
        return getSqlSession().insert(getSqlMapperPrefix() + "insertVacationApplication", vacationApplicationDTO);
    }

    public int updateVacationForVacationRequest(VacationDTO vacationDTO) {
        return getSqlSession().update(getSqlMapperPrefix() + "updateVacationForVacationRequest", vacationDTO);
    }

    public int updateVacationApplicationForCancel(VacationApplicationDTO vacationApplicationDTO) {
        return getSqlSession().update(getSqlMapperPrefix() + "updateVacationApplicationForCancel", vacationApplicationDTO);
    }

    private String getSqlMapperPrefix() {
        return "com.vacation.";
    }
}
