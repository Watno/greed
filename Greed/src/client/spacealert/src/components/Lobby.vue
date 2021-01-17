<template>
	<div>
		<div v-if="!nameSelected">
			<button @click="changeName">Select name</button>
			<input
				v-model="name"
				placeholder="Enter name"
				v-on:keyup.enter="changeName"
			/>
		</div>
		<div v-if="nameSelected">
			<button v-if="!ownTable" @click="newTable"> New table</button>
			<Table v-for="(table, index) in lobby.tables" :table="table" :index="index" :isOwn="table == ownTable" :key="index" />
		</div>
	</div>
</template>

<script lang="ts">
import Table from "@/components/Table.vue";
import { sendLobbyCommand } from "@/websocket";
import { computed, defineComponent, ref } from "vue";
import LobbyModel from "../models/lobby/LobbyModel";
export default defineComponent({
	components: { Table },
	props: {
		lobby: {
			type: LobbyModel,
			required: true,
		},
	},
	setup(props) {
		const name = ref("");
		const nameSelected = ref(false);

		const ownTable = computed(() => (props.lobby.tablenumber != undefined)? props.lobby.tables[props.lobby.tablenumber]: null)

		function changeName() {
			if (name.value != "") {
				sendLobbyCommand('{"namechange":"' + name.value + '"}');
				nameSelected.value = true;
			}
		}

		function newTable() {
			sendLobbyCommand('{"newTable":true}');
			sendLobbyCommand('{"changePlayers":4}');
		}

		return { name, nameSelected, changeName, newTable, ownTable };
	},
});
</script>

<style scoped>
</style>
