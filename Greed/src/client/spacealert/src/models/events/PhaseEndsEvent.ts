import { IsEnum } from "class-validator";
import { Phase } from "./Phase";
import Event from "./Event";
import AudioPlayer from "@/AudioPlayer";

export default class PhaseEndsEvent extends Event {
    @IsEnum(Phase)
    private phase: Phase

    constructor(phase: Phase) {
        super();
        this.phase = phase;
    }

    public playWhenHappensInAMinute(player: AudioPlayer): void {
        player.play(this.getPhaseString() + "_ends_in_1_minute.mp3")
    }
    public playWhenHappensInTwentySeconds(player: AudioPlayer): void {
        player.play(this.getPhaseString() + "_ends_in_20_seconds.mp3");
    }
    public playWhenCountdownStarts(player: AudioPlayer): void {
        switch (this.phase as Phase) {
            case Phase.ONE: player.playInSequence([this.getPhaseString() + "_ends.mp3", this.getNextPhaseString() + "_begins.mp3"]); break;
            case Phase.TWO: player.playInSequence([this.getPhaseString() + "_ends.mp3", this.getNextPhaseString() + "_begins.mp3"]); break;
            case Phase.THREE: player.playThenEnd(this.getPhaseString() + "_ends.mp3")
        }
    }

    getPhaseString(): string {
        switch (this.phase as Phase) {
            case Phase.ONE: return "first_phase"
            case Phase.TWO: return "second_phase"
            case Phase.THREE: return "operation"
        }
    }

    getNextPhaseString(): string {
        switch (this.phase as Phase) {
            case Phase.ONE: return "second_phase"
            case Phase.TWO: return "third_phase"
            case Phase.THREE: throw "No next phase"
        }
    }

}
