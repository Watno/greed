import AudioPlayer from "@/AudioPlayer";
import Event from "./Event";

export default class IncomingDataEvent extends Event{
    public playWhenTriggered(player: AudioPlayer): void {
        player.play("incoming_data.mp3")
    }
}
