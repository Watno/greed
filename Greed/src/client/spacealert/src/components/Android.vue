<template>
  <div>
      <ActionBoard
				:board="android.actionBoard"
				:selectedCard="selectedCard"
				@select-card="onCardSelected"
				@flip-card="onCardFlipped"
				@place-card="onCardPlaced"
        :class="android.color"
			/>
  </div>
</template>

<script lang="ts">
import {defineComponent} from "vue";
import AndroidModel from "../models/AndroidModel";
import SelectedCardModel from "../models/SelectedCardModel";
import ActionBoard from "./ActionBoard.vue";

export default defineComponent ({
    components: {ActionBoard},
    props:{
        android:{
            type: AndroidModel,
            required: true
        },
        selectedCard:{
            type: SelectedCardModel,
        }
    },
    setup(props, {emit}){

      function onCardSelected(cardId: string) {
        emit('select-card', props.android.color, cardId);
      } 

      function onCardFlipped(cardId: string) {
        emit('flip-card', props.android.color, cardId);
      } 

      function onCardPlaced(position: number){
        emit('place-card', props.android.color, position);
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
