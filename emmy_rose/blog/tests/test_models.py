from django.contrib.auth.models import User
from django.test import TestCase

# Create your tests here.
from emmy_rose.blog.models import Entry


class BlogTest(TestCase):
    USERNAME = 'testUser'
    ENTRY_TITLE = 'testTitle'
    ENTRY_CONTENT = 'testTitle'

    def setUp(self):
        User.objects.create(username=self.USERNAME, email='test@test.com')

    def test_create_entry(self):
        user = User.objects.get(username=self.USERNAME)
        Entry.objects.create(title=self.ENTRY_TITLE, content=self.ENTRY_CONTENT, user=user)
        entry = Entry.objects.get(title=self.ENTRY_TITLE)
        self.assertEqual(entry.title, self.ENTRY_TITLE)
        self.assertEqual(entry.content, self.ENTRY_CONTENT)
        self.assertEqual(entry.user.username, self.USERNAME)
