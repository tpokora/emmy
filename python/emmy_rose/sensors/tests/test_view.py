from django.shortcuts import get_list_or_404
from django.test import TestCase

# Create your tests here.
from rest_framework import status
from rest_framework.request import Request
from rest_framework.test import APIRequestFactory

from emmy_rose.sensors.models import SensorEntry
from emmy_rose.sensors.serializers import SensorEntrySerializer, SensorEntryPostSerializer
from emmy_rose.sensors.views import SensorsEntryViewSet

sensor_entry_create_entry = SensorsEntryViewSet.as_view({'post': 'create'})
sensor_entry_get_all = SensorsEntryViewSet.as_view({'get': 'list'})
sensor_entry_get_by_sensor_name = SensorsEntryViewSet.as_view({'get': 'get_sensor_entries_by_sensor_name'})
sensor_entry_get_by_id = SensorsEntryViewSet.as_view({'get': 'retrieve'})
sensor_entry_delete = SensorsEntryViewSet.as_view({'delete': 'destroy'})


class SensorEntryTest(TestCase):

    DHT11_NAME = 'DHT11'
    TEMPERATURE = 'temperature'
    TEMPERATURE_VALUE = 15.1

    def test_create_new_sensor_entry(self):
        test_sensor_entry = SensorEntry(name=self.DHT11_NAME, type=self.TEMPERATURE, value=self.TEMPERATURE_VALUE)

        url = '/sensors/'
        response = sensor_entry_create_entry(post_sensor_entry_request(url, test_sensor_entry))
        sensor_entry_check = SensorEntry.objects.get(name=test_sensor_entry.name)
        self.assertEqual(response.data['name'], sensor_entry_check.name)

        serializer = SensorEntryPostSerializer(sensor_entry_check, many=False, context={'request': Request(post_sensor_entry_request(url, test_sensor_entry))})
        self.assertEqual(response.data, serializer.data)
        self.assertEqual(response.status_code, status.HTTP_201_CREATED)

    def test_get_sensor_entries(self):
        SensorEntry.objects.create(name=self.DHT11_NAME, type=self.TEMPERATURE, value=self.TEMPERATURE_VALUE)
        SensorEntry.objects.create(name=self.DHT11_NAME, type=self.TEMPERATURE, value=self.TEMPERATURE_VALUE)
        SensorEntry.objects.create(name='TEST SENSOR', type=self.TEMPERATURE, value=self.TEMPERATURE_VALUE)

        url = '/sensors/'
        response = sensor_entry_get_all(get_request(url))

        sensors = SensorEntry.objects.all()
        serializer = SensorEntrySerializer(sensors, many=True, context={'request': Request(get_request(url))})
        self.assertEqual(response.data['results'], serializer.data)
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    def test_get_sensor_entries_by_name(self):
        SensorEntry.objects.create(name=self.DHT11_NAME, type=self.TEMPERATURE, value=self.TEMPERATURE_VALUE)
        SensorEntry.objects.create(name=self.DHT11_NAME, type=self.TEMPERATURE, value=self.TEMPERATURE_VALUE)
        SensorEntry.objects.create(name='TEST SENSOR', type=self.TEMPERATURE, value=self.TEMPERATURE_VALUE)

        url = '/sensors/name/%s' % self.DHT11_NAME
        response = sensor_entry_get_by_sensor_name(get_request(url), sensor_name=self.DHT11_NAME)

        sensors = get_list_or_404(SensorEntry, name=self.DHT11_NAME)
        serializer = SensorEntrySerializer(sensors, many=True, context={'request': Request(get_request(url))})
        self.assertEqual(response.data['results'], serializer.data)
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    def test_remove_sensor_by_id(self):
        SensorEntry.objects.create(name=self.DHT11_NAME, type=self.TEMPERATURE, value=self.TEMPERATURE_VALUE)
        sensor_entry = SensorEntry.objects.get(name=self.DHT11_NAME)
        url = '/sensors/%s' % sensor_entry.id

        response = sensor_entry_get_by_id(get_request(url), pk=sensor_entry.id)
        serializer = SensorEntrySerializer(sensor_entry, many=False, context={'request': Request(get_request(url))})
        self.assertEqual(response.data, serializer.data)
        self.assertEqual(response.status_code, status.HTTP_200_OK)

        response = sensor_entry_delete(delete_request(url), pk=sensor_entry.id)
        self.assertEqual(response.status_code, status.HTTP_204_NO_CONTENT)

        response = sensor_entry_get_by_id(get_request(url), pk=sensor_entry.id)
        self.assertEqual(response.status_code, status.HTTP_404_NOT_FOUND)


def post_sensor_entry_request(url, sensor_entry):
    factory = APIRequestFactory()
    data_string = {
        'name': sensor_entry.name,
        'type': sensor_entry.type,
        'value': sensor_entry.value
    }
    request = factory.post(url, data_string)
    return request


def get_request(url):
    factory = APIRequestFactory()
    request = factory.get(url)
    return request


def delete_request(url, token=''):
    factory = APIRequestFactory()
    # request = factory.delete(url, HTTP_AUTHORIZATION='Bearer %s' % token)
    request = factory.delete(url)
    return request
