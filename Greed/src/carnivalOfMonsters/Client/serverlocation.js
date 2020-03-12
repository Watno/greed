/**
 * The server location is an extra file so I don't actually make the online Client point to my local server after making changes.
 */

function connectToServer() {
    socket = new WebSocket('ws://localhost:8080/greed');
    return socket;
}