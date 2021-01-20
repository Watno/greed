export default class AudioPlayer {
    private currentlyPlayingAudio: HTMLAudioElement | undefined;
    private started = false;

    public start() {
        if (!this.started) {
            this.playBackground();
            this.started = true;
        }

    }

    public end() {
        this.currentlyPlayingAudio?.pause();
    }

    public async playInSequence(audiofiles: string[]): Promise<void> {
        for (const file of audiofiles) {
            await this.playInternal(file, false);
        }
        this.playBackground();
    }

    public async play(audiofile: string) {
        await this.playInternal(audiofile, false);
        this.playBackground();
    }

    public async playThenLoop(audiofile: string, audioFileToLoop: string) {
        await this.playInternal(audiofile, false)
        this.playInternal(audioFileToLoop, true);
    }

    private playInternal(audiofile: string, inLoop: boolean): Promise<void> {
        this.currentlyPlayingAudio?.pause();
        this.currentlyPlayingAudio = new Audio("./audio/" + audiofile);
        this.currentlyPlayingAudio.loop = inLoop;
        this.currentlyPlayingAudio.play();
        this.currentlyPlayingAudio.onerror = x => console.warn(x);
        return new Promise((resolve, reject) => {
            this.currentlyPlayingAudio!.onended = () => resolve();
            this.currentlyPlayingAudio!.onabort = () => reject();
        })
    }

    private playBackground() {
        this.playInternal("red_alert_1.mp3", true);
    }
} 