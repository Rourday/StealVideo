package com.example.uploadpic;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

  private String picname="image.jpg";
	private final String upurl="http://192.168.0.62/test.php";
	private String mPath;
	private Button uploadBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		uploadBtn=(Button)findViewById(R.id.upload);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()  
        .detectDiskReads().detectDiskWrites().detectNetwork()  
        .penaltyLog().build());  

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()  
        .detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()  
        .build());  

		uploadBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				uploadBtn.setEnabled(false);
				getSDPath();
				uploadPic();
				
			}
		});
	}
	public String getSDPath()
	{
		File sdDir=null;
		boolean sdCardExist=Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);  //判断SD卡是否存在
		if(sdCardExist)
		{
			sdDir=Environment.getExternalStorageDirectory();  //跟踪根目录
			mPath=sdDir.toString();
		}
		else
		{
			mPath="/mnt/emmc/DCIM";
		}
		return mPath;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	HttpURLConnection httpcon;
	public void uploadPic()
	{
		String end="\r\n";
		String twoHyhens="--";
		String boundary="******";
		String path=mPath+"/test/PIC/image.jpg";
		try
		{
    		URL url=new URL(upurl);
    		httpcon=(HttpURLConnection)url.openConnection();
    		//设置部分
    		httpcon.setDoInput(true);
    		httpcon.setDoOutput(true);
    		httpcon.setRequestMethod("POST");
    		httpcon.setRequestProperty("Connection", "Keep-Alive");
    		httpcon.setRequestProperty("Charset", "UTF-8");
    		httpcon.setRequestProperty("Content-Type", "multi-part/from-date;boundary="+boundary);
    		DataOutputStream ds=new DataOutputStream(httpcon.getOutputStream());
    		ds.writeBytes(twoHyhens+boundary+end);
    		ds.writeBytes("Cotent-Postion:from-date;"+"name=\"file1\";filename=\""+picname+"\""+end);
    		ds.writeBytes(end);
    		
    		FileInputStream fStream=new FileInputStream(path);
    		int bufferSize=1024;
    		byte[] buffer=new byte[bufferSize];
    		int length=-1;
    		while((length=fStream.read(buffer))!=-1)
    		{
    			ds.write(buffer,0,length);
    		}
    		ds.writeBytes(end);
    		ds.writeBytes(twoHyhens+boundary+twoHyhens+end);
    		fStream.close();
    		ds.flush();
    		
    		//看传送情况
    		InputStream is=httpcon.getInputStream();
    		int ch;
    		StringBuilder sb=new StringBuilder();
    		while((ch=is.read())!=-1)
    		{
    			sb.append((char)ch);
    		}
    		showdialog("上传成功"+sb.toString().trim());
    		ds.close();
    		//httpcon.disconnect();
		}
		catch(Exception e)
		{
			showdialog("上传失败"+path+e);
			//httpcon.disconnect();
		}
		finally
		{
			//httpcon.disconnect();
		}
		
	}
	
	public void showdialog(String s)
	{
		new AlertDialog.Builder(MainActivity.this).setTitle("Message")
		.setMessage(s)
		.setNegativeButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		})
		.show();		
	}

}
