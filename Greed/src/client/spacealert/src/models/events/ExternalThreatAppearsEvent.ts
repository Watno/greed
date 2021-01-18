import { IsBoolean, IsEnum, IsInt } from "class-validator";
import { Zone } from "./Zone";
import Event from "./Event";
import AudioPlayer from "@/AudioPlayer";

export default class ExternalThreatAppearsEvent extends Event{
    @IsInt()
    private turn: number    

    @IsBoolean()
    private seriousity: boolean

    @IsEnum(Zone)
    private zone: Zone

    constructor(seriousity: boolean, zone: Zone, turn: number) {
        super();
        this.turn = turn;
        this.seriousity = seriousity;
        this.zone = zone;
    }

    public playWhenTriggered(player: AudioPlayer): void {
        player.playInSequence(["alert.mp3", this.getTimeAudioFile(), this.getThreatAudioFile(), this.getZoneAudioFile(), "repeat.mp3", this.getTimeAudioFile(), this.getZoneAudioFile(), this.getThreatAudioFile() ])
    }

    getTimeAudioFile(): string {
        return "time_t_plus_"+this.turn+".mp3";
    }

    getThreatAudioFile(): string {
        return this.seriousity? "serious_threat.mp3": "threat.mp3";
    }

    getZoneAudioFile(): string {
        switch (this.zone as Zone){
            case Zone.BLUE: return "zone_blue.mp3";
            case Zone.WHITE: return "zone_white.mp3";
            case Zone.RED: return "zone_red.mp3";
        }
    }
}