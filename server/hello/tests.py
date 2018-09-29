from django.test import TestCase
from rest_framework.test import APIClient

from .serializers import DogSerializer
from .models import Hello, Dog
from rest_framework import status


class ModelTestCase(TestCase):

    def setUp(self):
        self.hello_content_string = 'Welcome to Emmy Rose application!'
        self.hello = Hello(content=self.hello_content_string)

    def test_hello_content(self):
        self.assertEqual(str(self.hello), self.hello_content_string)
        self.assertEqual(self.hello.content, self.hello_content_string)


class ViewTestCase(TestCase):
    def setUp(self):
        """Define the test client and other test variables."""
        self.client = APIClient()

    def test_api_get_normal(self):
        response = self.client.get(path="/hello/normal", format="json")
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(response.json()['content'], 'Welcome to Emmy Rose application!')

    def test_api_get_serialized(self):
        response = self.client.get(path="/hello/serialized", format="json")
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(response.json()['content'], 'Welcome to Emmy Rose application! - Serialized!')

    def test_api_get_dog(self):
        response = self.client.get(path="/hello/dog", format="json")
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(len(response.json()), 0)

    def test_api_post_add_dog(self):
        dog = {'name': 'Test'}
        response = self.client.post(path="/hello/dog", data=dog, format="json")
        self.assertEqual(response.status_code, status.HTTP_201_CREATED)

        response = self.client.get(path="/hello/dog", format="json")
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(len(response.json()), 1)

        dog = {'name': 'Test1'}
        response = self.client.post(path="/hello/dog", data=dog, format="json")
        self.assertEqual(response.status_code, status.HTTP_201_CREATED)

        response = self.client.get(path="/hello/dog", format="json")
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(len(response.json()), 2)

    def test_api_post_delete_all_dogs(self):
        dog = {'name': 'Test'}
        response = self.client.post(path="/hello/dog", data=dog, format="json")
        self.assertEqual(response.status_code, status.HTTP_201_CREATED)

        response = self.client.get(path="/hello/dog", format="json")
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(len(response.json()), 1)

        response = self.client.delete(path="/hello/dog_clear", format="json")
        self.assertEqual(response.status_code, status.HTTP_204_NO_CONTENT)

        response = self.client.get(path="/hello/dog", format="json")
        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertEqual(len(response.json()), 0)