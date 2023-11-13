package org.example.hospital.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.hospital.custom_exceptions.*;

import java.util.Scanner;

public class RequestMethods {
    private static final Scanner scanner;
    private static final Logger LOGGER;

    static {
        LOGGER = LogManager.getLogger("InsteadOfSOUT");
    }

    static {
        scanner = new Scanner(System.in);
    }

    public static int requestingInfoWithChoice(String text, int menuItemsNumber)
            throws EmptyInputException, NumberFormatException, MenuItemNumberOutOfBoundsException {
        LOGGER.info(text);
        String answer = scanner.nextLine();
        if (answer.isEmpty()) {
            throw new EmptyInputException("[EmptyInputException]: Entered data can not be empty!");
        }
        int numberFromAnswer = Integer.parseInt(answer);
        if (numberFromAnswer < 1 || numberFromAnswer > menuItemsNumber) {
            throw new MenuItemNumberOutOfBoundsException("[MenuItemNumberOutOfBoundsException]: Entered data " +
                    "must be equal to some menu item!");
        }
        return numberFromAnswer;
    }

    public static String requestingInfoString(String text) throws EmptyInputException, StringFormatException {
        LOGGER.info(text);
        String answer = scanner.nextLine();
        if (answer.isEmpty()) {
            throw new EmptyInputException("[EmptyInputException]: Entered data can not be empty!");
        }
        if (!answer.matches("^[a-zA-Zа-яёА-ЯЁ]+[\\s-]?[a-zA-Zа-яёА-ЯЁ]+$")) {
            throw new StringFormatException("[StringFormatException]: Entered data is not a letter character!");
        }
        return answer;
    }

    public static int requestingInfoInt(String text)
            throws EmptyInputException, NumberFormatException, NegativeNumberException {
        LOGGER.info(text);
        String answer = scanner.nextLine();
        if (answer.isEmpty()) {
            throw new EmptyInputException("[EmptyInputException]: Entered data can not be empty!");
        }
        int numberFromAnswer = Integer.parseInt(answer);
        if (numberFromAnswer < 0) {
            throw new NegativeNumberException("[NegativeNumberException]: Entered data can not be negative");
        }
        return numberFromAnswer;
    }

    public static String requestingInfoWithYesOrNo(String text) throws EmptyInputException, YesOrNoException {
        LOGGER.info(text);
        String answer = scanner.nextLine();
        if (answer.isEmpty()) {
            throw new EmptyInputException("[EmptyInputException]: Entered data can not be empty!");
        }
        if (!answer.equals("y") && !answer.equals("n")) {
            throw new YesOrNoException("[YesOrNoException]: Entered data must be equal to 'y' or 'n'!");
        }
        return answer;
    }

    static void closeScanner() {
        scanner.close();
    }
}
