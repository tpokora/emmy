# Create your views here.
from django.shortcuts import get_list_or_404
from rest_framework.response import Response
from rest_framework import viewsets, status
from rest_framework.decorators import action

from emmy_rose.sensors.models import SensorEntry
from emmy_rose.sensors.serializers import SensorEntrySerializer, SensorEntryPostSerializer


class SensorsEntryViewSet(viewsets.ModelViewSet):

    queryset = SensorEntry.objects.all().order_by('created')
    http_method_names = ['get', 'post', 'delete']

    def get_serializer_class(self):
        if self.action == 'create':
            return SensorEntryPostSerializer
        else:
            return SensorEntrySerializer

    @action(methods=['get'], detail=False, url_path='name/(?P<sensor_name>\w+)')
    def get_sensor_entries_by_sensor_name(self, request, sensor_name=None):
        sensors_entries = get_list_or_404(SensorEntry.objects.order_by('created'), name=sensor_name)

        serializer = SensorEntrySerializer(sensors_entries, context={'request': request}, many=True)

        return Response(serializer.data, status=status.HTTP_200_OK)
