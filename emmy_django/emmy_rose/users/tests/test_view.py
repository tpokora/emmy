from django.contrib.auth.models import User
from django.test import TestCase
from emmy_rose.common.tests.helpers import login
from emmy_rose.users.views import UserViewSet, UserSerializer
from rest_framework import status
from rest_framework.request import Request
from rest_framework.test import APIRequestFactory

user_get_user_by_token = UserViewSet.as_view({'post': 'get_user_by_token'})


class GetUserByTokenTest(TestCase):

    FIRST_USERNAME = 'testFirstUser'
    FIRST_PASSWORD = 'testFirstPassword'

    def setUp(self):
        User.objects.create_user(username=self.FIRST_USERNAME, email='test@test.com', password=self.FIRST_PASSWORD)
        self.user = User.objects.get(username=self.FIRST_USERNAME)
        self.token = login(self.FIRST_USERNAME, self.FIRST_PASSWORD)

    def test_get_user_by_username(self):
        url = '/users/token'
        response = user_get_user_by_token(post_request(url, self.token))
        serializer = UserSerializer(self.user, many=False, context={'request': Request(post_request(url, self.token))})
        self.assertEqual(response.data, serializer.data)
        self.assertEqual(response.status_code, status.HTTP_200_OK)


def post_request(url, token):
    factory = APIRequestFactory()
    data_string = {
        'token': token
    }
    request = factory.post(url, data_string)
    return request
