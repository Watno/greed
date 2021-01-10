import { IsUUID, IsEnum } from "class-validator";
import { SelectedCardPositionModel } from './SelectedCardPositionModel';

export default class SelectedCardModel{
    @IsUUID()
    id: string

    @IsEnum(SelectedCardPositionModel)
    position: SelectedCardPositionModel

    @IsUUID()
    boardId: string | null

    constructor(id: string, position: SelectedCardPositionModel, boardId: string | null) {
        this.id = id,
        this.position = position;
        this.boardId = boardId;
	}
}
