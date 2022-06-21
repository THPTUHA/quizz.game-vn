function chon_nguoidung() {
    document.getElementById("btn_cauhoi").style.backgroundColor = "#acbfe2";
    document.getElementById("btn_nguoidung").style.backgroundColor = "#d5e6c5";
    document.getElementById("nguoidung").style.display = "block";
    document.getElementById("cauhoi").style.display = "none";
    lay_nguoidung();
}

function chon_cauhoi() {
    document.getElementById("btn_cauhoi").style.backgroundColor = "#d5e6c5";
    document.getElementById("btn_nguoidung").style.backgroundColor = "#acbfe2";
    document.getElementById("nguoidung").style.display = "none";
    document.getElementById("cauhoi").style.display = "block";
    lay_cauhoi();
}

function lay_nguoidung() {
    const fetch = new Fetch();
    fetch.getJWT("quan-ly/danh-sach/nguoi-dung")
        .then(function (data) {
            const ds_nguoidung = data.danhSachNguoiDung;
            if (ds_nguoidung.length > 0) {
                var html = "";
                for (var i = 0; i < ds_nguoidung.length; i++) {
                    var nguoidung = ds_nguoidung[i];
                    html += `
                    <tr>
                        <td>${i + 1}</th>
                        <td>${nguoidung.id}</td>
                        <td>${nguoidung.ten}</td>
                        <td>
                            ${nguoidung.trangThai != -1
                            ?
                            `<button id="${nguoidung.id}" onclick="thaotac('${nguoidung.id}', -1)" class="nut nut_do">Khóa</button>`
                            :
                            `<button id="${nguoidung.id}" onclick="thaotac('${nguoidung.id}', 0)" class="nut nut_xanhduong">Mở khóa</button>`
                        }
                        </td>
                    </tr>
                `;
                }
                document.getElementById("nd_nguoidung").innerHTML = html;
            } else {
                // alert(data.message);
            }
        });
}

function thaotac(id, trangthai) {
    if (trangthai == -1 && !confirm("Bạn có chắc chắn muốn khóa tài khoản này?")) {
        return;
    }
    if (trangthai == 0 && !confirm("Bạn có chắc chắn muốn mở khóa tài khoản này?")) {
        return;
    }
    const fetch = new Fetch();
    fetch.postJWT("quan-ly/cam/nguoi-dung", {
        id: id
    })
        .then(function (data) {
            if (data.id == id) {
                if (trangthai) {
                    document.getElementById(id).innerHTML = "Mở khóa";
                    document.getElementById(id).onclick = function () {
                        thaotac(id, 0);
                    };
                    document.getElementById(id).classList.remove("nut_do");
                    document.getElementById(id).classList.add("nut_xanhduong");
                } else {
                    document.getElementById(id).innerHTML = "Khóa";
                    document.getElementById(id).onclick = function () {
                        thaotac(id, -1);
                    };
                    document.getElementById(id).classList.remove("nut_xanhduong");
                    document.getElementById(id).classList.add("nut_do");
                }
            } else {
                // alert(data.message);
                alert("Thao tác thất bại!");
            }
        });
}

function loc_cauhoi() {
    let capdo_inp = document.getElementById("loc_capdo");
    capdo_inp.onkeydown = function (e) {
        if (e.code != 'Digit3' && e.code != 'Digit2' && e.code != 'Digit1' && e.code != 'Digit0') {
            return;
        }
        capdo_inp.value = '';
    }
    if (capdo_inp.value != '1' && capdo_inp.value != '2' && capdo_inp.value != '3' && capdo_inp.value != '0') {
        capdo_inp.value = '';
        return;
    }
    Object.values(document.getElementsByClassName("tr_cauhoi")).forEach(function (tr) {
        tr.style.display = "none";
    });
    Object.values(document.getElementsByClassName("tr_" + capdo_inp.value)).forEach(function (tr) {
        tr.style.display = "table-row";
    });
    stt();
}


function stt() {
    var nd_cauhoi = document.getElementById("nd_cauhoi");
    let idx = 1;
    for (var i = 1; i < nd_cauhoi.children.length; i += 2) {
        if (nd_cauhoi.children[i].style.display != "none") {
            nd_cauhoi.children[i].children[0].innerHTML = idx;
            idx++;
        }
    }
}

