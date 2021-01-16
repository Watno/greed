import { Type } from "class-transformer";
import { IsEnum, IsDefined, IsArray, ValidateNested } from "class-validator";
import ActionCardModel from "./ActionCardModel";
import { ColorModel } from './ColorModel';

export default class PrivateGameStateModel {
    @IsEnum(ColorModel)
    @IsDefined()
    ownColor: ColorModel;

    @IsArray()
    @Type(() => ActionCardModel)
    @ValidateNested()
    hand: (ActionCardModel | null)[]

    constructor(ownColor: ColorModel, hand: ActionCardModel[]) {
        this.ownColor = ownColor;
        this.hand = hand;
    }
}
