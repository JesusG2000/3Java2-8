async function deleteAllPatientVisit(data,token) {
    console.log(data);
    return await fetch("/patient/deleteAllPatientVisit",{
        method :'DELETE',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    });
}
async function addPatientVisit(data,token) {
    return await fetch("/patient/addPatientVisit",{
        method :'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    });
}
async function getAllPatientVisit(id,token) {
    return await fetch(`/patient/getAllPatientVisit/${id}`,{
        method :'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        }

    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        return data;
    });
}