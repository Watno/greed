<template>
	<div>
		<button v-if="!gameStarted" @click="startGame">Start</button>
		<div v-if="gameStarted">
			<Hand
				:cards="hand"
				:selectedCard="selectedCard"
				@select-card="onCardInHandSelected"
				@flip-card="onCardInHandFlipped"
				@return-selected-card="onReturnSelectedCardToHand"
			/>
			<PlayerArea
				:publicPlayerGameState="ownPublicPlayerGameState"
				:selectedCard="selectedCard"
				@select-card="onCardOnActionBoardSelected"
				@flip-card="onCardOnOwnActionBoardFlipped"
				@place-card="onCardPlacedToOwnActionBoard"
			/>
			<PlayerArea
				v-for="publicPlayerGameState in otherPublicPlayerGameStates"
				:publicPlayerGameState="publicPlayerGameState"
				:key="publicPlayerGameState.color"
				:selectedCard="selectedCard"
			/>
			<Android
				v-for="android in androids"
				:android="android"
				:key="android.color"
				:selectedCard="selectedCard"
				@select-card="onCardOnActionBoardSelected"
				@flip-card="onCardOnAndroidActionBoardFlipped"
				@place-card="onCardPlacedToAndroidActionBoard"
			/>
		</div>
	</div>
</template>

<script lang="ts">
import { computed, defineComponent, ref } from "vue";
import {
	typesafeDeserialize,
	typesafeDeserializeArray,
} from "./typesafeDeserialize";
import { Guid } from "guid-typescript";
import { getServerlocation } from "./serverlocation";

import Hand from "./components/Hand.vue";
import Android from "./components/Android.vue";
import PlayerArea from "./components/PlayerArea.vue";

import ActionCardModel from "./models/ActionCardModel";
import GameStateWithPrivateInfoModel from "./models/GameStateWithPrivateInfoModel";
import { ColorModel } from "./models/ColorModel";

import SelectedCardModel from "./models/SelectedCardModel";
import { SelectedCardPositionModel } from "./models/SelectedCardPositionModel";

import FlipCardInHandCommand from "./models/commands/FlipCardInHandCommand";
import FlipCardOnOwnActionBoardCommand from "./models/commands/FlipCardOnOwnActionBoardCommand";
import FlipCardOnAndroidActionBoardCommand from "./models/commands/FlipCardOnAndroidActionBoardCommand";
import PlaceCardOnOwnActionBoardCommand from "./models/commands/PlaceCardOnOwnActionBoardCommand";
import PlaceCardOnAndroidActionBoardCommand from "./models/commands/PlaceCardOnAndroidActionBoardCommand";
import RetrieveCardFromOwnActionBoardCommand from "./models/commands/RetrieveCardFromOwnActionBoardCommand";
import RetrieveCardFromAndroidActionBoardCommand from "./models/commands/RetrieveCardFromAndroidActionBoardCommand";

export default defineComponent({
	components: { Hand, Android, PlayerArea },
	setup() {
		const socket = new WebSocket(getServerlocation());
		const gameState = ref(null as GameStateWithPrivateInfoModel);
		const selectedCard = ref(null as SelectedCardModel | null);

		function sendGameCommand(object: object) {
			console.log(object);
			socket.send(
				JSON.stringify({
					gamecommand: object,
				})
			);
		}

		function onCardInHandSelected(id: string) {
			selectedCard.value = new SelectedCardModel(
				id,
				SelectedCardPositionModel.Hand,
				null
			);
		}

		function onCardOnActionBoardSelected(boardId: string, cardId: string) {
			selectedCard.value = new SelectedCardModel(
				cardId,
				SelectedCardPositionModel.ActionBoard,
				boardId
			);
		}

		function onCardInHandFlipped(id: string) {
			sendGameCommand(new FlipCardInHandCommand(id));
		}

		function onCardOnOwnActionBoardFlipped(color: ColorModel, cardId: string) {
			sendGameCommand(new FlipCardOnOwnActionBoardCommand(cardId));
		}

		function onCardOnAndroidActionBoardFlipped(
			boardId: string,
			cardId: string
		) {
			sendGameCommand(new FlipCardOnAndroidActionBoardCommand(cardId));
		}

		function onCardPlacedToOwnActionBoard(
			color: ColorModel,
			position: number
		) {
			sendGameCommand(new PlaceCardOnOwnActionBoardCommand(selectedCard.value.id, position));
		}

		function onCardPlacedToAndroidActionBoard(
			color: ColorModel,
			position: number
		) {
			sendGameCommand(new PlaceCardOnAndroidActionBoardCommand(selectedCard.value.id, position, color));
		}

		function onReturnSelectedCardToHand() {
			if (
				selectedCard.value?.position != SelectedCardPositionModel.ActionBoard ||
				selectedCard.value.boardId == null
			)
				return;
		}

		const gameStarted = computed(() => gameState.value != null);
		const hand = computed(() => gameState.value.privateGameState.hand);
		const androids = computed(() => gameState.value.publicGameState.androids);
		const ownPublicPlayerGameState = computed(() =>
			gameState.value.publicGameState.playerGameStates.find(
				(x) => x.color == gameState.value.privateGameState.ownColor
			)
		);
		const otherPublicPlayerGameStates = computed(() =>
			gameState.value.publicGameState.playerGameStates.filter(
				(x) => x.color != gameState.value.privateGameState.ownColor
			)
		);

		function startGame() {
			socket.send('{"newTable":"spacealert"}');
			socket.send('{"changePlayers":4}');
			socket.send('{"ready":true}');
		}

		socket.onopen = function () {
			console.log("Connected to WebSocket.");
		};
		socket.onmessage = function (msg) {
			console.log(msg.data);
			typesafeDeserialize(GameStateWithPrivateInfoModel, msg.data).then(
				(x) => (gameState.value = x)
			);
		};
		socket.onerror = function (msg) {
			alert("Sorry but there was an error.\n\n" + msg);
		};
		socket.onclose = function () {
			alert("Server offline.");
		};

		return {
			gameStarted,
			hand,
			androids,
			ownPublicPlayerGameState,
			otherPublicPlayerGameStates,
			selectedCard,
			onCardInHandSelected,
			onCardInHandFlipped,
			onReturnSelectedCardToHand,
			onCardOnActionBoardSelected,
			onCardOnOwnActionBoardFlipped,
			onCardOnAndroidActionBoardFlipped,
			onCardPlacedToOwnActionBoard,
			onCardPlacedToAndroidActionBoard,
			startGame,
		};
	},
});
</script>

<style scoped>
</style>
