package study.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class StudyDate {
    public static void main(String[] args) throws ParseException {
//        studyCalendar();
//        studyDateFormat();
//        studyDateFormatAlternatives();
        studyIsoLocalDateTime();
    }

    private static void studyIsoLocalDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME; // Ex: 2011-12-03T10:15:30
        LocalDateTime planDtTm;
        String date_str = "2011-12-03T10:15:30";
        planDtTm = LocalDateTime.parse(date_str, dtf);
        System.out.println("planDtTm = " + planDtTm);

        date_str =  "2017-07-14T15:19:00.000Z";
        try {
            planDtTm = LocalDateTime.parse(date_str, dtf);
            System.out.println("planDtTm = " + planDtTm);
        } catch (DateTimeParseException ex) {
            System.out.println("ex = " + ex);
        }

        Instant instant = Instant.parse(date_str);
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(3));
        System.out.println("ldt = " + ldt);

        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        String nowAsISO = df.format(new Date());
        System.out.println("nowAsISO = " + nowAsISO);
        // 2017-07-14T18:19

        String thisMoment = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmX")
                .withZone(ZoneOffset.UTC)
                .format(Instant.now());
        System.out.println("thisMoment = " + thisMoment);
        // 2017-07-26T09:32Z


        date_str = "2017-07-14T18:19:00+03:00";
        planDtTm = LocalDateTime.parse(date_str, dtf);
        System.out.println("planDtTm = " + planDtTm);
    }

    public static void studyDateFormatAlternatives() throws ParseException {
        // ISO8601 with GMT time zone
        java.util.Date date = Date.from( Instant.parse( "2014-12-12T10:39:40Z" ));
        System.out.println("date = " + date);

        // Time zone other than GMT:
        OffsetDateTime odt = OffsetDateTime.parse( "2010-01-01T12:00:00+01:00" );
        Instant instant = odt.toInstant();  // Instant is always in UTC.
        java.util.Date date2 = java.util.Date.from( instant );
        System.out.println("date2 = " + date2);

    }

    public static void studyDateFormat() throws ParseException {
        SimpleDateFormat inFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date validFromD = inFormat.parse("4/1/2017");
        System.out.println("validFromD = " + validFromD);
    }

    public static void studyCalendar() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
        Calendar calendar = new GregorianCalendar(2013,1,28,13,24,56);

        int year       = calendar.get(Calendar.YEAR);
        int month      = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek  = calendar.get(Calendar.DAY_OF_WEEK);
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        int weekOfMonth= calendar.get(Calendar.WEEK_OF_MONTH);

        int hour       = calendar.get(Calendar.HOUR);        // 12 hour clock
        int hourOfDay  = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
        int minute     = calendar.get(Calendar.MINUTE);
        int second     = calendar.get(Calendar.SECOND);
        int millisecond= calendar.get(Calendar.MILLISECOND);

        System.out.println(sdf.format(calendar.getTime()));

        System.out.println("year \t\t: " + year);
        System.out.println("month \t\t: " + month);
        System.out.println("dayOfMonth \t: " + dayOfMonth);
        System.out.println("dayOfWeek \t: " + dayOfWeek);
        System.out.println("weekOfYear \t: " + weekOfYear);
        System.out.println("weekOfMonth \t: " + weekOfMonth);

        System.out.println("hour \t\t: " + hour);
        System.out.println("hourOfDay \t: " + hourOfDay);
        System.out.println("minute \t\t: " + minute);
        System.out.println("second \t\t: " + second);
        System.out.println("millisecond \t: " + millisecond);

    }
}
