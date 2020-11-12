async function createDocDocument(data,token) {
    return await fetch("/doctor/createDoctorDocument",{
        method :'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    });
}
async function isDocDocumentExist(data,token) {
    return await fetch("/doctor/isExistDoctorDocument",{
        method :'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    });
}
async function deleteDocDocument(data,token) {
    return await fetch("/doctor/deleteDoctorDocument",{
        method :'DELETE',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    });
}
async function updateDocDocument(data,token) {
    return await fetch("/doctor/updateDoctorDocument",{
        method :'PUT',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    });
}
async function getDocDocument(data,token) {
    return await fetch(`/doctor/getDoctorDocument/${data}`,{
        method :'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },

    });
}
async function getAllSubPatients(data,token) {
    return await fetch(`/doctor/getAllSubPatients/${data}`,{
        method :'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },

    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        return data;
    });
}