<?php

$userInput = $_GET["text"];

if ($userInput == NULL) { //Incase the user doesn't input anything
    $url = 'https://xkcd.com/info.0.json';
    $curlHandle = curl_init($url);
    curl_setopt($curlHandle, CURLOPT_RETURNTRANSFER, true);
    $result = curl_exec($curlHandle);
    $resultArray = json_decode($result, true);  
    echo "I see you didn't add anything. Please try again and add a number between 0 and " .  $resultArray["num"] . " at the end.";
    exit;
}

$url = 'https://xkcd.com/' . $userInput . '/info.0.json';
$curlHandle = curl_init($url);
curl_setopt($curlHandle, CURLOPT_RETURNTRANSFER, true);
$result = curl_exec($curlHandle);
$resultArray = json_decode($result, true);

if ($resultArray == NULL) { //Check if the user did not upload a number higher than the current highest comic
    $url = 'https://xkcd.com/info.0.json';
    $curlHandle = curl_init($url);
    curl_setopt($curlHandle, CURLOPT_RETURNTRANSFER, true);
    $result = curl_exec($curlHandle);
    $resultArray = json_decode($result, true);  
    echo "You entered a value above the newest comic. Feel free to try again with a number between 0 and  " . $resultArray["num"];
} else { //The code were it takes in the user input and returns the img
    echo "Here is comic " . $userInput;
    //echo "Here is the link to the comic " . $userInput . ": " . $resultArray["img"];
}

$responseURL = $_GET["response_url"];
//echo $responseURL;
$imageURL = $resultArray["img"];
$payload = ["attachments" => [["image_url" => $imageURL]]];
$payloadJSON = json_encode($payload);
$curlHandleSlack = curl_init($responseURL);
curl_setopt($curlHandleSlack, CURLOPT_POST, true);
curl_setopt($curlHandleSlack, CURLOPT_POSTFIELDS, $payloadJSON);
curl_setopt($curlHandleSlack, CURLOPT_RETURNTRANSFER, true);
curl_exec($curlHandleSlack);
?>  