const token = localStorage.getItem("token");
const phongId = localStorage.getItem("phongId");

const phongMoi = document.getElementById("doiNguoiChoi");
const phong = document.getElementById("phongId");
const nguoiChoiSoMot = document.getElementById("nguoiChoiSoMot");
const nguoiChoiSoHai = document.getElementById("nguoiChoiSoHai");
const cauHoi = document.getElementById("giaoDienCauHoi");
const dapAn1 = document.getElementById("dapAn0");
const dapAn2 = document.getElementById("dapAn1");
const dapAn3 = document.getElementById("dapAn2");
const dapAn4 = document.getElementById("dapAn3");

const danhSachLuaChon = [dapAn1, dapAn2, dapAn3, dapAn4];
const soCau = document.getElementById("soCau");
const tenCau = document.getElementById("tenCau");
const diem = document.getElementById("diem");
const capDo = document.getElementById("capDo");
const thoiGianTonTai = document.getElementById("thoiGianTonTai");
let interval;

const websocket = new WebSocket(`ws://26.69.27.44:8080/wg.server/tro-choi?token=${token}&&phongId=${phongId}`);

websocket.onmessage = (duLieu) => {
    console.log("DATA", duLieu);
    const lenh = JSON.parse(duLieu.data);
    console.dir(cauHoi);

    switch (lenh.lenh) {
        case "khoiTao":
            phong.innerHTML = "Phòng ID: " + phongId;
            nguoiChoiSoMot.childNodes[1].src = `../img/avt${lenh.chuPhong.nguoiDung.anhDaiDien}.png`;
            nguoiChoiSoMot.childNodes[3].innerText = lenh.chuPhong.nguoiDung.ten;
            nguoiChoiSoMot.childNodes[5].childNodes[3].innerText = lenh.chuPhong.diem;
            nguoiChoiSoMot.style.display = "block";

            break;
        case "batDau":
            nguoiChoiSoMot.childNodes[1].src = `../img/avt${lenh.chuPhong.nguoiDung.anhDaiDien}.png`;
            nguoiChoiSoMot.childNodes[3].innerText = lenh.chuPhong.nguoiDung.ten;
            nguoiChoiSoMot.childNodes[5].childNodes[3].innerText = lenh.chuPhong.diem;
            nguoiChoiSoMot.style.display = "block";

            nguoiChoiSoHai.childNodes[1].src = `../img/avt${lenh.khach.nguoiDung.anhDaiDien}.png`;
            nguoiChoiSoHai.childNodes[3].innerText = lenh.khach.nguoiDung.ten;
            nguoiChoiSoHai.childNodes[5].childNodes[3].innerText = lenh.khach.diem;
            nguoiChoiSoHai.style.display = "block";
            phongMoi.style.display = "none";

            websocket.send(JSON.stringify({
                lenh: "batDau"
            }));
        case "guiCauHoi":
            soCau.innerText = "Câu " + (lenh.cauHoi.soThuTu + 1);
            tenCau.innerText = lenh.cauHoi.noiDung;
            diem.innerText = "Điểm " + lenh.cauHoi.diem;
            capDo.innerText = "Cấp độ " + lenh.cauHoi.capDo;
            for (let i = 0; i < lenh.cauHoi.goiY.danhSachLuaChon.length; ++i) {
                danhSachLuaChon[i].innerText = lenh.cauHoi.goiY.danhSachLuaChon[i].noiDung;
                danhSachLuaChon[i].tabIndex = lenh.cauHoi.goiY.danhSachLuaChon[i].id;
            }
            cauHoi.style.display = "block";
            let thoiGianConLai = parseInt(lenh.cauHoi.thoiGianTonTai / 1000);
            clearInterval(interval);
            interval = setInterval(() => {
                if (thoiGianConLai < 0) {
                    clearInterval(interval);
                }
                thoiGianTonTai.innerText = (thoiGianConLai - 1) + 's';
                thoiGianConLai -= 1;
            }, 1000);

    }
}

const sendMessage = () => {
    console.log("mesage", messageText, messageText.value);
    websocket.send(messageText.value);
    messageText.value = "";
}
function btnThoat() {
    phongMoi.style.display = "none";
    window.location.href = "../trangchu";
}
