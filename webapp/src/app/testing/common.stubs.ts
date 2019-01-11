import { Message } from '../common/common.model';
export const MESSAGES: Map<string, Message> = new Map([
  ['access_token', Message.createMessage('Access token has been revoked')],
  ['refresh_token', Message.createMessage('Refresh token has been revoked')]
]);
