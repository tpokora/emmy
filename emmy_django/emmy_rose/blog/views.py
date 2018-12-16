# Create your views here.
from django.contrib.auth.models import User, Group
from rest_framework import viewsets, status
from emmy_rose.blog.models import Entry
from emmy_rose.blog.serializers import UserSerializer, GroupSerializer, EntrySerializer
from rest_framework.decorators import action
from rest_framework.generics import get_object_or_404
from rest_framework.permissions import IsAuthenticated
from rest_framework.response import Response


class UserViewSet(viewsets.ModelViewSet):
    """
    API endpoint that allows users to be viewed or edited.
    """
    queryset = User.objects.all().order_by('-date_joined')
    serializer_class = UserSerializer

    @action(methods=['get'], detail=False, url_path='username/(?P<username>\w+)')
    def get_by_username(self, request, username=None):
        user = get_object_or_404(User, username=username)
        return Response(UserSerializer(user, context={'request': request}).data, status=status.HTTP_200_OK)


class GroupViewSet(viewsets.ModelViewSet):
    """
    API endpoint that allows groups to be viewed or edited.
    """
    queryset = Group.objects.all()
    serializer_class = GroupSerializer


class EntryViewSet(viewsets.ModelViewSet):
    permission_classes = (IsAuthenticated, )
    queryset = Entry.objects.all()
    serializer_class = EntrySerializer
