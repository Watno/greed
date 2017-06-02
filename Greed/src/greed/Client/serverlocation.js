/**
 * The server location is an extra file so I don't actually make the online Client point to my  local server after making changes.
 */

function connectToServer(){
    //socket = new WebSocket('ws://ec2-35-157-160-241.eu-central-1.compute.amazonaws.com:8080/greed');
    socket = new WebSocket('ws://192.168.1.114:8080/greed');
    return socket;
}