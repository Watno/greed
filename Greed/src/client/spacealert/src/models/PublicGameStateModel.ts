import { Type } from "class-transformer";
import { IsArray, ValidateNested } from "class-validator";
import AndroidModel from "./AndroidModel";
import PublicPlayerGameStateModel from "./PublicPlayerGameStateModel";
import ThreatModel from "./ThreatModel";
import TrajectoryModel from "./TrajectoryModel";
import { ZoneModel } from "./ZoneModel";

export default class PublicGameStateModel {
    @IsArray()
    @Type(() => PublicPlayerGameStateModel)
    @ValidateNested()
    playerGameStates: PublicPlayerGameStateModel[];

    @IsArray()
    @Type(() => AndroidModel)
    @ValidateNested()
    androids: AndroidModel[];

    @IsArray()
    @ValidateNested()
    threatsBySpawn: ThreatModel[][];

    @ValidateNested()
    trajectories: Map<ZoneModel,TrajectoryModel>;

    @ValidateNested()
    internalTrajectory: TrajectoryModel;
  
    constructor(playerGameStates: PublicPlayerGameStateModel[], androids: AndroidModel[], threatsBySpawn: ThreatModel[][], trajectories: Map<ZoneModel,TrajectoryModel>, internalTrajectory: TrajectoryModel) {
        this.playerGameStates = playerGameStates;
        this.androids = androids;
        this.threatsBySpawn = threatsBySpawn;
        this.trajectories = trajectories;
        this.internalTrajectory = internalTrajectory;
    }
}
