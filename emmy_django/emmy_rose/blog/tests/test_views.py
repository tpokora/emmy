import json

from django.contrib.auth.models import User
from emmy_rose.blog.views import EntryViewSet
from rest_framework import status
from django.test import TestCase, Client
from django.urls import reverse
from emmy_rose.blog.models import Entry
from emmy_rose.blog.serializers import UserSerializer, EntrySerializer


# initialize the APIClient app
from rest_framework.request import Request
from rest_framework.test import APIRequestFactory

client = Client()
entry_list = EntryViewSet.as_view({'get': 'list'})
entry_detail = EntryViewSet.as_view({'get': 'retrieve'})
entry_delete = EntryViewSet.as_view({'delete': 'destroy'})


class GetEntriesTest(TestCase):
    """ Test module for GET all entries API """

    USERNAME = 'testUser'
    ENTRY1_TITLE = 'testTitle1'
    ENTRY1_CONTENT = 'testTitle1'
    ENTRY2_TITLE = 'testTitle2'
    ENTRY2_CONTENT = 'testTitle2'

    def setUp(self):
        User.objects.create(username=self.USERNAME, email='test@test.com')
        self.user = User.objects.get(username=self.USERNAME)
        create_entry(self.ENTRY1_TITLE, self.ENTRY1_CONTENT, self.user)
        create_entry(self.ENTRY2_TITLE, self.ENTRY2_CONTENT, self.user)

    def test_get_entries_list(self):
        url = '/blog/'
        response = entry_list(get_request(url))
        entries = Entry.objects.all()
        serializer = EntrySerializer(entries, many=True, context={'request': Request(get_request(url))})
        self.assertEqual(response.data['results'], serializer.data)
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    def test_get_entry_by_id(self):
        entry = Entry.objects.get(title=self.ENTRY1_TITLE)
        url = '/blog/%s' % (entry.id)
        response = entry_detail(get_request(url), pk=entry.id)
        serializer = EntrySerializer(entry, many=False, context={'request': Request(get_request(url))})
        self.assertEqual(response.data, serializer.data)
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    def test_create_entry(self):
        new_entry_title = 'newTestEntryTitle'
        new_entry_content = 'newTestEntryContent'
        entry = create_entry(new_entry_title, new_entry_content, self.user)
        url = '/blog/%s' % (entry.id)
        response = entry_detail(get_request(url), pk=entry.id)
        serializer = EntrySerializer(entry, many=False, context={'request': Request(get_request(url))})
        self.assertEqual(response.data, serializer.data)
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    def test_delete_entry(self):
        new_entry_title = 'newTestEntryTitleToDelete'
        new_entry_content = 'newTestEntryContentToDelete'
        entry = create_entry(new_entry_title, new_entry_content, self.user)
        url = '/blog/%s' % (entry.id)
        response = entry_detail(get_request(url), pk=entry.id)
        serializer = EntrySerializer(entry, many=False, context={'request': Request(get_request(url))})
        self.assertEqual(response.data, serializer.data)
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        response = entry_delete(delete_request(url), pk=entry.id)
        self.assertEqual(response.status_code, status.HTTP_204_NO_CONTENT)


def create_entry(new_entry_title, new_entry_content, user):
    Entry.objects.create(title=new_entry_title, content=new_entry_content, user=user)
    entry = Entry.objects.get(title=new_entry_title)
    return entry


def get_request(url):
    factory = APIRequestFactory()
    request = factory.get(url)
    return request


def delete_request(url):
    factory = APIRequestFactory()
    request = factory.delete(url)
    return request
