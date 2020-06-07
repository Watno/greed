<template>
  <div class="space">
      <div v-if="!hasCard" class="position" @click="placeCard">{{position}}</div>
      <ActionCard v-if="hasCard" class="card" :card="card" :selectedCard="selectedCard"  @select="selectCard" @flip="flipCard"/>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Emit } from 'vue-property-decorator'
import ActionCardModel from "../models/ActionCardModel";
import SelectedCardModel from "../models/SelectedCardModel";
import ActionCard from "./ActionCard.vue";

@Component({
    components: { ActionCard }
})
export default class ActionBoardSpace extends Vue {
    @Prop()
    position!: number

    @Prop()
    card!: ActionCardModel | null

    @Prop()
    selectedCard!: SelectedCardModel | null

    get hasCard(): boolean {
        return this.card != null;
    }

    @Emit()
    selectCard(id: string): string {
        return id;
    }

    @Emit()
    flipCard(id: string): string {
        return id;
    }

    @Emit()
    placeCard(): number {
        return this.position;
    }
}

</script>

<style scoped>
.space {
  width: 54px;
  height: 104px;
  display: inline-block;
  border:2px;
  margin:2px;
  border-color:black;
  border-style:solid;
}

.position {
    height: 100%;
    width: 100%;
    font-size:50px;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
