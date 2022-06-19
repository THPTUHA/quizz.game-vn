function dangNhap() {
    var tendangnhap = document.getElementById("tendangnhap").value;
    var matkhau = document.getElementById("matkhau").value;
    if (tendangnhap == "" || matkhau == "") {
        alert("Vui lòng nhập đầy đủ thông tin!");
    } else {
        var fetch = new Fetch();
        fetch.post("xac-thuc/dang-nhap", {
            email: tendangnhap,
            matKhau: matkhau
        })
        .then(function (data) {
            try {
                localStorage.setItem("token", data.token);
                alert("Đăng nhập thành công!");
                window.location.href = "/trangchu";
            }
            catch (err) {
                alert("Tên đăng nhập hoặc mật khẩu không đúng!");
            }
        })
        .catch(function (err) {
            alert("Đã có lỗi xảy ra!");
            console.log(err);
        });        
    }
}