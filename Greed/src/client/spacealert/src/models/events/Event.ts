import AudioPlayer from "@/AudioPlayer";
import { NotificationType } from "./NotificationType";

export default abstract class Event {
    public playAudio(player: AudioPlayer, notificationType: NotificationType) {
        switch (notificationType as NotificationType) {
            case NotificationType.TRIGGERED: this.playWhenTriggered(player); break;
            case NotificationType.ENDED: this.playWhenEnded(player); break;
            case NotificationType.HAPPENSINAMINUTE: this.playWhenHappensInAMinute(player); break;
            case NotificationType.HAPPENSINTWENTYSECONDS: this.playWhenHappensInTwentySeconds(player); break;
        }
    }

    protected playWhenTriggered(player: AudioPlayer): void {
        // override if necessary
    }

    protected playWhenEnded(player: AudioPlayer): void {
        // override if necessary
    }

    protected playWhenHappensInAMinute(player: AudioPlayer): void {
        // override if necessary
    }

    protected playWhenHappensInTwentySeconds(player: AudioPlayer): void {
        // override if necessary
    }
}