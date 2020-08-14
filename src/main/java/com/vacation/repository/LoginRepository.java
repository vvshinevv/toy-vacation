package com.vacation.repository;

import com.vacation.dto.MemberDTO;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LoginRepository extends SqlSessionDaoSupport {

    @Autowired
    @Qualifier(value = "loginSqlSessionFactory")
    public void setSessionFactory(SqlSessionFactory sessionFactory) {
        super.setSqlSessionFactory(sessionFactory);
    }

    public MemberDTO selectMember(String name) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        return getSqlSession().selectOne(getSqlMapperPrefix() + "selectMember", params);
    }


    private String getSqlMapperPrefix() {
        return "com.login.";
    }
}
