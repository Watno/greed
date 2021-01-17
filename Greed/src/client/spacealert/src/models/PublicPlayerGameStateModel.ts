import { Type } from "class-transformer";
import { IsEnum, IsDefined, IsInt } from "class-validator";
import ActionBoardModel from "./ActionBoardModel";
import { ColorModel } from './ColorModel';

export default class PublicPlayerGameStateModel {
    @IsEnum(ColorModel)
    @IsDefined()
    color: ColorModel;

    @Type(() => ActionBoardModel)
    @IsDefined()
    actionBoard: ActionBoardModel;

    @IsInt()
    handSize: number;

    constructor(color: ColorModel, actionBoard: ActionBoardModel, handSize: number) {
        this.color = color;
        this.actionBoard = actionBoard;
        this.handSize = handSize;
    }
}
