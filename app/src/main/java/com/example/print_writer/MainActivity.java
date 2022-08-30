package com.example.print_writer;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.admin.SystemUpdateInfo;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private File file;
    private File Dir;
    private BufferedWriter writer;
    private BufferedReader reader;
    private FileOutputStream fos;
    private static final int PERMISSION_REQUEST_CODE = 0;

    private final String[] permission = new String[]{
            //允許應用程序寫入外部存儲
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //呼叫要權限
        requestPermissions(permission);
    }
    //刪除檔案監聽
    public void deletClick(View view){
        file.delete();
        Log.e("note",file.getAbsolutePath());

    }
    //跳出拒絕或允許權限的方法
    private void requestPermissions(String... permissions) {
        Log.e("note","requestPermissions");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, PERMISSION_REQUEST_CODE);
        }
    }


    //拿取權限的Request
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==0) {
            Log.e("note", "拿取權限完成");
            //建立文字檔的目錄
            String pathFile =Environment.getExternalStorageDirectory().getPath()+"/Documents/Demo/demo2.txt";
            //建立資料夾的目錄
            String pathDir =Environment.getExternalStorageDirectory().getPath()+"/Documents/Demo";
            //--------------建立的try...catch-----------
            try {
                //建立資料夾
                Dir = new File(pathDir);
                if (!Dir.exists()) {
                    Dir.mkdirs();
                    Log.e("note", "MakeDir" );
                }
                else Log.e("note", "ErrorDir" );
                //建立文字檔
                file = new File(pathFile);
                if (!file.exists()) {
                    file.createNewFile();
                    Log.e("note", "MakeFile" );
                }
                else Log.e("note", "ErrorFile" );
            } catch(Exception e){
                Log.e("creating file Error", e.toString());
            }
            //-------------------------------------------


            //------------要寫的文字檔的try...catch------------
            try {
                fos = new FileOutputStream(file, true);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //---------------------------------------------

            //建立writer
            writer = new BufferedWriter(new OutputStreamWriter(fos));

            //--------------寫檔的try...catch-------------
            try {
                writer.write("\n666666666\n");
                writer.flush();
                Log.e("note", "write" );
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //---------------------------------------------
        }
    }
}