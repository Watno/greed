<template>
  <div v-if="loaded" >
    <Hand :cards="hand" :selectedCard="selectedCard" @select-card="onCardInHandSelected" @flip-card="onCardInHandFlipped" @return-selected-card="onReturnSelectedCardToHand" />
    <ActionBoard v-for="board in boards" :board="board" :key="board.Id" :selectedCard="selectedCard" @select-card="onCardInActionBoardSelected" @flip-card="onCardInActionBoardFlipped" @place-card="onCardPlaced"/>
  </div>
</template>

<script lang="ts">
import {defineComponent, ref} from "vue";
import Hand from "./components/Hand.vue";
import ActionBoard from "./components/ActionBoard.vue";
import ActionCardModel from "./models/ActionCardModel";
import {typesafeDeserialize, typesafeDeserializeArray} from "./typesafeDeserialize";
import SelectedCardModel from './models/SelectedCardModel';
import { SelectedCardPositionModel } from './models/SelectedCardPositionModel';
import { CardOrientationModel } from './models/CardOrientationModel';
import ActionBoardModel from './models/ActionBoardModel';
import { Guid } from "guid-typescript";

export default defineComponent({
  components: {Hand, ActionBoard},
  setup(){
    const hand = ref([] as ActionCardModel[]);
    const boards = ref([] as ActionBoardModel[]);
    const loaded = ref(false);
    const selectedCard = ref(null as SelectedCardModel | null);


    function refresh(){
      typesafeDeserializeArray(ActionCardModel, JSON.stringify(hand.value))
        .then(x => hand.value = x);
      typesafeDeserializeArray(ActionBoardModel, JSON.stringify(boards.value))
        .then(x => boards.value = x);
    }
    
    function findBoardById(id: string): ActionBoardModel{
      const board = boards.value.find(x => x.id == id);
      if (board == undefined) throw "No board with id" + id;
      return board;
    }

    function onCardInHandSelected(id: string){
      selectedCard.value = new SelectedCardModel(id, SelectedCardPositionModel.Hand, null);
      refresh();
    }

    function onCardInActionBoardSelected(boardId: string, cardId: string){
      selectedCard.value = new SelectedCardModel(cardId, SelectedCardPositionModel.ActionBoard, boardId);
      refresh();
    }

    function onCardInHandFlipped(id: string){
      const card = hand.value.find(x => x.id == id);
      if (card == null) return;
      switch (card.orientation){
        case CardOrientationModel.ACTION: card.orientation = CardOrientationModel.MOVEMENT; 
        break;
        case CardOrientationModel.MOVEMENT: card.orientation = CardOrientationModel.ACTION;
        break;
      }
      refresh();
    }

    function onCardInActionBoardFlipped(boardId: string, cardId: string){
      const board = findBoardById(boardId);
      const card = board.cards.find(x => x?.id == cardId);
      if (card == null) return;
      switch (card.orientation){
        case CardOrientationModel.ACTION: card.orientation = CardOrientationModel.MOVEMENT; 
        break;
        case CardOrientationModel.MOVEMENT: card.orientation = CardOrientationModel.ACTION;
        break;
      }
      refresh();
    }

    function onCardPlaced(boardId: string, position: number){
        const board = findBoardById(boardId);
        const card = hand.value.find(x => x.id == selectedCard.value?.id);
        if (card == null) return;
        board.cards[position-1] = card;
        hand.value.splice(hand.value.indexOf(card),1);

        selectedCard.value = null;
        refresh();
    }

    function onReturnSelectedCardToHand(){
        if (selectedCard.value?.position != SelectedCardPositionModel.ActionBoard || selectedCard.value.boardId == null) return;
        const board = findBoardById(selectedCard.value.boardId);
        const card = board.cards.find(x => x?.id == selectedCard.value?.id);
        if (card == null) return;
        board.cards[board.cards.indexOf(card)] = null;
        hand.value.push(card);

        selectedCard.value = null;
        refresh();
    }

    boards.value.push(new ActionBoardModel(Guid.create().toString(), Array(12).fill(null)));
    boards.value.push(new ActionBoardModel(Guid.create().toString(), Array(12).fill(null)));
    boards.value.push(new ActionBoardModel(Guid.create().toString(), Array(12).fill(null)));
    boards.value.push(new ActionBoardModel(Guid.create().toString(), Array(12).fill(null)));

    const json = `{"id": "42d2233f-2dff-42ed-9210-4ae29014663b", "actionHalf":{"type":"BEffect"},"movementHalf":{"type":"GravoliftMoveEffect"},"orientation":"ACTION"}`;
    const json2 = `{"id": "1e4c4c77-d7c4-4215-9889-6e94929cfd09", "actionHalf":{"type":"BEffect"},"movementHalf":{"type":"BlueMoveEffect"},"orientation":"MOVEMENT"}`;
    typesafeDeserialize(ActionCardModel, json)
      .then(x => {
        hand.value.push(x);
      })
      .then(() => {
        typesafeDeserialize(ActionCardModel, json2).then(x => {
          hand.value.push(x);
          loaded.value = true;
        });
      })
      .catch(e => {
        // eslint-disable-next-line no-console
        console.log(e);
      });
  
  return{hand, boards, loaded, selectedCard, onCardInHandSelected, onCardInHandFlipped, onReturnSelectedCardToHand, onCardInActionBoardSelected, onCardInActionBoardFlipped, onCardPlaced }
  }
})
</script>

<style scoped>
</style>
