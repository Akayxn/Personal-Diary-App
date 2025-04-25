import java.io.*;
import  java.time.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    static File file;
    static Scanner scanner = new Scanner(System.in);
    static BufferedReader reader;
    static BufferedWriter writer;
    static String filePath = "src/main/java/";

    public static void main(String[] args) throws InputMismatchException {
        LocalDate localDate = LocalDate.now(); //getting today's date.
        // For taking in inputs from the users and showing the outputs when needed.

            do{
                try{
                    int choice;
                    UserMenu();
                    System.out.print("What do you want to do?:");
                    choice = scanner.nextInt();
                    scanner.nextLine(); //taking up the extra space created
                    switch (choice){
                        case 1:
                            writeDiary(localDate);
                            break;
                        case 2:
                            readDiary();
                            break;
                        case 3:
                            deleteDiary();
                            break;
                        case 4:
                            System.out.println("Diary Closed!");
                            System.exit(444);
                        default:
                            System.out.println("Invalid! Enter a correct option");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter the number for choices! ");
                    scanner.nextLine();
                }
            }while(true);
    }


    private static void writeDiary(LocalDate date){
        System.out.print("Enter your pen Name for your Diary:");
        String name = scanner.nextLine();
        file = new File(filePath+String.valueOf(date));
        try{
            String userInput = "";
            if(file.createNewFile()) {
                System.out.println("Creating a page with the current date.");
            }
            else {
                System.out.println("You have already made a Diary today.");
                return;
            }
            writer = new BufferedWriter(new FileWriter(file,false));

            System.out.print("Enter your contents (Type diary.close to exit):");
            writer.write(String.valueOf(date)+":");
            writer.newLine();
            while(true){
                userInput = scanner.nextLine();
                if(userInput.equals("diary.close")){
                    break;
                }
                writer.write(userInput);
                writer.newLine();
                }
            writer.write("By: "+name);
            System.out.println("Diary written in: " + file);
            writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    private static void readDiary() {
        file = new File(filePath);
        ArrayList<String> fileArrayList = new ArrayList<>();

        fileArrayList = listFiles();

        System.out.print("Choose a Diary Date to read:");
        String userChoice = scanner.nextLine().trim();

        try{
            String line;

            if(fileArrayList.contains(userChoice)){
                String fullFilePath = filePath +"/" + userChoice;
                reader = new BufferedReader(new FileReader(fullFilePath));
                while((line = reader.readLine())!=null){
                    System.out.println(line);
                }
                reader.close();
            }
            else{
                System.out.println("No Diary Page with that date exists! Please try again.");
                return;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteDiary() {
        file = new File(filePath);
        ArrayList<String> fileArrayList = new ArrayList<>();

        fileArrayList = listFiles();

        System.out.print("Choose a Diary Date to delete:");
        String userChoice = scanner.nextLine().trim();

        try{
            String line;
            if(fileArrayList.contains(userChoice)) {
                File fullFilePath = new File(file + "/" + userChoice);
                if (fullFilePath.delete()) {
                    System.out.println("Diary Page with the date " + userChoice + " scrapped successfully.");
                }
            }
            else{
                System.out.println("No Diary Page with that Date exists! Please try again.");
                return;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static ArrayList<String> listFiles(){
        file = new File(filePath);
        String[] files = file.list();

        if(files == null || files.length == 0){
            System.out.println("No Diaries right now, create one to read it!");
            return null;
        }

        System.out.println("Files:");
        System.out.println("-----------------------------------------");
        ArrayList<String> fileNamesArray = new ArrayList<>();
        for(String fileName:files){
            int i = 1 ;
            if(fileName.startsWith("20")){ // only shows diaries from that directory.
                System.out.println(String.valueOf(i) + ". " +fileName);
                fileNamesArray.add(fileName);
            }
            i++;
        }
        System.out.println("-----------------------------------------");
        return fileNamesArray;
    }


    private static void UserMenu(){
        System.out.println("===============MENU=================");
        System.out.println("1. Write a Diary.");
        System.out.println("2. Read the Diary.");
        System.out.println("3. Delete the Diary.");
        System.out.println("4. Exit.");
        System.out.println("===================================\n");
    }


}




