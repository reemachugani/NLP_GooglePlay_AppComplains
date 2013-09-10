import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GenerateXML {

    public void xml() {

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            int length = Crawler.reviewList.size();

            // root element
            Document document = docBuilder.newDocument();
            Element rootElement = document.createElement("Reviews");
            document.appendChild(rootElement);

            Element doc;
            Element stars;
            Element review;

            // generating doc elements for all reviews
            for(int i = 0; i < length; i++){
                doc = document.createElement("doc");
                rootElement.appendChild(doc);
                doc.setAttribute("id", Integer.toString(Crawler.reviewList.get(i).getDocID()));

                stars = document.createElement("rating");
                stars.appendChild(document.createTextNode(Integer.toString(Crawler.reviewList.get(i).getRating())));
                doc.appendChild(stars);

                review = document.createElement("review");
                review.appendChild(document.createTextNode(Crawler.reviewList.get(i).getReview()));
                doc.appendChild(review);

            }
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("src/reviews.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

}