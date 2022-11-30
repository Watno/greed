<template>
	<div>
		<Lobby v-if="!gameStarted && lobby != null" :lobby="lobby" />
		<div v-if="gameStarted">
			<Hand
				:cards="hand"
				:selectedCard="selectedCard"
				@select-card="onCardInHandSelected"
				@flip-card="onCardInHandFlipped"
				@return-selected-card="onReturnSelectedCardToHand"
			/>
			<div class="middle-row">
				<div class="playerBoardContainer">
					<PlayerArea
						:publicPlayerGameState="ownPublicPlayerGameState"
						:selectedCard="selectedCard"
						@select-card="onCardOnActionBoardSelected"
						@flip-card="onCardOnOwnActionBoardFlipped"
						@place-card="onCardPlacedToOwnActionBoard"
						:class="ownPublicPlayerGameState.color"
					/>
					<PlayerArea
						v-for="publicPlayerGameState in otherPublicPlayerGameStates"
						:publicPlayerGameState="publicPlayerGameState"
						:key="publicPlayerGameState.color"
						:selectedCard="selectedCard"
						:class="publicPlayerGameState.color"
					/>
					<Android
						v-for="android in androids"
						:android="android"
						:key="android.color"
						:selectedCard="selectedCard"
						@select-card="onCardOnActionBoardSelected"
						@flip-card="onCardOnAndroidActionBoardFlipped"
						@place-card="onCardPlacedToAndroidActionBoard"
						:class="android.color"
					/>
				</div>
				<Trajectory :trajectory="redTrajectory" :zone="'RED'"></Trajectory>
				<Trajectory :trajectory="whiteTrajectory" :zone="'WHITE'"></Trajectory>
				<Trajectory :trajectory="blueTrajectory" :zone="'BLUE'"></Trajectory>
				<Trajectory :trajectory="internalTrajectory"></Trajectory>
				<img :src="'./images/board.jpg'" class="board" />
			</div>
			<ThreatsBySpawn :threatsBySpawn="threatsBySpawn" />
		</div>
		<div v-if="gameOver" style="white-space: pre-wrap">{{JSON.stringify(JSON.parse(result),null,4)}}</div>
	</div>
</template>

<script lang="ts">
/* eslint-disable no-prototype-builtins */
import { computed, defineComponent, ref } from "vue";
import { typesafeDeserialize } from "./typesafeDeserialize";
import { sendGameCommand, setOnMessageCallback, startGame } from "./websocket";

import Lobby from "./components/Lobby.vue";
import Hand from "./components/Hand.vue";
import Android from "./components/Android.vue";
import PlayerArea from "./components/PlayerArea.vue";
import ThreatsBySpawn from "./components/ThreatsBySpawn.vue";
import Trajectory from "./components/Trajectory.vue";

import GameStateWithPrivateInfoModel from "./models/GameStateWithPrivateInfoModel";
import { ColorModel } from "./models/ColorModel";

import SelectedCardModel from "./models/SelectedCardModel";
import { SelectedCardPositionModel } from "./models/SelectedCardPositionModel";

import Notification from "./models/events/Notification";

import FlipCardInHandCommand from "./models/commands/FlipCardInHandCommand";
import FlipCardOnOwnActionBoardCommand from "./models/commands/FlipCardOnOwnActionBoardCommand";
import FlipCardOnAndroidActionBoardCommand from "./models/commands/FlipCardOnAndroidActionBoardCommand";
import PlaceCardOnOwnActionBoardCommand from "./models/commands/PlaceCardOnOwnActionBoardCommand";
import PlaceCardOnAndroidActionBoardCommand from "./models/commands/PlaceCardOnAndroidActionBoardCommand";
import RetrieveCardFromOwnActionBoardCommand from "./models/commands/RetrieveCardFromOwnActionBoardCommand";
import RetrieveCardFromAndroidActionBoardCommand from "./models/commands/RetrieveCardFromAndroidActionBoardCommand";
import LobbyModel from "./models/lobby/LobbyModel";
import AudioPlayer from "./AudioPlayer";
import { ZoneModel } from "./models/ZoneModel";

