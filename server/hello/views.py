from .models import Hello, Dog
from .serializers import HelloSerializer, DogSerializer
from django.http import HttpResponse, JsonResponse
from django.views.decorators.csrf import csrf_exempt
from rest_framework.parsers import JSONParser


def get_hello(request):
    hello = Hello(content='Welcome to Emmy Rose application!')
    return JsonResponse({'content': hello.content})


def get_hello_serialized(request):
    hello = Hello(content='Welcome to Emmy Rose application! - Serialized!')
    serializer = HelloSerializer(hello)
    return JsonResponse(serializer.data)


@csrf_exempt
def dogs(request):
    if request.method == 'GET':
        dogs = Dog.objects.all()
        serializer = DogSerializer(dogs, many=True)
        return JsonResponse(serializer.data, safe=False)

    elif request.method == 'POST':
        data = JSONParser().parse(request)
        serializer = DogSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data, status=201)
        return JsonResponse(serializer.errors, status=400)


@csrf_exempt
def dogs_clear(request):
    if request.method == 'DELETE':
        dogs = Dog.objects.all()
        dogs.delete()
        return HttpResponse(status=204)