function lay_cauhoi() {
    const fetch = new Fetch();
    fetch.getJWT("quan-ly/danh-sach/cau-hoi")
        .then(function (data) {
            var ds_cauhoi = data.danhSachCauHoi;
            var html = `
                    <tr>
                        <td>0</td>
                        <td>0</td>
                        <td>0</td>
                        <td>0</td>
                        <td>
                            <input type="text" id="loc_capdo" maxlength="1" onkeyup="loc_cauhoi()" style="width:20px;text-align:center;" />
                        </td>
                        <td>
                            <button class="nut nut_xanhcay" onclick="them()">Thêm</button>
                        </td>
                `
            for (var i = 0; i < ds_cauhoi.length; i++) {
                var id = ds_cauhoi[i].id;
                var cauhoi = ds_cauhoi[i].noiDung;
                var a = ds_cauhoi[i].goiY.danhSachLuaChon[0].noiDung;
                var b = ds_cauhoi[i].goiY.danhSachLuaChon[1].noiDung;
                var c = ds_cauhoi[i].goiY.danhSachLuaChon[2].noiDung;
                var d = ds_cauhoi[i].goiY.danhSachLuaChon[3].noiDung;
                var dapan = ds_cauhoi[i].dapAn.danhSachLuaChon[0].id;
                dapan = dapan == 1 ? 'a' : dapan == 2 ? 'b' : dapan == 3 ? 'c' : 'd';
                var capdo = ds_cauhoi[i].capDo;
                var diem = ds_cauhoi[i].diem;

                html += ` 
                    <tr id="${id}" class="tr_cauhoi tr_${capdo} tr_0">
                        <div id="${id}_dapan" hidden>${dapan}</div>
                        <td class="stt">${i + 1}</td>
                        <td id="${id}_ch">${cauhoi}</td>
                        <td>
                            <p id="${id}_a" class="dapan ${dapan === 'a' ? 'dapandung' : ''}">${a}</p>
                            <p id="${id}_b" class="dapan ${dapan === 'b' ? 'dapandung' : ''}">${b}</p>
                            <p id="${id}_c" class="dapan ${dapan === 'c' ? 'dapandung' : ''}">${c}</p>
                            <p id="${id}_d" class="dapan ${dapan === 'd' ? 'dapandung' : ''}">${d}</p>
                        </td>
                        <td id="${id}_diem">${diem}</td>
                        <td id="${id}_capdo">${capdo}</td>
                        <td>
                            <button id="sua${id}" onclick="suacauhoi('${id}')" class="nut nut_xanhduong">Sửa</button>
                            <button id="xoa${id}" onclick="xoacauhoi('${id}')" class="nut nut_do">Xóa</button>
                        </td>
                    </tr>
                `;
            }
            document.getElementById("nd_cauhoi").innerHTML = html;
        });
}


function xoacauhoi(id) {
    if (!confirm("Bạn có chắc chắn muốn xóa câu hỏi này?")) {
        return;
    }
    const fetch = new Fetch();
    fetch.postJWT("quan-ly/xoa/cau-hoi", {
        id: id
    })
        .then(function (data) {
            try {
                let id = data.id;
                alert("Xóa thành công!");
                document.getElementById(id).remove();
            }
            catch (error) {
                alert("Đã xảy ra lỗi!");
            }
        });
}


function suacauhoi(id) {
    document.getElementById("nenmo").style.display = "block";
    document.getElementById("cuaso_themcauhoi").style.display = "none";
    document.getElementById("cuaso_suacauhoi").style.display = "block";
    document.getElementsByTagName('body')[0].style.overflow = 'hidden';
    document.getElementById("sch_id").value = id;
    document.getElementById("sch_cauhoi").value = document.getElementById(id + "_ch").innerHTML;
    document.getElementById("sch_a").value = document.getElementById(id + "_a").innerHTML;
    document.getElementById("sch_b").value = document.getElementById(id + "_b").innerHTML;
    document.getElementById("sch_c").value = document.getElementById(id + "_c").innerHTML;
    document.getElementById("sch_d").value = document.getElementById(id + "_d").innerHTML;
    document.getElementById("sch_dapan").value = document.getElementById(id + "_dapan").innerHTML;
    document.getElementById("sch_capdo").value = document.getElementById(id + "_capdo").innerHTML;
    document.getElementById("sch_diem").value = document.getElementById(id + "_diem").innerHTML;
}

function them() {
    document.getElementById("nenmo").style.display = "block";
    document.getElementById("cuaso_themcauhoi").style.display = "block";
    document.getElementById("cuaso_suacauhoi").style.display = "none";
    document.getElementsByTagName('body')[0].style.overflow = 'hidden';
}

