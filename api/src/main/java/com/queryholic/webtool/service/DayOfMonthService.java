package com.queryholic.webtool.service;

import com.queryholic.webtool.model.DayOfWeek;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : queryholic
 * @since : 30/01/2019
 */
@Slf4j
@Service
public class DayOfMonthService {
    /**
     * get day list of month
     *
     * @param year
     * @param month
     * @return
     */
    public List<DayOfWeek> getDayOfMonth(int year, int month) {
        final List<DayOfWeek> dayOfWeeks = new ArrayList<>();

        normalizeByWeek(getListOfTheMonth(year, month))
                .forEach((weakNumber, localDates) -> {
                    final DayOfWeek dayOfWeek = DayOfWeek.builder()
                            .weekNumber(weakNumber)
                            .days(
                                    localDates.stream()
                                            .map(this::dateFormat)
                                            .collect(Collectors.toList())
                            )
                            .build();

                    dayOfWeeks.add(dayOfWeek);

                });

        return dayOfWeeks;
    }

    private List<LocalDate> getListOfTheMonth(int year, int month) {
        List<LocalDate> result = new ArrayList<>();
        LocalDate first = LocalDate.of(year, month, 1);
        LocalDate date = first;

        while (date.getMonthValue() == first.getMonthValue()) {
            result.add(date);
            date = date.plusDays(1);
        }
        return result;
    }

    private Map<Integer, List<LocalDate>> normalizeByWeek(List<LocalDate> list) {
        final Map<Integer, List<LocalDate>> map = new HashMap<>();
        int weekCounter = 1;

        if (list.isEmpty()) {
            System.out.println("### list is empty.");
            return null;
        }

        while (!list.get(0).getDayOfWeek().equals(java.time.DayOfWeek.MONDAY)) {
            list.remove(0);
        }

        while (!list.get(list.size() - 1).getDayOfWeek().equals(java.time.DayOfWeek.SUNDAY)) {
            list.add(list.get(list.size() - 1).plusDays(1));
        }

        int index = 0;

        do {
            int toIndex = index + 7;
            if (toIndex > list.size()) {
                toIndex = list.size();
            }

            map.put(weekCounter, list.subList(index, toIndex));

            weekCounter++;
            index += 7;
        } while (index < list.size());

        return map;
    }

    private String dateFormat(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ISO_LOCAL_DATE)
                + " ("
                + localDate.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN)
                + ")";
    }

    private String weakFormat(int year, int month, int weekCounter) {
        return year + "-" + (month < 10 ? "0" + month : month) + " #" + weekCounter;
    }
}
