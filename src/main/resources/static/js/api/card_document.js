async function createCardDocument(data,token) {
    return await fetch("/patient/createCardDocument",{
        method :'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    });
}

async function deleteCardDocumentByCardIdFromPatient(data, token) {
    return await fetch("/patient/deleteCardDocumentByCardId",{
        method :'DELETE',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    });
}
async function deleteCardDocumentByCardIdFromDoctor(data, token) {
    return await fetch("/doctor/deleteCardDocumentByCardId",{
        method :'DELETE',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    });
}
async function isCardDocumentExist(data,token) {
    return await fetch("/patient/isCardDocumentExist",{
        method :'POST',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body:JSON.stringify(data)

    });
}