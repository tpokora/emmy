from django.contrib.auth.models import User
from rest_framework import serializers

from .models import Dog


class HelloSerializer(serializers.Serializer):
    content = serializers.CharField(max_length=200)


class UserSerializer(serializers.ModelSerializer):
    dog = serializers.PrimaryKeyRelatedField(many=True, queryset=Dog.objects.all(), allow_null=True)

    class Meta:
        model = User
        fields = ('id', 'username', 'dog')


class DogSerializer(serializers.ModelSerializer):
    owner = serializers.ReadOnlyField(source='owner.username')

    class Meta:
        model = Dog
        fields = ('id', 'name', 'owner')

