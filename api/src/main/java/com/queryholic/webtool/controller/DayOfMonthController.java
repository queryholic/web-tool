package com.queryholic.webtool.controller;

import com.queryholic.webtool.model.DayOfWeek;
import com.queryholic.webtool.service.DayOfMonthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author : queryholic
 * @since : 30/01/2019å
 */
@Slf4j
@RestController
public class DayOfMonthController {
    @Autowired
    private DayOfMonthService dayOfMonthService;

    @GetMapping("/day-of-month")
    @ResponseBody
    public List<DayOfWeek> get(@RequestParam("year") int year, @RequestParam("month") int month, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        return dayOfMonthService.getDayOfMonth(year, month);
    }

}
