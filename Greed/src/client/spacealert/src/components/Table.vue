<template>
	<div>
		<span> Table {{index+1}}</span>
		<button v-if="!isOwn" @click="joinTable">Join table</button>
		<button v-if="isOwn" @click="leaveTable">Leave table</button>
		<span v-for="player in table.players" :key="player">{{player}}</span>
		<button v-if="isOwn" @click="ready">Ready</button>
	</div>
</template>

<script lang="ts">
import TableModel from "@/models/lobby/TableModel";
import { sendLobbyCommand } from "@/websocket";
import { defineComponent } from "vue";
export default defineComponent({
	props: {
		table: {
			type: TableModel,
			required: true
		},
		isOwn: {
			type: Boolean,
			required: true
		},
		index: {
			type: Number,
			required: true
		}
	},
	setup(props) {

		function ready() {
			sendLobbyCommand('{"ready":' + true + "}");
		}

		function joinTable() {
			sendLobbyCommand('{"joinTable":' + props.index + "}");
		}

		function changePlayers(number: number) {
			sendLobbyCommand('{"changePlayers":' + number + "}");
		}

		function leaveTable() {
			sendLobbyCommand('{"leaveTable":false}');
		}


		return { joinTable, ready, leaveTable, changePlayers };
	},
});
</script>

<style scoped>
div{
    display: flex;
	margin: auto;
}
</style>
