var socket;
var nameSelected = false;

var landtypes = ["DARKLANDS", "AERIE", "DEPTHS", "ENCHANTEDFOREST", "CAVES", "DREAMLANDS" ];
var landTypeNames = ["Dunkellande", "Wolkenlande", "Tiefsee", "Wald", "Höhlen", "Traumlande" ];

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

function sendGameChat() {
	var message = document.getElementById('gameChatMessage').value;
	document.getElementById('gameChatMessage').value = "";
	sendLobbyCommand('{"chat":"' + message + '","location":"table"}');
}

function sendCommand(message) {
	socket.send('{"gamecommand":'+JSON.stringify(message)+'}');
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
				drawLog(parsedMsg.publicGameState.gameLog)
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
	if (enabled == true){
		buttonmetastart += ` onclick="javascript:sendCommand(\'${card.name}\')"`;
	}
	else{
		buttonmetastart += " disabled"
	}
	buttonmetastart += `>`;
	var buttonmetaend= "</button></span>";
	
	if (card.type != "Season") buttonmetastart += `<img class='icon' src='images\\${card.type.toLowerCase()}.png'/> `;

    var prefix = ``;
    var suffix = '';

    if (card.type == "Monster"){
        prefix = appendTo(prefix, card.level)
        if (card.dangerLevel > 0){
            suffix = appendTo(suffix, "<img class='icon' src='images\\danger.png'/>" + card.dangerLevel);
        }
        if (card.monstrousLore > 0){
            suffix = appendTo(suffix, "<img class='icon' src='images\\card.png'/>" + card.monstrousLore);
        }
        suffix = appendTo(suffix, "<img class='icon' src='images\\vp.png'/>" + card.victoryPoints);
    }
    if (card.hasOwnProperty('landpoints')){
        suffix = appendTo(suffix, card.landpoints)
	}
	if (card.hasOwnProperty('requiredLandpoints')){
        prefix = appendTo(prefix, card.requiredLandpoints)
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

	return  `<div class="playerName">${name}</div>
	<div class="playerinfoHeader">${crowns} ${landTypeInfo} ${numberOfKeptCards} ${hunterTokens}  ${trophies}  ${loans}</div>
	${inPlay}<br\>
	${menagerie}`
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
			prompt += ` <button onclick="javascript:sendCommand(null)">Keine Karte ausspielen</button>`;
			break;
		case "LandtypeForExplorerRequest":
			prompt = "Wähle den Landtyp für den mutigen Entedecker:<br\>" 
			for (var i = 0; i<landtypes.length; i++){
				prompt = appendTo(prompt, `<button class="${landtypes[i]}" onclick="javascript:sendCommand(\'${landtypes[i]}\')"> ${landTypeNames[i]}</button>`);
			}
			break;
		case "AssignLandpointsForDreamlandRequest":
			prompt = `Ordne dem Traumlandemonster ${request.level} Landpunkte zu :<br\>` 
			for (var i = 0; i<landtypes.length; i++){
				prompt = appendTo(prompt, `${landTypeNames[i]}: <input id="AssignLandpointsForDreamland_${landtypes[i]}" class="${landtypes[i]}" type="number" value="0" min="0" size ="2"/>`);
			}
			prompt+="<br\>"
			prompt+=`<button onclick="javascript:assignLandPointsForDreamland(${request.level})">Zuordnen</button>`
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

function assignLandPointsForDreamland(requiredLandpoints){
	let assignment = new Object();
	for (var i = 0; i<landtypes.length; i++){
		assignment[landtypes[i]] = parseInt(document.getElementById(`AssignLandpointsForDreamland_${landtypes[i]}`).value);
	}
	let sum = Array.from(Object.values(assignment)).reduce((a,b) => a+b, 0);
	if (sum == requiredLandpoints){
		sendCommand(assignment);
	}
	else {
		alert(`Genau ${requiredLandpoints} Landpunkte müssen zugewiesen werden.`);
	}
}


function drawLog(gamelog){
	function toString(logentry){

		var textstart = `<div class = "logentry ${logentry.type}">`;
		var textend = `</div>`
		var innertext = ""
		switch (logentry.type){
			case "GameLogEntry":
				innertext = "Carnival of Monsters: "
				break;
			case "SeasonLogEntry":
				innertext = `Saison ${logentry.seasonnumber}`;
				break;
			case "TurnLogEntry":
				innertext = `Runde ${logentry.turnNumber}`;
				break;
			case "PlayerTurnLogEntry":
				innertext = `${logentry.playername}`;
				break;
			case "KeepCardLogEntry":
				innertext = `behält eine Karte.`;
				break;
			case "PayLogEntry":
				innertext = `bezahlt ${logentry.amount}`;
				if (logentry.loans > 0){
					innertext += " und muss dafür "
					if (logentry.loans == 1){
						innertext += "einen Kredit"
					}
					else{
						innertext += `${logentry.loans} Kredite`
					}
					innertext += " aufnehmen"
				}
				innertext += "."
				break;
			case "PlayCardLogEntry":
				innertext = `spielt ${drawCard(logentry.card)}`;
				if (logentry.playSource == "KEPT"){
					innertext += " aus seinen aufgehobenen Karten"
				}
				innertext += "."
				break;
			case "TriggerLogEntry":
				innertext = `löst den Effekt von ${drawCard(logentry.triggeringCard)} aus.`;
				break;
			case "RevealKeptMonstersLogEntry":
				if (logentry.revealedMonsters != 0){
					revealedMonstersText = logentry.revealedMonsters.map(x => drawCard(x)).join(", ")
				}
				else {
					revealedMonstersText = "keine Monster"
				}
				innertext = `zeigt ${revealedMonstersText}.`;
				break;
			case "HuntPhaseEntry":
				innertext = `Jagdphase: Königliche Jäger würfeln ${logentry.dieRolls[0]}, ${logentry.dieRolls[1]}, ${logentry.dieRolls[2]}`;
				break;
			case "DangerCheckLogEntry":
				innertext = `${logentry.playername} hat ${logentry.dangerLevel} Gefahrensymbole`
				if (logentry.spentHunterTokens > 0){
					innertext += ` und gibt ${logentry.spentHunterTokens} Jägermarker aus`
				}
				innertext += "."
				break;
			case "AwardTrophyLogEntry":
				if (logentry.playername != null){
					winner = logentry.playername;
				}
				else {
					winner = "niemanden"
				}
				innertext = `Die Trophäe für ${drawCard(logentry.trophy)} geht an ${winner}.`;
				break;
			case "ScorePhaseLogEntry":
				innertext = `Spielende`;
				break;
			case "PlayerScoreLogEntry":
				innertext = `${logentry.playername}: ${logentry.score} Siegpunkte: `;
				innertext += `${logentry.scoreForMenagerie} Punkte für Monster in der Menagerie, `;
				innertext += `${logentry.scoreForSecretGoals} Punkte für geheime Ziele, `;
				innertext += `${logentry.scoreForTrophies} Punkte für Trophäen, `;
				innertext += `${logentry.scoreForCrowns} Punkte für übrige Kronen, `;
				innertext += `${logentry.scoreForHunterTokens} Punkte für Jägermarker, `;
				innertext += `${logentry.scoreForLoans} Punkte für Kredite, `;
				break;
			case "SecretGoalScoreEntry":
				innertext = `${logentry.score} Punkte für ${drawCard(logentry.goal)}`;
				break;
			case "SecondRowLogEntry":
				innertext = `${logentry.playername} erhält 7 weitere Punkte für ${drawCard(logentry.secondRowIsGoodEnough)}.`;
				break;

		}

		var nestedEntries ="";
		for (var i = 0; i<logentry.dependantEntries.length; i++){
			nestedEntries+= toString(logentry.dependantEntries[i]);
		}

		return textstart + innertext + nestedEntries +textend;
	}

	var log = document.getElementById('log');
	var text = toString(gamelog);
	log.innerHTML = text;
	log.scrollTop = log.scrollHeight - log.clientHeight;
}
