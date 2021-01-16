import { IsUUID, IsEnum } from "class-validator";
import { ColorModel } from "./ColorModel";
import { SelectedCardPositionModel } from './SelectedCardPositionModel';

export default class SelectedCardModel{
    @IsUUID()
    id: string

    @IsEnum(SelectedCardPositionModel)
    position: SelectedCardPositionModel

    @IsEnum(ColorModel)
    boardColor: ColorModel | null

    constructor(id: string, position: SelectedCardPositionModel, boardColor: ColorModel | null) {
        this.id = id,
        this.position = position;
        this.boardColor = boardColor;
	}
}
