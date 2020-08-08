<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    Main!!
    <c:forEach items="${memberDTOS}" var="user">
        ${user.name}<br/>
    </c:forEach>
</div>
