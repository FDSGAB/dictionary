package Dictionary;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;


public class Dictionary {


    public static void main(String[] args){
        /*
        Basic input Function:
        1. Creates variable to save the string
        2. System writes something for the user
        3.Receives the input from the user
         */
        Scanner sc= new Scanner(System.in);
        System.out.print("言葉を教えてください: ");
        String searchWord = sc.nextLine();

        final String url = "https://www.weblio.jp/content/" + searchWord;

        try {
            final Document document = Jsoup.connect(url).get();

            System.out.println(document.outerHtml());

        }
        catch (Exception ex){
            ex.printStackTrace();

        }

    }
}

