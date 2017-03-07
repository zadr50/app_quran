package com.talagasoft.appqurannew;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class SuratActivity extends AppCompatActivity  {
    Xml oIsi;
    DaftarIsi daftar_isi;
    private CharSequence mTitle;
    ListView lstSurat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surat_activity);
        lstSurat=(ListView)findViewById(R.id.lstSurat);
        daftar_isi=new DaftarIsi(this.getBaseContext());
        oIsi = new Xml(this.getBaseContext());
        oIsi.parse("toc.xml", daftar_isi);
        mTitle = getTitle();

        lstSurat.setAdapter(daftar_isi.getAdapter());

    }

}
