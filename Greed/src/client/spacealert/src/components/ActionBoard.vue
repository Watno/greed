<template>
  <div>
      <ActionBoardSpace v-for="(card, position) in board.cards" :card="card" :position="position+1" :selectedCard="selectedCard" :key="position" @select-card="selectCard" @flip-card="flipCard" @place-card="placeCard"/>
  </div>
</template>

<script lang="ts">
import {defineComponent} from "vue";
import ActionBoardModel from "../models/ActionBoardModel";
import SelectedCardModel from "../models/SelectedCardModel";
import ActionBoardSpace from "./ActionBoardSpace.vue";

export default defineComponent ({
    components: {ActionBoardSpace},
    props:{
        board:{
            type: ActionBoardModel,
            required: true
        },
        selectedCard:{
            type: SelectedCardModel,
        }
    },
    setup(props, {emit}){

      function selectCard(cardId: string) {
        emit('select-card', cardId);
      } 

      function flipCard(cardId: string) {
        emit('flip-card', cardId);
      } 

      function placeCard(position: number){
        emit('place-card', position);
      }

      return {selectCard, flipCard, placeCard}
    }
})

</script>

<style scoped>
div{
    display: flex;
}
</style>
