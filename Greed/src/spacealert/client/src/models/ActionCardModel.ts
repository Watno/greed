import { Type } from "class-transformer";
import { ValidateNested, IsEnum, IsDefined, IsUUID } from "class-validator";
import {CardOrientationModel} from './CardOrientationModel';
import CardEffectModel from './CardEffectModel';

export default class ActionCardModel {
    @IsUUID()
    id: string

    @Type(() => CardEffectModel)
    @IsDefined()
    @ValidateNested()
    actionHalf: CardEffectModel;

    @ValidateNested()
    @IsDefined()
    @Type(() => CardEffectModel)
    movementHalf: CardEffectModel;

    @IsEnum(CardOrientationModel)
    @IsDefined()
    orientation: CardOrientationModel;

    constructor(id: string, actionHalf: CardEffectModel, movementHalf: CardEffectModel, orientation: CardOrientationModel) {
        this.id = id;
        this.actionHalf = actionHalf;
        this.movementHalf = movementHalf;
        this.orientation = orientation;
    }
}
