package com.talagasoft.quran.appquran;

import android.content.Context;
import android.util.Log;
import android.view.Display;

import java.util.ArrayList;

/**
 * Created by compaq on 09/06/2015.
 */
public class Surat {
    private Integer nomor=0;
    private String arti=new String();
    private String suara=new String();
    private Integer jumlah=0;
    private String judul=new String();
    private String surat=new String();

    public void setNomor(Integer nomor){this.nomor = nomor;}
    public Integer getNomor(){return this.nomor;}
    public void setSurat(String surat){this.surat = surat;}
    public String getSurat(){return this.surat;}
    public void setJudul(String judul){this.judul = judul;}
    public String getJudul(){return this.judul;}
    public void setArti(String arti){this.arti = arti;}
    public String getArti(){return this.arti;}
    public void setSuara(String suara){this.suara = suara;}
    public String getSuara(){return this.suara;}
    public void setJumlah(Integer jumlah){this.jumlah = jumlah;}
    public Integer getJumlah(){return this.jumlah;}

    public String load_as_html(Context context, int number) {
        Xml xml=new Xml();
        ParsedAyat parsed_ayat=new ParsedAyat();
        String filename=new String();
        String fld=new String();
        fld=String.format("%03d", number);
        filename= new StringBuilder().append(fld).append("/ayat.xml").toString();
        xml.parse(context,filename,parsed_ayat);
        StringBuilder retval=new StringBuilder();
        ArrayList<Ayat> ayat_list=parsed_ayat.getAyatList();
        Ayat oAyat=new Ayat();
        String img=new String();
        retval.append("<body>");
        for(int i=0;i<ayat_list.size();i++){
            oAyat=ayat_list.get(i);
            img=fld+"/"+number+"_"+oAyat.getKe()+".png";
//            retval.append("<h1>Ayat Ke: ").append(oAyat.getKe()).append("</h1>");
            retval.append("<img src='").append(img).append("'  style='width:95%'/>");
            retval.append("<p>").append(oAyat.getArti()).append("</p>");
        }
        retval.append("</body>");
        return retval.toString();
    }
}
