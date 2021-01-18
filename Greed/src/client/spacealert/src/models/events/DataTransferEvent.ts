import AudioPlayer from "@/AudioPlayer";
import Event from "./Event";

export default class DataTransferEvent extends Event {
    public playWhenTriggered(player: AudioPlayer): void {
        player.play("data_transfer.mp3")
    }
}
