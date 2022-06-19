
function kiemtratendangnhap(tendangnhap) {
    var kiemtra = /^[a-zA-Z0-9]{4,32}$/;
    return kiemtra.test(tendangnhap);
}

function dangKy() {
    var ten = document.getElementById("ten").value;
    var tendangnhap = document.getElementById("tendangnhap").value;
    var matkhau = document.getElementById("matkhau").value;
    var matkhau2 = document.getElementById("matkhau2").value;
    if (ten == "" || tendangnhap == "" || matkhau == "" || matkhau2 == "") {
        alert("Vui lòng nhập đầy đủ thông tin!");
    }
    else if (matkhau != matkhau2) {
        alert("Mật khẩu không trùng khớp!");
    }
    else if (!kiemtratendangnhap(tendangnhap)) {
        alert("Tên đăng nhập có độ dài từ 4 đến 30 ký tự và chỉ chứa các chữ cái và số");
    }
    else {
        var fetch = new Fetch();
        fetch.post("dangky", {
            ten: ten,
            tendangnhap: tendangnhap,
            matkhau: matkhau
        })
        .then(function (data) {
            if (data.success) {
                alert("Đăng ký thành công!");
                localStorage.setItem("token", data.token);
                localStorage.setItem("ten", data.user.name);
                window.location.href = "/";
            } else {
                alert(data.message);
            }
        })
        .catch(function (err) {
            alert("Đã có lỗi xảy ra!");
            console.log(err);
        });
    }
}

