from django.db import models
from enum import Enum
# Create your models here.


class SensorEntry(models.Model):
    name = models.CharField(max_length=100, blank=True, default='')
    type = models.CharField(max_length=20, blank=True, default='')
    value = models.FloatField(null=True, blank=True)
    created = models.DateTimeField(auto_now_add=True)

    class Meta:
        ordering = ('name', 'type', 'value', 'created')


class ReadingTypes(Enum):
    TEMP = 'TEMPERATURE'
    HUM = 'HUMIDITY'
    PM10 = 'PM10'
    PM25 = 'PM2.5'
