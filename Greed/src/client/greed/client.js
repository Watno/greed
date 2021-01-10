var socket;
var cardtexts = [];
var cardnames = [];
var draftbuttons = false;
var handbuttons = false;
var thugbuttons = false;
var holdingbuttons = false;
var yesbutton = false;
var nobutton = false;
var returnbutton = false;
var nameSelected = false;

function initialize() {
	makeCardTexts();
	makeCardNames();
	subscribeToWebSocket();
}

function makeCardTexts() {
	cardtexts[1] = "This turn, when the player to your left gains $, you also gain that much $.";
	cardtexts[2] = "Gain $10,000. When an adjacent player plays an Action this turn, gain $10,000.";
	cardtexts[3] = "Gain $10,000. When an adjacent player plays a Thug this turn, gain $10,000.";
	cardtexts[4] = "<div class='requirement'>Cost: <img class='greedicon' src='images/holding.png' alt= 'Holding'></div>Gain $15,000 for each marker that was on the Holding you paid."
	cardtexts[5] = "<div class='requirement'>Need: <img class='greedicon' src='images/key.png' alt= '🔑'></div>Gain $5,000 for each marker on the Holding of yours with the most markers. At the end of next turn, each player (including you) loses a Holding they choose.";
	cardtexts[6] = "<div class='permanenteffect'>Each turn: if you have no $, gain $10,000.</div>";
	cardtexts[7] = "<div class='requirement'>Cost: $10,000</div><div class='permanenteffect'>At the end of the game, gain an extra $5,000 for each marker on this.</div>";
	cardtexts[8] = "<div class='requirement'>Cost: $10,000</div>";
	cardtexts[9] = "<div class='requirement'>Cost: $10,000</div>";
	cardtexts[10] = "<div class='requirement'>Cost: $15,000</div><div class='permanenteffect'>Each turn: If one player has the most Holdings, that player gains $5,000.</div>";
	cardtexts[11] = "<div class='requirement'>Cost: $15,000</div>Place 2 extra markers on this.";
	cardtexts[12] = "<div class='requirement'>Cost: $10,000</div>This gets its normal markers at the end of the game rather than when played.";
	cardtexts[13] = "<div class='requirement'>Cost: $5,000</div>At the end of the game, if you are the one player with the most Thugs, gain $20,000.";
	cardtexts[14] = "<div class='requirement'>Cost: $10,000</div>";
	cardtexts[15] = "Place an extra marker on this for each <img class='greedicon' src='images/car.png' alt= '🚘'> you have.";
	cardtexts[16] = "<div class='requirement'>Cost: $10,000</div><div class='permanenteffect'>Each turn: If you have at most 2 Holdings and at most 2 Thugs, gain $5,000.</div>";
	cardtexts[17] = "<div class='requirement'>Cost: $20,000</div><div class='permanenteffect'>When you play an Action, place a marker on this afterwards.</div>";
	cardtexts[18] = "<div class='requirement'>Cost: $30,000</div>Place 5 extra markers on this.";
	cardtexts[19] = "<div class='requirement'>Cost: $15,000</div><div class='permanenteffect'>Each turn: If one player has the most <img class='greedicon' src='images/heart.png' alt= '❤'>, that player gains $5,000.</div>";
	cardtexts[20] = "<div class='requirement'>Need: You are the one player with the fewest Thugs</div>Gain $25,000.";
	cardtexts[21] = "<div class='requirement'>Cost: <img class='greedicon' src='images/thug.png' alt= 'Thug'> or <img class='greedicon' src='images/holding.png' alt= 'Holding'> or $10,000</div>";
	cardtexts[22] = "Gain $10,000.";
	cardtexts[23] = "Gain $5,000 per <img class='greedicon' src='images/gun.png' alt= '🔫'> you have.";
	cardtexts[24] = "Gain $20,000.<div class='permanenteffect'>At the end of the game, lose $25,000.</div>";
	cardtexts[25] = "Gain $10,000 for each <img class='greedicon' src='images/bottle.png' alt= '🍼'> you have.";
	cardtexts[26] = "<div class='requirement'>Need <img class='greedicon' src='images/holding.png' alt= 'Holding'><img class='greedicon' src='images/holding.png' alt= 'Holding'></div>Gain $10,000 for each Thug you have.";
	cardtexts[27] = "<div class='requirement'>Cost: Discard a card from your hand</div> Gain $30,000";
	cardtexts[28] = "Gain $10,000 if you have a <img class='greedicon' src='images/gun.png' alt= '🔫'>.<br>Gain $10,000 if you have a <img class='greedicon' src='images/car.png' alt= '🚘'>.<br>Gain $10,000 if you have a <img class='greedicon' src='images/key.png' alt= '🔫'>.";
	cardtexts[29] = "<div class='requirement'>Need: $90,000</div>Gain $45,000.";
	cardtexts[30] = "<div class='requirement'>Need: <img class='greedicon' src='images/gun.png' alt= '🔫'><img class='greedicon' src='images/car.png' alt= '🚘'><img class='greedicon' src='images/key.png' alt= '🔫'></div>Gain $25,000.";
	cardtexts[31] = "Gain $10,000 for each <img class='greedicon' src='images/key.png' alt= '🔫'> you have and $10,000 for each <img class='greedicon' src='images/wrench.png' alt= '🔧'> you have.";
	cardtexts[32] = "<div class='requirement'>Need: <img class='greedicon' src='images/car.png' alt= '🚘'><img class='greedicon' src='images/bottle.png' alt= '🍼'></div>Gain $25,000.";
	cardtexts[33] = "Gain $10,000 for each <img class='greedicon' src='images/car.png' alt= '🚘'> you have and $10,000 for each <img class='greedicon' src='images/heart.png' alt= '❤'> you have.";
	cardtexts[34] = "Gain $30,000. Each other player gains $10,000.";
	cardtexts[35] = "<div class='requirement'>Cost: <img class='greedicon' src='images/thug.png' alt= 'Thug'></div>Gain $25,000.";
	cardtexts[36] = "Gain $15,000. Then, if you have less than $50,000, return this card to your hand.";
	cardtexts[37] = "<div class='requirement'>Need: <img class='greedicon' src='images/gun.png' alt= '🔫'><img class='greedicon' src='images/key.png' alt= '🔫'></div>Place a marker on this for each Holding you have.";
	cardtexts[38] = "Place two markers on one of your Holdings.";
	cardtexts[39] = "<div class='requirement'>Need: <img class='greedicon' src='images/heart.png' alt= '❤'></div>Place a marker on each of your Holdings.";
	cardtexts[40] = "Place a marker on each of your Holdings with no more than one marker.<br>At the end of next turn, each opponent loses a Thug they choose.";
	cardtexts[41] = "<div class='requirement'>Cost: <img class='greedicon' src='images/holding.png' alt= 'Holding'> Put that Holding into your hand.</div>Next turn, any time you play a Holding, place as many extra markers markers on it as were on the Holding you paid, plus two.";
	cardtexts[42] = "<div class='requirement'>Need: <img class='greedicon' src='images/thug.png' alt= 'Thug'><img class='greedicon' src='images/thug.png' alt= 'Thug'></div><div class='permanenteffect'>When you play a Holding, place an extra marker on it.</div>";
	cardtexts[43] = "<div class='requirement'>Need: <img class='greedicon' src='images/key.png' alt= '🔫'><img class='greedicon' src='images/wrench.png' alt= '🔧'></div>Next turn, any time you play a Holding, place 3 extra markers on it.";
	cardtexts[44] = "Gain $20,000.<div class='permanenteffect'> When you play a Holding, remove a marker from it.</div>";
	cardtexts[45] = "<div class='permanenteffect'>When you lose a Thug or another Holding, place 2 markers on this. You can't lose markers from this Holding.</div>";
	cardtexts[46] = "<div class='permanenteffect'>Each turn: If there are no <img class='greedicon' src='images/bottle.png' alt= '🍼'> or <img class='greedicon' src='images/heart.png' alt= '❤'> Holdings in play, gain $10,000.</div>";
	cardtexts[47] = "<div class='requirement'>Need: <img class='greedicon' src='images/gun.png' alt= '🔫'><img class='greedicon' src='images/gun.png' alt= '🔫'></div>";
	cardtexts[48] = "<div class='requirement'>Need: <img class='greedicon' src='images/car.png' alt= '🚘'><img class='greedicon' src='images/car.png' alt= '🚘'></div>";
	cardtexts[49] = "<div class='requirement'>Need: <img class='greedicon' src='images/car.png' alt= '🚘'></div><div class='permanenteffect'>Each turn: If this holding has at least 3 markers on it, gain $5,000.</div>";
	cardtexts[50] = "Place a marker on each of your Holdings with no Icons (<img class='greedicon' src='images/bottle.png' alt= '🍼'>, <img class='greedicon' src='images/heart.png' alt= '❤'>, <img class='greedicon' src='images/wrench.png' alt= '🔧'>).";
	cardtexts[51] = "<div class='requirement'>Need: <img class='greedicon' src='images/key.png' alt= '🔫'><img class='greedicon' src='images/key.png' alt= '🔫'></div>";
	cardtexts[52] = "<div class='permanenteffect'>When you play another Holding, gain $5,000.<br>Holdings cost you $5,000 less.</div>";
	cardtexts[53] = "Turn over cards from the draw pile until you find a Thug. Play it, ignoring Costs and Needs. Discard all other cards that you revealed from the draw pile.";
	cardtexts[54] = "<div class='requirement'>Need: <img class='greedicon' src='images/thug.png' alt= 'Thug'></div>This Thug becomes a copy of one of your other Thugs; do its rules.";
	cardtexts[55] = "Do all of the rules of all of your Thugs played in previous turns.";
	cardtexts[56] = "<div class='requirement'>Need: <img class='greedicon' src='images/wrench.png' alt= '🔧'></div> Place as many markers on one of your Holdings as are on the Holding an opponent has with the most markers.";
	cardtexts[57] = "<div class='requirement'>Cost: <img class='greedicon' src='images/holding.png' alt= 'Holding'></div>Gain $5,000 for each marker the Holding with the most has.";
	cardtexts[58] = "<div class='requirement'>Cost: <img class='greedicon' src='images/holding.png' alt= 'Holding'></div>Each opponent removes a marker from each of their Holdings.";
	cardtexts[59] = "<div class='requirement'>Need: <img class='greedicon' src='images/gun.png' alt= '🔫'></div>Each opponent removes a marker from each of their Holdings.";
	cardtexts[60] = "Gain $5,000 for each Holding the player with the most Holdings has.";
	cardtexts[61] = "<div class='requirement'>Need: <img class='greedicon' src='images/gun.png' alt= '🔫'><img class='greedicon' src='images/car.png' alt= '🚘'></div><div class='permanenteffect'>When you play another Thug, gain $15,000.</div>";
	cardtexts[62] = "<div class='permanenteffect'>When you lose this Thug, gain $20,000.";
	cardtexts[63] = "Next turn, you ignore Costs.";
	cardtexts[64] = "Next turn, you ignore Needs.";
	cardtexts[65] = "Draw a card from the deck for each <img class='greedicon' src='images/key.png' alt= '🔫'> you have, putting them in your hand.";
	cardtexts[66] = "<div class='permanenteffect'>All Costs for you are reduced by $5,000.</div>";
	cardtexts[67] = "Next turn, after all played cards resolve, play an extra card.";
	cardtexts[68] = "Next turn, when you play an Action, return it to your hand afterwards.";
	cardtexts[69] = "<div class='requirement'>Need: <img class='greedicon' src='images/holding.png' alt= 'Holding'><img class='greedicon' src='images/holding.png' alt= 'Holding'></div><div class='permanenteffect'>Each turn: Gain $5,000.</div>";
	cardtexts[70] = "<div class='permanenteffect'>When you play an Action, gain $5,000 afterwards.</div>";
	cardtexts[71] = "<div class='requirement'>Cost: <img class='greedicon' src='images/thug.png' alt= 'Thug'></div><div class='permanenteffect'>When you play an Action, gain $5,000 per <img class='greedicon' src='images/gun.png' alt= '🔫'> you have afterwards.</div>";
	cardtexts[72] = "Next turn only, this Thug also has <img class='greedicon' src='images/gun.png' alt= '🔫'> and <img class='greedicon' src='images/key.png' alt= '🔫'>.";
	cardtexts[73] = "<div class='requirement'>Need: <img class='greedicon' src='images/gun.png' alt= '🔫'></div>Gain $5,000 for each Thug the player with the most Thugs has.<br>At the end of next turn, each player (including you) loses a Thug they choose.";
	cardtexts[74] = "If you have a <img class='greedicon' src='images/car.png' alt= '🚘'>, gain $10,000.<br>If you have a <img class='greedicon' src='images/gun.png' alt= '🔫'>, each opponent loses $10,000.<br>If you have a <img class='greedicon' src='images/key.png' alt= '🔫'>, draw a card from the deck, taking it to your hand.";
	cardtexts[75] = "Each opponent loses $10,000 for each Thug you have.";
	cardtexts[76] = "Each opponent loses $10,000.";
	cardtexts[77] = "Each other player loses $5,000 for each Holding that player has.";
	cardtexts[78] = "Next turn, double all $ you gain.";
	cardtexts[79] = "<div class='requirement'>Need: <img class='greedicon' src='images/car.png' alt= '🚘'></div>At the end of next turn, each opponent loses a Holding they choose.";
	cardtexts[80] = "Gain $10,000. Place a marker on one of your Holdings. You may play another card.";
}

