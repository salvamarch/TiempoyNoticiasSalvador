package com.example.usuario.tiempoynoticiassalvador;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class tiempo extends AppCompatActivity {
Button mos;
ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiempo);
        mos=findViewById(R.id.mos);
        lista=findViewById(R.id.lista);

        mos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DescargarXML descargarXML = new DescargarXML();
                descargarXML.execute("consultaXML.php");
            }
        });

    }

    private class DescargarXML extends AsyncTask<String, Void, Void> {

        List<String> list = new ArrayList<>();
        @Override
        protected Void doInBackground(String... strings) {
            String script = strings[0];
            String url = SERVIDOR+ script;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try{
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(new URL(url).openStream());

                Element raiz = doc.getDocumentElement();
                System.out.println("Raíz: " + ((Element) raiz).getNodeName());
                NodeList hijos = raiz.getChildNodes();

                for (int i = 0; i < hijos.getLength(); i++) {
                    Node nodo = hijos.item(i);
                    if (nodo instanceof Element) {
                        NodeList nietos = nodo.getChildNodes();

                        System.out.println("Nietos:" + nietos.getLength());

                        String registro = "";
                        for (int j = 0; j < nietos.getLength(); j++) {
                            if (nietos.item(j) instanceof Element) {

                                System.out.println("" + nietos.item(j).getNodeName() + " " + nietos.item(j).getTextContent());
                                registro+=" " + nietos.item(j).getNodeName() + " " + nietos.item(j).getTextContent();
                            }
                        }
                        list.add(registro);
                    }
                }

            } catch (ParserConfigurationException ex) {
            } catch (MalformedURLException ex) {
            } catch (IOException ex) {
            } catch (SAXException ex) {
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ArrayAdapter<String> adapter;

            adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,list);
            lista.setAdapter(adapter);
            ProgressDialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ProgressDialog= new ProgressDialog(MainActivity.this);
            ProgressDialog.setIndeterminate(true);
            ProgressDialog.setTitle("Descargando datos...");
            ProgressDialog.show();

        }
    }

    public void inicio(View view) {
        Intent i = new Intent(this, MainActivity.class );
        startActivity(i);
    }
}
