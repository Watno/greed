/**
 * The server location is an extra file so I don't actually make the online Client point to my local server after making changes.
 */

export function getServerlocation(): string {
    return 'ws://localhost:8081/spacealert'
}