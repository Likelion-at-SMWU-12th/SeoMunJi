from rest_framework import serializers
from rest_framework.serializers import ModelSerializer
from .models import User, Post, Comment

class CommentSerializer(ModelSerializer):
    class Meta:
        model = Comment
        fields = '__all__'

class PostSerializer(serializers.ModelSerializer):
    comments = CommentSerializer(many=True, read_only=True)

    class Meta:
        model = Post
        fields = '__all__'
