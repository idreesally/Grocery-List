package seclass.qc.edu.glm.Util;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

import seclass.qc.edu.glm.Model.SearchEntry;

/**
 * Created by kraigslist on 4/19/2018.
 */

public class ParserSupermarket {

    private static final String TAG = "ParseApplications";
    private static ArrayList<SearchEntry> supermarketProducts;

    public ParserSupermarket() {
        this.supermarketProducts = new ArrayList<>();
    }

    public static ArrayList<SearchEntry> getSupermarketProducts() {
        return supermarketProducts;
    }

    public boolean parse(String xmlData){
        boolean status = true;
        SearchEntry currentRecord = null;
        boolean inEntry = false;
        String textValue = "";

        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(xmlData));
            int eventType = xpp.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){
                String tagName = xpp.getName();
                switch (eventType){

                    case XmlPullParser.START_TAG:
                        Log.d(TAG, "parse: Starting tag for " + tagName);
                        if("Product".equalsIgnoreCase(tagName)) {
                            inEntry = true;
                            currentRecord = new SearchEntry();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        Log.d(TAG, "parse: Ending tag for " + tagName);
                        if(inEntry){
                            if("Product".equalsIgnoreCase(tagName)) {
                                supermarketProducts.add(currentRecord);
                                inEntry = false;
                            } else if ("Itemname".equalsIgnoreCase(tagName)){
                                currentRecord.setItemName(textValue);
                            } else if ("ItemDescription".equalsIgnoreCase(tagName)){
                                currentRecord.setItemDescription(textValue);
                            } else if ("ItemCategory".equalsIgnoreCase(tagName)) {
                                currentRecord.setItemCategory(textValue);
                            } else if ("ItemID".equalsIgnoreCase(tagName)){
                                currentRecord.setItemID(textValue);
                            } else if ("ItemImage".equalsIgnoreCase(tagName)){
                                currentRecord.setItemImage(textValue);
                            }
                        }
                        break;

                    default:
                        //Nothing else to do.
                }
                eventType = xpp.next();

            }

            for(SearchEntry app: supermarketProducts){
                Log.d(TAG, "***************");
                Log.d(TAG, app.toString());
            }

        } catch (Exception e){
            status = false;
            e.printStackTrace();
        }

        return status;
    }

}