export default defineComponent({
	components: { Hand, Android, PlayerArea, ThreatsBySpawn, Lobby, Trajectory },
	setup() {
		const lobby = ref(null as LobbyModel | null);

		const audioPlayer = new AudioPlayer();

		const gameState = ref(null as GameStateWithPrivateInfoModel | null);
		const selectedCard = ref(null as SelectedCardModel | null);
		const result = ref("");

		const gameStarted = computed(() => gameState.value != null);
		const gameOver = computed(() => result.value != "");
		const hand = computed(() => gameState.value?.privateGameState.hand);
		const androids = computed(() => gameState.value?.publicGameState.androids);
		const threatsBySpawn = computed(() => gameState.value?.publicGameState.threatsBySpawn);
		const blueTrajectory = computed(() => gameState.value?.publicGameState.trajectories[ZoneModel.BLUE]);
		const whiteTrajectory = computed(() => gameState.value?.publicGameState.trajectories[ZoneModel.WHITE]);
		const redTrajectory = computed(() => gameState.value?.publicGameState.trajectories[ZoneModel.RED]);
		const internalTrajectory = computed(() => gameState.value?.publicGameState.internalTrajectory);
		const ownPublicPlayerGameState = computed(() =>
			gameState.value?.publicGameState.playerGameStates.find(
				(x) => x.color == gameState.value?.privateGameState.ownColor
			)
		);
		const otherPublicPlayerGameStates = computed(() =>
			gameState.value?.publicGameState.playerGameStates.filter(
				(x) => x.color != gameState.value?.privateGameState.ownColor
			)
		);
		const isSinglePlayer = computed(
			() =>
				gameState.value?.publicGameState.playerGameStates.length != null &&
				gameState.value?.publicGameState.playerGameStates.length == 1
		);

		function onCardInHandSelected(id: string) {
			selectedCard.value = new SelectedCardModel(
				id,
				SelectedCardPositionModel.Hand,
				null
			);
		}

		function onCardOnActionBoardSelected(
			boardColor: ColorModel,
			cardId: string
		) {
			selectedCard.value = new SelectedCardModel(
				cardId,
				SelectedCardPositionModel.ActionBoard,
				boardColor
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
			if (isSinglePlayer.value) {
				sendGameCommand(new FlipCardOnAndroidActionBoardCommand(cardId));
			}
		}

		function onCardPlacedToOwnActionBoard(color: ColorModel, position: number) {
			if (selectedCard.value != null) {
				sendGameCommand(
					new PlaceCardOnOwnActionBoardCommand(selectedCard.value.id, position)
				);
			}
			selectedCard.value = null;
		}

		function onCardPlacedToAndroidActionBoard(
			color: ColorModel,
			position: number
		) {
			if (selectedCard.value != null) {
				sendGameCommand(
					new PlaceCardOnAndroidActionBoardCommand(
						selectedCard.value.id,
						position,
						color
					)
				);
			}

			selectedCard.value = null;
		}

		function onReturnSelectedCardToHand() {
			if (
				selectedCard.value == null ||
				selectedCard.value?.position != SelectedCardPositionModel.ActionBoard ||
				selectedCard.value.boardColor == null
			) {
				return;
			}
			if (
				selectedCard.value.boardColor ==
				gameState.value?.privateGameState.ownColor
			) {
				sendGameCommand(
					new RetrieveCardFromOwnActionBoardCommand(selectedCard.value.id)
				);
			} else if (isSinglePlayer.value) {
				sendGameCommand(
					new RetrieveCardFromAndroidActionBoardCommand(
						selectedCard.value.id,
						selectedCard.value.boardColor
					)
				);
			}
			selectedCard.value = null;
		}

		function deserializeLobby(json: string) {
			typesafeDeserialize(LobbyModel, json).then((x) => (lobby.value = x));
		}

		function deserializeGameState(json: string) {
			typesafeDeserialize(GameStateWithPrivateInfoModel, json).then(
				(x) => (gameState.value = x)
			);
		}
		function deserializeNotification(json: string) {
			typesafeDeserialize(Notification, json).then((x) =>
				x.playAudio(audioPlayer)
			);
		}
		function showResult(json: string) {
			result.value = json;
		}

		setOnMessageCallback(function (msg) {
			const json = msg.data;
			if (JSON.parse(json).hasOwnProperty("tables")) {
				deserializeLobby(json);
			}
			if (JSON.parse(json).hasOwnProperty("publicGameState")) {
				deserializeGameState(json);
			}
			if (JSON.parse(json).hasOwnProperty("event")) {
				deserializeNotification(json);
			}
			if (JSON.parse(json).hasOwnProperty("won")) {
				showResult(json);
			}
		});

		return {
			gameStarted,
			gameOver,
			hand,
			androids,
			threatsBySpawn,
			blueTrajectory,
			whiteTrajectory,
			redTrajectory,
			internalTrajectory,
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
			lobby,
			result
		};
	},
});
</script>

<style scoped>
.playerBoardContainer {
	display: flex;
	flex-direction: column;
	width: fit-content;
}

.RED {
	border-style: solid;
	border-color: red;
}
.BLUE {
	border-style: solid;
	border-color: blue;
}
.YELLOW {
	border-style: solid;
	border-color: yellow;
}
.GREEN {
	border-style: solid;
	border-color: green;
}
.PURPLE {
	border-style: solid;
	border-color: purple;
}

.middle-row{
	display: flex;
	width: fit-content;
}

img.board{
	height: 25em;
	width: auto;
}
</style>
