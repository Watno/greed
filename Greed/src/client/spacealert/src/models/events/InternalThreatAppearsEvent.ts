import AudioPlayer from "@/AudioPlayer";
import { IsBoolean, IsInt } from "class-validator";
import Event from "./Event";

export default class ExternalThreatAppearsEvent extends Event{
    @IsInt()
    private turn: number    

    @IsBoolean()
    private seriousity: boolean


    constructor(seriousity: boolean, turn: number) {
        super();
        this.turn = turn;
        this.seriousity = seriousity;
    }

    public playWhenTriggered(player: AudioPlayer): void {
        player.playInSequence(["alert.mp3", this.getTimeAudioFile(), this.getThreatAudioFile(), "repeat.mp3", this.getTimeAudioFile(), this.getThreatAudioFile() ])
    }

    getTimeAudioFile(): string {
        return "time_t_plus_"+this.turn+".mp3";
    }

    getThreatAudioFile(): string {
        return this.seriousity? "serious_internal_threat.mp3": "internal_threat.mp3";
    }
}