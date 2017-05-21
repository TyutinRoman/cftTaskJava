package insertionsort;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Tyutin
 */
public class InsertionSort {

    private static List<String> inputdata = new ArrayList<>();
    static int fourthArg;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        String FileIn = args[0];
        String FileOut = args[1];
        int checkOut;  //Проверка расширения выходного файла
        String extOut = FileOut.substring(FileOut.lastIndexOf('.') + 1);
        if (extOut.equalsIgnoreCase("txt")) {
            checkOut = 0;
        } else {
            checkOut = 1;
        }

        if (args.length == 4) { // Проверка количества аргументов
            if (Files.exists(Paths.get(FileIn))) { // Проверка наличия файла
                try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
                    // чтение посимвольно
                    String c;
                    while ((c = br.readLine()) != null) {
                        inputdata.add(c);
                    }

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

                if (args[2].equalsIgnoreCase("-i")) { // Действие в случае третьего аргумента соответствующего int
                    Integer num[] = new Integer[inputdata.size()];
                    for (int z = 0; z < inputdata.size(); z++) {
                        try {
                            num[z] = Integer.parseInt(inputdata.get(z));
                        } catch (NumberFormatException e) {

                            System.out.println("Тип данных элементов входного файла не "
                                    + "соответствует заявленному (не все элементы целые числа)");
                            throw new Exception("Программа будет завершена");
                        }

                    }

                    int j, key, i;

                    for (j = 1; j < num.length; j++) {
                        key = num[j];
                        // реализация сортировки по возрастанию 
                        //либо убыванию в зависимости от значеня четвертого аргумента
                        if (args[3].equalsIgnoreCase("-a")) {
                            fourthArg = 1;
                            for (i = j - 1; (i >= 0) && (num[i] > key); i--) {
                                num[i + 1] = num[i];
                            }
                            num[i + 1] = key;
                        } else if (args[3].equalsIgnoreCase("-d")) {
                            fourthArg = 1;
                            for (i = j - 1; (i >= 0) && (num[i] < key); i--) {
                                num[i + 1] = num[i];
                            }
                            num[i + 1] = key;
                        } else {
                            fourthArg = 0;
                        }
                    }

                    if (fourthArg == 0) {
                        System.out.println("Четвертый аргумент должен принимать занчения: "
                                + "-a - для сортировки по возрастанию"
                                + " -d - для сортировки по убыванию");
                    }

                    String str[] = new String[num.length];
                    for (int z = 0; z < num.length; z++) {
                        str[z] = num[z].toString();

                    }
                    if (checkOut == 0) {
                        try (BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]))) {
                            for (int z = 0; z < str.length; z++) {
                                bw.write(str[z]);
                                bw.newLine();
                            }
                            bw.close();
                        } catch (IOException ex) {

                            System.out.println(ex.getMessage());
                        }
                    } else {
                        System.out.println("Расширение выходного файла отлично от "
                                + ".txt ");
                    }
                } else if (args[2].equalsIgnoreCase("-s")) {
                    // Действия в случае значения третьего аргумента, соответствующего String

                    String[] str = {}; // конвертируем ArrayList в массив
                    str = inputdata.toArray(new String[inputdata.size()]);

                    int j;
                    String key;
                    int i;

                    for (j = 1; j < str.length; j++) {
                        key = str[j];
                        if (args[3].equalsIgnoreCase("-a")) {
                            fourthArg = 1;
                            for (i = j - 1; (i >= 0) && (key.compareTo(str[i]) < 0); i--) {
                                str[i + 1] = str[i];
                            }
                            str[i + 1] = key;
                        }
                        if (args[3].equalsIgnoreCase("-d")) {
                            fourthArg = 1;
                            for (i = j - 1; (i >= 0) && (key.compareTo(str[i]) > 0); i--) {
                                str[i + 1] = str[i];
                            }
                            str[i + 1] = key;
                        } else {
                            int fourthArg = 0;
                        }
                    }

                    if (fourthArg == 0) {
                        System.out.println("Четвертый аргумент должен принимать занчения: "
                                + "-a - для сортировки по возрастанию"
                                + " -d - для сортировки по убыванию");
                    }
                    if (checkOut == 0) {
                        try (BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]))) {
                            for (int z = 0; z < str.length; z++) {
                                bw.write(str[z]);
                                bw.newLine();
                            }
                            bw.close();
                        } catch (IOException ex) {

                            System.out.println(ex.getMessage());
                        }
                    } else {
                        System.out.println("Расширение выходного файла отлично от "
                                + ".txt ");
                    }
                } else {
                    System.out.println("Третий аргумент должен принимать занчения: "
                            + "-i - для обрабатываемого массива целых чисел"
                            + " -s - для обрабатываемого массива строк");
                }
            } else {
                System.out.println("Входной файл не найден");
            }
        } else {
            System.out.println("Не все аргументы заданы");
        }
    }
}
