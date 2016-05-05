<?php
require "laki_koneksi.php";
session_start();
if (isset($_POST["username"]) AND isset($_POST["password"]))
{
$username = $_POST["username"];
$userpass = $_POST["password"];
$mysql_qry = "select * from user where username ='$username' and password ='$userpass';";
$image = $_POST['image'];
$path = "upload/joddy.png";
$actualpath = "http://192.168.88.5/$path";
$sql = "UPDATE user SET photo='$actualpath' WHERE username=11";
 if(mysqli_query($conn,$sql)){
 file_put_contents($path,base64_decode($image));
 $_SESSION['username'] = $username;
 echo "Successfully Uploaded";
 }else{
	 echo "gagal";
 }
$result = mysqli_query($conn,$mysql_qry);
$hasil = mysqli_query($mysql_qry);
$data = mysqli_fetch_array($hasil,MYSQLI_ASSOC);
$active = $data['username'];
if(mysqli_num_rows($result) > 0){
	$output=mysqli_fetch_assoc($result);
	echo '{user:';
	print(json_encode($output));
	echo '}';
	
}else{
	echo "login not success";
}
}else
{
   echo "Maaf, anda harus mengakses halaman ini dari form.html";
}

?>