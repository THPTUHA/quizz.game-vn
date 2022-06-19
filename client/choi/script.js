var thua_cuoc = false;

function tach_params(url) {
    var params = {};
    url.split('?')[1].split('&').forEach(param => {
        const [key, value] = param.split('=');
        params[key] = value;
    });
    return params;
}


function lay_thong_tin_phong_choi() {
    const params = tach_params(window.location.href);
    const fetch = new Fetch();
    fetch.getJWT('/phong_choi.json' + params.phong).then(res => {
        if (res.success) {

        }
        else {

        }
    });
}


function lay_cau_hoi_moi() {
    const params = tach_params(window.location.href);
    const fetch = new Fetch();
    fetch.getJWT('/cau_hoi.json' + params.phong).then(data => {
        if (data.success) {
            const cauhoi = data.cauhoi;
            document.getElementById('cauhoi').innerHTML = cauhoi.cauhoi;
            document.getElementById('cau_hoi_id').value = cauhoi.id;
        }
        else {

        }
    });
}

