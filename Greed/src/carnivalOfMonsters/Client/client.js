var socket;
var nameSelected = false;

function initialize() {

	subscribeToWebSocket();
}

function sendLobbyCommand(text) {
	socket.send(text);
}

function newTable() {
	sendLobbyCommand('{"newTable":"CarnivalOfMonsters"}');
}

function ready(yesno) {
	sendLobbyCommand('{"ready":' + yesno + '}');
}

function joinTable(number) {
	sendLobbyCommand('{"joinTable":' + number + '}');
}

function changePlayers(number) {
	sendLobbyCommand('{"changePlayers":' + number + '}');
}

function leaveTable() {
	sendLobbyCommand('{"leaveTable":false}');
}

function joinLobby() {
	sendLobbyCommand('{"joinLobby":false}');
}

function changeName() {
	var name = document.getElementById('newName').value;
	if (!name.trim() || name == "") {
		showMessage('Name must not be empty.');
		return;
	}
	document.getElementById('newName').value = "";
	sendLobbyCommand('{"namechange":"' + name + '"}');
	nameSelected = true;
	document.getElementById('lobby').hidden = false;
	document.getElementById('initial').hidden = true;
}

function sendLobbyChat() {
	var message = document.getElementById('lobbyChatMessage').value;
	document.getElementById('lobbyChatMessage').value = "";
	sendLobbyCommand('{"chat":"' + message + '","location":"lobby"}');
}

function sendCommand() {
	var message = document.getElementById('gamecommand').value;
	document.getElementById('gamecommand').value = "";
	socket.send('{"greedcommand":"' + message + '"}');
}

function showMessage(text) {
	alert(text);
}

function showPrompt(text) {
	document.getElementById('prompt').innerHTML = text;
}

function drawTable(table, tablenumber, owntable) {
	var text = "<div class=\"row\">";
	if (!owntable) {
		text += "<div class=\"col-md-1\"></div>";
		text += "<div class=\"col-md-1\"><button onclick=\"javascript:joinTable(" + tablenumber + ");\">Join</button></div>";
	}
	if (owntable) {
		text += "<div class=\"col-md-1\"><button onclick=\"javascript:ready(true)\">Ready</button></div>";
		text += "<div class=\"col-md-1\"><button onclick=\"javascript:leaveTable()\">Leave</button></div>";
	}
	text += "<div class=\"col-md-1\">" + table.numberOfPlayers + " player game</div>";
	var i = 0;
	while (i < table.players.length) {
		text += "<div class=\"col-md-1\">" + table.players[i] + "</div>";
		i++;
	}
	if (owntable) {
		while (i < table.numberOfPlayers) {
			if (i != 1) {
				text += "<div class=\"col-md-1\"><button onclick=\"javascript:changePlayers(" + i + ");\">Change to " + i + " players</button></div>";
			}
			else {
				text += "<div class=\"col-md-1\"></div>";
			}
			i++;
		}
		while (i < 5) {
			text += "<div class=\"col-md-1\"><button onclick=\"javascript:changePlayers(" + (i + 1) + ");\">Change to " + (i + 1) + " players</button></div>";
			i++;
		}
	}
	while (i < 9) {
		text += "<div class=\"col-md-1\"></div>";
		i++;
	}
	text += "</div>";
	return text;
}

function drawLobby(parsedMsg) {
	var text = "";
	for (var i = 0; i < parsedMsg.tables.length; i++) {
		text += drawTable(parsedMsg.tables[i], i, i == parsedMsg.tablenumber);
	}
	document.getElementById('lobbyTables').innerHTML = text;

	document.getElementById('lobby').hidden = !nameSelected;
}
function subscribeToWebSocket() {
	if ('WebSocket' in window) {
		socket = connectToServer();
		socket.onopen = function () {
			console.log('Connected to WebSocket.');
		};
		socket.onmessage = function (msg) {
			console.log(msg);
			var parsedMsg = JSON.parse(msg.data);
			if (parsedMsg.hasOwnProperty('players')) {
				document.getElementById('lobby').hidden = true;
				document.getElementById('game').hidden = false;
				var log = document.getElementById('log');
				var text = parsedMsg.log;
				text = text.replace(/(?:\r\n|\r|\n)/g, '<br />');
				for (var i = 1; i <= 80; i++) {
					text = text.replace(RegExp(cardnames[i], "g"), "<span data-placement='auto' data-html=true data-toggle=\"tooltip\" data-title=\"" + cardtexts[i] + "\">" + cardnames[i] + "</span>");
				}
				log.innerHTML = text;
				log.scrollTop = log.scrollHeight - log.clientHeight;

				var hand = parsedMsg.privateInformation.hand;
				text = "Hand <br>"
				for (var i = 0; i < hand.length; i++) {
					text += drawCard(hand[i], i, 'handbutton');
				}
				document.getElementById('hand').innerHTML = text;
				var draftPile = parsedMsg.privateInformation.draftPile;
				text = "Draftpile <br>";
				for (var i = 0; i < draftPile.length; i++) {
					//text += draftPile[i].name + "<br>"; 
					//text += "<button class =\"draftbutton\" onclick=\"javascript:sendCommand("+i+");\" disabled>"+draftPile[i].name + "</button>	";
					text += drawCard(draftPile[i], i, 'draftbutton');
				}
				document.getElementById('draftPile').innerHTML = text;
				text = "";
				for (var i = 0; i < parsedMsg.players.length; i++) {
					text += drawPlayer(parsedMsg.players[(i + parsedMsg.privateInformation.position) % parsedMsg.players.length], i == 0);
				}
				document.getElementById('players').innerHTML = text;
				activateButtons();
				$('[data-toggle="tooltip"]').tooltip();

			}
			else if (parsedMsg.type == 'lobby') {
				drawLobby(parsedMsg);
			}
			else if (parsedMsg.type == 'chat') {
				if (parsedMsg.location == 'lobby') {
					var lobbyChat = document.getElementById('lobbyChat');
					var text = lobbyChat.innerHTML;
					text += "<br>" + parsedMsg.sender + ": " + parsedMsg.message;
					lobbyChat.innerHTML = text;
					lobbyChat.scrollTop = lobbyChat.scrollHeight - lobbyChat.clientHeight;
				}
				if (parsedMsg.location == 'table') {
					var gameChat = document.getElementById('gameChat');
					var text = gameChat.innerHTML;
					text += "<br>" + parsedMsg.sender + ": " + parsedMsg.message;
					gameChat.innerHTML = text;
					gameChat.scrollTop = gameChat.scrollHeight - gameChat.clientHeight;
				}
			}
			else {
			    document.getElementById('lobby').hidden = true;
			    document.getElementById('gamearea').hidden = false;
					var gameMessages = document.getElementById('gameMessages');
					var text = gameMessages.innerHTML;
					text += "<br>" + msg.data;
					gameMessages.innerHTML = text;
					gameMessages.scrollTop = gameMessages.scrollHeight - gameMessages.clientHeight;

			}

		};
		socket.onerror = function (msg) {
			showMessage('Sorry but there was an error.\n\n'+msg);
		};
		socket.onclose = function () {
			showMessage('Server offline.');
		};
	} else {
		showMessage('Your browser does not support HTML5 WebSockets.');
	}
}