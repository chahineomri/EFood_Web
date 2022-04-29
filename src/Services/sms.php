<?php
// Require the bundled autoload file - the path may need to change
// based on where you downloaded and unzipped the SDK
//require __DIR__ . '/twilio-php-main/src/Twilio/autoload.php';

// Use the REST API Client to make requests to the Twilio REST API


use Twilio\Rest\Client;

// Your Account SID and Auth Token from twilio.com/console
$sid = 'ACbee35cfef4310a604b6e4230bc515fa1';
$token = '51a307636f19552ab2ac4a25dab95087';
$client = new Client($sid, $token);
$message= $client->messages->create(
// the number you'd like to send the message to
    '+21653157628',
    [
        // A Twilio phone number you purchased at twilio.com/console
        'from' => '+19379143990',
        // the body of the text message you'd like to send
        'body' => 'bibou it worked!!'
    ]
);
if($message)
{
    echo 'message was sent';

}else{
    echo 'message was not sent';
}
?>
