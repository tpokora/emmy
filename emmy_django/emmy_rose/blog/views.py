# Create your views here.
from django.contrib.auth.models import User, Group
from rest_framework import viewsets
from emmy_rose.blog.models import Entry
from emmy_rose.blog.serializers import UserSerializer, GroupSerializer, EntrySerializer
from rest_framework.permissions import IsAuthenticated


class UserViewSet(viewsets.ModelViewSet):
    """
    API endpoint that allows users to be viewed or edited.
    """
    queryset = User.objects.all().order_by('-date_joined')
    serializer_class = UserSerializer


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
