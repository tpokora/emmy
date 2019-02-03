from emmy_rose.users.serializers import *
from rest_framework import viewsets, status
from rest_framework.generics import get_object_or_404
from rest_framework.decorators import action
from rest_framework.response import Response
from rest_framework_jwt.serializers import VerifyJSONWebTokenSerializer


class UserViewSet(viewsets.ModelViewSet):
    """
    API endpoint that allows users to be viewed or edited.
    """
    queryset = User.objects.all().order_by('-date_joined')
    serializer_class = UserSummarySerializer

    @action(methods=['get'], detail=False, url_path='username/(?P<username>\w+)')
    def get_by_username(self, request, username=None):
        user = get_object_or_404(User, username=username)
        return Response(UserSummarySerializer(user, context={'request': request}).data, status=status.HTTP_200_OK)

    @action(methods=['get'], detail=False, url_path='username/(?P<username>\w+)/details')
    def get_details_by_username(self, request, username=None):
        user = get_object_or_404(User, username=username)
        return Response(UserSerializer(user, context={'request': request}).data, status=status.HTTP_200_OK)

    @action(methods=['post'], detail=False, url_path='token')
    def get_user_by_token(self, request):
        token = request.data['token']
        data = {'token': token}
        username = VerifyJSONWebTokenSerializer().validate(data)['user']
        user = get_object_or_404(User, username=username)
        return Response(UserSerializer(user, context={'request': request}).data, status=status.HTTP_200_OK)


class GroupViewSet(viewsets.ModelViewSet):
    """
    API endpoint that allows groups to be viewed or edited.
    """
    queryset = Group.objects.all()
    serializer_class = GroupSerializer