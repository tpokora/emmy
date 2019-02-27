from emmy_rose.users.serializers import UserSummarySerializer
from rest_framework import serializers
from emmy_rose.blog.models import Entry


class EntryListSerializer(serializers.ModelSerializer):
    user = UserSummarySerializer(many=False, read_only=True)

    class Meta:
        model = Entry
        fields = ('id', 'created', 'title', 'content', 'user')


class EntryPostSerializer(serializers.ModelSerializer):
    class Meta:
        model = Entry
        fields = ('id', 'created', 'title', 'content', 'user')
