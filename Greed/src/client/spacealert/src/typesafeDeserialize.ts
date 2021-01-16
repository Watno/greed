import "es6-shim";
import "reflect-metadata";
import {deserialize, deserializeArray} from "class-transformer";
import {validateOrReject} from "class-validator";

declare type ClassType<T> = {
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    new (...args: any[]): T;
};

export async function typesafeDeserialize<T>(type: ClassType<T>, json: string): Promise<T>{
    const result = deserialize<T>(type, json);
    await validateOrReject(result, { })
    return result;
}

export async function typesafeDeserializeArray<T>(type: ClassType<T>, json: string): Promise<T[]>{
    const result = deserializeArray<T>(type, json);
    await validateOrReject(result, { })
    return result;
}

