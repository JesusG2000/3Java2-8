async function patientVisit() {
    let token = localStorage.getItem('token');
    let userData = await getUserByToken(token);
    let text = await userData.text();
    let textUserData = JSON.parse(text);
    let isNotExist = await isPatientCardExist({id: textUserData['id']}, token);
    if (!isNotExist.ok) {
        let res = await getPatientCard(textUserData['id'], token);
        let text = await res.text();
        let docDocumentText = JSON.parse(text);
        await addPatientVisit({
            date: new Date(),
            patientCard: docDocumentText
        }, token);

    }
}

async function genCard() {
    let token = localStorage.getItem('token');
    let userData = await getUserByToken(token);
    let text = await userData.text();
    let textUserData = JSON.parse(text);
    let isNotExist = await isPatientCardExist({id: textUserData['id']}, token);
    let info = document.querySelector(".personalInfo");

    let nameValue = '';
    let surnameValue = '';
    let fathernameValue = '';
    let patientReportValue = '';
    let isHeadache = false;
    let isStomachache = false;
    let isBrainDeadPerson = true;

    let isDisabled = false;

    if (!isNotExist.ok) {
        let res = await getPatientCard(textUserData['id'], token);
        let text = await res.text();
        let docDocumentText = JSON.parse(text);

        isBrainDeadPerson = false;
        isDisabled = true;

        nameValue = docDocumentText['name'];
        surnameValue = docDocumentText['surname'];
        fathernameValue = docDocumentText['fathername'];
        patientReportValue = docDocumentText['patientReport'];

        switch (docDocumentText['sicks'][0]) {
            case 'HEADACHE': {
                isHeadache = true;
                break;
            }
            case 'STOMACHACHE': {
                isStomachache = true;
                break;
            }
            case 'BRAIN_DEAD_PERSON': {
                isBrainDeadPerson = true;
                break;
            }
        }


    }
    let name = input('text', 'name', 'Name', nameValue);

    let surname = input('text', 'surname', 'Surname', surnameValue);

    let fathername = input('text', 'fathername', 'Fathername', fathernameValue);

    let patientReport = input('text', 'patientReport', 'Report', patientReportValue);

    let fieldset = div();

    let radioHeadacheLabel = label('Headache');
    let radioHeadache = input('radio', 'headache', '', 'Headache', 'patientSick');
    radioHeadache.checked = isHeadache;
    radioHeadache.disabled = isDisabled;
    radioHeadacheLabel.appendChild(radioHeadache);


    let radioStomachacheLabel = label('Stomachache');
    let radioStomachache = input('radio', 'stomachache', '', 'Stomachache', 'patientSick');
    radioStomachache.checked = isStomachache;
    radioStomachache.disabled = isDisabled;
    radioStomachacheLabel.appendChild(radioStomachache);


    let radioBrainDeadPersonLabel = label('BrainDeadPerson');
    let radioBrainDeadPerson = input('radio', 'brain_Dead_Person', '', 'Brain_Dead_Person', 'patientSick');
    radioBrainDeadPerson.checked = isBrainDeadPerson;
    radioBrainDeadPerson.disabled = isDisabled;
    radioBrainDeadPersonLabel.appendChild(radioBrainDeadPerson);


    fieldset.appendChild(radioHeadacheLabel);
    fieldset.appendChild(br());
    fieldset.appendChild(radioStomachacheLabel);
    fieldset.appendChild(br());
    fieldset.appendChild(radioBrainDeadPersonLabel);
    fieldset.appendChild(br());


    info.appendChild(name);
    info.appendChild(surname);
    info.appendChild(fathername);
    info.appendChild(patientReport);
    info.appendChild(fieldset);
}

function validateCard() {
    let nameL = document.getElementById('name').value.length;
    let surnameL = document.getElementById('surname').value.length;
    let fathernameL = document.getElementById('fathername').value.length;
    let reportL = document.getElementById('patientReport').value.length;

    if (!(nameL >= 2 && nameL <= 16)) {
        return false;
    }
    if (!(surnameL >= 4 && surnameL <= 16)) {
        return false;
    }
    if (!(fathernameL >= 4 && fathernameL <= 16)) {
        return false;
    }
    if (!(reportL >= 4 && reportL <= 255)) {
        return false;
    }
    return true;

}

