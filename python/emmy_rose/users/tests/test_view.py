from django.contrib.auth.hashers import PBKDF2PasswordHasher
from django.contrib.auth.models import User
from django.test import TestCase
from emmy_rose.common.tests.helpers import login
from emmy_rose.users.views import UserViewSet, UserSerializer, UserSummarySerializer
from rest_framework import status
from rest_framework.request import Request
from rest_framework.test import APIRequestFactory

user_create_user = UserViewSet.as_view({'post': 'create'})
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

    def test_create_new_user(self):
        test_user = User(username='testUser', email='testUser@mail.com', password='password')
        url = '/users/'
        response = user_create_user(post_user_request(url, test_user))
        user_check = User.objects.get(username=test_user.username)
        self.assertEqual(response.data['username'], user_check.username)
        hasher = PBKDF2PasswordHasher()
        self.assertTrue(hasher.verify(password=test_user.password, encoded=user_check.password))

    def test_create_new_user_without_password_should_fail(self):
        test_user = User(username='testUser', email='testUser@mail.com')
        url = '/users/'
        response = user_create_user(post_user_request(url, test_user))
        self.assertTrue(response.status_code, status.HTTP_400_BAD_REQUEST)

    def test_get_user_by_username(self):
        url = '/users/username/{username}'.format(username=self.user.username)
        response = user_get_by_username(get_request(url, self.token), username=self.user.username)
        serializer = UserSummarySerializer(self.user, many=False, context={'request': Request(get_request(url, self.token))})
        self.assertEqual(response.data, serializer.data)
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    def test_get_user_details_by_username(self):
        url = '/users/username/{username}/details'.format(username=self.user.username)
        response = user_get_details_by_username(get_request(url, self.token), username=self.user.username)
        serializer = UserSerializer(self.user, many=False, context={'request': Request(get_request(url, self.token))})
        self.assertEqual(response.data, serializer.data)
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    def test_get_token_by_username(self):
        url = '/users/token'
        response = user_get_user_by_token(post_token_request(url, self.token))
        serializer = UserSerializer(self.user, many=False, context={'request': Request(post_token_request(url, self.token))})
        self.assertEqual(response.data, serializer.data)
        self.assertEqual(response.status_code, status.HTTP_200_OK)


def get_request(url, token):
    factory = APIRequestFactory()
    request = factory.get(url, HTTP_AUTHORIZATION='Bearer %s' % token)
    return request


def post_token_request(url, token):
    factory = APIRequestFactory()
    data_string = {
        'token': token
    }
    request = factory.post(url, data_string)
    return request


def post_user_request(url, user):
    factory = APIRequestFactory()
    if user.password is None or user.password == '':
        data_string = {
            'username': user.username,
            'email': user.email
        }
    else:
        data_string = {
            'username': user.username,
            'email': user.email,
            'password': user.password
        }
    request = factory.post(url, data_string)
    return request
