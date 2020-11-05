async function getAllUsers() {
    return await fetch("/users", {
        method: "POST",
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        return data;
    });
}

async function logUser(data) {
   return await fetch("/login", {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json'
        }
    });
}

async function regUser(data) {
   return  await fetch("/register", {
        method: "POST",
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify(data)
    });
}

async function getUserByToken(token) {
   return await fetch(`/getUser/${token}`, {
        method: 'POST',
        headers: {
            'Authorisation': `Bearer ${token}`
        }
    });
}

async function authorizedUser(token) {
    return await fetch("/authorized", {
        method: "POST",
        headers: {
            'Authorization': `Bearer ${token}`
        }
    });
}
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