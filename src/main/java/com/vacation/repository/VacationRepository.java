package com.vacation.repository;

import com.vacation.dto.MemberDTO;
import com.vacation.dto.VacationDTO;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VacationRepository extends SqlSessionDaoSupport {

    @Autowired
    @Qualifier(value = "vacationSqlSessionFactory")
    public void setSessionFactory(SqlSessionFactory sessionFactory) {
        super.setSqlSessionFactory(sessionFactory);
    }

    public int selectQuery() {
        return getSqlSession().selectOne(getSqlMapperPrefix() + "selectTemplate");
    }

    public List<MemberDTO> selectMembers() {
        return getSqlSession().selectList(getSqlMapperPrefix() + "selectMembers");
    }

    public List<VacationDTO> selectVacationsPerMember(MemberDTO memberDTO) {
        return getSqlSession().selectList(getSqlMapperPrefix() + "selectVacationsPerMember");
    }

    public List<VacationDTO> selectVacations() {
        return getSqlSession().selectList(getSqlMapperPrefix() + "selectVacations");
    }

    public int insertVacationPerMember(VacationDTO vacationDTO) {
        return getSqlSession().insert(getSqlMapperPrefix() + "insertVacationPerMember", vacationDTO);
    }

    public int updateVacationForAssignAnnualVacation(VacationDTO vacationDTO) {
        return getSqlSession().update(getSqlMapperPrefix() + "updateVacationForAssignAnnualVacation", vacationDTO);
    }

    private String getSqlMapperPrefix() {
        return "com.vacation.";
    }
}
