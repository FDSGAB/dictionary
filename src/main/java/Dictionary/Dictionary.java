package Dictionary;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        System.out.print("検索したい言葉を教えてください: ");
        String searchWord = sc.nextLine();

        final String url = "https://www.weblio.jp/content/" + searchWord;

        try {
            final Document document = Jsoup.connect(url).get();

            //System.out.println(document.outerHtml());

            /*
            Creates a list to gather each title of every entry
             */

            List<String> titles = new ArrayList<>();

            for (Element title : document.select("h2.midashigo")) {     //Need to put the outer label of the class, in this case "h2", followed by a "."

                final String entry = title.text();
                titles.add(entry);
                //System.out.println(entry);

            }


            /*
            Creates a list for the descriptions of each entry
            Creates a list to count the number of different meanings in an entry.
             */

            List<String> descriptions = new ArrayList<>();
            List<Integer> numbers = new ArrayList<>();


            for (Element description : document.select("div.Sgkdj")) {     //Need to put the outer label of the class, in this case "h2", followed by a "."

                final String explanation = description.text();
                //System.out.println(explanation);
                descriptions.add(explanation);


                final String[] numberList = description.select("b").text().split("\\s+");
                final int listSize = numberList.length;

                if (Objects.equals(numberList[0], "")) {
                    numbers.add(1);   //List append 1 because there is only one definition
                }
                else {
                    for (String s : numberList) {
                        try {
                            final int number = Integer.parseInt(s);
                            numbers.add(number);
                            //System.out.println(number);
                        } catch (NumberFormatException e) {
                            continue;
                        }

                    }
                }

            }


            /*
            Gets the correct number of entries of a word.
             */
            for (int n = 0; n < numbers.size(); n++) {
                try {
                    if (descriptions.get(n).getClass().equals(String.class)) {
                        continue;
                    }
                } catch (Exception r) {
                    numbers.add(n);
                    break;
                }
            }
            try {
               /*
            Prints each title
            followed by each description.
             */
                for (int j = 0; j < numbers.get(numbers.size()-1); j++) {
                    System.out.println(titles.get(j));
                    System.out.println(descriptions.get(j));

                }
                System.out.println(searchWord +"という言葉はきっと面白いですね");
            }
            catch (Exception x) {
                System.out.println(searchWord +"という言葉は見つかれなかったです");
            }


        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }
}

