# DiangoForm

## Table of Contents
1. [HTML 폼](#html-폼)
    - [GET&POST](#get&post)
2. [폼에서 Django의 역할](#폼에서-django의-역할)
3. [Forms in Django](#forms-in-django)
    - [The Django Form class](#the-django-form-class)
4. [양식 인스턴스화, 처리 및 렌더링](#양식-인스턴스화,-처리-및-렌더링)
5. [Building a form](#building-a-form)
    - [필요한 작업](#필요한-작업)
6. [Building a form in Django](#building-a-form-in-django)
    - [The Form class](#the-form-class)
    - [The view](#the-view)
    - [The template](#the-template)
7. [More about Django Form classes](#more-about-django-form-classes)
    - [Bound and unbound form instances](#bound-and-unbound-form-instances)
    - [More on fields](#more-on-fields)
    - [Widgets](#widgets)
    - [Field data](#field-data)
8. [Working with form templates](#working-with-form-templates)
    - [Reusable form templates](#reusable-form-templates)
    - [Reusable field group templates](#reusable-field-group-templates)
    - [Rendering fields manually](#rendering-fields-manually)
9. [Looping over the form’s fields](#looping-over-the-form’s-fields)

## HTML 폼
> HTML에서 폼은 사용자가 텍스트를 입력, 옵션을 선택, 오브젝트나 제어 요소를 조작하는 등의 작업을 수행<br>-> 해당 정보를 서버로 전송할 수 있도록 하는 form…/form 내부의 요소 모음

=> 텍스트 입력이나 체크박스 등 일부 폼 인터페이스 요소는 HTML 자체에 내장. HTML 폼 input 요소 + JavaScript 및 CSS를 사용.

폼은 input 요소와 함께 두 가지를 지정:
 - 위치: 사용자의 입력에 해당하는 데이터를 반환해야 하는 URL.
 - 방법: 데이터를 반환할 HTTP 메서드.

### * GET&POST *
> 폼을 처리할 때 사용할 수 있는 메서드: GET & POST

- Django의 로그인 폼은 POST 메서드를 사용하여 반환, 브라우저는 폼 데이터를 번들로 묶어 전송을 위해 인코딩하고 서버로 전송한 다음 응답을 다시 받음.
- 반면 GET은 제출된 데이터를 문자열로 묶고 이를 사용하여 URL을 작성. 

GET과 POST는 다른 용도로 사용<br>
-> 시스템 상태를 변경하는 데 사용될 수 있는 모든 요청(예: 데이터베이스를 변경하는 요청)은 ``POST``를 사용<br>
-> ``GET``은 시스템 상태에 영향을 주지 않는 요청에만 사용
 
- ``POST``를 Django의 :doc: CSRF protection과 같은 다른 보호 기능과 함께 사용하면 액세스에 대한 제어를 더욱 강화할 수 O
- ``GET``은 GET 요청을 나타내는 URL을 쉽게 북마크, 공유 또는 다시 제출할 수 있으므로 웹 검색 양식과 같은 것에 적합

## 폼에서 Django의 역할
> Django의 양식 기능은 여러 가지 유형의 수많은 데이터 항목을 양식에 표시할 수 있도록 준비하고, HTML로 렌더링하고, 편리한 인터페이스를 사용하여 편집하고, 서버로 반환하고, 유효성을 검사하고 정리한 다음, 추가 처리를 위해 저장하거나 전달해야 하는 작업의 상당 부분을 단순화하고 자동화

- Django: 폼과 관련된 작업의 세 가지 구분된 영역을 처리:
1. 렌더링할 수 있도록 데이터를 준비하고 재구성합니다.
2. 해당 데이터에 대한 HTML 양식 생성
3. 클라이언트로부터 제출된 양식 및 데이터 수신 및 처리

## Forms in Django
> 웹 애플리케이션의 컨텍스트에서 ‘양식’은 해당 HTML form, 또는 이를 생성하는 Django Form, 또는 제출 시 반환되는 구조화된 데이터 또는 이러한 부분들의 포괄적인 동작 전체를 지칭할 수 O
### * The Django Form class *
- 이 컴포넌트 시스템의 핵심은 Django의 Form 클래스. Django 모델이 객체의 논리적 구조, 동작 및 객체의 부분들이 어떻게 표시되는지를 설명하는 것과 마찬가지로, Form 클래스는 폼을 설명하고 폼이 어떻게 작동하고 표시되는지를 결정
- 모델 클래스의 필드가 데이터베이스 필드에 매핑되는 것과 비슷한 방식으로, 폼 클래스의 필드는 HTML 폼 input 요소에 매핑

## 양식 인스턴스화, 처리 및 렌더링
 Django에서 객체를 렌더링할 때:
1. 뷰에서 해당 객체를 가져옴(예: 데이터베이스에서 가져오기)
2. 템플릿 컨텍스트로 전달
3. 템플릿 변수를 사용하여 HTML 마크업으로 전개

- 데이터가 포함되지 않은 모델 인스턴스의 경우 템플릿에서 이 데이터로 무언가를 하는 것이 거의 유용하지 않을 것. 반면에 채워지지 않은 양식을 렌더링하는 것은 사용자가 양식을 채우기를 원할 때 매우 적합. <br>
-> 따라서 뷰에서 모델 인스턴스를 처리할 때는 일반적으로 데이터베이스에서 조회. 폼을 처리할 때는 일반적으로 뷰에서 폼을 인스턴스화.

-> 양식을 인스턴스화할 때는 양식을 비워두거나 다음과 같이 미리 채우도록 선택할 수 O:
- 저장된 모델 인스턴스의 데이터( 수정하기 위한 관리자 양식의 경우처럼)
- 다른 출처에서 수집한 데이터
- 이전 HTML 양식 제출에서 받은 데이터

## Building a form
### 필요한 작업
> 사용자의 이름을 얻기 위해 웹사이트에 간단한 양식을 만들고 싶다고 가정. 템플릿에 다음과 같은 내용이 필요:<br>
 form action="/your-name/" method="post"<br>
    label for="your_name" Your name: /label<br>
    input id="your_name" type="text" name="your_name" value="{{ current_name }}"<br>
    input type="submit" value="OK"<br>
/form

- 이렇게 하면 브라우저가 POST 메서드를 사용하여 양식 데이터를 URL /your-name/으로 반환하도록 지시. 그러면 "Your name:"이라는 레이블이 붙은 텍스트 필드와 "OK"이라고 표시된 버튼이 표시. 템플릿 컨텍스트에 ``current_name 변수가 포함되어 있으면 이 변수가 your_name 필드를 미리 채우는 데 사용.
- HTML 양식이 포함된 템플릿을 렌더링하고 ‘current_name’ 필드를 적절히 제공할 수 있는 뷰가 필요. 양식이 제출되면 서버로 전송되는 POST 요청에 양식 데이터가 포함.

## Building a form in Django
### The Form class
> 원하는 HTML 양식의 모양을 이미 정했으며 장고에서 이를 구현하기 위한 시작점은 다음과 같다:<br>
from django import forms<br>
class NameForm(forms.Form):<br>
    your_name = forms.CharField(label="Your name", max_length=100)

- 단일 필드(your_name)가 있는 Form 클래스를 정의. 필드가 렌더링될 때 label에 표시되는 인간 친화적인 레이블을 필드에 적용
- 클래스:Form 인스턴스에는 모든 필드에 대해 유효성 검사 루틴을 실행하는 is_valid() 메서드가 있다. 이 메서드가 호출될 때 모든 필드의 데이터가 유효하다면:
 1. True 을 반환
 2. 양식의 데이터를 cleaned_data 속성에 배치

-> 전체 양식은 처음 렌더링될 때 다음과 같이 표시:<br>
label for="your_name">Your name: /label<br>
input id="your_name" type="text" name="your_name" maxlength="100" required
### The view
> Django 웹사이트로 반환된 양식 데이터는 일반적으로 양식을 게시한 뷰와 동일한 뷰에서 처리. 따라서 동일한 로직을 일부 재사용할 수 있다. 양식을 처리하려면 게시할 URL에 대한 뷰에서 양식을 인스턴스화해야한다.

- 이 뷰에 GET 요청이 도착하면 빈 양식 인스턴스가 생성되고 템플릿 컨텍스트에 배치되어 렌더링됨. 
- 폼이 POST 요청을 사용하여 제출되는 경우 뷰는 다시 한 번 폼 인스턴스를 만들고 요청의 데이터로 채운다: <br>
form = NameForm(request.POST) 이를 “폼에 데이터 바인딩”이라고 한다.
- 양식의 is_valid() 메서드를 호출하고, ``True``가 아닌 경우 양식과 함께 템플릿으로 돌아간다. 이전에 제출한 데이터로 HTML 양식이 채워지고 필요에 따라 편집 및 수정할 수 있다.
- is_valid()가 ``True``이면 이제 cleaned_data 속성에서 모든 유효성 검사된 양식 데이터를 찾을 수 있다. 
### The template
> name.html 템플릿에서는 많은 작업을 할 필요가 없고 양식의 모든 필드와 해당 속성은 Django의 템플릿 언어에 의해 해당 {{ form }}에서 HTML 마크업으로 압축 해제됨.

## More about Django Form classes
> 모든 폼 클래스는 django.forms.Form 또는 django.forms.ModelForm의 하위 클래스로 생성
### Bound and unbound form instances
> 바운드 양식과 바운드 양식의 구분: <br>
바인딩되지 않은 폼에는 연관된 데이터가 없으며 사용자에게 렌더링되면 비어 있거나 기본값을 포함. 잘못된 바인딩된 양식이 렌더링되면 사용자에게 수정해야 할 데이터를 알려주는 인라인 오류 메시지를 포함할 수 있다.
### More on fields
개인 웹사이트에서 "contact me" 기능을 구현하는 데 사용할 수 있는 위의 최소 예제보다 더 유용한 양식을 고려해보아야 함.
### Widgets
각 양식 필드에는 해당 위젯 클래스가 있으며, 이는 다시 <입력 유형="text">와 같은 HTML 양식 위젯에 해당.
### Field data
양식과 함께 제출된 데이터가 무엇이든 is_valid()를 호출하여 성공적으로 검증되면(그리고 is_valid() True를 반환하면) 검증된 양식 데이터는 .cleaned_data dictionary 형식이 된다. 이 데이터는 Python 형식으로 잘 변환될 것.

## Working with form templates
> 양식을 템플릿으로 만들기 위해 수행해야 할 일은 양식 인스턴스를 템플릿 컨텍스트에 배치하는 것. 따라서 양식이 컨텍스트에서 양식이라고 하면 {{form}}에서 해당 label 및 input 요소를 적절하게 렌더링.
### Reusable form templates
- 양식을 렌더링할 때 HTML 출력은 그 자체로 템플릿을 통해 생성. 적절한 템플릿 파일을 만들고 해당 form_template_name site-wide를 사용하도록 사용자 정의 FORM_RENDERER를 설정하여 이를 제어할 수 있다.
### Reusable field group templates
- 템플릿에서 {{form.name _of_field}}을(를) 사용하여 각 필드를 양식의 속성으로 사용할 수 있다. 필드는 필드의 관련 요소를 그룹으로, 해당 레이블, 위젯, 오류 및 도움말 텍스트로 렌더링하는 as_field_group () 메서드를 사용
### Rendering fields manually
- 필드 렌더링에 대한 더 세밀한 제어도 가능. 템플릿을 한 번 작성하여 각 필드에 재사용할 수 있도록 사용자 정의 필드 템플릿에 있을 가능성이 높지만 양식의 필드 속성에서 직접 액세스할 수도 있다.
### Rendering form error messages
- 양식 오류를 표시하는 방법에 대해 걱정할 필요가 없었는데, 이는 우리가 그것을 처리하기 때문.

## Looping over the form’s fields
> 양식 필드 각각에 대해 동일한 HTML을 사용하는 경우 {% for %} 루프를 사용하여 각 필드를 차례로 루프하여 중복 코드를 줄일 수 있다:<br>
{{field}}의 유용한 특성:<br>
- {{ 필드.errors }: 이 필드에 해당하는 모든 유효성 검사 오류를 포함하는 ul class="errorlist"를 출력. field.errors %} 루프의 {%}을(를) 사용하여 오류 표시를 사용자 지정할 수 있다. 이 경우 루프의 각 개체는 오류 메시지를 포함하는 문자열.
- {{ field.field }}: 이 BoundField가 래핑하는 폼 클래스의 필드 인스턴스. 이 인스턴스를 사용하여 {char_field.field.max_length}와 같은 필드 특성에 액세스할 수 있다.
- {{ field.help_text }}: 필드와 연결된 도움말 텍스트.
- {{ field.html_name}}: 입력 요소의 이름 필드에서 사용할 필드의 이름. 설정된 경우 양식 접두사가 고려된다.
- { field.id _for_label }: 이 필드에 사용할 ID(위 예제의 id_email). 수동으로 레이블을 구성하는 경우 label_tag 대신 이를 사용할 수 있다. 예를 들어 인라인 자바스크립트가 있고 필드의 ID를 하드코딩하는 것을 피하고 싶을 때 유용
- { field.is _숨김 }: 이 속성은 양식 필드가 숨겨진 필드이면 True이고 그렇지 않으면 False. 템플릿 변수로 특별히 유용하지는 않지만 다음과 같은 조건부 테스트에서 유용할 수 있다
- {{ field.label }} : 필드의 레이블(예: 이메일 주소).
- {{ field.label_tag }}: 필드의 레이블은 적절한 HTML - label 태그로 싸여 있다. 여기에는 형식의 label_suffix가 포함. 
- {{ field.legend_tag }
field.label_tag와 유사하지만 fieldset에 래핑된 여러 입력이 있는 위젯의 경우 label 대신 <전설> 태그를 사용.
- { field.use_fieldset }
양식 필드의 위젯에 접근성을 향상시키기 위해 <레전드>가 포함된 <필드 세트>에 의미론적으로 그룹화되어야 하는 여러 입력이 포함된 경우 이 속성은 True.