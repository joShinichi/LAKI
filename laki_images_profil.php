<?php
 session_start();
  include('laki_koneksi.php');
if($_SERVER['REQUEST_METHOD']=='POST'){

 $image = $_POST['image'];
 $path = "upload/joddy.png";
$actualpath = "http://192.168.88.5/$path";
$sql = "UPDATE user SET photo='$actualpath' WHERE username=11";
 if(mysqli_query($conn,$sql)){
 file_put_contents($path,base64_decode($image));
 echo "Successfully Uploaded";
 }
 else{
	 echo "gagal";
 }
 
 mysqli_close($conn);
}
 ?>