async function genPatientCreateButton() {
    let token = localStorage.getItem('token');
    let userData = await getUserByToken(token);
    let text = await userData.text();
    let userTextData = JSON.parse(text);
    let isNotExist = await isPatientCardExist({id: userTextData['id']}, token);
    let errMes = document.getElementById('errMes');
    if (validateCard() && await isAuth() && isNotExist.ok) {
        let name = document.getElementById('name').value;
        let surname = document.getElementById('surname').value;
        let fathername = document.getElementById('fathername').value;
        let patientReport = document.getElementById('patientReport').value;
        let userId = userTextData['id'];

        let patientSickList = document.getElementsByName('patientSick');
        let patientSick;

        for (let i = 0; i < patientSickList.length; i++) {
            patientSickList[i].disabled = true;
            if (patientSickList[i].checked) {
                patientSick = patientSickList[i];
            }
        }

        let data = {
            name: name,
            surname: surname,
            fathername: fathername,
            patientReport: patientReport,
            sick: patientSick.value.toUpperCase(),
            userId: userId,
        };
        await createPatientCard(data, token);
        await patientVisit();
        console.log(data);
        errMes.innerHTML = 'Created';
    } else {
        errMes.innerHTML = 'Not all fields are correct or card exist';
    }

}

async function genCardCreate() {
    let create = document.querySelector('.create');
    let createButton = button(await genPatientCreateButton, 'Create');
    createButton.id = 'patientCreateButton';
    create.appendChild(createButton);
}

async function genUpdatePatientCard() {
    let errMes = document.getElementById('errMes');
    let token = localStorage.getItem('token');
    let userData = await getUserByToken(token);
    let text = await userData.text();
    let userTextData = JSON.parse(text);
    let isNotExist = await isPatientCardExist({id: userTextData['id']}, token);
    if (await isAuth() && !isNotExist.ok && validateCard()) {
        let res = await getPatientCard(userTextData['id'], token);
        let text = await res.text();
        let patientInfo = JSON.parse(text);
        await updatePatientCard({
            id: patientInfo['id'],
            name: document.getElementById('name').value,
            surname: document.getElementById('surname').value,
            fathername: document.getElementById('fathername').value,
            patientReport: document.getElementById('patientReport').value,
        }, token);
        errMes.innerHTML = 'updated';
    } else {
        errMes.innerHTML = 'not validate data or card not exist';
    }
}

async function genCardUpdate() {
    let create = document.querySelector('.update');
    let deleteButton = button(genUpdatePatientCard, 'update');
    create.appendChild(deleteButton);
}

async function genDeletePatientCard() {
    let errMes = document.getElementById('errMes');
    let token = localStorage.getItem('token');
    let userData = await getUserByToken(token);
    let text = await userData.text();
    let userTextData = JSON.parse(text);
    let isNotExist = await isPatientCardExist({id: userTextData['id']}, token);
    let patientSickList = document.getElementsByName('patientSick');

    if (await isAuth() && !isNotExist.ok) {

        let elem = document.querySelector('.neededInfo');
        elem.innerHTML='';
        let res = await getPatientCard(userTextData['id'], token);
        let text = await res.text();
        let patientCardText = JSON.parse(text);
        await deleteAllPatientVisit({id: patientCardText['id']}, token);

        await deleteCardDocumentByCardIdFromPatient({id: patientCardText['id']}, token);

        await deletePatientCard({id: userTextData['id']}, token);

        for (let i = 0; i < patientSickList.length; i++) {
            patientSickList[i].disabled = false;
        }

        errMes.innerHTML = 'deleted';

    } else {
        errMes.innerHTML = 'nothing to delete';
    }
}

