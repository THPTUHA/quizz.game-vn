function hienAvt(res) {
    document.getElementById('chonAnh').src = "../img/avt" + res + ".png";
}

//tạo phòng
var phongMoi = document.getElementById("doiNguoiChoi");
var taoPhong = document.getElementById("buttonTaoPhong");
var taoPhongMoi = document.getElementsByClassName("taoPhong");
var thoatPhong = document.getElementsByClassName("thoat");
var tenPhong = document.getElementById("txtPhongMoi");

taoPhong.onclick = function () {
    phongMoi.style.display = "block";
}

function btnThoat() {
    document.getElementById("txtNhapPhong").value = "";
    phongMoi.style.display = "none";
}

var thamGia = "phong/tham-gia";

function btnBatDau() {
    var txtPhong = document.getElementById("txtNhapPhong").value;
    if (txtPhong == "") {
        alert("tên phòng không được để trống");
    } else {
        var fetch = new Fetch();
        var idPhong = document.getElementById("txtNhapPhong").value;
        var idAnh = document.getElementById("chonAnh").src.split('avt')[1][0];

        fetch.post(thamGia, {
            id: idPhong,
            anh: idAnh
        })
            .then(function (data) {
                if (data.id) {
                    doiNguoiChoi();
                } else {
                    alert(data.loiNhan);
                }
            })
    }
}

function btnTaoPhong() {
    var fetch = new Fetch();
    var idAnh = document.getElementById("chonAnh").src.split('avt')[1][0];

    fetch.post(thamGia, {
        anh: idAnh
    })
        .then(function (data) {
            if (data.thanhCong) {
                doiNguoiChoi();
            } else {
                alert(data.loiNhan);
            }
        })
}

function doiNguoiChoi() {
    var txtNhapPhong = document.getElementById("txtPhongMoi").value;
    if (txtNhapPhong == "") {
        alert("Yêu cầu nhập tên phòng");
    } else {
        document.getElementById("txtNhapPhong").value = document.getElementById("txtPhongMoi").value
    }
    phongMoi.style.display = "none";
}



function btnThoatTroChoi() {
    window.location.href = "../dangnhap/index.html";
}
