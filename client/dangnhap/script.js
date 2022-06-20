function dangNhap() {
    var tendangnhap = document.getElementById("tendangnhap").value;
    var matkhau = document.getElementById("matkhau").value;
    if (tendangnhap == "" || matkhau == "") {
        alert("Vui lòng nhập đầy đủ thông tin!");
    } else {
        var fetch = new Fetch();
        fetch.post("xac-thuc/dang-nhap", {
            ten: tendangnhap,
            matKhau: matkhau
        })
            .then(function (data) {
                if (data.token) {
                    localStorage.setItem("token", data.token);
                    alert("Đăng nhập thành công!");
                    window.location.href = "../trangchu";
                }
                else if (data.trangThai == -1) {
                    alert(data.loiNhan);
                }
            })
            .catch(function (err) {
                alert("Đã có lỗi xảy ra!");
                console.log(err);
            });
    }
}