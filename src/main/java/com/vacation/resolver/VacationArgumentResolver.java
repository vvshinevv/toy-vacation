package com.vacation.resolver;

import com.vacation.dto.request.VacationRequest;
import com.vacation.model.VacationRequestParameter;
import com.vacation.model.VacationUsingType;
import com.vacation.util.VacationWebServiceRequest;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Conventions;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;

import javax.servlet.http.HttpServletRequest;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class VacationArgumentResolver implements HandlerMethodArgumentResolver, ApplicationContextAware {

    private VacationWebServiceRequest vacationWebServiceRequest;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        vacationWebServiceRequest = applicationContext.getBean("vacationWebServiceRequest", VacationWebServiceRequest.class);
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return VacationRequest.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {

        Map<String, String> webRequestParams = vacationWebServiceRequest.getRequestBody(webRequest);

        WebDataBinder webDataBinder = createBinder(parameter, webRequest, binderFactory);
        ((ExtendedServletRequestDataBinder) webDataBinder).bind((HttpServletRequest) webRequest.getNativeRequest());
        doBindManually((VacationRequest) webDataBinder.getTarget(), webRequestParams);

        // argument validation 도 추가하면 좋을 것 같습니다.

        return webDataBinder.getTarget();
    }

    private WebDataBinder createBinder(MethodParameter parameter, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String argumentName = Conventions.getVariableNameForParameter(parameter);
        return binderFactory.createBinder(webRequest, new VacationRequest(), argumentName);
    }

    private void doBindManually(VacationRequest target, Map<String, String> webRequestParams) {

        String vacationNoParam = webRequestParams.get(VacationRequestParameter.VACATION_NO.getParameterName());
        long vacationNo = Long.valueOf(vacationNoParam);

        String startVacationDateParam = webRequestParams.get(VacationRequestParameter.START_VACATION_DATE.getParameterName());
        LocalDateTime startVacationDate = LocalDateTime.parse(startVacationDateParam, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String endVacationDateParam = webRequestParams.get(VacationRequestParameter.END_VACATION_DATE.getParameterName());
        LocalDateTime endVacationDate = LocalDateTime.parse(endVacationDateParam, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String vacationUsingTypeParam = webRequestParams.get(VacationRequestParameter.VACATION_USING_TYPE.getParameterName());
        VacationUsingType vacationUsingType = VacationUsingType.getType(vacationUsingTypeParam);

        if (vacationUsingType != VacationUsingType.DAY_OFF) {
            endVacationDate = startVacationDate;
        }

        float useVacationCount = calculateUseVacationCount(vacationUsingType, startVacationDate, endVacationDate);
        String comment = webRequestParams.get(VacationRequestParameter.COMMENT.getParameterName());

        target.setVacationNo(vacationNo);
        target.setStartVacationDate(startVacationDate);
        target.setEndVacationDate(endVacationDate);
        target.setUseVacationCount(useVacationCount);
        target.setComment(comment);
    }

    private float calculateUseVacationCount(VacationUsingType vacationUsingType, LocalDateTime start, LocalDateTime end) {

        if (vacationUsingType != VacationUsingType.DAY_OFF) {
            return vacationUsingType.getCount();
        }

        if (end.getDayOfWeek() == DayOfWeek.SATURDAY) {
            end = end.minus(Period.ofDays(1));
        }
        if (end.getDayOfWeek() == DayOfWeek.SUNDAY) {
            end = end.minus(Period.ofDays(2));
        }

        Period period = Period.between(start.toLocalDate(), end.toLocalDate());
        return (float) period.getDays();
    }
}
