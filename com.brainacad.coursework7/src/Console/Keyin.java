package Console;

/**
 * Created by Зая on 20.02.2016.
 */
class Keyin {


    //Текст строки приглашения
    public static void printPrompt(String prompt) {
        System.out.print(prompt + " ");
        System.out.flush();//When you can't wait for the item to be displayed, flush the stream.
    }

    //Чтобы убедится, что нет данных во входном потоке
    public static void inputFlush() {
        int dummy;
        try {
            while ((System.in.available()) != 0) {//Returns an estimate of the number of bytes that can be read
                // (or skipped over) from this input stream without blocking
                // by the next invocation of a method for this input stream
                dummy = System.in.read();
            }
        } catch (java.io.IOException e) {
            System.out.println("Input error");
        }
    }


    public static String inString(String prompt) {
        inputFlush();
        printPrompt(prompt);
        return inString();
    }


    public static String inString() {
        int aChar;
        String s = "";
        boolean finished = false;

        while (!finished) {
            try {
                aChar = System.in.read();
                if ((char) aChar == '\n') {
                    if (s != "") {
                        finished = true;
                    } else {
                        System.out.println("Write down smth,please!!");

                    }
                } else if ((char) aChar != '\r')//http://programmers.stackexchange.com/questions/29075/difference-between-n-and-r-n
                    s = s + (char) aChar; // Enter into string
            } catch (java.io.IOException e) {
                System.out.println("Input error");
                finished = true;
            }
        }
        return s;
    }

    public static int inInt(String prompt) {
        while (true) {
            inputFlush();
            printPrompt(prompt);
            try {
                return Integer.valueOf(inString().trim()).intValue();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Not an integer!");
            }
        }
    }


}
