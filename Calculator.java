import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {

        System.out.println("Калькулятор умеет делить, умножать, вычитать и складывать римские или арабские цифры. " +
                "Введите через пробел выражение вида 2 - 5 или I * IV и нажмите Enter:");
        Scanner scanner = new Scanner(System.in);

        int number1, number2, res;

        // Ловим пользовательский ввод, режем по пробелам и помещаем в в архив userIn
        String[] userIn = scanner.nextLine().split(" ");
        if (userIn.length != 3) {
            throw new InputMismatchException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        String num1 = userIn[0], num2 = userIn[2], action = userIn[1];


        // Пробуем конвертировать ввод римских в арабские
        number1 = romainToArabic(num1);
        number2 = romainToArabic(num2);

        // Разбираем ответ конвертера по ситуациям:
        if (number1 < 0 && number2 < 0) {
            res = 0;

        } else if (number1 < 0 || number2 < 0) {
            throw new InputMismatchException("Используются одновременно разные системы счисления!");
        } else {
            res = calc(number1, number2, action);
            if (res < 0) {
                throw new InputMismatchException("В римской системе исчисления нет отрицательных чисел!");
            } else {
                String romRes = arabicToRomain(res);
                System.out.println(romRes);
            }
        }

        // Если оба числа при конвертации вернули -1, то переходим к арабским цифрам
        if (res == 0) {            
            try {
                number1 = Integer.parseInt(num1);
                number2 = Integer.parseInt(num2);
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Некорректный ввод!");
            }

            if (number1 < 0 || number2 < 0) {
                throw new InputMismatchException("Вводимое число не может быть меньше нуля!");
            } else if (number1 > 10 || number2 > 10) {
                throw new InputMismatchException("Пожалуйста, вводите цифры от 1 до 10!");
            } else {
                res = calc(number1, number2, action);
                System.out.println(res);
            }
        }
    }

    // Конвертируем римский ввод в арабский
    private static int romainToArabic (String rom) {
        if (rom.equals("I")) {
                return 1;
            } else if (rom.equals("II")) {
                return 2;
            } else if (rom.equals("III")) {
                return 3;
            } else if (rom.equals("IV")) {
                return 4;
            } else if (rom.equals("V")) {
                return 5;
            } else if (rom.equals("VI")) {
                return 6;
            } else if (rom.equals("VII")) {
                return 7;
            } else if (rom.equals("VIII")) {
                return 8;
            } else if (rom.equals("IX")) {
                return 9;
            } else if (rom.equals("X")) {
                return 10;
            }
        return -1;
    }

    // Конвертируем арабский ввод в римский
    private static String arabicToRomain (int arab) {
        String[] rom = {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV",
                "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII",
                "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII",
                "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI",
                "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII",
                "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII",
                "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX",
                "C"};
        String r = rom[arab];
        return r;
    }

    // Метод для математических вычислений
    public static int calc (int num1, int num2, String action) {
        int res;
        switch (action) {
            case ("+"):
                res = num1 + num2;
                break;
            case ("-"):
                res = num1 - num2;
                break;
            case ("*"):
                res = num1 * num2;
                break;
            case ("/"):
                try {
                    res = num1 / num2;
                } catch (ArithmeticException e) {
                    throw new ArithmeticException("На ноль делить нельзя!");
                }
                break;
            default:
                throw new InputMismatchException("Неверный знак операции!");
        }
        return res;
    }
}
