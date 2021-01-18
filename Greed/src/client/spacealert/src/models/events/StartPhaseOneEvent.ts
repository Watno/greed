import AudioPlayer from "@/AudioPlayer";
import Event from "./Event";

export default class StartPhaseOneEvent extends Event {
    public playWhenTriggered(player: AudioPlayer): void {
        player.play("begin_first_phase.mp3")
    }

}
