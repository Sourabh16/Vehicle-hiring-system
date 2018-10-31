package Utility;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * Provides date utility methods for converting dates from string to
 * date formats, and vice versa.
 *
 * @author Sourabh Kolekar
 * @version 25-09-2017
 */
public class DateUtility {

    public static final String PATTERN_DD_MM_YYYY = "dd-MM-yyyy";
    private static final String PATTERN_DD_MMM_YYYY = "dd-MMM-yyyy";
    private static final String PATTERN_YYYY_DD_MM = "yyyy-MM-dd";

    /**
     * Receives a date as a String object, converting to LocalDate object
     *
     * @param aDate a string date object to convert
     * @return LocalDate object
     */
    public static LocalDate convertStringToDate(String aDate) throws ParseException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(PATTERN_DD_MM_YYYY);
        return LocalDate.parse(aDate, format);
    }

    /**
     * Receives a String object, converting to LocalTime object in 24 hr format
     *
     * @param aTime a string time object to convert
     * @return LocalTime object in 24 Hr format
     */
    public static LocalTime convertStringToTime(String aTime) throws ParseException {
        DateTimeFormatter parseFormat = new DateTimeFormatterBuilder().appendPattern("H:m").toFormatter();
        return LocalTime.parse(aTime, parseFormat);
    }

    /**
     * converts localDate to String
     *
     * @param localDate input local date
     * @return string formatted locale date
     */
    public static String convertLocalDateToString(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_YYYY_DD_MM);
        return localDate.format(formatter);
    }

    public static String convertLocalTimeToString(LocalTime localTime) {
        return localTime.toString();
    }


    /**
     * Returns current date in specified format
     *
     * @return current date in provided format
     */
    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    /**
     * Returns current time in specified format
     *
     * @return current time in provided format
     */
    public static LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    /**
     * Returns current Date time in specified format
     *
     * @return current Date time in provided format
     */

    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    /**
     * generates localised date from provided string Date
     *
     * @param inputDate provided string date
     * @return Localised date as per the requirements
     */
    public static String getLocalisedDate(LocalDate inputDate) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(PATTERN_DD_MMM_YYYY, Locale.getDefault());
        return df.format(inputDate);
    }

    /**
     * Returns the days between provided dates
     *
     * @param firstDate  First date with which comparison is to be done
     * @param secondDate second date with which comparison is to be done
     * @return Number of days between two dates
     * <p><p>
     * See <a href ="https://stackoverflow.com/a/29812532/3796452">https://stackoverflow.com/a/29812532/3796452</a> for reference
     */
    public static long daysBetweenDates(LocalDate firstDate, LocalDate secondDate) {
        return ChronoUnit.DAYS.between(firstDate, secondDate);
    }

    /**
     * Returns the time between provided times in minutes
     *
     * @param firstTime  First time with which comparison is to be done
     * @param secondTime second time with which comparison is to be done
     * @return number of minutes between provided times
     */
    public static long timeBetweenDateTimes(LocalDateTime firstTime, LocalDateTime secondTime) {
        return ChronoUnit.MINUTES.between(firstTime, secondTime);
    }


    /**
     * Returns the time between provided times in hours
     *
     * @param firstTime  First time with which comparison is to be done
     * @param secondTime second time with which comparison is to be done
     * @return number of hours between provided times
     */
    public static long hoursBetweenDateTimes(LocalDateTime firstTime, LocalDateTime secondTime) {
        return ChronoUnit.HOURS.between(firstTime, secondTime);
    }
}
