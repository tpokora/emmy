import { USERS } from './user.stubs';
import { Entry } from '../blog/common/entry.model';

export const ENTRIES: Entry[] = [
  {
    id: 1,
    created: new Date(),
    title: 'Updated title',
    content: 'Updated content',
    user: USERS[0]
  },
  {
    id: 4,
    created: new Date(),
    title: 'testUser title',
    content: 'content',
    user: USERS[1]
  }
];