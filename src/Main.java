import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        if(args.length > 1) {
            if(args[0].equals("-")){
                System.out.println("Введите, пожалуйста, команду (add, mul или misc), \n" +
                        " а также числа, в количестве от двух (для первых двух команд) \n" +
                        " и ровно трех для последней команды");
                try {
                    String str = new BufferedReader( new InputStreamReader(System.in)).readLine().trim();
                    Command cmd = parseFromConsole(str);

                    if(cmd != null){
                        operate(cmd, args[1]);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                Command cmd = parseFromFile(args[0]);

                if(cmd != null){
                    operate(cmd, args[1]);
                }
            }
        }
    }

    private static Command parseFromConsole(String str){
        String[] array = str.split(" ");
        return getNewCommand(array);
    }

    private static Command parseFromFile(String filePath){
        try {
            File file = new File(filePath);
            if(file.exists()) {
                String[] array = new String(Files.readAllBytes(Paths.get(filePath))).split(" ");
                return getNewCommand(array);
            } else {
                System.out.println("Указанный файл не найден!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Command getNewCommand(String[] array){
        if(array.length > 2){
            String command = array[0];
            List<Double> doubleList = new ArrayList<>();
            for(int i = 1; i < array.length; i++){
                doubleList.add(Double.valueOf(array[i]));
            }
            return new Command(command, doubleList);
        } else {
            System.out.println("Неверное количество аргументов!");
        }
        return null;
    }

    private static void operate(Command cmd, String arg){
        if(arg.equals("-")) {
            if (cmd.getCommand().equals("add")) {
                System.out.println("Сумма введенных чисел равна: " + sum(cmd.getList()));
            } else if (cmd.getCommand().equals("mul")) {
                System.out.println("Произведение введенных чисел равно: " + multiply(cmd.getList()));
            } else if (cmd.getCommand().equals("misc")) {
                System.out.println("Перемножение первых двух введенных чисел и их дальнейшая сумма с третьим равна: " + multiplyFirstTwoNumbersAndAddToTheThirdOne(cmd.getList()));
            } else{
                System.out.println("Неверный формат команды!");
            }
        } else {
            String result = "Неверный формат команды!";
            if (cmd.getCommand().equals("add")) {
                result = "Сумма введенных чисел равна: " + sum(cmd.getList());
            } else if (cmd.getCommand().equals("mul")) {
                result = "Произведение введенных чисел равно: " + multiply(cmd.getList());
            } else if (cmd.getCommand().equals("misc")) {
                result = "Перемножение первых двух введенных чисел и их дальнейшая сумма с третьим равна: " + multiplyFirstTwoNumbersAndAddToTheThirdOne(cmd.getList());
            }
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(arg, false));
                writer.write(result);

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private static double sum(List<Double> numbers) {
        double sum = 0;
        if(numbers.size() > 1) {
            for (Double d : numbers) {
                sum += d;
            }
        } else {
            System.out.println("Недостаточно аргументов: требуется минимум два. Повторите, пожалуйста, ввод.");
        }
        return sum;
    }

    private static double multiply(List<Double> numbers){
        if(numbers.size() > 1) {
            double result = 1;
            for (double d : numbers) {
                result *= d;
            }
            return result;
        } else {
            System.out.println("Недостаточно аргументов: требуется минимум два. Повторите, пожалуйста, ввод.");
            return 0;
        }
    }

    private static double multiplyFirstTwoNumbersAndAddToTheThirdOne(List<Double> numbers) {
        if(numbers.size() >= 3) {
            return numbers.get(0) * numbers.get(1) + numbers.get(2);
        } else {
            System.out.println("Недостаточно аргументов: требуется три. Повторите, пожалуйста, ввод.");
        }
        return 0;
    }
}
