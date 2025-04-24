import java.io.*;
import  java.time.*;
import java.util.Scanner;

public class App {
    static File file;
    static Scanner scanner = new Scanner(System.in);
    static BufferedReader reader;
    static BufferedWriter writer;
    static String filePath = "src/main/java/";

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now(); //getting today's date.
        // For taking in inputs from the users and showing the outputs when needed.

        try{

            int choice;
            do{
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
            }while(true);


        }catch (Exception e){
            System.out.println("Error:"+ e);
        }

    }


    private static void writeDiary(LocalDate date){
        System.out.print("Enter your pen Name for your Diary:");
        String name = scanner.nextLine();
        file = new File(filePath+String.valueOf(date));
        try{
            String userInput = "";
            if(file.createNewFile()) {
                System.out.println("Creating a file with the current date.");
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
            System.out.println("File written to " + file);
            writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    private static void readDiary() {
        file = new File(filePath);
        String[] files = file.list();

        if(files == null || files.length == 0){
            System.out.println("No Diaries right now, create one to read it!");
            return;
        }

        System.out.print("Files:");
        System.out.println("-----------------------------------------");
        for(String fileName:files){
            System.out.println(fileName);
        }
        System.out.println("-----------------------------------------");

        System.out.println("Choose a Diary Date to read:");
        String userChoice = scanner.nextLine();

        try{
            if(userChoice.equals(file.getName())){
                String fullFilePath = filePath +"/" + userChoice;
                reader = new BufferedReader(new FileReader(fullFilePath));
                while((reader.readLine()!=null)){
                    System.out.println(reader.readLine());
                }
                reader.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteDiary() {
        System.out.println("Writing");
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




