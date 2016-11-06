package org.bogdan.remindme;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Bodia on 05.11.2016.
 */

public class UserVK implements Comparable<UserVK> {

    private final String name;
    private final DateTime birthDate;
    private final String dateFormat;
    private final String avatarURL;

    static private ArrayList<UserVK> users=new ArrayList<>();

    public UserVK(String name, DateTime birthDate, String dateFormat, String avatarURL) {
        this.name = name;
        this.birthDate = birthDate;
        this.dateFormat = dateFormat;
        this.avatarURL =avatarURL;
    }

    public DateTime getBirthDate() {
        return birthDate;
    }

    public String getName() {
        return name;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public String getAvatarURL() {return avatarURL; }

    public static List<UserVK> getUsersList(){
        return users;
    }


    @Override
    public int compareTo(UserVK another) {
        return another.getBirthDate().compareTo(DateTime.now());
    }

    public static String timeToNextBirht(UserVK userVK){

        LocalDate dateOfBirth =userVK.getBirthDate().toLocalDate();
        LocalDate currentDate = new LocalDate();
        // Take birthDay  and birthMonth  from dateOfBirth
        int birthDay = dateOfBirth.getDayOfMonth();
        int birthMonth = dateOfBirth.getMonthOfYear();
        // Current year's birthday
        LocalDate currentYearBirthDay = new LocalDate().withDayOfMonth(birthDay)
                .withMonthOfYear(birthMonth);
        PeriodType monthDay = PeriodType.yearMonthDayTime().withYearsRemoved();
        PeriodFormatter periodFormatter = new PeriodFormatterBuilder()
                .appendMonths().appendSuffix(" Months ").appendDays()
                .appendSuffix(" Days ").printZeroNever().toFormatter();
        if (currentYearBirthDay.isAfter(currentDate)) {
            Period period = new Period(currentDate, currentYearBirthDay,monthDay );
            String currentBirthday = periodFormatter.print(period);
            return currentBirthday;
        } else {
            LocalDate nextYearBirthDay =currentYearBirthDay.plusYears(1);
            Period period = new Period(currentDate, nextYearBirthDay ,monthDay );
            String nextBirthday = periodFormatter.print(period);
            return nextBirthday;
        }
    }


}
