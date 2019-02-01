package com.queryholic.webtool.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author : queryholic
 * @since : 01/02/2019
 */
@Slf4j
@Builder
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NON_PRIVATE)
public class DayOfWeek {
    protected int weekNumber;
    protected List<String> days;
}
