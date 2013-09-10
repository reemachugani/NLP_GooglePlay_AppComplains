import java.io.*;
import java.util.*;

public class Review{
    int docID;
    int rating;
    String review;
//    int cmp_class;

    public Review(){
        docID = 0;
        rating = 0;
        review = "";
//        cmp_class = 0;
    }

    public void setFields(int docID, int rating, String review){
        this.docID = docID;
        this.rating = rating;
        this.review = review;
    }

//    public void setConfidence(double confidence){
//        this.confidence = confidence;
//    }

    public String getReview(){
        return this.review;
    }

    public int getRating(){
        return this.rating;
    }

    public int getDocID(){
        return this.docID;
    }

    public void printReviews(){
        System.out.println("Document : " + this.docID);
        System.out.println("Stars : " + this.rating);
        System.out.println("Review : " + this.review);
        System.out.println("----------------------------------");
        System.out.println();
    }
}