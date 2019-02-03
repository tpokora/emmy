from django.contrib.auth.models import User
from django.test import TestCase
from emmy_rose.common.tests.helpers import login
from emmy_rose.users.views import UserViewSet, UserSerializer, UserSummarySerializer
from rest_framework import status
from rest_framework.request import Request
from rest_framework.test import APIRequestFactory

user_get_by_username = UserViewSet.as_view({'get': 'get_by_username'})
user_get_details_by_username = UserViewSet.as_view({'get': 'get_details_by_username'})
user_get_user_by_token = UserViewSet.as_view({'post': 'get_user_by_token'})


class UsersTest(TestCase):

    FIRST_USERNAME = 'testFirstUser'
    FIRST_PASSWORD = 'testFirstPassword'

    def setUp(self):
        User.objects.create_user(username=self.FIRST_USERNAME, email='test@test.com', password=self.FIRST_PASSWORD)
        self.user = User.objects.get(username=self.FIRST_USERNAME)
        self.token = login(self.FIRST_USERNAME, self.FIRST_PASSWORD)

    def test_get_user_by_username(self):
        url = '/api/users/username/{username}'.format(username=self.user.username)
        response = user_get_by_username(get_request(url, self.token), username=self.user.username)
        serializer = UserSummarySerializer(self.user, many=False, context={'request': Request(get_request(url, self.token))})
        self.assertEqual(response.data, serializer.data)
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    def test_get_user_details_by_username(self):
        url = '/api/users/username/{username}/details'.format(username=self.user.username)
        response = user_get_details_by_username(get_request(url, self.token), username=self.user.username)
        serializer = UserSerializer(self.user, many=False, context={'request': Request(get_request(url, self.token))})
        self.assertEqual(response.data, serializer.data)
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    def test_get_token_by_username(self):
        url = '/api/users/token'
        response = user_get_user_by_token(post_request(url, self.token))
        serializer = UserSerializer(self.user, many=False, context={'request': Request(post_request(url, self.token))})
        self.assertEqual(response.data, serializer.data)
        self.assertEqual(response.status_code, status.HTTP_200_OK)


def get_request(url, token):
    factory = APIRequestFactory()
    request = factory.get(url, HTTP_AUTHORIZATION='Bearer %s' % token)
    return request


def post_request(url, token):
    factory = APIRequestFactory()
    data_string = {
        'token': token
    }
    request = factory.post(url, data_string)
    return request
