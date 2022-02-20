<script language="javascript">
    function checkAll() {
        if (!checkUserId(form.userId.value)) {
            return false;
        }
        if (!checkPassword(form.userId.value, form.password1.value)) {
            return false;
        }
        if (!checkName(form.name.value)) {
            return false;
        }


        return true;
    }

    // 공백확인
    function checkExistData(value, dataName) {
        if (value == "") {
            alert(dataName + " 입력해주세요!");
            return false;
        }
        return true;
    }

    function checkUserId(id) {

        if (!checkExistData(id, "아이디를"))
            return false;

        var idRegExp = /^[a-zA-z0-9]{2,9}$/; //아이디 유효성 검사
        if (!idRegExp.test(id)) {
            alert("아이디는 영문 대소문자와 숫자 4~12자리로 입력해야합니다!");
            form.userId.value = "";
            form.userId.focus();
            return false;
        }
        return true;
    }

    function checkPassword(id, pw) {
        if (!checkExistData(pw, "비밀번호를"))
            return false;

        var password1RegExp = /^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}$/; //비밀번호 유효성 검사
        if (!password1RegExp.test(password1)) {
            alert("비밀번호는 영문 대소문자와 숫자 4~12자리로 입력해야합니다!");
            form.pw.value = "";
            form.pw.focus();
            return false;
        }
    }

    function checkName(name) {
        if (!checkExistData(name, "이름을"))
            return false;

        var nameRegExp = /^[가-힣]{2,8}$/;
        if (!nameRegExp.test(name)) {
            alert("이름이 올바르지 않습니다.");
            return false;
        }
        return true;
    }

</script>

