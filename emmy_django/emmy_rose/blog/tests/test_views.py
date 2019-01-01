from django.contrib.auth.models import User
from emmy_rose.blog.views import EntryViewSet
from emmy_rose.common.tests.helpers import login
from rest_framework import status
from django.test import TestCase, Client
from emmy_rose.blog.models import Entry
from emmy_rose.blog.serializers import UserSerializer, EntryListSerializer, EntryPostSerializer
from rest_framework.utils import json
from rest_framework_jwt.views import obtain_jwt_token

# initialize the APIClient app
from rest_framework.request import Request
from rest_framework.test import APIRequestFactory

client = Client()
entry_list = EntryViewSet.as_view({'get': 'list'})
entry_detail = EntryViewSet.as_view({'get': 'retrieve'})
entry_create = EntryViewSet.as_view({'post': 'create'})
entry_update = EntryViewSet.as_view({'put': 'update'})
entry_delete = EntryViewSet.as_view({'delete': 'destroy'})


class GetEntriesTest(TestCase):
    """ Test module for GET all entries API """

    USERNAME = 'testUser'
    PASSWORD = 'testPassword'
    ENTRY1_TITLE = 'testTitle1'
    ENTRY1_CONTENT = 'testTitle1'
    ENTRY2_TITLE = 'testTitle2'
    ENTRY2_CONTENT = 'testTitle2'

    def setUp(self):
        User.objects.create_user(username=self.USERNAME, email='test@test.com', password=self.PASSWORD)
        self.user = User.objects.get(username=self.USERNAME)
        create_entry(self.ENTRY1_TITLE, self.ENTRY1_CONTENT, self.user)
        create_entry(self.ENTRY2_TITLE, self.ENTRY2_CONTENT, self.user)
        self.token = login(self.USERNAME, self.PASSWORD)

    def test_get_entries_list(self):
        url = '/blog/entries'
        response = entry_list(get_request(url, self.token))
        entries = Entry.objects.all()
        serializer = EntryListSerializer(entries, many=True, context={'request': Request(get_request(url, self.token))})
        self.assertEqual(response.data['results'], serializer.data)
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    def test_get_entry_by_id(self):
        entry = Entry.objects.get(title=self.ENTRY1_TITLE)
        url = '/blog/entries/%s' % (entry.id)
        response = entry_detail(get_request(url, self.token), pk=entry.id)
        serializer = EntryListSerializer(entry, many=False, context={'request': Request(get_request(url, self.token))})
        self.assertEqual(response.data, serializer.data)
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    def test_post_create_entry(self):
        new_entry_title = 'newTestEntryTitle'
        new_entry_content = 'newTestEntryContent'
        entry = Entry(title=new_entry_title, content=new_entry_content, user=self.user)
        url = '/blog/entries/'
        response = entry_create(post_request(url, self.token, entry))
        entry_id = response.data['id']
        url = '/blog/entries/%s' % entry_id
        entry = Entry.objects.get(id=entry_id)
        serializer = EntryPostSerializer(entry, many=False, context={'request': Request(post_request(url, self.token, entry))})
        self.assertEqual(response.data, serializer.data)
        self.assertEqual(response.status_code, status.HTTP_201_CREATED)

    def test_put_update_entry(self):
        new_entry_title = 'newTestEntryTitle'
        new_entry_content = 'newTestEntryContent'
        entry = create_entry(new_entry_title, new_entry_content, self.user)
        url = '/blog/entries/%s' % entry.id
        updated_entry = entry
        updated_entry_title = 'updatedTestEntryTitle'
        updated_entry_content = 'updatedTestEntryContent'
        updated_entry.title = updated_entry_title
        updated_entry.content = updated_entry_content
        response = entry_update(put_request(url, self.token, updated_entry), pk=updated_entry.id)
        serializer = EntryPostSerializer(updated_entry, many=False, context={'request': Request(post_request(url, self.token, updated_entry))})
        self.assertEqual(response.data, serializer.data)
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    def test_delete_entry(self):
        new_entry_title = 'newTestEntryTitleToDelete'
        new_entry_content = 'newTestEntryContentToDelete'
        entry = create_entry(new_entry_title, new_entry_content, self.user)
        url = '/blog/entries/%s' % (entry.id)
        response = entry_detail(get_request(url, self.token), pk=entry.id)
        serializer = EntryListSerializer(entry, many=False, context={'request': Request(get_request(url, self.token))})
        self.assertEqual(response.data, serializer.data)
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        response = entry_delete(delete_request(url, self.token), pk=entry.id)
        self.assertEqual(response.status_code, status.HTTP_204_NO_CONTENT)


def create_entry(new_entry_title, new_entry_content, user):
    Entry.objects.create(title=new_entry_title, content=new_entry_content, user=user)
    entry = Entry.objects.get(title=new_entry_title)
    return entry


def get_request(url, token):
    factory = APIRequestFactory()
    request = factory.get(url, HTTP_AUTHORIZATION='Bearer %s' % (token))
    return request


def post_request(url, token, data):
    factory = APIRequestFactory()
    data_string = {
        'title': data.title,
        "content": data.content,
        'user': data.user.id
    }
    request = factory.post(url, data_string, HTTP_AUTHORIZATION='Bearer %s' % (token))
    return request


def put_request(url, token, data):
    factory = APIRequestFactory()
    data_string = {
        'id': data.id,
        'title': data.title,
        "content": data.content,
        'user': data.user.id
    }
    request = factory.put(url, data_string, HTTP_AUTHORIZATION='Bearer %s' % (token))
    return request


def delete_request(url, token):
    factory = APIRequestFactory()
    request = factory.delete(url, HTTP_AUTHORIZATION='Bearer %s' % (token))
    return request
