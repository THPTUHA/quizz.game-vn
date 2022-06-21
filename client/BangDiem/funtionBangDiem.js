function btnThoat() {
    window.location.href = "../TrangChu/TrangChu.html";
}

if (!localStorage.getItem("token")) {
    window.location.href = "/dangnhap";
}
