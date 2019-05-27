from rest_framework import serializers

from emmy_rose.sensors.models import SensorEntry


class SensorEntryPostSerializer(serializers.ModelSerializer):
    class Meta:
        model = SensorEntry
        fields = ('id', 'name', 'type', 'value', )


class SensorEntrySerializer(serializers.ModelSerializer):
    created = serializers.DateTimeField(format="%Y-%m-%d %H:%M:%S")

    class Meta:
        model = SensorEntry
        fields = ('id', 'name', 'type', 'value', 'created')
