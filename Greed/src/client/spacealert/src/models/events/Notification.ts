import { Type } from 'class-transformer';
import { IsEnum, ValidateNested } from 'class-validator';
import ExternalThreatAppearsEvent from './ExternalThreatAppearsEvent';
import Event from './Event';
import { NotificationType } from './NotificationType';
import CommunicationsDownEvent from './CommunicationsDownEvent';
import DataTransferEvent from './DataTransferEvent';
import IncomingDataEvent from './IncomingDataEvent';
import PhaseEndsEvent from './PhaseEndsEvent';
import StartPhaseOneEvent from './StartPhaseOneEvent';
import AudioPlayer from '@/AudioPlayer';


export default class Notification {
    @IsEnum(NotificationType)
    private notificationType: NotificationType;

    @Type(() => Event, {
        discriminator: {
            property: 'type',
            subTypes: [
                { value: CommunicationsDownEvent, name: 'CommunicationsDownEvent' },
                { value: DataTransferEvent, name: 'DataTransferEvent' },
                { value: ExternalThreatAppearsEvent, name: 'ExternalThreatAppearsEvent' },
                { value: IncomingDataEvent, name: 'IncomingDataEvent' },
                { value: ExternalThreatAppearsEvent, name: 'ExternalThreatAppearsEvent' },
                { value: PhaseEndsEvent, name: 'PhaseEndsEvent' },
                { value: StartPhaseOneEvent, name: 'StartPhaseOneEvent' }
            ],
        },
    })
    @ValidateNested()
    private event: Event;

    constructor(notificationType: NotificationType, event: Event) {
        this.notificationType = notificationType;
        this.event = event;
    }

    public playAudio(player: AudioPlayer) {
        this.event.playAudio(player, this.notificationType);
    }
}