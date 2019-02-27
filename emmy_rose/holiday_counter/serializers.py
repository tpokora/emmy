from emmy_rose.holiday_counter.models import HolidayCounter
from rest_framework import serializers


class HolidayCounterSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = HolidayCounter
        fields = ('id', 'deadline', 'title')