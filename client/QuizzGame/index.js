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
const chuPhong = localStorage.getItem("chuPhong");

const websocket = new WebSocket(`ws://26.69.27.44:8080/wg.server/tro-choi?token=${token}&&phongId=${phongId}`);

websocket.onmessage = (duLieu) => {
    console.log("DATA", duLieu);
    const lenh = JSON.parse(duLieu.data);
    console.log("Lenh", lenh);
    console.dir(cauHoi);

    switch (lenh.lenh) {
        case "khoiTao":
            phong.innerHTML = "Phòng ID: " + phongId;
            nguoiChoiSoMot.childNodes[1].src = `../img/avt${lenh.chuPhong.nguoiDung.anhDaiDien}.png`;
            nguoiChoiSoMot.childNodes[3].innerText = lenh.chuPhong.nguoiDung.ten;
            nguoiChoiSoMot.childNodes[5].childNodes[3].innerText = lenh.chuPhong.diem;
            nguoiChoiSoMot.style.display = "block";
            localStorage.setItem("chuPhong", 1);
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

            if (chuPhong) {
                websocket.send(JSON.stringify({
                    lenh: "batDau"
                }));
            }
        // case "guiCauHoi": 
        //     cauHoi.childNodes[1].childNodes[1].innerText = "Câu :" + "";
        //     cauHoi.childNodes[1].childNodes[3].innerText = lenh.cauHoi.noiDung;
        //     for(let i = 0; i < 4; ++i) {
        //         let noiDung = 
        //         document.getElementById('dapAn'+i).innerText = noiDung
        //         ;
        //     }


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
