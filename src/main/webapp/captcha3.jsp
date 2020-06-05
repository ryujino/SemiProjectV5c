  Created by IntelliJ IDEA.
  User: jinoru
  Date: 2020-06-05
  Time: 오전 9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>구글 리캡차</title>
    <script src='https://www.google.com/recaptcha/api.js'></script>
    <script>
        function onSubmit() {
            if (grecaptcha.getResponse() == "") {
                alert("자동가입방지 확인 필요!!");
                return false;
            } else {
                alert("자동가입방지 성공 확인!!");
                alert(grecaptcha.getResponse());
                document.getElementById("g-recaptcha").value = grecaptcha.getResponse();
                return true;
            }
        }
    </script>
</head>
<body>
<h1>구글 reCaptcha : checkbox v3</h1>
<form method="post" id="loginfrm" action="checkcaptcha.jsp" onsubmit="return onSubmit()">
    <div>아이디: <input type="text" id="userid" name="userid"></div>
    <div>비밀번호: <input type="password" id="passwd" name="passwd"></div>
    <input type="hidden" name="g-recaptcha" id="g-recaptcha">
    <div class="g-recaptcha"
        data-sitekey="6LfSlQAVAAAAAJ3-_qLnKWrXuJH9yjbXYxvIqrtx"></div>
    <div><button type="submit">로그인 하기</button></div>
</form>
</body>
</html>
<%--
    자동가입방지captcha
    어떤 사용자가 사람인지 컴포터 프로그램인지 구별하기 위해
    사용하는 프로그램
    사람만이 구별할 수 있고 컴퓨터는 구별하기 힘들도록
    의도적으로 비틀거나 덧칠한 그림을 보여주고
    그것이 무엇인지 물어보는 방법을 주로 사용
    튜링 테스트를 시초로 해서 만들어 졌기 때문에
    리버스 튜링 테스트라고도 한다.

    checkbox recaptcha
    사용자가 captcha를 클릭해야만
    정상적인 작업을 허용하는 형태

    recaptcha 서비스 등록시 2가지 키를 제공해 주는데
    sitekey는 클라이언트 측 입력폼에 사용하는 키이고
    secretkey는 서버측에서 전송된 captcha token을 확인하는 용도로 사용하는 키이다.

    recaptcha v2는 2가지 서비스( checkbox, invisible )를 제공하는데
    각 기능별로 제공하는 키가 다름으로 주의해서 사용할것
 --%>