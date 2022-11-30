import { ZoneModel } from "./ZoneModel";

export default class ThreatModel {
    type: string;

    zone: ZoneModel;

    constructor(type: string, zone: ZoneModel) {
        this.type = type;
        this.zone = zone;
    }
}