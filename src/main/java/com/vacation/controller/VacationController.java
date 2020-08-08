package com.vacation.controller;

import com.vacation.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VacationController {

    @Autowired
    private VacationService vacationService;

    @RequestMapping("/template")
    public ModelAndView index(@RequestParam(value = "title") String title) {
        return new ModelAndView("index")
                .addObject("title", title);
    }
}
