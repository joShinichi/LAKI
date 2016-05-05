<?php
require "laki_koneksi.php";

$nama = $_POST["nama"];
$username = $_POST["Username"];
$password = $_POST["Password"];
$email = $_POST["Email"];
$mysql_qry = "insert into user(nama,username,password,email) values ('$nama','$username','$password','$email')";

if($conn->query($mysql_qry) === TRUE){
	echo "Register_sukses";
}else{
	echo "some_error";
	echo "ERROR : " . $mysql_qry . "</br>" . $conn->error;
}

$conn->close();
?>