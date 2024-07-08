from django.shortcuts import render, redirect
from django.http import HttpResponse, JsonResponse
from rest_framework.viewsets import ModelViewSet

from django.views.generic import ListView
from .models import Post
from .forms import PostBasedForm, PostModelForm, PostDetailForm, PostCreateForm, PostUpdateForm
from .serializers import PostModelSerializer

class PostModelViewSet(ModelViewSet):
    queryset=Post.objects.all()
    serializer_class=PostModelSerializer
    
def index(request):
    return render(request, 'index.html')

def post_list_view(request):
    return render(request, 'posts/post_list.html')

def post_detail_view(request, id):
    return render(request, 'posts/post_detail.html')

def post_detail_form_view(request, id):
    try:
        post = Post.objects.get(id=id)
    except Post.DoesNotExist:
        return redirect('index')
    context={
        'post': post,
        'form': PostDetailForm(),
    }
    return render(request, 'posts/post_detail.html', context)

def post_create_view(request):
    return render(request, 'posts/post_form.html')

def post_form_view(request):
    if request.method == "GET":
        form = PostBasedForm()
        context = {'form': form}
        return render(request, 'posts/post_form2.html', context)
    else:
        return redirect('index')
    
def post_create_form_view(request):
    if request.method == "GET":
        form = PostModelForm()
        context = {'form': form}
        return render(request, 'posts/post_form2.html', context)
    else:
        form = PostModelForm(request.POST, request.FILES)

        if form.is_valid():
            Post.objects.create(
                image = form.cleaned_data['image'],
                content = form.cleaned_data['content'],
                writer = request.user
            )
        else:
            return redirect('post:post-new')
        return redirect('index')

def post_update_view(request, id):
    return render(request, 'posts/post_update.html')

def post_delete_view(request, id):
    return render(request, 'posts/post_confirm_delete.html')

class class_view(ListView):
    model=Post
    template_name='cbv_view.html'

def url_view(request):
    data={'code':'001','msg':'OK'}
    return HttpResponse('<h1>url_views</h1>')

def url_parameter_view(request, username):
    print('url_parameter_view()')
    print(f'username: {username}')
    print(f'request.GET:{request.GET}')
    return HttpResponse(username)

def function_view(request):
    print(f'request.method:{request.method}')
    if request.method =="GET":
        print(f'request.GET:{request.GET}')
    elif request.method == "POST":
        print(f'request.POST:{request.POST}')
    return render(request,'view.html')

    # print(f'request.GET: {request.GET}')
    # print(f'request.POST:{request.POST}')
    # return render(request, 'view.html')
