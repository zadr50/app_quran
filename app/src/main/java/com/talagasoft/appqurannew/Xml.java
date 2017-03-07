package com.talagasoft.appqurannew;
import android.content.Context;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by compaq on 09/06/2015.
 */
public class Xml {

    //DaftarIsi myXMLHandler = new DaftarIsi();
    Context context;
    public Xml(Context baseContext){
        this.context=baseContext;
    }


    public void parse(String xml_file,ContentHandler myXmlHandler) {
        String isi_xml=ReadFromfile(xml_file, this.context);

//        Log.d("AppQuran", "getTocList isi_xml: " + isi_xml);
        XMLReader xr = null;
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            xr = sp.getXMLReader();

            xr.setContentHandler(myXmlHandler);
            InputSource inStream = new InputSource();
            inStream.setCharacterStream(new StringReader(isi_xml.toString()));
            xr.parse(inStream);

        } catch (Exception e) {
            System.out.println("XML Pasing Excpetion = " + e);
        }
    }

    public String ReadFromfile(String fileName, Context context) {
        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = context.getResources().getAssets()
                    .open(fileName, Context.MODE_WORLD_READABLE);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            String line = "";
            while ((line = input.readLine()) != null) {
                returnString.append(line);
            }

        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fIn != null)
                    fIn.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        return returnString.toString();
    }
}
