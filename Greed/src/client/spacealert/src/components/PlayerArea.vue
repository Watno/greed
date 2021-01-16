<template>
  <div>
      <ActionBoard
				:board="publicPlayerGameState.actionBoard"
				:selectedCard="selectedCard"
				@select-card="onCardSelected"
				@flip-card="onCardFlipped"
				@place-card="onCardPlaced"
			/>
  </div>
</template>

<script lang="ts">
import {defineComponent} from "vue";
import PublicPlayerGameStateModel from "../models/PublicPlayerGameStateModel";
import SelectedCardModel from "../models/SelectedCardModel";
import ActionBoard from "./ActionBoard.vue";

export default defineComponent ({
    components: {ActionBoard},
    props:{
        publicPlayerGameState:{
            type: PublicPlayerGameStateModel,
            required: true
        },
        selectedCard:{
            type: SelectedCardModel,
        }
    },
    setup(props, {emit}){

      function onCardSelected(cardId: string) {
        emit('select-card', props.publicPlayerGameState.color, cardId);
      } 

      function onCardFlipped(cardId: string) {
        emit('flip-card', props.publicPlayerGameState.color, cardId);
      } 

      function onCardPlaced(position: number){
        emit('place-card', props.publicPlayerGameState.color, position);
      }

      return {onCardSelected, onCardFlipped, onCardPlaced}
    }
})

</script>

<style scoped>
div{
    display: flex;
}
</style>
