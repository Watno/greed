import { Type } from "class-transformer";
import { IsDefined } from "class-validator";
import PrivateGameStateModel from "./PrivateGameStateModel";
import PublicGameStateModel from "./PublicGameStateModel";

export default class GameStateWithPrivateInfoModel {
    @Type(() => PublicGameStateModel)
    @IsDefined()
    publicGameState: PublicGameStateModel;

    @Type(() => PrivateGameStateModel)
    @IsDefined()
    privateGameState: PrivateGameStateModel

    constructor(publicGameState: PublicGameStateModel, privateGameState: PrivateGameStateModel) {
        this.publicGameState = publicGameState;
        this.privateGameState = privateGameState;
    }
}
