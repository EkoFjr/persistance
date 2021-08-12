package com.example.datapersistance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Ekstenal extends AppCompatActivity implements View.OnClickListener{
    public static final String FILENAME = "kamifile.txt";
    Button buatFile,ubahFile,bacaFile,deleteFile;
    TextView textBaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekstenal);

        buatFile = findViewById(R.id.buatfile);
        ubahFile = findViewById(R.id.ubahfile);
        bacaFile = findViewById(R.id.bacafile);
        deleteFile = findViewById(R.id.hapusfile);
        textBaca = findViewById(R.id.textbaca);

        buatFile.setOnClickListener(this);
        ubahFile.setOnClickListener(this);
        bacaFile.setOnClickListener(this);
        deleteFile.setOnClickListener(this);
    }

    void buatFile()
    {
        String isiFile = "Coba Isi Data File Text";
        String state = Environment.getExternalStorageState();

        if (!Environment.MEDIA_MOUNTED.equals(state))
        {
            return;
        }

        File file = new File(Environment.getExternalStorageDirectory(),FILENAME);

        FileOutputStream outputStream = null;
        try
        {
            file.createNewFile();
            outputStream = new FileOutputStream(file,true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    void ubahFile ()
    {
        String ubah = "Update Isi Data File Text";
        String state = Environment.getExternalStorageState();

        if(!Environment.MEDIA_MOUNTED.equals(state))
        {
            return;
        }

        File file = new File(Environment.getExternalStorageDirectory(),FILENAME);

        FileOutputStream outputStream= null;
        try
        {
            file.createNewFile();
            outputStream = new FileOutputStream(file,false);
            outputStream.write(ubah.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    void bacaFile()
    {
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard,FILENAME);

        if(file.exists()){

            StringBuilder text = new StringBuilder();

            try
            {
                BufferedReader br = new BufferedReader(new FileReader(file));

                String line = br.readLine();

                while (line!=null)
                {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            }catch (IOException e){
                System.out.println("Error" + e.getMessage());
            }
            textBaca.setText(text.toString());
        }
    }

    void hapusFile()
    {
        File file = new File(Environment.getExternalStorageDirectory(),FILENAME);
        if (file.exists())
        {
            file.delete();
        }
    }


    @Override
    public void onClick(View v) {
        jalankanPeritah(v.getId());

    }

    private void jalankanPeritah(int id) {
        switch (id)
        {
            case R.id.buatfile:
                buatFile();
                break;
            case R.id.bacafile:
                bacaFile();
                break;
            case R.id.ubahfile:
                ubahFile();
                break;
            case R.id.hapusfile:
                hapusFile();
                break;
        }
    }
}