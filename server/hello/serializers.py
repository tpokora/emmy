from rest_framework import serializers

from .models import Dog


class HelloSerializer(serializers.Serializer):

    content = serializers.CharField(max_length=200)


class DogSerializer(serializers.ModelSerializer):
    class Meta:
        model = Dog
        fields = ('id', 'name',)

