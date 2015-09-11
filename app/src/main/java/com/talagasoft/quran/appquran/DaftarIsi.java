package com.talagasoft.quran.appquran;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by compaq on 09/06/2015.
 */
public class DaftarIsi extends DefaultHandler {
    private boolean in_surat_open = false;
    private boolean in_nomor_open=false;
    private boolean in_judul_open=false;
    private boolean in_arti_open=false;
    private boolean in_suara_open=false;
    private boolean in_jumlah_open=false;

    private String surat=new String();
    private String judul=new String();
    private String arti=new String();
    private String suara=new String();
    private Integer nomor=0;
    private Integer jumlah=0;

    ArrayList<Surat> m_surat=new ArrayList<>();

    public void addSurat(){
        Surat osurat=new Surat();
        osurat.setSurat(surat);
        osurat.setNomor(nomor);
        osurat.setJudul(judul);
        osurat.setArti(arti);
        osurat.setSuara(suara);
        osurat.setJumlah(jumlah);
        Log.d("AppQuran", "addSurat.Judul=" + osurat.getJudul());
        m_surat.add(osurat);
    }

    public String[] getSuratList(){
        Surat surat;
        Log.d("AppQuran", "size:" + m_surat.size());
        String[] retval=new String[m_surat.size()];
        for(int i=0;i<m_surat.size();i++){
            surat=m_surat.get(i);
            retval[i]=surat.getJudul();
        }
        Log.d("AppQuran", "retval.size()=" + retval.length + ", retval[0]=" + retval[0]);
        return retval;
    }
    public String getJudul(int nomor){
        if(m_surat.size()==0)nomor=0;
        return m_surat.get(nomor).getJudul();
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
        if (s1.equals("surat")) {
            this.in_surat_open = true;
        }  if (s1.equals("nomor")) {
            this.in_nomor_open = true;
        }  if (s1.equals("judul")) {
            this.in_judul_open=true;
        }  if (s1.equals("arti")) {
            this.in_arti_open=true;
        }  if (s1.equals("suara")) {
            this.in_suara_open=true;
        }
    }

    @Override
    public void endElement(String s, String s1, String s2) throws SAXException {
       if (s1.equals("nomor")) {
            this.in_nomor_open = false;
        }  if (s1.equals("judul")) {
            this.in_judul_open=false;
        }  if (s1.equals("arti")) {
            this.in_arti_open=false;
        }  if (s1.equals("suara")) {
            this.in_suara_open=false;
        }  if (s1.equals("jumlah")) {
            this.in_jumlah_open=false;
        }   if (s1.equals("surat")) {
           this.in_surat_open = false;
           this.addSurat();
       }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(this.in_nomor_open) {
            nomor=Integer.valueOf(new String(ch, start, length));
        }  if (this.in_surat_open){
            surat=new String(ch, start, length);
            Log.d("AppQuran","characters: surat: "+surat);
        }  if (this.in_judul_open){
            judul=new String(ch, start, length);
        }  if (this.in_arti_open){
            arti=new String(ch, start, length);
        }  if (this.in_suara_open){
            suara=new String(ch, start, length);
        }  if (this.in_jumlah_open){
            jumlah=Integer.valueOf(new String(ch, start, length));
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
        Log.d("AppQuran","skippedEntity: "+s);
    }

}
