function hienAvt(res) {
    document.getElementById('chonAnh').src = "../img/avt" + res + ".png";
}

//tạo phòng
var phongMoi = document.getElementById("doiNguoiChoi");
var taoPhong = document.getElementById("buttonTaoPhong");
var taoPhongMoi = document.getElementsByClassName("taoPhong");
var thoatPhong = document.getElementsByClassName("thoat");
var tenPhong = document.getElementById("txtPhongMoi");

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

        fetch.postJWT(thamGia, {
            id: idPhong,
            anhDaiDien: idAnh
        })
            .then(function (data) {
                if (data.id) {
                    localStorage.setItem("phongId", data.id);
                    window.location.href = "../quizzGame";
                } else {
                    alert(data.loiNhan);
                }
            })
    }
}

function btnTaoPhong() {
    var fetch = new Fetch();
    var idAnh = document.getElementById("chonAnh").src.split('avt')[1][0];
    // const phong = document.getElementById("phongId");

    fetch.postJWT("phong/tao", {
        anhDaiDien: idAnh,

    }).then(function (data) {
        if (data.id) {
            localStorage.setItem("phongId", data.id);
            window.location.href = "../quizzGame";
        } else {
            alert(data.loiNhan);
        }
    })
}



function btnThoatTroChoi() {
    localStorage.removeItem("token");
    window.location.href = "/";
}


if (!localStorage.getItem("token")) {
    window.location.href = "/dangnhap";
}
