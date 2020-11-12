async function createPatientCard(data,token) {
    return await fetch("/patient/createPatientCard",{
        method :'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    });
}
async function isPatientCardExist(data,token) {
    return await fetch("/patient/isPatientCardExist",{
        method :'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    });
}
async function deletePatientCard(data,token) {
    return await fetch("/patient/deletePatientCard",{
        method :'DELETE',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    });
}
async function updatePatientCard(data,token) {
    return await fetch("/patient/updatePatientCard",{
        method :'PUT',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    });
}
async function updateFullPatientCard(data,token) {
    return await fetch("/doctor/updateFullPatientCard",{
        method :'PUT',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    });
}
async function getPatientCard(data,token) {
    return await fetch(`/patient/getPatientCard/${data}`,{
        method :'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },

    });
}