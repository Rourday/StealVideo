import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


import android.os.Environment;

public class uploadPic {
  
	private String picname="image.jpg";
	private final String upurl="http://192.168.0.62/test.php";
	private String mPath;
	private static final int TIME_OUT = 10*10000000;
	private static final String CHARSET = "utf-8"; //设置编码
	HttpURLConnection httpcon;
	
	//获取SD卡的路径
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
	
	//上传图片
	public void upload()
	{
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try
				{
					getSDPath();
					String end="\r\n";
					String twoHyhens="--";
					String boundary="******";
					String path_pic=mPath+"/test/PIC/image.jpg";
					String CONTENT_TYPE = "multipart/form-data";
		    		URL url=new URL(upurl);
		    		httpcon=(HttpURLConnection)url.openConnection();
		    		
		    		//设置部分
		    		httpcon.setReadTimeout(TIME_OUT);
		    		httpcon.setDoInput(true);
		    		httpcon.setDoOutput(true);
		    		httpcon.setUseCaches(false);   //不允许使用缓存 
		    		httpcon.setRequestMethod("POST");
		    		httpcon.setRequestProperty("connection", "Keep-alive");
		    		httpcon.setRequestProperty("Charset", CHARSET); //设置编码 
		    		httpcon.setRequestProperty("Content-Type", CONTENT_TYPE+";boundary="+boundary);
		    		OutputStream outputstr=httpcon.getOutputStream();
		    		DataOutputStream ds=new DataOutputStream(outputstr);
		    		File file=new File(path_pic);
		    		StringBuilder sb=new StringBuilder();
		    		sb.append(twoHyhens);
		    		sb.append(boundary);
		    		sb.append(end);
		    		//添加协议的头!!要按规则写！！
		    		sb.append("Content-Disposition: form-data; name=\"img\"; filename=\""+file.getName()+"\""+end); 
		    		sb.append("Content-Type: image/jpeg; charset="+CHARSET+end); 
		    		sb.append(end); 
		    		ds.write(sb.toString().getBytes());
		    		//传送文件的正文部分！！
		    		FileInputStream fStream=new FileInputStream(file);
		    		int bufferSize=1024;
		    		byte[] buffer=new byte[bufferSize];
		    		int length=0;
		    		while((length=fStream.read(buffer))!=-1)
		    		{
		    			ds.write(buffer,0,length);
		    		}
		    		fStream.close();
		    		//传送完写文件尾
		    		ds.write(end.getBytes());
		    		ds.writeBytes(twoHyhens+boundary+twoHyhens+end);
		    		ds.flush();
		    		
		    		//看传送情况
		    		InputStream is=httpcon.getInputStream();
		    		int ch;
		    		while((ch=is.read())!=-1)
		    		{
		    			sb.append((char)ch);
		    		}
		    		//showdialog("上传成功"+sb2.toString().trim());
		    		ds.close();
				}
				catch(Exception e)
				{
					e.getStackTrace();
				}
				finally
				{
					httpcon.disconnect();
				}
			}
			
		}).start();
		
		
	}
}
