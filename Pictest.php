
<?php
  $data=file_get_contents('php://input');
  $date=md5(date('Y-m-d H:i:s',time()));
  $filename='androidimage_'.$date.'.jpg';
  $handle=fopen($filename,'w');
  if($handle)
  {
     fwrite($handle,$data);
     fclose($handle);
     echo  "上传成功！";
   }
   else echo "上传失败";
?>
