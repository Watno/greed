import { getServerlocation } from "./serverlocation";

const socket = new WebSocket(getServerlocation());
socket.onopen = function () {
    console.log("Connected to WebSocket.");
};
socket.onerror = function (msg) {
    alert("Sorry but there was an error.\n\n" + msg);
};
socket.onclose = function () {
    alert("Server offline.");
};

export function startGame() {
    socket.send('{"newTable":"spacealert"}');
    socket.send('{"changePlayers":4}');
    socket.send('{"ready":true}');
}

export function sendGameCommand(object: object) {
    console.log(object);
    socket.send(
        JSON.stringify({
            gamecommand: object,
        })
    );
}

export function setOnMessageCallback(callback: ((this: WebSocket, ev: MessageEvent) => void)){
    socket.onmessage = callback;
}

export function sendLobbyCommand(text: string) {
	socket.send(text);
}