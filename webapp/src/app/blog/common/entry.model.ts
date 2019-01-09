import { User } from 'src/app/users/common/user.model';

export class Entry {
  public id: number;
  public created: Date;
  public title: string;
  public content: string;
  public user: User;
}
