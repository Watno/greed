import { Type } from "class-transformer";
import { IsEnum, IsDefined } from "class-validator";
import ActionBoardModel from "./ActionBoardModel";
import { ColorModel } from './ColorModel';

export default class AndroidModel {
    @IsEnum(ColorModel)
    @IsDefined()
    color: ColorModel;

    @Type(() => ActionBoardModel)
    @IsDefined()
    actionBoard: ActionBoardModel;

    constructor(color: ColorModel, actionBoard: ActionBoardModel) {
        this.color = color;
        this.actionBoard = actionBoard;
    }
}
