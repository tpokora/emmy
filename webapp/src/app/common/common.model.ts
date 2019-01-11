export class Message {
  public text: string;

  static createMessage(text: string) {
    const message = new Message();
    message.text = text;
    return message;
  }
}
