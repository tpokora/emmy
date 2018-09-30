from rest_framework.decorators import api_view

from .models import Hello, Dog
from .serializers import HelloSerializer, DogSerializer
from django.http import HttpResponse, JsonResponse
from django.views.decorators.csrf import csrf_exempt
from rest_framework import status
from rest_framework.parsers import JSONParser
from rest_framework.response import Response


def get_hello(request):
    hello = Hello(content='Welcome to Emmy Rose application!')
    return JsonResponse({'content': hello.content})


def get_hello_serialized(request):
    hello = Hello(content='Welcome to Emmy Rose application! - Serialized!')
    serializer = HelloSerializer(hello)
    return JsonResponse(serializer.data)


@api_view(['GET', 'POST'])
def dogs(request):
    if request.method == 'GET':
        dogs = Dog.objects.all()
        serializer = DogSerializer(dogs, many=True)
        return Response(serializer.data)

    elif request.method == 'POST':
        data = JSONParser().parse(request)
        serializer = DogSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


@api_view((['GET', 'DELETE']))
def dog_detail(request, pk):
    try:
        dog = Dog.objects.get(pk=pk)
    except Dog.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if request.method == 'GET':
        serializer = DogSerializer(dog)
        return Response(serializer.data)

    if request.method == 'DELETE':
        dog.delete()
        return Response(status.HTTP_204_NO_CONTENT)


@api_view(['DELETE'])
def dogs_clear(request):
    if request.method == 'DELETE':
        dogs = Dog.objects.all()
        dogs.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)

