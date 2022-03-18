/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package quotes;

import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class App {


    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Plz select the \'Online\' or \'Local\'");
        String input =scanner.nextLine();


        try {
            if (input.equals("Online")) {
                quoteUrl();
            } else if (input.equals("Local")) {
                quoteFile();
            } else {
                System.out.println("Wrong input");
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }



     }

     public static void quoteFile(){
                 try {
            Scanner scanner =new Scanner(System.in);
            Gson gson =new Gson();

            Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\ghl1590\\java\\quotes\\resources\\book.json"));
            System.out.println("done");
            BookContact[] bookContact = gson.fromJson(reader,BookContact[].class); //https://howtodoinjava.com/gson/gson-parse-json-array/

            random(bookContact);

        }catch (IOException exception){
            System.err.println(exception.getMessage());
        }
     }

     public static void quoteUrl() throws IOException{

         URL quoteUrl = new URL("http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=en");

         HttpURLConnection quoteHttp = (HttpURLConnection) quoteUrl.openConnection();
         quoteHttp.setRequestMethod("GET");

         InputStreamReader quoteReader = new InputStreamReader(quoteHttp.getInputStream());
         BufferedReader quoteBufferedReader = new BufferedReader(quoteReader);

         // another way
//         BufferedReader quotBReader = new BufferedReader(new InputStreamReader(quoteUrl.openStream()));

         String data = quoteBufferedReader.readLine();

         Gson gson = new Gson();
         Quote quote = gson.fromJson(data , Quote.class);
         System.out.println(quote);

         File quotFile = new File("C:\\Users\\ghl1590\\java\\quotes\\resources\\quote.json");
         try(FileWriter quoteFileWriter = new FileWriter(quotFile)){
             gson.toJson(quote, quoteFileWriter);
         }
     }

     public static void random  (BookContact[] bookContacts){
         int random = (int)Math.floor(Math.random()*(100-1+1)+1);
         int count = 0;


         for (BookContact val:bookContacts
              ) {
             if(count == random && !Objects.equals(val.text, "")){
                 System.out.println(val.text);
             }
             count++;

         }

     }

     public static String searchByAuthor(BookContact[] bookContacts, String author){

         for (BookContact val:bookContacts
              ) {
             if(val.author.equals(author)){
                return val.text;
             }
         }
         return "not found";
     }

     public static String searchByWord(BookContact[] bookContacts, String word){
         for (BookContact val:bookContacts
         ) {
             if(val.text.contains(word)){
                return val.text;

             }
         }
         return "not found";
     }
}
