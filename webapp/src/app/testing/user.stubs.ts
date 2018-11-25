import { Injectable } from "@angular/core";
import { User } from "../users/common/user.model";

export const USERS: User[] = [
  {
    id: 1,
    username: 'Test User 1',
  }, 
]

@Injectable()
export class UserServiceStub {

  getUsers(): Promise<User[]> {
    return Promise.resolve(USERS);
  }
}