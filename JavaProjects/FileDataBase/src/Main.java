import java.awt.*;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static File createFile() {
        Scanner inputFileName = new Scanner(System.in);
        System.out.println("Введите название файла");
        String fileName = inputFileName.next();
        File fileForDatabase = new File(fileName);
        if (!fileForDatabase.exists()) {
            try {
                fileForDatabase.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileForDatabase;
    }

    public static void createDatabase() {
        String arrayOfColumns[] = new String[4];
        arrayOfColumns[0] = "id";
        arrayOfColumns[1] = "name";
        arrayOfColumns[2] = "averageMark";
        arrayOfColumns[3] = "isFree";
        String lineSeparator = System.getProperty("line.separator");
        try {
            FileWriter fileForDatabase = new FileWriter(createFile(), true);
            for (int i = 0; i < arrayOfColumns.length; i++) {
                fileForDatabase.write(arrayOfColumns[i]);
                fileForDatabase.append('\t');
                fileForDatabase.append('\t');
            }
            fileForDatabase.write(lineSeparator);
            fileForDatabase.flush();
            fileForDatabase.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addUser() {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите id");
        int userId = input.nextInt();
        System.out.println("Введите name");
        String name = input.next();
        System.out.println("Введите averageMark");
        float averageMark = input.nextFloat();
        System.out.println("Введите isFree");
        boolean isFree = input.nextBoolean();
        User user = new User(userId, name, averageMark, isFree);
        String res = "Таких id нет";
        if(res.equals(findById())){
            System.out.println("Введите имя файла");
            String fileName = input.next();
            String lineSeparator = System.getProperty("line.separator");
            try {
                FileWriter fileForDatabase = new FileWriter(fileName, true);
                fileForDatabase.write(Integer.toString(user.getId()));
                fileForDatabase.append('\t');
                fileForDatabase.append('\t');
                fileForDatabase.write(user.getName());
                fileForDatabase.append('\t');
                fileForDatabase.append('\t');
                fileForDatabase.write(String.format("%.1f", user.getAverageMark()));
                fileForDatabase.append('\t');
                fileForDatabase.append('\t');
                fileForDatabase.write(user.isFree() + lineSeparator);
                fileForDatabase.flush();
                fileForDatabase.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Пользователь с таким id уже существует");
        }
    }

    public static void openFile() {
        Desktop desk = Desktop.getDesktop();
        Scanner input = new Scanner(System.in);
        System.out.println("Введите название файла");
        String fileName = input.next();
        File fileForDatabase = new File(fileName);
        if (fileForDatabase.exists()) {
            try {
                desk.open(fileForDatabase);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Такого файла не существует");
        }
    }

    public static void deleteFile() {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите название файла");
        String fileName = input.next();
        File fileForDatabase = new File(fileName);
        if (fileForDatabase.delete()) {
            System.out.println("Файл удален");
        } else {
            System.out.println("Файл не удален");
        }
    }

    public static void clearDatabase() {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите название файла");
        String fileName = input.next();
        String lineSeparator = System.getProperty("line.separator");
        try {
            FileWriter fileForDatabase = new FileWriter(fileName, false);
            PrintWriter fileForDatabaseToClear = new PrintWriter(fileForDatabase, false);
            fileForDatabaseToClear.flush();
            fileForDatabaseToClear.close();
            fileForDatabase.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter fileForDatabase = new FileWriter(fileName, true);
            fileForDatabase.write("id" + "\t\t" + "name" + "\t\t" + "averageMark" + "\t\t" + "isFree" + lineSeparator);
            fileForDatabase.flush();
            fileForDatabase.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUserById() {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите id пользователя");
        String userId = input.next();
        System.out.println("Введите название файла");
        String fileName = input.next();
        File fileForDatabase = new File(fileName);
        try {
            FileWriter fileForDeletions = new FileWriter("temp.txt", true);
            String lineSeparator = System.getProperty("line.separator");
            FileReader file = new FileReader(fileForDatabase);
            BufferedReader buffer = new BufferedReader(file);
            String line = buffer.readLine();
            while(line != null){
                String [] lines = line.split("\t\t");
                if(lines[0].equals(userId)){
                    line = buffer.readLine();
                    continue;
                }
                else {
                    fileForDeletions.write(line + lineSeparator);
                }
                line = buffer.readLine();
            }
            fileForDeletions.flush();
            fileForDeletions.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fileWriter = new FileWriter(fileName, false);
            PrintWriter pw = new PrintWriter(fileWriter, false);
            pw.flush();
            pw.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File source = new File("temp.txt");
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(fileForDatabase).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            sourceChannel.close();
            destChannel.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fw = new FileWriter("temp.txt", false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void deleteUserByValue() {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите поле пользователя, по которому хотите удалить");
        String userField = input.next();
        System.out.println("Введите значение поля пользователя, по которому хотите удалить");
        String value = input.next();
        System.out.println("Введите название файла");
        String fileName = input.next();
        File file = new File(fileName);
        try {
            FileWriter a = new FileWriter("temp.txt", true);
            String lSep = System.getProperty("line.separator");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while(line != null){
                String [] lines = line.split("\t\t");
                if((userField.equals("name") && lines[1].equals(value)) ||
                        (userField.equals("averageMark") && lines[2].equals(value)) ||
                        (userField.equals("isFree") && lines[3].equals(value))){
                    line = br.readLine();
                    continue;
                } else {
                    a.write(line + lSep);
                }
                line = br.readLine();
            }
            a.flush();
            a.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fw = new FileWriter(fileName, false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File source = new File("temp.txt");
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(file).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            sourceChannel.close();
            destChannel.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fw = new FileWriter("temp.txt", false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String findById() {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите id пользователя для поиска или проверки бд");
        String userId = input.next();
        System.out.println("Введите название файла");
        String fileName = input.next();
        File file = new File(fileName);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while(line != null){
                String [] lines = line.split("\t\t");
                if(lines[0].equals(userId)){
                    return line;
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Таких id нет";
    }

    public static void findByValue() {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите поле пользователя, по которому хотите найти");
        String userField = input.next();
        System.out.println("Введите значение поля пользователя, по которому хотите найти");
        String value = input.next();
        System.out.println("Введите название файла");
        String fileName = input.next();
        File file = new File(fileName);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while(line != null){
                String [] lines = line.split("\t\t");
                if((userField.equals("name") && lines[1].equals(value)) ||
                        (userField.equals("averageMark") && lines[2].equals(value)) ||
                        (userField.equals("isFree") && lines[3].equals(value))){
                    for(String s: lines){
                        System.out.print(s + " ");
                    }
                    System.out.println();
                    line = br.readLine();
                    continue;
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeValue() {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите название файла");
        String fileName = input.next();
        System.out.println("Введите id пользователя, у которого хотите заменить значение");
        String userId = input.next();
        System.out.println("Введите поле пользователя, которое хотите заменить");
        String field = input.next();
        System.out.println("Введите значение, которое хотите вставить");
        String value = input.next();
        File file = new File(fileName);
        try {
            FileWriter a = new FileWriter("temp.txt", true);
            String lSep = System.getProperty("line.separator");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while(line != null){
                String [] lines = line.split("\t\t");
                if(lines[0].equals(userId)){
                    if(field.equals("name")){
                        lines[1] = value;
                    } else if(field.equals("averageMark")){
                        lines[2] = value;
                    } else if(field.equals("isFree")) {
                        lines[3] = value;
                    }
                    a.write(lines[0] + "\t\t" + lines[1] + "\t\t" + lines[2] + "\t\t" + lines[3] + lSep);
                    line = br.readLine();
                }
                else {
                    a.write(line + lSep);
                    line = br.readLine();
                }
            }
            a.flush();
            a.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fw = new FileWriter(fileName, false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File source = new File("temp.txt");
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(file).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            sourceChannel.close();
            destChannel.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fw = new FileWriter("temp.txt", false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void copyFile() {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите название файла");
        String fileName = input.next();
        File source = new File(fileName);
        System.out.println("Создание файла копирования");
        File dest = createFile();
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(dest).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            sourceChannel.close();
            destChannel.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        System.out.println("1. Создать базу данных");
        Scanner inputOperation = new Scanner(System.in);
        String choice = inputOperation.next();
        if(choice.equals("1")) {
            createDatabase();
            System.out.println();
            System.out.println("Выберите операцию");
            System.out.println("1. Добавить пользователя");
            System.out.println("2. Удалить пользователя по id");
            System.out.println("3. Удалить пользователя по значению поля");
            System.out.println("4. Найти пользователя по id");
            System.out.println("5. Найти пользователя по значению поля");
            System.out.println("6. Редактировать значение поля пользователя");
            System.out.println("7. Открыть файл");
            System.out.println("8. Удалить базу данных");
            System.out.println("9. Очистить базу данных");
            System.out.println("10. Скопировать базу данных в другой файл");
            System.out.println("11. Создать базу данных");
            choice = inputOperation.next();
            while(true) {
                switch (choice) {
                    case ("1"):
                        addUser();
                        break;
                    case ("2"):
                        deleteUserById();
                        break;
                    case ("3"):
                        deleteUserByValue();
                        break;
                    case ("4"):
                        String result = findById();
                        System.out.println(result);
                        break;
                    case ("5"):
                        findByValue();
                        break;
                    case ("6"):
                        changeValue();
                        break;
                    case ("7"):
                        openFile();
                        break;
                    case ("8"):
                        deleteFile();
                        break;
                    case ("9"):
                        clearDatabase();
                        break;
                    case ("10"):
                        copyFile();
                        break;
                    case ("11"):
                        createDatabase();
                        break;
                }
                System.out.println();
                System.out.println("Выберите операцию");
                System.out.println("1. Добавить пользователя");
                System.out.println("2. Удалить пользователя по id");
                System.out.println("3. Удалить пользователя по значению поля");
                System.out.println("4. Найти пользователя по id");
                System.out.println("5. Найти пользователя по значению поля");
                System.out.println("6. Редактировать значение поля пользователя");
                System.out.println("7. Открыть файл");
                System.out.println("8. Удалить базу данных");
                System.out.println("9. Очистить базу данных");
                System.out.println("10. Скопировать базу данных в другой файл");
                System.out.println("11. Создать базу данных");
                choice = inputOperation.next();
            }
        }
    }
}