async function genCardDelete() {
    let create = document.querySelector('.create');
    let deleteButton = button(genDeletePatientCard, 'Delete');
    create.appendChild(deleteButton);
}

async function subscribePatient(doctorDocument) {
    let errMes = document.getElementById('errMes');
    let token = localStorage.getItem('token');
    let userData = await getUserByToken(token);
    let text = await userData.text();
    let userTextData = JSON.parse(text);
    let isNotExist = await isPatientCardExist({id: userTextData['id']}, token);
    if (!isNotExist.ok) {
        let res = await getPatientCard(userTextData['id'], token);
        let text = await res.text();
        let patientCardInfo = JSON.parse(text);
        isNotExist = await isCardDocumentExist({id: patientCardInfo['id']}, token);
        if (isNotExist.ok) {
            await createCardDocument({
                card: patientCardInfo,
                doctorDocument: doctorDocument
            }, token);
            errMes.innerHTML = 'subscribed';
        } else {
            errMes.innerHTML = 'U have already sub on someone';
        }

    } else {
        errMes.innerHTML = 'U need to create a card';
    }


}

async function getListOfDoctors() {
    let doctorsDocs = await getAllDoctors(localStorage.getItem('token'));
    let list = document.querySelector('.someList');
    for (let i = 0; i < doctorsDocs.length; i++) {
        let infoDiv = div();
        let infoP = p(doctorsDocs[i].surname + '.' + doctorsDocs[i].name[0] + '.' + doctorsDocs[i].fathername[0] + ' - ' + doctorsDocs[i].specs[0]);
        let subButton = buttonWithParams('subscribe');
        subButton.onclick = async () => {
            await subscribePatient(doctorsDocs[i])
        };
        infoDiv.appendChild(infoP);
        infoDiv.appendChild(subButton);
        list.appendChild(infoDiv)
    }

}

async function genPatientInfo() {
    let token = localStorage.getItem('token');
    let info = document.querySelector('.neededInfo');
    let table = document.createElement('table');
    table.setAttribute('class' , 'table');


    let userData = await getUserByToken(token);
    let text = await userData.text();
    let textUserData = JSON.parse(text);


    let res = await getPatientCard(textUserData['id'], token);
    let text1 = await res.text();
    let patientInfo = JSON.parse(text1);


    let visits = await getAllPatientVisit(patientInfo['id'],token);


    for (let i = visits.length-1; i >=0; i--) {
        if (i === visits.length-1) {
            let tr = document.createElement('tr');
            let th1 = document.createElement('th');
            th1.innerHTML = 'Name';
            let th2 = document.createElement('th');
            th2.innerHTML = 'Surname';
            let th3 = document.createElement('th');
            th3.innerHTML = 'FatherName';
            let th4 = document.createElement('th');
            th4.innerHTML = 'Recommendation';
            let th5 = document.createElement('th');
            th5.innerHTML = 'Sick';
            let th6 = document.createElement('th');
            th6.innerHTML = 'Visit';
            tr.appendChild(th1);
            tr.appendChild(th2);
            tr.appendChild(th3);
            tr.appendChild(th4);
            tr.appendChild(th5);
            tr.appendChild(th6);
            table.appendChild(tr);
        }
        let tr = document.createElement('tr');

        for (let y = 0; y < 6; y++) {
                let th = document.createElement('th');
                switch (y) {
                    case 0: {
                        th.innerHTML = patientInfo['name'];
                        break;
                    }
                    case 1: {
                        th.innerHTML = patientInfo['surname'];
                        break;
                    }
                    case 2: {
                        th.innerHTML = patientInfo['fathername'];
                        break;
                    }
                    case 3: {
                        th.innerHTML = patientInfo['recommendation'];
                        break;
                    }
                    case 4: {
                        th.innerHTML = patientInfo['sicks'][0];
                        break;
                    }
                    case 5: {
                        th.innerHTML = visits[i]['date'];
                        break;
                    }
                }
                tr.appendChild(th);
            }

        table.appendChild(tr);
    }
    info.appendChild(table);

}
