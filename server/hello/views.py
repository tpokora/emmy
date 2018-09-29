from django.http import HttpResponse

def index(request):
    return HttpResponse("Welcome to Emmy Rose application!")

# Create your views here.
