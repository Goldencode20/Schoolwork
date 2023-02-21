<?php

//$userText = $_GET["text"];

//echo (pi() * $_GET["text"] ** 2);

//echo " Square inches";

//circlearea($_GET[8]);

$userInput = $_GET["text"];

$url = 'https://xkcd.com/' . $userInput . '/info.0.json';

$curlHandle = curl_init($url);

curl_setopt($curlHandle, CURLOPT_RETURNTRANSFER, true);

$result = curl_exec($curlHandle);

$resultArray = json_decode($result, true);

//Here can I check if the user input was valid

//What does an error 404 return?
if ($resultArray == NULL) {
    
    $url = 'https://xkcd.com/info.0.json';

    $curlHandle = curl_init($url);

    curl_setopt($curlHandle, CURLOPT_RETURNTRANSFER, true);

    $result = curl_exec($curlHandle);

    $resultArray = json_decode($result, true);  
    
    echo "Here is the newest comic becuase you entered a value above the newest comic. Feel free to try again with a number under this number " . $resultArray["num"];
} else {
    echo "Here is the link to the comic " . $userInput . ": " . $resultArray["img"];
}
// check if error 404 is returned and if it is return newset comic and the number of it

//var_dump($resultArray);

?>  