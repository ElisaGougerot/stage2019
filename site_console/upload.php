<?php

$nameFile = $_FILES['file']['name']
$typeFile =$_FILES['file']['type']
$sizeFile =$_FILES['file']['size']
$adresseFile =$_FILES['file']['tmp_name']
$error = $_FILES['file']['error']

$extensions_valides = array('','','');
$maxsize = ..... ;

if(isset($_POST['upload'])){
  $nameCreateur = $_POST['nameCreateur'];
  $nameGame = $_POST['nameGame'];
  $description = $_POST['description'];
}


function upload ($file,$nameGame,$maxsize=FALSE,$extensions_valides=FALSE) {
  //fichier non uploader
  if ($_FILES['file']['error'] > 0) {
    $error = "Erreur lors du transfert";
  }

  //fichier trop volumineux
  if ($_FILES['file']['size'] > $maxsize) {
    $error = "Le fichier est trop volumineux";
  }

  //l'extension du fichier n'est pas valide
  $extension_upload = strtolower(substr(strrchr($_FILES['file']['name'],'.'),1));
  if (in_array($extension_upload,$extensions_valides)) {
    $error = "Extension incorrecte";
  }

  //Créer un dossier 'games/$nameCreateur/'
  mkdir('games/'$nameCreateur'/', 0777, true);

  $ok = move_uploaded_file($_FILES['file']['tmp_name'],$nameGame);

  if ($ok) {
    echo "Transfert réussi";
  }
}

?>
