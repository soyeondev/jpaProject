<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    var groupLevel = "${sessionScope.sessionKey.roleLevel}"
    var url = "";
    if(groupLevel == 1){
        url = "/seminar/qna";
    }else if(groupLevel == 5){
        url = "/confer";
    }else if(groupLevel == 9){
//        url = "/master/confer/list";
        url = "notice/list"
    }else{
        alert("권한등급을 확인해주세요.");
        url = "/login";
    }

    window.location.href = url;
</script>