<template>
  <div v-if="loaded" >
    <Hand :cards="hand" :selectedCard="selectedCard" @select-card="onCardInHandSelected" @flip-card="onCardInHandFlipped" @return-selected-card="onReturnSelectedCardToHand" />
    <ActionBoard v-for="board in boards" :board="board" :key="board.Id" :selectedCard="selectedCard" @select-card="onCardInActionBoardSelected" @flip-card="onCardInActionBoardFlipped" @place-card="onCardPlaced"/>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import Hand from "./components/Hand.vue";
import ActionBoard from "./components/ActionBoard.vue";
import ActionCardModel from "./models/ActionCardModel";
import {typesafeDeserialize, typesafeDeserializeArray} from "./typesafeDeserialize";
import Component from 'vue-class-component';
import SelectedCardModel from './models/SelectedCardModel';
import { SelectedCardPositionModel } from './models/SelectedCardPositionModel';
import { CardOrientationModel } from './models/CardOrientationModel';
import ActionBoardModel from './models/ActionBoardModel';
import { Guid } from "guid-typescript";

@Component({
    components: {Hand, ActionBoard}
})
export default class App extends Vue{
  hand = {} as ActionCardModel[];
  boards = {} as ActionBoardModel[];
  loaded = false;
  selectedCard = null as SelectedCardModel | null

  onCardInHandSelected(id: string){
    this.selectedCard = new SelectedCardModel(id, SelectedCardPositionModel.Hand, null);
    this.refresh();
  }

  onCardInActionBoardSelected(boardId: string, cardId: string){
    this.selectedCard = new SelectedCardModel(cardId, SelectedCardPositionModel.ActionBoard, boardId);
    this.refresh();
  }

  onCardInHandFlipped(id: string){
    const card = this.hand.find(x => x.id == id);
    if (card == null) return;
    switch (card.orientation){
      case CardOrientationModel.ACTION: card.orientation = CardOrientationModel.MOVEMENT; 
      break;
      case CardOrientationModel.MOVEMENT: card.orientation = CardOrientationModel.ACTION;
      break;
    }
    this.refresh();
  }

  onCardInActionBoardFlipped(boardId: string, cardId: string){
    const board = this.findBoardById(boardId);
    const card = board.cards.find(x => x?.id == cardId);
    if (card == null) return;
    switch (card.orientation){
      case CardOrientationModel.ACTION: card.orientation = CardOrientationModel.MOVEMENT; 
      break;
      case CardOrientationModel.MOVEMENT: card.orientation = CardOrientationModel.ACTION;
      break;
    }
    this.refresh();
  }

  onCardPlaced(boardId: string, position: number){
      const board = this.findBoardById(boardId);
      const card = this.hand.find(x => x.id == this.selectedCard?.id);
      if (card == null) return;
      Vue.set(board.cards, position-1, card);
      Vue.delete(this.hand, this.hand.indexOf(card));

      this.selectedCard = null;
      this.refresh();
  }

    onReturnSelectedCardToHand(){
      if (this.selectedCard?.position != SelectedCardPositionModel.ActionBoard || this.selectedCard.boardId == null) return;
      const board = this.findBoardById(this.selectedCard.boardId);
      const card = board.cards.find(x => x?.id == this.selectedCard?.id);
      if (card == null) return;
      Vue.set(board.cards, board.cards.indexOf(card), null);
      this.hand.push(card);

      this.selectedCard = null;
      this.refresh();
  }

  findBoardById(id: string): ActionBoardModel{
    const board = this.boards.find(x => x.id == id);
    if (board == undefined) throw "No board with id" + id;
    return board;
  }

  refresh(){
    typesafeDeserializeArray(ActionCardModel, JSON.stringify(this.hand))
      .then(x => this.hand = x);
    typesafeDeserializeArray(ActionBoardModel, JSON.stringify(this.boards))
      .then(x => this.boards = x);
  }

  created() {
    this.boards = Array(0);
    this.boards.push(new ActionBoardModel(Guid.create().toString(), Array(12).fill(null)));
    this.boards.push(new ActionBoardModel(Guid.create().toString(), Array(12).fill(null)));
    this.boards.push(new ActionBoardModel(Guid.create().toString(), Array(12).fill(null)));
    this.boards.push(new ActionBoardModel(Guid.create().toString(), Array(12).fill(null)));

    const json = `{"id": "42d2233f-2dff-42ed-9210-4ae29014663b", "actionHalf":{"type":"BEffect"},"movementHalf":{"type":"GravoliftMoveEffect"},"orientation":"ACTION"}`;
    const json2 = `{"id": "1e4c4c77-d7c4-4215-9889-6e94929cfd09", "actionHalf":{"type":"BEffect"},"movementHalf":{"type":"BlueMoveEffect"},"orientation":"MOVEMENT"}`;
    this.hand = [];
    typesafeDeserialize(ActionCardModel, json)
      .then(x => {
        this.hand.push(x);
      })
      .then(() => {
        typesafeDeserialize(ActionCardModel, json2).then(x => {
          this.hand.push(x);
          this.loaded = true;
        });
      })
      .catch(e => {
        // eslint-disable-next-line no-console
        console.log(e);
      });
  }
}
</script>

<style scoped>
</style>
