public class Review{
    int docID;
    int rating;
    String text;
    String date;
    String source;
    String title;
//    int cmp_class;

    public Review(){
        docID = 0;
        rating = 0;
        text = "";
//        cmp_class = 0;
    }

    public void setFields(int docID, int rating, String review){
        this.docID = docID;
        this.rating = rating;
        this.text = review;
    }

//    public void setConfidence(double confidence){
//        this.confidence = confidence;
//    }

    public String getText(){
        return this.text;
    }

    public int getRating(){
        return this.rating;
    }

    public int getDocID(){
        return this.docID;
    }

    public String getDate(){
        return this.date;
    }

    public String getSource(){
        return this.source;
    }

    public String getTitle(){
        return this.title;
    }

    public void printReviews(){
        System.out.println("Document : " + this.docID);
        System.out.println("Stars : " + this.rating);
        System.out.println("Review : " + this.text);
        System.out.println("----------------------------------");
        System.out.println();
    }
}