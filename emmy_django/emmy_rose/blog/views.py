# Create your views here.
from django.shortcuts import get_list_or_404
from rest_framework import viewsets, status
from emmy_rose.blog.serializers import *
from rest_framework.decorators import action
from rest_framework.permissions import IsAuthenticated
from rest_framework.response import Response


class EntryViewSet(viewsets.ModelViewSet):
    permission_classes = (IsAuthenticated, )
    queryset = Entry.objects.all()

    def get_serializer_class(self):
        if self.action == 'create' or self.action == 'update':
            return EntryPostSerializer
        else:
            return EntryListSerializer

    @action(methods=['get'], detail=False, url_path='username/(?P<username>\w+)')
    def get_entries_by_username(self, request, username=None):
        entries_by_username = get_list_or_404(Entry, user__username=username)
        serializer = EntryListSerializer(entries_by_username, context={'request': request}, many=True)

        page = self.paginate_queryset(entries_by_username)
        if page is not None:
            serializer = self.get_serializer(page, context={'request': request}, many=True)
            return self.get_paginated_response(serializer.data)

        return self.get_paginated_response(Response(serializer, status=status.HTTP_200_OK))
