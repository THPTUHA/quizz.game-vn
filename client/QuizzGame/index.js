const token = localStorage.getItem("token");
const phongId = localStorage.getItem("phongId");

const phongMoi = document.getElementById("doiNguoiChoi");
const phong = document.getElementById("phongId");
const nguoiChoiSoMot = document.getElementById("nguoiChoiSoMot");
const nguoiChoiSoHai = document.getElementById("nguoiChoiSoHai");
const cauHoi = document.getElementById("giaoDienCauHoi");
const dapAn1 = document.getElementById("dapAn1");
const dapAn2 = document.getElementById("dapAn2");
const dapAn3 = document.getElementById("dapAn3");
const dapAn4 = document.getElementById("dapAn4");

const danhSachLuaChon = [dapAn1, dapAn2, dapAn3, dapAn4];
const soCau = document.getElementById("soCau");
const tenCau = document.getElementById("tenCau");
const diem = document.getElementById("diem");
const capDo = document.getElementById("capDo");
const thoiGianTonTai = document.getElementById("thoiGianTonTai");
let interval;

const websocket = new WebSocket(`ws://26.69.27.44:8080/wg.server/tro-choi?token=${token}&&phongId=${phongId}`);

websocket.onmessage = (duLieu) => {
    //console.log("DATA", duLieu);
    const lenh = JSON.parse(duLieu.data);
    //console.dir(cauHoi);
    console.log("req",lenh);

    switch (lenh.lenh) {
        case "khoiTao":
            phong.innerHTML = "Phòng ID: " + phongId;
            nguoiChoiSoMot.childNodes[1].src = `../img/avt${lenh.chuPhong.nguoiDung.anhDaiDien}.png`;
            nguoiChoiSoMot.childNodes[3].innerText = lenh.chuPhong.nguoiDung.ten;
            nguoiChoiSoMot.childNodes[5].childNodes[3].innerText = lenh.chuPhong.diem;
            nguoiChoiSoMot.style.display ="block";

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
            break;
        case "guiCauHoi": 
            localStorage.setItem("cauHoiId",lenh.cauHoi.id);
            soCau.innerText = "Câu " +(lenh.cauHoi.soThuTu +1) ;
            tenCau.innerText = lenh.cauHoi.noiDung;
            diem.innerText = "Điểm " + lenh.cauHoi.diem;
            capDo.innerText = "Cấp độ " + lenh.cauHoi.capDo;
            for (let i = 0; i < lenh.cauHoi.goiY.danhSachLuaChon.length; ++i) {
                danhSachLuaChon[i].innerText = lenh.cauHoi.goiY.danhSachLuaChon[i].noiDung;
                danhSachLuaChon[i].tabIndex = lenh.cauHoi.goiY.danhSachLuaChon[i].id;
                danhSachLuaChon[i].style.background = "blueviolet"
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
            },1000);
                break;
            case "guiKetQua":
                nguoiChoiSoMot.childNodes[5].childNodes[3].innerText = lenh.chuPhong.diem;
                nguoiChoiSoHai.childNodes[5].childNodes[3].innerText = lenh.khach.diem;

                const cauTraLoi =  localStorage.getItem("cauTraLoi");
                const luaChon = document.getElementById(`dapAn${cauTraLoi}`);

                
                if(lenh.phienGuiDapAn == 0){
                    luaChon.style.background = 'red';
                    console.log("Sai");
                }else if(lenh.phienGuiDapAn == 1){
                    luaChon.style.background = 'green';
                    console.log("DUng");
                }
                break;
            case "ketThuc":
                console.log("KET THUC");
                window.location.href = "../xephang";

    }
}

const guiDapAn = (id) => {
    console.log("ID CAU HOI",localStorage.getItem("cauHoiId"),id);
    localStorage.setItem("cauTraLoi",id);
    websocket.send(JSON.stringify({
        lenh:"guiDapAn",
        cauHoi:{
            id: parseInt(localStorage.getItem("cauHoiId")),
            dapAn: {
                danhSachLuaChon: [{
                    id: id
                }]
            }
        }
    }));
}

function btnThoat() {
    phongMoi.style.display = "none";
    window.location.href = "../trangchu";
}


if (!localStorage.getItem("token")) {
    window.location.href = "/dangnhap";
}
