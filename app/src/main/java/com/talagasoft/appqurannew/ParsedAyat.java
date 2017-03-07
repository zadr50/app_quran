package com.talagasoft.appqurannew;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by compaq on 09/09/2015.
 */
public class ParsedAyat extends DefaultHandler{
    private boolean in_ke_open=false;
    private Integer m_ke=0;
    private boolean in_gambar_open=false;
    private String m_gambar=new String();
    private boolean in_arti_open=false;
    private String m_arti=new String();
    private boolean in_suara_open=false;
    private String m_suara=new String();
    private boolean in_ayat_open=false;
    ArrayList<Ayat> m_ayat=new ArrayList<>();

    public ArrayList<Ayat> getAyatList(){
        return m_ayat;
    }

        @Override
        public void setDocumentLocator(Locator locator) {

        }

        @Override
        public void startDocument() throws SAXException {
        }

        @Override
        public void endDocument() throws SAXException {

        }

        @Override
        public void startPrefixMapping(String s, String s1) throws SAXException {

        }

        @Override
        public void endPrefixMapping(String s) throws SAXException {

        }

        @Override
        public void startElement(String s, String s1, String s2, Attributes attributes) throws SAXException {
            if(s1.equals("ke")){
                in_ke_open=true;
            }  if (s1.equals("gambar")) {
                in_gambar_open=true;
            }  if (s1.equals("arti")){
                in_arti_open = true;
            }  if (s1.equals("suara")){
                in_suara_open=true;
            }  if (s1.equals("ayat")){
                in_ayat_open=true;
            }
        }

        @Override
        public void endElement(String s, String s1, String s2) throws SAXException {
            if(s1.equals("ke")){
                in_ke_open=false;
            }  if (s1.equals("gambar")) {
                in_gambar_open=false;
            }  if (s1.equals("arti")){
                in_arti_open = false;
            }  if (s1.equals("suara")){
                in_suara_open=false;
            }  if(s1.equals("ayat")){
                in_ayat_open=false;

                Ayat oAyat=new Ayat();
                oAyat.setKe(m_ke);
                oAyat.setArti(m_arti);
                oAyat.setSuara(m_suara);
                oAyat.setGambar(m_gambar);
                //Log.e("AppQuran","Ke: "+m_ke+ " - "+m_arti);
                m_ayat.add(oAyat);


            }
       }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if(this.in_ke_open){
                m_ke=Integer.valueOf(new String(ch, start, length));
            } else if(this.in_gambar_open){
                m_gambar=new String(ch, start, length);
            } else if(this.in_suara_open){
                m_suara=new String(ch, start, length);
            } else if(this.in_arti_open){
                m_arti=new String(ch, start, length);
            }
        }

        @Override
        public void ignorableWhitespace(char[] chars, int i, int i1) throws SAXException {

        }

        @Override
        public void processingInstruction(String s, String s1) throws SAXException {

        }

        @Override
        public void skippedEntity(String s) throws SAXException {

        }

}


