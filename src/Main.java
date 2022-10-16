import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    static String[] rome={"I","II","III","IV","V","VI","VII","VIII","IX","X","L","C"};
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println(calculator(scan.nextLine()));
    }
    public static boolean isNumeric(String value) {
        return Pattern.matches("\\d+",value);
    }
    public static String calculator(String value) throws Exception {
        String[] spl = value.split(" ");
        int result = 0;
        boolean isRome = false;
        int a = Arrays.asList(rome).indexOf(spl[0]);
        int b = Arrays.asList(rome).indexOf(spl[2]);
        if (a != -1) {
            a++;
            b++;
            isRome = true;
            if (a <= b ) {
                throw new Exception();
            }
        } else if (isNumeric(spl[0]) & isNumeric(spl[2])) {
            a = Integer.parseInt(spl[0]);
            b = Integer.parseInt(spl[2]);
        } else {
            throw new Exception();
        }
        if (spl.length!=3 | (a == -1 & b != -1) | (a != -1 & b == -1) | b > 10 | a > 10 | b < 1 | a < 1 ) {
            throw new Exception();
        } else {
            result = switch (spl[1]) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                case "/" -> a / b;
                default -> throw new Exception();
            };
        }
        if (isRome) {
            if (result <= 0) {
                return ("");
            }
            return romeToInt(result);
        }
        return String.valueOf(result);
    }

    private static String romeToInt(int num) {
        String result = "";
        int a = num / 10;
        int b = num % 10;
        if (num < 40) {
            for (int i = 0; i < a; i++) {
                result = result + "X";
            }
            if (b != 0) {
                result = result + rome[b - 1];
            }
        } else {
            if (num < 50) {
                result = "XL";
                if (b != 0) {
                    result = result + rome[b - 1];
                }
            } else {
                if (num < 90) {
                    result = "L";
                    for (int i = 0; i < a; i++) {
                        result = result + "X";
                    }
                    if (b != 0) {
                        result = result + rome[b - 1];
                    }
                } else {
                    if (num < 100) {
                        result = "XC";
                        if (b != 0) {
                            result = result + rome[b - 1];
                        }
                    } else {
                        if (num == 100) {
                            result = "C";
                        }
                    }
                }
            }
        }
        return(result);
    }
}