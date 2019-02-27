from emmy_rose.holiday_counter.models import HolidayCounter
from emmy_rose.holiday_counter.serializers import HolidayCounterSerializer
from rest_framework import viewsets, status

# Create your views here.


class HolidayCounterViewSet(viewsets.ModelViewSet):
    """
    API endpoint that allows groups to be viewed or edited.
    """
    queryset = HolidayCounter.objects.all()
    serializer_class = HolidayCounterSerializer
