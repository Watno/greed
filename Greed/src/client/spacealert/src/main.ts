import "reflect-metadata";
import { createApp } from "vue";
import App from "./App.vue";

createApp(App).mount("#app");

// //legacy lobby code
// function drawTable(table: any, tablenumber: any, owntable: any) {
// 	let text = "<div class=\"row\">";
// 	if (!owntable) {
// 		text += "<div class=\"col-md-1\"></div>";
// 		text += "<div class=\"col-md-1\"><button onclick=\"javascript:joinTable(" + tablenumber + ");\">Join</button></div>";
// 	}
// 	if (owntable) {
// 		text += "<div class=\"col-md-1\"><button onclick=\"javascript:ready(true)\">Ready</button></div>";
// 		text += "<div class=\"col-md-1\"><button onclick=\"javascript:leaveTable()\">Leave</button></div>";
// 	}
// 	text += "<div class=\"col-md-1\">" + table.numberOfPlayers + " player game</div>";
// 	let i = 0;
// 	while (i < table.players.length) {
// 		text += "<div class=\"col-md-1\">" + table.players[i] + "</div>";
// 		i++;
// 	}
// 	if (owntable) {
// 		while (i < table.numberOfPlayers) {
// 			if (i >= 4) {
// 				text += "<div class=\"col-md-1\"><button onclick=\"javascript:changePlayers(" + i + ");\">Change to " + i + " players</button></div>";
// 			}
// 			else {
// 				text += "<div class=\"col-md-1\"></div>";
// 			}
// 			i++;
// 		}
// 		while (i < 5) {
// 			text += "<div class=\"col-md-1\"><button onclick=\"javascript:changePlayers(" + (i + 1) + ");\">Change to " + (i + 1) + " players</button></div>";
// 			i++;
// 		}
// 	}
// 	while (i < 9) {
// 		text += "<div class=\"col-md-1\"></div>";
// 		i++;
// 	}
// 	text += "</div>";
// 	return text;
// }

// function drawLobby(parsedMsg: any) {
// 	let text = "";
// 	for (let i = 0; i < parsedMsg.tables.length; i++) {
// 		text += drawTable(parsedMsg.tables[i], i, i == parsedMsg.tablenumber);
// 	}
// 	document.getElementById('lobbyTables')!.innerHTML = text;

// 	document.getElementById('lobby')!.hidden = false;
// }

// function subscribeToWebSocket() {

// 	setOnMessageCallback(function (msg) {
// 			const parsedMsg = JSON.parse(msg.data);
// 			if (parsedMsg.type == 'lobby') {
// 				drawLobby(parsedMsg);
// 			}
// 			else{
// 				createApp(App).mount("#app");
// 			}
// 		})
// }
// function initialize() {
// 	subscribeToWebSocket();
// }

// export function newTable() {
// 	sendLobbyCommand('{"newTable":true}');
// }

// export function ready(yesno: boolean) {
// 	sendLobbyCommand('{"ready":' + yesno + '}');
// }

// export function joinTable(number: number) {
// 	sendLobbyCommand('{"joinTable":' + number + '}');
// }

// export function changePlayers(number: number) {
// 	sendLobbyCommand('{"changePlayers":' + number + '}');
// }

// export function leaveTable() {
// 	sendLobbyCommand('{"leaveTable":false}');
// }

// export function joinLobby() {
// 	sendLobbyCommand('{"joinLobby":false}');
// }

// export function changeName() {
// 	if (document == null) return;
// 	const name = (document!.getElementById('newName')! as HTMLInputElement).value;
// 	if (!name.trim() || name == "") {
// 		return;
// 	}
// 	(document!.getElementById('newName')! as HTMLInputElement).value = "";
// 	sendLobbyCommand('{"namechange":"' + name + '"}');
// 	document!.getElementById('lobby')!.hidden = false;
// 	document!.getElementById('initial')!.hidden = true;
// }


