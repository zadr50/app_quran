package com.talagasoft.appqurannew;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
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
    Context context;
    private LayoutInflater inflater;
    AssetManager assetManager;

    public DaftarIsi(Context baseContext) {
        this.context=baseContext;
        this.assetManager= this.context.getAssets();
    }

    public void addSurat(){
        Surat osurat=new Surat(this.context);
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

    public ListAdapter getAdapter() {


        ListAdapter lst=new ListAdapter() {
            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEnabled(int i) {
                return false;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public int getCount() {
                return m_surat.size();
            }

            @Override
            public Object getItem(int i) {
                return m_surat.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                if (inflater == null)
                    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                if (view == null)
                    view = inflater.inflate(R.layout.surat_row, null);
                Surat surat=m_surat.get(i);
                final int ke=surat.getNomor();
                TextView nama_surat=(TextView)view.findViewById(R.id.nama_surat);
                nama_surat.setFocusable(false);
                nama_surat.setText(surat.getJudul());
                final String n= (String) nama_surat.getText();
                final Context privateContext=context;

                TextView arti_surat=(TextView)view.findViewById(R.id.arti_surat);
                arti_surat.setText(surat.getArti());
                arti_surat.setFocusable(false);
                ImageView img=(ImageView)view.findViewById(R.id.imageView);
                String fld=String.format("%03d", surat.getNomor());
                img.setImageBitmap(getBitmapFromAssets(fld+"/surat.jpg"));
                img.setFocusable(false);
                LinearLayout btnRow=(LinearLayout)view.findViewById(R.id.btnRow);
                btnRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
///                        Toast.makeText(context,"Surat ke: " + String.valueOf(ke),Toast.LENGTH_LONG).show();
                        DisplaySurat(ke,n);

                    }
                });

                return view;
            }
            @Override
            public int getItemViewType(int i) {
                return i;
            }

            @Override
            public int getViewTypeCount() {
                return m_surat.size();
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        };

        return lst;
    }

    private void DisplaySurat(int ke, String n) {
        Intent intent = new Intent("SuratWebView");
        intent.putExtra("surat_ke", ke);
        intent.putExtra("nama_surat", n);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public Bitmap getBitmapFromAssets(String fileName) {
        InputStream istr = null;
        try {
            istr = assetManager.open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(istr);

        return bitmap;
    }

};
