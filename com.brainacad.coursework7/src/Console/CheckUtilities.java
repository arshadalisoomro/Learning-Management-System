package Console;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by User on 02.03.2016.
 */
public class CheckUtilities {
    public static String checkForValidDate(String val) {


        if (isValidFormat(val)) {
            return val;
        } else {
            while (!isValidFormat(val)) {
                val = Keyin.inString("Wrong DateFormat.Try again!\n");
            }
        }

        return val;
    }

    public static void checkTwoDates(String startDate,String endDate) {

        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        String format = "dd.MM.yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        try {
            calendar1.setTime(sdf.parse(startDate));
            calendar2.setTime(sdf.parse(endDate));
            long milsecs1 = calendar1.getTimeInMillis();
            long milsecs2 = calendar2.getTimeInMillis();
            long diff = milsecs2 - milsecs1;
            long ddays = diff / (24 * 60 * 60 * 1000);
            if (ddays > 1) {
                return;
            } else {
                while (ddays < 1) {
                    endDate = Keyin.inString("The difference between two dates should be at least one day! " +
                            "Try again to Enter end date:\n" +
                            " (For example: 01.04.2015)\n");
                    calendar2.setTime(sdf.parse(endDate));
                    ddays = (calendar2.getTimeInMillis() - milsecs1) / (24 * 60 * 60 * 1000);
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    public static String getDayOfWeek() {
        System.out.println("Enter working days: ");
        String result = "";
        while (true) {
            if (result != "") {
                break;
            } else {
                String d = Keyin.inString("(For example: Tue, Wed, Sat)\n");
                String[] words = d.split("[,]");
                String[] trimmed = new String[words.length];
                for (int i = 0; i < trimmed.length; i++) {
                    trimmed[i] = words[i].trim();
                }
                String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
                for (int j = 0; j < trimmed.length; j++) {
                    for (int i = 0; i < days.length; i++) {
                        if (days[i].equalsIgnoreCase(trimmed[j])) {
                            if (result != "") {
                                result += ", " + trimmed[j];
                            } else {
                                result += trimmed[j];
                            }
                        }
                    }
                }
                if (result == "") {
                    System.out.println("Write down the correct day of the week");
                }

            }
        }

        return result;
    }

    public static boolean isValidFormat(String value) {
        Date date = null;
        String format = "dd.MM.yyyy";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);// на вход конструктору передали Date  Pattern, например,
            // dd.MM.yyyy (день месяца с лидирующими нулями//номер месяца с лидирующими нулями/год 4-х значное число)
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {


        }
        return date != null;
    }


}
