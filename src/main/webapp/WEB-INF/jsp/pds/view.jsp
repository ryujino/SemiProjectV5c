<%@ page  pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- 줄바꿈 문자를 newChar 변수에 저장 --%>
<c:set var="newChar" value="
" scope="page"/>
<%-- 엔터친게 저장 ㅋㅋ --%>

    <!-- 메인영역 시작 -->
    <div id="main">
        <div class="margin30">
            <i class="fa fa-upload fa-2x"> 자료실</i>
            <hr>
        </div> <!-- 타이틀 -->

        <div class="row margin1050">
            <div class="col-6">
                <button type="button" id="prevpd"
                        class="btn btn-light"
                        style="margin-left: -14px">
                    <i class="fa fa-chevron-left"> </i>
                    이전게시물
                </button>
                <button type="button" id="nextpd"
                        class="btn btn-light">
                    <i class="fa fa-chevron-right"> </i>
                    다음게시물
                </button>
            </div>
            <div class="col-6 text-right">
                <button type="button" id="newpd"
                        class="btn btn-light"
                        style="margin-right: -14px">
                    <i class="fa fa-plus-circle"> </i>
                    새글쓰기
                </button>
            </div>
        </div><!-- 버튼들 -->

        <div class="row margin1050">
            <table class="table">
                <tr><th colspan="2"
                    style="border-bottom: 3px solid black;
                           background: #dff0f8">
                    <h3>${p.title}</h3>
                </th></tr>
                <tr style="background: #ccff99">
                    <td class="text-left">${p.userid}</td>
                    <td class="text-right">${p.regdate} /${p.thumbup} / ${p.views}</td></tr>
                <tr style="background: #ffffcc">
                    <td colspan="2" class="text-left"
                        style="border-bottom: 3px solid black;">
                        ${fn:replace(p.contents, newChar, "<br>")}
                        <%-- 엔터를 br태그로 바꿈 --%>
                    </td></tr>
                <tr><td class="text-left">첨부1</td><td><a href="/pds/pdown.do?f=${p.fname}&pno=${p.pno}">${p.fname}</a>(${p.fsize}KB, ${p.fdown}회 다운로드함)</td></tr>
                <tr><td class="text-left">첨부2</td><td>${p.fname}.${p.ftype} (${p.fsize}KB, ${p.fdown}회 다운로드함)</td></tr>
            </table>
        </div><!-- 본문 -->

        <div class="row margin1050">
            <div class="col-6">
                <button type="button" id="updpd"
                        class="btn btn-warning"
                        style="margin-left: -14px">
                    <i class="fa fa-pencil"> </i>
                    수정하기
                </button>
                <button type="button" id="delpd"
                        class="btn btn-danger">
                    <i class="fa fa-trash-o"> </i>
                    삭제하기
                </button>
            </div>
            <div class="col-6 text-right">
                <button type="button" id="lstpd"
                        class="btn btn-light"
                        style="margin-right: -14px">
                    <i class="fa fa-list"> </i>
                    목록으로
                </button>
            </div>
        </div><!-- 버튼들 -->

        <div class="row margin1050">
            <h3 style="margin-top: 45px"><i class="fa fa-commenting"></i> 나도 한마디</h3>
            <table class="table" style="border-top: 3px solid black;
                                        border-bottom:3px solid black">
                <tr><td style="width:25%" class="text-left"><h4>zzyigy</h4></td>
                    <td>
                        <div style="background: yellow; padding: 5px 0">2012-02-08 14:43:49</div>
                        <p style="padding: 5px 0">지금도 등골 브레이커 맞습니다.<br>
                            애들 레고교육센터인가 뭔가 보낼려다가...학원비는 둘째치고, <br>
                           재료비에서 깜놀...<br><br>
                            그냥 작은 거 사다가 애들하고 맞추고 놀고 있습죠 ^^</p>
                        <ul class="list-unstyled"><!-- 댓글의 댓글 시작 -->
                            <li>
                                <div style="background: lime; padding: 5px 0">
                                    <span><b>zzyzzy</b></span>
                                    <span style="float: right">2012-02-08 14:50:35</span></div>
                                <p style="padding: 5px 0">정말인가요? 우아~ 심하다!~</p>
                            </li>
                        </ul>
                    </td></tr>

                <tr><td style="width:25%" class="text-left"><h4>zzyigy</h4></td>
                    <td>
                        <div style="background: yellow; padding: 5px 0">2012-02-08 14:43:49</div>
                        <p style="padding: 5px 0">지금도 등골 브레이커 맞습니다.<br>
                            애들 레고교육센터인가 뭔가 보낼려다가...학원비는 둘째치고, <br>
                            재료비에서 깜놀...<br><br>
                            그냥 작은 거 사다가 애들하고 맞추고 놀고 있습죠 ^^</p>
                    </td></tr>

                <tr><td style="width:25%" class="text-left"><h4>zzyigy</h4></td>
                    <td>
                        <div style="background: yellow; padding: 5px 0">2012-02-08 14:43:49</div>
                        <p style="padding: 5px 0">지금도 등골 브레이커 맞습니다.<br>
                            애들 레고교육센터인가 뭔가 보낼려다가...학원비는 둘째치고, <br>
                            재료비에서 깜놀...<br><br>
                            그냥 작은 거 사다가 애들하고 맞추고 놀고 있습죠 ^^</p>
                    </td></tr>

                <tr><td style="width:25%" class="text-left"><h4>zzyigy</h4></td>
                    <td>
                        <div style="background: yellow; padding: 5px 0">2012-02-08 14:43:49</div>
                        <p style="padding: 5px 0">지금도 등골 브레이커 맞습니다.<br>
                            애들 레고교육센터인가 뭔가 보낼려다가...학원비는 둘째치고, <br>
                            재료비에서 깜놀...<br><br>
                            그냥 작은 거 사다가 애들하고 맞추고 놀고 있습죠 ^^</p>
                    </td></tr>

                <tr><td style="width:25%" class="text-left"><h4>zzyigy</h4></td>
                    <td>
                        <div style="background: yellow; padding: 5px 0">2012-02-08 14:43:49</div>
                        <p style="padding: 5px 0">지금도 등골 브레이커 맞습니다.<br>
                            애들 레고교육센터인가 뭔가 보낼려다가...학원비는 둘째치고, <br>
                            재료비에서 깜놀...<br><br>
                            그냥 작은 거 사다가 애들하고 맞추고 놀고 있습죠 ^^</p>
                    </td></tr>


            </table>
        </div><!-- 댓글 -->

        <div class="row margin1050">
            <form class="card card-body bg-light"
                style="margin: 30px 0;">
                <div class="form-group row">
                    <label style="margin:auto">로그인하세요</label>

                    <textarea id="comment" rows="10"
                        class="form-control col-7"></textarea>

                    <button type="button" id="bdcmt"
                        class="btn btn-dark" style="margin:auto">
                        <i class="fa fa-commenting"></i>
                        댓글쓰기</button>
                </div>
            </form>
        </div><!-- 댓글쓰기 -->

    </div>
    <!-- 메인영역 끝 -->

