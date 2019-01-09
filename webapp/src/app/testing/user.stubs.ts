import { Injectable } from '@angular/core';
import { User } from '../users/common/user.model';

export const USERS: User[] = [
  {
    id: 1,
    username: 'testUser',
    email: 'test@test.com'
  },
  {
    id: 2,
    username: 'testUser2',
    email: 'test2@test.com'
  }
];

@Injectable()
export class UserServiceStub {

  getUsers(): Promise<User[]> {
    return Promise.resolve(USERS);
  }

  getUser(username: string): Promise<User> {
    const user = USERS.filter(eachUser => eachUser.username === username)[0];
    return Promise.resolve(user);
  }

  getUserDetails(username: string): Promise<User> {
    const user = USERS.filter(eachUser => eachUser.username === username)[0];
    return Promise.resolve(user);
  }
}
