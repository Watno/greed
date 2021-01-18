import AudioPlayer from "@/AudioPlayer";
import Event from "./Event";

export default class CommunicationsDownEvent extends Event {
    public playWhenTriggered(player: AudioPlayer): void {
        player.play("communications_down.mp3")
            .then(() => player.playInLoop("white_noise.ogg"));
    }

    public playWhenEnded(player: AudioPlayer): void {
        player.play("communications_restored.mp3")
    }
}