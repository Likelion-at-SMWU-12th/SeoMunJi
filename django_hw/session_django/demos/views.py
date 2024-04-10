from django.shortcuts import render
from django.http import HttpResponse

# Create your views here.

def helloBabyLion(request):
    #return HttpResponse('4주차 장고 과제')
    return render(request, 'myhobby.html')