function makeCardNames() {
	cardnames[1] = 'Harvey "Brains" Ratcliffe';
	cardnames[2] = 'Inform!';
	cardnames[3] = 'Shakedown!';
	cardnames[4] = 'Liquidate!';
	cardnames[5] = 'Insurance Scam!';
	cardnames[6] = '"Biscuits" O\'Malley';
	cardnames[7] = 'Bookie Joint';
	cardnames[8] = 'Chinatown';
	cardnames[9] = 'Daisy\'s Cookies';
	cardnames[10] = 'Dolls on Call';
	cardnames[11] = 'Joe\'s Gin Joint';
	cardnames[12] = 'Junkyard';
	cardnames[13] = 'Lamonte\'s Escort Service';
	cardnames[14] = 'Morticia\'s Absinthe Parlor';
	cardnames[15] = 'Paddy\'s Pub';
	cardnames[16] = 'Poor House';
	cardnames[17] = 'Sandy\'s Snooker \'n\' Schnaps';
	cardnames[18] = 'The Ritz';
	cardnames[19] = 'Trotsky\'s Burlesque';
	cardnames[20] = 'Beggars Banquet!';
	cardnames[21] = '"King" Richard the Third';
	cardnames[22] = 'Dickie "Flush" Diamond';
	cardnames[23] = 'Ed "Cheesecloth" McGinty';
	cardnames[24] = '"Generous" Jenny Jones';
	cardnames[25] = 'Mickey Istari';
	cardnames[26] = 'Circus of crime!';
	cardnames[27] = 'Gambit!';
	cardnames[28] = 'Estate heist!';
	cardnames[29] = 'Insider trading!';
	cardnames[30] = 'Museum heist!';
	cardnames[31] = 'Pickpocket network!';
	cardnames[32] = 'Smuggling!';
	cardnames[33] = 'Sting!';
	cardnames[34] = 'Sucker convention!';
	cardnames[35] = 'Suicide mission';
	cardnames[36] = 'Honest work!';
	cardnames[37] = 'Headquarters';
	cardnames[38] = 'Renovate!';
	cardnames[39] = 'Street walkers!';
	cardnames[40] = 'Take care of business!';
	cardnames[41] = 'Relocate!';
	cardnames[42] = 'Wolfgang Buttercup';
	cardnames[43] = 'Complex Scheme!';
	cardnames[44] = 'Loan Shark';
	cardnames[45] = 'Insurance Office';
	cardnames[46] = 'Jenny\'s Waterfront Dive';
	cardnames[47] = 'Krazy Kat Club';
	cardnames[48] = 'Massage Parlor';
	cardnames[49] = 'Sexy Sadie\'s';
	cardnames[50] = 'Six Corners';
	cardnames[51] = 'Thieves\' House';
	cardnames[52] = 'Zoning Office';
	cardnames[53] = '"Polycephalus" Patricia Johns';
	cardnames[54] = 'Ed "Rubberface" Teach';
	cardnames[55] = 'Hideout';
	cardnames[56] = 'Steal Ideas!';
	cardnames[57] = '"Peeping" Tom "Thumb"';
	cardnames[58] = 'Tommy\'s Guns \'n\' Ammo';
	cardnames[59] = 'Raid!';
	cardnames[60] = 'Protection Racket!';
	cardnames[61] = '"Friendly" Gus Caspar';
	cardnames[62] = '"Halloween" Jack Paris';
	cardnames[63] = '"Vicious" Syd Varney';
	cardnames[64] = '"Rotten" Johnny Simmons';
	cardnames[65] = '"Random" Scrub Patterson';
	cardnames[66] = '"Stingy" Stan McDowell';
	cardnames[67] = 'Louie "Savoir" O\'Farrell';
	cardnames[68] = 'Pete "Repeat" Fell';
	cardnames[69] = 'Natascha "The Squirrel" Rubin';
	cardnames[70] = '"Nothing beats" Rock Benson';
	cardnames[71] = 'Eugene "The Butcher" Midge';
	cardnames[72] = 'Ted "Napoleon" Bonham';
	cardnames[73] = 'One last heist!';
	cardnames[74] = 'Scouting!';
	cardnames[75] = 'Arson';
	cardnames[76] = 'Bobby "Corduroy" Brown';
	cardnames[77] = 'Jack "Cracker" Thompson';
	cardnames[78] = 'Master Plan!';
	cardnames[79] = 'Vandalism!';
	cardnames[80] = 'Seance';
}

