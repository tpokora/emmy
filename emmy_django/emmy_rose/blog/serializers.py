from django.contrib.auth.models import User, Group
from rest_framework import serializers
from emmy_rose.blog.models import Entry


class UserSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = User
        fields = ('id', 'url', 'username', 'email', 'groups', 'password')


class GroupSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Group
        fields = ('url', 'name')


class UserSummarySerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = User
        fields = ('id', 'username', 'email')


class EntryListSerializer(serializers.ModelSerializer):
    user = UserSummarySerializer(many=False, read_only=True)

    class Meta:
        model = Entry
        fields = ('id', 'created', 'title', 'content', 'user')


class EntryPostSerializer(serializers.ModelSerializer):
    class Meta:
        model = Entry
        fields = ('id', 'created', 'title', 'content', 'user')
