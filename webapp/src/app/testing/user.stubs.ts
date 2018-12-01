import { Injectable } from '@angular/core';
import { User } from '../users/common/user.model';

export const USERS: User[] = [
  {
    id: 1,
    username: 'testUser',
  },
];

@Injectable()
export class UserServiceStub {

  getUsers(): Promise<User[]> {
    return Promise.resolve(USERS);
  }

  getUser(username: string): Promise<User> {
    const user = USERS.filter(user => user.username === username)[0];
    return Promise.resolve(user);
  }
}
