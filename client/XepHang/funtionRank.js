function btnXemDiem() {
    window.location.href = "../BangDiem/BangDiem.html";
}


function btnThoat() {
    window.location.href = "../TrangChu/TrangChu.html";
}

const nhanVat = document.getElementById("nhanVat");
const anhDaiDien = document.getElementById("anhDaiDien");
const ten = document.getElementById("ten");
const phongId = localStorage.getItem("phongId");
const diem = document.getElementById("diem");

(function(){
    var fetch = new Fetch();
    fetch.postJWT("tro-choi/ket-qua", {
        id: phongId
    })
    .then(function (data) {
        console.log(data);
        if (data) {
            anhDaiDien.src = `../img/avt${data.nguoiDung.anhDaiDien}.png`;
            ten.innerText = data.nguoiDung.ten;
            diem.innerText = data.diem;
            nhanVat.style.display = "block";
        } else {
            alert(data.loiNhan);
        }
    })
    .catch(function (err) {
        console.log(err);
        // alert("Đã có lỗi xảy ra!");
    });
})();