function sendLobbyCommand(text) {
	socket.send(text);
}

function newTable() {
	sendLobbyCommand('{"newTable":true}');
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

function sendCommand(number) {
	socket.send("{\"gamecommand\" :" + number + "}");
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

function drawCard(card, number, buttontype) {
	var text = "";
	var type = "action";
	if (card.hasOwnProperty('cars')) {
		type = "thug";
	}
	if (card.hasOwnProperty('bottles')) {
		type = "holding";
	}
	text += "<span class = 'buttonwrapper' data-placement='auto' data-html=true data-toggle=\"tooltip\" data-title=\"" + cardtexts[card.timingNumber] + "\">";
	text += "<button class =\"" + buttontype + " " + type + " timingnumber" + card.timingNumber + "\" onclick=\"javascript:sendCommand(" + number + ");\"  disabled>";
	text += card.timingNumber + " - " + card.name;
	if (card.hasOwnProperty('markers')) {
		text += " (" + card.markers + ")";
	}
	for (var i = 0; i < card.cars; i++) {
		text += " <img class=\"greedicon\" src=\"images/car.png\" alt= \"🚘\">";
	}
	for (var i = 0; i < card.guns; i++) {
		text += " <img class=\"greedicon\" src=\"images/gun.png\" alt= \"🔫\">";
	}
	for (var i = 0; i < card.keys; i++) {
		text += " <img class=\"greedicon\" src=\"images/key.png\" alt= \"🔑\">";
	}
	for (var i = 0; i < card.bottles; i++) {
		text += " <img class=\"greedicon\" src=\"images/bottle.png\" alt= \"🍼\">";
	}
	for (var i = 0; i < card.hearts; i++) {
		text += " <img class=\"greedicon\" src=\"images/heart.png\" alt= \"❤\">";
	}
	for (var i = 0; i < card.wrenches; i++) {
		text += " <img class=\"greedicon\" src=\"images/wrench.png\" alt= \"🔧\">";
	}
	text += "</button></span>";
	//		console.log(text);
	return text;
}

function resetButtonStates() {
	draftbuttons = false;
	handbuttons = false;
	thugbuttons = false;
	holdingbuttons = false;
	yesbutton = false;
	nobutton = false;
	returnbutton = false;
}
function activateButtons() {
	var elements;
	elements = document.getElementsByClassName('handbutton');
	for (i = 0; i < elements.length; i++) {
		elements[i].disabled = !handbuttons;
	}
	elements = document.getElementsByClassName('draftbutton');
	for (i = 0; i < elements.length; i++) {
		elements[i].disabled = !draftbuttons;
	}
	elements = document.getElementsByClassName('thugbutton');
	for (i = 0; i < elements.length; i++) {
		elements[i].disabled = !thugbuttons;
	}
	elements = document.getElementsByClassName('holdingbutton');
	for (i = 0; i < elements.length; i++) {
		elements[i].disabled = !holdingbuttons;
	}
	if (document.getElementById('prompt') != "") {
		document.getElementById('yesbutton').hidden = !yesbutton;
		document.getElementById('nobutton').hidden = !nobutton;
		document.getElementById('returnbutton').hidden = !returnbutton;
	}
	//workaround to make Rubberface unable to select himself
	var rubberfaces = document.getElementsByClassName('timingnumber54');
	for (var i = 0; i < rubberfaces.length; i++) {
		var rubberface = rubberfaces[i];
		var classes = rubberface.classList;
		if (classes.contains('thugbutton')) {
			rubberface.disabled = true;
		}
	}
}


function numberWithCommas(x) {
	return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function drawPlayer(player, active) {
	var text = player.name;
	text += "&emsp;" + numberWithCommas(player.cash) + " $";
	text += "&emsp;" + player.cars + " <img class=\"greedicon\" src=\"images/car.png\" alt= \"🚘\">";
	text += "&emsp;" + player.guns + " <img class=\"greedicon\" src=\"images/gun.png\" alt= \"🔫\">";
	text += "&emsp;" + player.keys + " <img class=\"greedicon\" src=\"images/key.png\" alt= \"🔑\">";
	text += "&emsp;" + player.bottles + " <img class=\"greedicon\" src=\"images/bottle.png\" alt= \"🍼\">";
	text += "&emsp;" + player.hearts + " <img class=\"greedicon\" src=\"images/heart.png\" alt= \"❤\">";
	text += "&emsp;" + player.wrenches + " <img class=\"greedicon\" src=\"images/wrench.png\" alt= \"🔧\">";
	text += "</br>";
	text += "Holdings:"
	for (var i = 0; i < player.holdings.length; i++) {
		if (active) {
			text += drawCard(player.holdings[i], i, 'holdingbutton');
		}
		else {
			text += drawCard(player.holdings[i], i, '');
		}
	}
	text += "</br>";
	text += "Thugs:"
	for (var i = 0; i < player.thugs.length; i++) {
		if (active) {
			text += drawCard(player.thugs[i], i, 'thugbutton');
		}
		else {
			text += drawCard(player.thugs[i], i, '');
		}
	}
	text += "</br>";
	text += "Actions:"
	for (var i = 0; i < player.actions.length; i++) {
		if (active) {
			text += drawCard(player.actions[i], i, 'actionbutton');
		}
		else {
			text += drawCard(player.actions[i], i, '');
		}
	}
	text += "</br>";
	text += "</br>";
	return text;
}

function drawLobby(parsedMsg) {
	document.getElementById('game').hidden = true;
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
			//console.log(msg);
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
			if (parsedMsg.hasOwnProperty('request')) {
				resetButtonStates();
				var text = parsedMsg.reason;
				for (var i = 1; i <= 80; i++) {
					text = text.replace(RegExp(cardnames[i], "g"), "<span data-placement='auto' data-html=true data-toggle=\"tooltip\" data-title=\"" + cardtexts[i] + "\">" + cardnames[i] + "</span>");
				}
				showPrompt(text + "<button id=\"yesbutton\" onclick=\"javascript:sendCommand(0)\" hidden>Yes</button><button id=\"nobutton\" onclick=\"javascript:sendCommand(-1) \" hidden>No</button><button id=\"returnbutton\" onclick=\"javascript:joinLobby() \" hidden>Return to Lobby</button>");
				var requesttype = parsedMsg.request;
				//				console.log(requesttype);
				if (requesttype == 'hand') {
					handbuttons = true;
				}
				if (requesttype == 'draftPile') {
					draftbuttons = true;
				}
				if (requesttype == 'thug') {
					thugbuttons = true;
				}
				if (requesttype == 'holding') {
					holdingbuttons = true;
				}
				if (requesttype == 'YesNo') {
					yesbutton = true;
				}
				if (requesttype == 'returnToLobby') {
					returnbutton = true;
				}
				if (parsedMsg.optional) {
					nobutton = true;
				}
				activateButtons();
			}
			if (parsedMsg.type == 'lobby') {
				drawLobby(parsedMsg);
			}
			if (parsedMsg.type == 'chat') {
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