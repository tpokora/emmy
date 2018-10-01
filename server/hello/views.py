from rest_framework.decorators import api_view
from rest_framework.views import APIView

from .models import Hello, Dog
from .serializers import HelloSerializer, DogSerializer
from django.http import JsonResponse, Http404
from rest_framework import status
from rest_framework.response import Response
from rest_framework import generics


def get_hello(request):
    hello = Hello(content='Welcome to Emmy Rose application!')
    return JsonResponse({'content': hello.content})


@api_view(['GET'])
def get_hello_serialized(request):
    hello = Hello(content='Welcome to Emmy Rose application! - Serialized!')
    serializer = HelloSerializer(hello)
    return Response(serializer.data)


class DogsList(APIView):

    def get(self, request, format=None):
        dogs = Dog.objects.all()
        serializer = DogSerializer(dogs, many=True)
        return Response(serializer.data)

    def post(self, request, format=None):
        serializer = DogSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


@api_view(['DELETE'])
def dogs_clear(request):
    if request.method == 'DELETE':
        dogs = Dog.objects.all()
        dogs.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


class DogDetails(generics.RetrieveUpdateDestroyAPIView):
    queryset = Dog.objects.all()
    serializer_class = DogSerializer

    """ Override methods if needed """
    def get(self, request, *args, **kwargs):
        return self.retrieve(request, *args, **kwargs)



