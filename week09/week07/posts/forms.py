from django import forms
from .models import Post # Post 모델 import

class PostBasedForm(forms.Form):
    image = forms.ImageField(label = '이미지')
    content = forms.CharField(label = '내용', widget=forms.Textarea)
    CATEGORY_CHOICES =[
        ('1', '일반'),
        ('2', '계정'),
    ]
    category = forms.ChoiceField(label = '카테고리', choices=CATEGORY_CHOICES)

# Model Forms
class PostModelForm(forms.ModelForm):
    class Meta:
        model = Post # Post 모델(models.py에 정의) 사용
        fields = '__all__' # Post 클래스의 모든 항목 가져오기

class PostCreateForm(PostModelForm):
    class Meta(PostModelForm.Meta):
        fields = ['image', 'content']

class PostUpdateForm(PostModelForm):
    class Meta(PostModelForm.Meta):
        fields=['image', 'content']

class PostDetailForm(PostModelForm):
    def __init__(self, *args, **kwargs):
        super(PostDetailForm, self).__init__(*args, **kwargs)
        for key in self.fields:
            self.fields[key].widget.attrs['disabled']=True
