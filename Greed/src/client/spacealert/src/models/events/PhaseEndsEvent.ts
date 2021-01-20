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
    public playWhenTriggered(player: AudioPlayer): void {
        player.play(this.getPhaseString() + "_ends.mp3")
            .then(() => this.playNextPhaseStarts(player))
            .then(() => { if (this.phase == Phase.THREE) { player.end() } });
    }

    playNextPhaseStarts(player: AudioPlayer): void {
        if (this.phase == Phase.ONE) {
            player.play("second_phase_begins.mp3");
        }
        if (this.phase == Phase.TWO) {
            player.play("third_phase_begins.mp3");
        }
    }

    getPhaseString(): string {
        switch (this.phase as Phase) {
            case Phase.ONE: return "first_phase"
            case Phase.TWO: return "second_phase"
            case Phase.THREE: return "operation"
        }
    }
}
