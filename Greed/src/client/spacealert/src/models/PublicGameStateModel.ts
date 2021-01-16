import { Type } from "class-transformer";
import { IsArray, ValidateNested } from "class-validator";
import AndroidModel from "./AndroidModel";
import PublicPlayerGameStateModel from "./PublicPlayerGameStateModel";

export default class PublicGameStateModel {
    @IsArray()
    @Type(() => PublicPlayerGameStateModel)
    @ValidateNested()
    playerGameStates: PublicPlayerGameStateModel[];

    @IsArray()
    @Type(() => AndroidModel)
    @ValidateNested()
    androids: AndroidModel[];

    constructor(playerGameStates: PublicPlayerGameStateModel[], androids: AndroidModel[]) {
        this.playerGameStates = playerGameStates;
        this.androids = androids;
    }
}
