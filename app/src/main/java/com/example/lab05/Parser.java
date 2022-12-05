package com.example.lab05;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Parser {
    public static String getRateFromECB(InputStream stream) throws IOException {
        Log.d("Parser", "getRateFromECB method started");
        String result = "";
        try {
            DocumentBuilderFactory xmlDocFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder xmlDocBuilder = xmlDocFactory.newDocumentBuilder();
            Document doc = xmlDocBuilder.parse(stream);

            NodeList rateNodes = doc.getElementsByTagName("Cube");
            for (int i = 0; i < rateNodes.getLength(); ++i) {
                Element cube = (Element) rateNodes.item(i);
                if(cube.hasAttribute("currency")){
                    String currencyName = cube.getAttribute("currency");
                    String rate = cube.getAttribute("rate");
                    result += currencyName + "       " + rate + '\n';
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            Log.e("Parser Error", e.toString());
        } catch (SAXException e) {
            e.printStackTrace();
            Log.e("Parser Error", e.toString());
        }
        return result;
    }
}