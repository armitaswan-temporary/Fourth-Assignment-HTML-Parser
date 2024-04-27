import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Parser {
    static List<Country> countries = new ArrayList<>();

    public List<Country> sortByName(){
        List<Country> sortedByName = new ArrayList<>(countries);
        Collections.sort(sortedByName, new CountryComparator(1));
        return  sortedByName;
    }

    public List<Country> sortByPopulation(){
        List<Country> sortedByPopulation = new ArrayList<>(countries);
        Collections.sort(sortedByPopulation, new CountryComparator(2));
        return sortedByPopulation;
    }

    public List<Country> sortByArea(){
        List<Country> sortedByArea = new ArrayList<>(countries);
        Collections.sort(sortedByArea, new CountryComparator(3));
        return sortedByArea;
    }

    public static String searchC(String name) {
        for (int i = 0; i < countries.size(); i++) {
            if (countries.get(i).getName().equals(name)) {
                return countries.get(i).toString();
            }
        }
        return "Country not found";
    }

    public static void setUp() {

        //loading the HTML file
        File htmlFile = new File("src\\Resources\\country-list.html");

        //Parse the HTML file using Jsoup
        Document document = null;
        try {
            document = Jsoup.parse(htmlFile, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Extract data from the HTML
        Elements divs = document.selectFirst("section#countries").select("div.col-md-4.country");

        // Iterate through each country div to extract country data
        for (Element div : divs) {
            String name = div.select(".country-name").text();
            String capital = div.select(".country-capital").text();
            int population = Integer.parseInt(div.select(".country-population").text());
            double area = Double.parseDouble(div.select(".country-area").text());
            Country country = new Country(name, capital, population, area);
            countries.add(country);
        }
    }

    public static void main(String[] args) {
        //you can test your code here before you run the unit tests ;)
        setUp();
        Scanner scanner = new Scanner (System.in);
        while (true) {
            System.out.println ("\n\n\n\n\n\n\n\n\n\n");
            System.out.println ("1- view all countries   2- search");
            System.out.print ("choose: ");
            int choice = scanner.nextInt();
            boolean flag1 = true;
            while (flag1) {
                if (choice == 1) {
                    boolean show = true;
                    int i = 0;
                    while (show && i < countries.size() - 10) {
                        for (int j = i; j < i + 10; j++) {
                            countries.get(j).toString();
                        }
                        i += 10;
                        if (i < countries.size()) {
                            System.out.println ("1- show more   2- exit");
                            int choice2 = scanner.nextInt();
                            if (choice2 == 1) {
                                continue;
                            }
                            else {
                                show = false;
                                break;
                            }
                        }
                    }
                }
                else if (choice == 2) {
                    System.out.println ("what country are you looking for?");
                    String choice2 = scanner.nextLine();
                    System.out.println (searchC(choice2));
                }
                else {
                    break;
                }
            }
        }
    }
}
