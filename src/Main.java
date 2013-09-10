import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException{
        File file = new File("src/urls.txt");
        Crawler crawler = new Crawler();
        crawler.crawl();
        GenerateXML generateXML = new GenerateXML();
        generateXML.xml();
    }
}
