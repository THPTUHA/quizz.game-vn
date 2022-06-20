
function kiemtraten(ten) {
    var kiemtra = /^[a-zA-Z0-9]{3,32}$/;
    return kiemtra.test(ten);
}

function dangKy() {
    var ten = document.getElementById("ten").value;
    var matkhau = document.getElementById("matkhau").value;
    var matkhau2 = document.getElementById("matkhau2").value;
    if (ten == "" || matkhau == "" || matkhau2 == "") {
        alert("Vui lòng nhập đầy đủ thông tin!");
    }
    else if (matkhau != matkhau2) {
        alert("Mật khẩu không trùng khớp!");
    }
    else if (!kiemtraten(ten)) {
        alert("Tên có độ dài từ 3 đến 32 ký tự và chỉ chứa các chữ cái và số");
    }
    else {
        var fetch = new Fetch();
        fetch.post("xac-thuc/dang-ky", {
            ten: ten,
            matKhau: matkhau
        })
        .then(function (data) {
            console.log(data);
            if (data.token == 'OK') {
                alert("Đăng ký thành công!");
                window.location.href = "/dangnhap";
            } else {
                alert(data.loiNhan);
            }
        })
        .catch(function (err) {
            alert("Đã có lỗi xảy ra!");
            console.log(err);
        });
    }
}

