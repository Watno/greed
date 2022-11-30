<template>
  <div>
      <ActionCard v-for="card in cards" :card="card" :key="card.id" :selectedCard="selectedCard" @select="selectCard" @flip="flipCard" />
      <div class="returnToHand" v-if="cardOnBoardSelected" @click="returnSelectedCard"><div class="returnToHandText">Return selected card to hand</div></div>
  </div>
</template>

<script lang="ts">
import {defineComponent, computed, PropType} from "vue";
import ActionCardModel from "../models/ActionCardModel";
import SelectedCardModel from "../models/SelectedCardModel";
import ActionCard from "./ActionCard.vue";
import { SelectedCardPositionModel } from '../models/SelectedCardPositionModel';

export default defineComponent({
    components: {ActionCard},
    props: {
        cards:{
            type: Array as PropType<Array<ActionCardModel>>,
            required: true
        },
        selectedCard:{
            type: SelectedCardModel,
        }
    },
    setup(props, {emit}){
        const cardOnBoardSelected = computed(() => (props.selectedCard != null) && props.selectedCard.position == SelectedCardPositionModel.ActionBoard )
         
        function selectCard(cardId: string) {
            emit('select-card', cardId);
        } 

        function flipCard(cardId: string) {
            emit('flip-card', cardId);
        } 

        function returnSelectedCard(){
            emit('return-selected-card')
        }

        return {cardOnBoardSelected, selectCard, flipCard, returnSelectedCard};
    }
});

</script>

<style scoped>
.returnToHand {
    width: 3em;
    height: 6em;
    display: inline-block;
    border: 0.1em;
    border-color:black;
    border-style:dotted;
}

.returnToHandText {
    font-size: 0.9em;
    display: flex;
    text-align: center;
    justify-content: center;
    align-items: center;
}

div{
    display: flex;
    height: 6.2em
}
</style>
