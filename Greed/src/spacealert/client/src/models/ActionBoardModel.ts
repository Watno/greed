import { Type } from "class-transformer";
import { ValidateNested, IsUUID, IsArray, Length } from "class-validator";
import ActionCardModel from './ActionCardModel';

export default class ActionBoardModel {
    @IsUUID()
    id: string

    @IsArray()
    @Length(12)
    @Type(() => ActionCardModel)
    @ValidateNested()
    cards: (ActionCardModel | null)[]

    constructor(id: string, cards: (ActionCardModel | null)[]) {
        this.id = id;
        this.cards = cards;
    }
}