function luu() {
    const id = document.getElementById("sch_id").value;
    const cauhoi = document.getElementById("sch_cauhoi").value;
    const a = document.getElementById("sch_a").value;
    const b = document.getElementById("sch_b").value;
    const c = document.getElementById("sch_c").value;
    const d = document.getElementById("sch_d").value;
    var dapan = document.getElementById("sch_dapan").value;
    const capdo = document.getElementById("sch_capdo").value;
    const diem = document.getElementById("sch_diem").value;

    if (dapan === 'a') {
        dapan = {
            id: 1,
            noiDung: a,
            giaiThich: ""
        }
    }
    else if (dapan === 'b') {
        dapan = {
            id: 2,
            noiDung: b,
            giaiThich: ""
        }
    }
    else if (dapan === 'c') {
        dapan = {
            id: 3,
            noiDung: c,
            giaiThich: ""
        }
    }
    else if (dapan === 'd') {
        dapan = {
            id: 4,
            noiDung: d,
            giaiThich: ""
        }
    }

    document.getElementsByTagName('body')[0].style.overflow = 'auto';

    const fetch = new Fetch();
    fetch.postJWT("quan-ly/cap-nhat/cau-hoi", {
        id: id,
        noiDung: cauhoi,
        goiY: {
            danhSachLuaChon: [
                {
                    id: 1,
                    giaiThich: "",
                    noiDung: a,
                },
                {
                    id: 2,
                    giaiThich: "",
                    noiDung: b,
                },
                {
                    id: 3,
                    giaiThich: "",
                    noiDung: c,
                },
                {
                    id: 4,
                    giaiThich: "",
                    noiDung: d,
                }
            ],
        },
        dapAn: {
            danhSachLuaChon: [
                dapan
            ]
        },
        capDo: capdo,
        diem: diem
    })
        .then(function (data) {
            try {
                let id = data.id;
                dapan = dapan.id === 1 ? "a" : dapan.id === 2 ? "b" : dapan.id === 3 ? "c" : "d";
                alert("Sửa thành công!");
                document.getElementById(id + "_ch").innerHTML = cauhoi;
                document.getElementById(id + "_a").innerHTML = a;
                document.getElementById(id + "_a").classList.remove("dapandung");
                document.getElementById(id + "_b").innerHTML = b;
                document.getElementById(id + "_b").classList.remove("dapandung");
                document.getElementById(id + "_c").innerHTML = c;
                document.getElementById(id + "_c").classList.remove("dapandung");
                document.getElementById(id + "_d").innerHTML = d;
                document.getElementById(id + "_d").classList.remove("dapandung");
                document.getElementById(id + "_dapan").innerHTML = dapan;
                if (dapan === "a") {
                    document.getElementById(id + "_a").classList.add("dapandung");
                } else if (dapan === "b") {
                    document.getElementById(id + "_b").classList.add("dapandung");
                } else if (dapan === "c") {
                    document.getElementById(id + "_c").classList.add("dapandung");
                } else {
                    document.getElementById(id + "_d").classList.add("dapandung");
                }
                document.getElementById(id + "_capdo").innerHTML = capdo;
                document.getElementById(id + "_diem").innerHTML = diem;
                document.getElementById("nenmo").style.display = "none";
            }
            catch (error) {
                alert("Sửa thất bại!");
            }
        });
}

function post() {
    const cauhoi = document.getElementById("them_cauhoi").value;
    const a = document.getElementById("them_a").value;
    const b = document.getElementById("them_b").value;
    const c = document.getElementById("them_c").value;
    const d = document.getElementById("them_d").value;
    var dapan = document.getElementById("them_dapan").value;
    const capdo = document.getElementById("them_capdo").value;
    const diem = document.getElementById("them_diem").value;

    if (dapan === "a") {
        dapan = {
            id: 1,
            noiDung: a,
            giaiThich: ""
        }
    }
    if (dapan === "b") {
        dapan = {
            id: 2,
            noiDung: b,
            giaiThich: ""
        }
    }
    if (dapan === "c") {
        dapan = {
            id: 3,
            noiDung: c,
            giaiThich: ""
        }
    }
    if (dapan === "d") {
        dapan = {
            id: 4,
            noiDung: d,
            giaiThich: ""
        }
    }

    const fetch = new Fetch();
    fetch.postJWT("quan-ly/tao/cau-hoi", {
        noiDung: cauhoi,
        goiY: {
            danhSachLuaChon: [
                {
                    id: 1,
                    giaiThich: "",
                    noiDung: a,
                },
                {
                    id: 2,
                    giaiThich: "",
                    noiDung: b,
                },
                {
                    id: 3,
                    giaiThich: "",
                    noiDung: c,
                },
                {
                    id: 4,
                    giaiThich: "",
                    noiDung: d,
                }
            ],
        },
        dapAn: {
            danhSachLuaChon: [
                dapan
            ]
        },
        capDo: capdo,
        diem: diem
    })
        .then(function (data) {
            try {
                let id = data.id;
                alert("Thêm thành công!");
                document.getElementById("nenmo").style.display = "none";
                document.getElementById("them_cauhoi").value = "";
                document.getElementById("them_a").value = "";
                document.getElementById("them_b").value = "";
                document.getElementById("them_c").value = "";
                document.getElementById("them_d").value = "";
                document.getElementById("them_dapan").value = "";
                document.getElementById("them_capdo").value = "";
                document.getElementsByTagName('body')[0].style.overflow = 'auto';
                chon_cauhoi();
            }
            catch (error) {
                alert("Đã xảy ra lỗi!");
            }
        });
}

function huy() {
    document.getElementById("nenmo").style.display = "none";
    document.getElementsByTagName('body')[0].style.overflow = 'auto';
}

document.addEventListener("keydown", function onPress(event) {
    if (event.key === "Escape" && document.getElementById("nenmo").style.display === "block") {
        huy();
    }
});

chon_nguoidung();


if (!localStorage.getItem("token")) {
    window.location.href = "/dangnhap";
}
