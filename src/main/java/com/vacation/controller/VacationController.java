package com.vacation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VacationController {

    // ui 는 평가 대상이 아님
    @RequestMapping("/template")
    public ModelAndView index(@RequestParam(value = "title") String title) {
        return new ModelAndView("index")
                .addObject("title", title);
    }
}
