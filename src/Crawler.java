import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.reflect.TypeToken;
import com.google.gson.*;

import java.io.*;
import java.util.*;
import java.net.*;


public class Crawler{

    public static ArrayList<Review> reviewList = new ArrayList<Review>();
    public static int docIDCounter = 0;
    public static int pagesToCrawl = 50;
    public static String packageName = "com.whatsapp";
    public static String pageUrl = "https://play.google.com/store/getreviews";


    public void crawl() throws IOException{

        try{

            String urlParameters = "reviewType=0&pageNum="+pagesToCrawl+"&id="+packageName+"&reviewSortOrder=2&xhr=1";
            URL url = new URL(pageUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);

            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

            writer.write(urlParameters);
            writer.flush();

            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            int i=0;

            while ((line = reader.readLine()) != null) {
                ++i;
                if(i==3)
                    break;
            }

            int startIdx = line.indexOf('1')+3;
            int endIdx = line.lastIndexOf(',')-1;

            line = line.substring(startIdx, endIdx).trim();

            String json = "{\"foo\" : \"" + line + "\" }";
            Map<String, String> map = new Gson().fromJson(json, new TypeToken<Map<String, String>>() {}.getType());
            String htmlText = map.get("foo");


            if(pageUrl != null){
                Document doc = Jsoup.parse(htmlText);

                Elements reviews = doc.getElementsByClass("single-review");
                for(Element singleReview : reviews){
                    Review revObj = new Review();
                    revObj.docID = ++docIDCounter;

                    Element reviewBody = singleReview.getElementsByClass("review-body").first();
                    String reviewText = reviewBody.text();
                    revObj.review = reviewText;

                    String reviewRating = singleReview.getElementsByClass("current-rating").first().attr("style");
                    int len = reviewRating.length();
                    if(reviewRating.charAt(len-7) == ' ')
                        reviewRating = reviewRating.substring(len-6, len-4);
                    else
                        reviewRating = reviewRating.substring(len-7, len-4);
                    int stars = Integer.parseInt(reviewRating)/20;
                    revObj.rating = stars;

                    reviewList.add(revObj);
                }
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}