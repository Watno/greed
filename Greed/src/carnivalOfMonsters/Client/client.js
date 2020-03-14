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

function sendCommand(message) {
	socket.send('{"greedcommand":"' + message + '"}');
}

function sendNullCommand() {
	socket.send('{"greedcommand":null}');
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
			if (parsedMsg.type == 'lobby') {
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
			else if(parsedMsg.hasOwnProperty('publicGameState')){
			    document.getElementById('lobby').hidden = true;
			    document.getElementById('gamearea').hidden = false;
					var gameState = document.getElementById('gamestate');
					gameState.innerHTML = drawGameState(parsedMsg);
			}
			else if(parsedMsg.hasOwnProperty('requestType')){
				var requestArea = document.getElementById('requestArea');
				requestArea.innerHTML = drawRequest(parsedMsg)
			}
			else {
				var requestArea = document.getElementById('requestArea');
				requestArea.innerHTML = "";
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

function appendTo(original, addition){
    if (original != ''){
        original += ' ';
    }
    original += addition;

    return original;
}

function drawCard(card, enabled) {
    var buttonmetastart = "";

    buttonmetastart += "<span class = 'buttonwrapper' data-placement='auto' data-html=true data-toggle=\"tooltip\" data-title=\"Test\">";
	buttonmetastart += `<button class ="${card.type} ${card.landType}"`;
	if (enabled != null){
		buttonmetastart += ` onclick="javascript:sendCommand(\'${card.name}\')"`;
	}
	else{
		buttonmetastart += " disabled"
	}
	buttonmetastart += `>`;
    var buttonmetaend= "</button></span>";

    var prefix = '';
    var suffix = '';

    if (card.type == "Monster"){
        prefix = appendTo(prefix, card.level)
        if (card.dangerLevel > 0){
            suffix = appendTo(suffix, "G: " + card.dangerLevel);
        }
        if (card.monstrousLore > 0){
            suffix = appendTo(suffix, "W: " + card.monstrousLore);
        }
        suffix = appendTo(suffix, "SP: " + card.victoryPoints);
    }
    if (card.hasOwnProperty('landpoints')){
        suffix = appendTo(suffix, card.landpoints)
    }
    if (card.hasOwnProperty('cost')){
        prefix = appendTo(prefix, "$"+card.cost)
    }

    if (prefix != ''){
        prefix += ' - ';
    }
    if (suffix != ''){
        suffix = ' - ' + suffix;
    }

    return buttonmetastart + prefix + card.name + suffix + buttonmetaend;
}

function drawGameState(gameState){
    var season = "Aktuelle Saison: " + drawCard(gameState.publicGameState.currentSeason);

    var playerInfos = "";
    var ownIndex;
    for (var i = 0; i<gameState.publicGameState.playerGameStates.length; i++){
        if (gameState.publicGameState.playerGameStates[i].name == gameState.privateGameState.name){
            ownIndex = i;
			playerInfos += drawPlayer(gameState.publicGameState.playerGameStates[i]);
			
			var keptCards = 'Aufgehobene Karten: ';
			for (var j = 0; j<gameState.privateGameState.keptCards.length; j++){
				keptCards = appendTo(keptCards, drawCard(gameState.privateGameState.keptCards[j]));
			}
			playerInfos += `<br\> ${keptCards}`
        }
	}
	
	for (var i = 1; i<gameState.publicGameState.playerGameStates.length; i++){
		playerInfos +="<br\><br\>"+ drawPlayer(gameState.publicGameState.playerGameStates[i%gameState.publicGameState.playerGameStates.length]);
    }

    return season + "<br\><br\>" + playerInfos;
}

function drawPlayer(playerInfo){
    var name = playerInfo.name;
    var landTypeInfo ='';
    var landtypes = ["DARKLANDS", "AERIE", "DEPTHS", "ENCHANTEDFOREST", "CAVES", "DREAMLANDS" ];
    for (var i = 0; i<landtypes.length; i++){
           landTypeInfo = appendTo(landTypeInfo, `<span class="${landtypes[i]}"> ${playerInfo.usedLandPoints[landtypes[i]]}/${playerInfo.totalLandPoints[landtypes[i]]}</span>`);
    }

    var crowns = "$" + playerInfo.crowns;
    var loans = `Kredite: ${playerInfo.loans}`;
    var trophies = `Trophäen: ${playerInfo.trophies.length}`;
    var hunterTokens = `Jagdmarker: ${playerInfo.hunterTokens}`;
    var numberOfKeptCards = `Aufgehobene Karten: ${playerInfo.numberOfKeptCards}`;

    var inPlay = 'Im Spiel:';
    for (var i = 0; i<playerInfo.cardsInPlay.length; i++){
           inPlay = appendTo(inPlay, drawCard(playerInfo.cardsInPlay[i]));
    }

    var menagerie = 'Menagerie:';
    for (var i = 0; i<playerInfo.menagerie.length; i++){
           menagerie = appendTo(menagerie, drawCard(playerInfo.menagerie[i]));
    }

    return  `${name} <br\>${crowns} ${landTypeInfo} ${numberOfKeptCards} ${hunterTokens}  ${trophies}  ${loans}<br\>${inPlay}<br\>${menagerie}`
}

function drawRequest(request){
	var prompt;
	switch (request.requestType){
		case "DraftRequest":
			prompt = "Wähle eine Karte aus:";
			break;
		case "PlayOrKeepRequest":
			prompt = "Spiele die Karte aus oder behalte sie:";
			prompt += "<br\>"+drawCard(request.card); 
			prompt += ` <button onclick="javascript:sendCommand('PLAY')">Ausspielen</button>`;
			prompt += ` <button onclick="javascript:sendCommand('KEEP')">Behalten</button>`;
			break;
		case "PlayFromKeptRequest":
			prompt = "Du darfst eine aufgehobene Karte ausspielen:<br\>" 
			prompt += ` <button onclick="javascript:sendNullCommand()">Keine Karte ausspielen</button>`;
			break;
	}
	if (request.hasOwnProperty("cards")){
		var cards = ""
		for (var i = 0; i<request.cards.length; i++){
			cards = appendTo(cards, drawCard(request.cards[i], true));
	 	}
		prompt += "<br\>"+cards
	}
	return prompt;
}

