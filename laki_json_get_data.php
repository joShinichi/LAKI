<?php
$DB_name = "laki";
$mysql_username = "root";
$mysql_password = "";
$server_name = "localhost";
$koneksi = mysqli_connect($server_name,$mysql_username,$mysql_password,$DB_name)

   //Jika anda menggunakan localhost, jika tidak, ganti localhost dengan host yang anda   punya, dan ganti root dengan username database anda, password anda dan nama database   anda
   or die("Error ".mysqli_error($koneksi));
//Mengambil data pada table dari database MySQL
$sql = "select * from user";
$result = mysqli_query($koneksi, $sql) 
    or die("Error in Selecting " . mysqli_error($koneksi));
//Membuat array
$identitas = array();
while($row =mysqli_fetch_assoc($result))
{
  $identitas[] = $row;
}
//Menampilkan konversi data pada tabel identitas ke format JSON
echo '{user:';

//Silahkan rubah nama info menjadi judul yang anda kehendaki 


echo json_encode($identitas);
echo '}';
//close the db connection
mysqli_close($koneksi);
?>

