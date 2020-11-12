async function genDocument() {
    let token = localStorage.getItem('token');
    let userData = await getUserByToken(token);
    let text = await userData.text();
    let textUserData = JSON.parse(text);
    let isNotExist = await isDocDocumentExist({id: textUserData['id']}, token);
    let info = document.querySelector(".personalInfo");

    let nameValue = '';
    let surnameValue = '';
    let fathernameValue = '';
    let isCheckedPro = false;
    let isCheckedMiddle = false;
    let isCheckedJunior = true;

    let isDisabled = false;


    if (!isNotExist.ok) {
        let res = await getDocDocument(textUserData['id'], token);
        let text = await res.text();
        let docDocumentText = JSON.parse(text);
        nameValue = docDocumentText['name'];
        surnameValue = docDocumentText['surname'];
        fathernameValue = docDocumentText['fathername'];
        isCheckedJunior = false;
        isDisabled = true;
        switch (docDocumentText['specs'][0]) {
            case 'PRO':
                isCheckedPro = true;
                break;
            case 'MIDDLE':
                isCheckedMiddle = true;
                break;
            case 'JUNIOR':
                isCheckedJunior = true;
                break;
        }

    }


    let name = input('text', 'name', 'Name', nameValue);

    let surname = input('text', 'surname', 'Surname', surnameValue);

    let fathername = input('text', 'fathername', 'Fathername', fathernameValue);


    let fieldset = div();

    let radioProLabel = label('Pro');
    let radioPro = input('radio', 'pro', '', 'Pro', 'docSpec');
    radioPro.checked=isCheckedPro;
    radioPro.disabled=isDisabled;
    radioProLabel.appendChild(radioPro);


    let radioMiddleLabel = label('Middle');
    let radioMiddle = input('radio', 'middle', '', 'Middle', 'docSpec');
    radioMiddle.checked = isCheckedMiddle;
    radioMiddle.disabled = isDisabled;
    radioMiddleLabel.appendChild(radioMiddle);


    let radioJuniorLabel = label('Junior');
    let radioJunior = input('radio', 'junior', '', 'Junior', 'docSpec');
    radioJunior.checked = isCheckedJunior;
    radioJunior.disabled = isDisabled;
    radioJuniorLabel.appendChild(radioJunior);


    fieldset.appendChild(radioProLabel);
    fieldset.appendChild(br());
    fieldset.appendChild(radioMiddleLabel);
    fieldset.appendChild(br());
    fieldset.appendChild(radioJuniorLabel);
    fieldset.appendChild(br());

    info.appendChild(name);
    info.appendChild(surname);
    info.appendChild(fathername);
    info.appendChild(fieldset);


}

async function genDocCreate() {

    let create = document.querySelector('.create');
    let createButton = button(await genDocCreateButton, 'Create');
    createButton.id = 'docCreateButton';
    create.appendChild(createButton);

}

async function genDocDelete() {

    let create = document.querySelector('.create');
    let deleteButton = button(genDeleteDocDocument, 'Delete');
    create.appendChild(deleteButton);
}

async function genDocCreateButton() {
    let token = localStorage.getItem('token');
    let userData = await getUserByToken(token);
    let text = await userData.text();
    let textData = JSON.parse(text);
    let isNotExist = await isDocDocumentExist({id: textData['id']}, token);
    let errMes = document.getElementById('errMes');
    if (validateDocument() && await isAuth() && isNotExist.ok) {
        let name = document.getElementById('name').value;
        let surname = document.getElementById('surname').value;
        let fathername = document.getElementById('fathername').value;
        let userId = textData['id'];
        let doctorSpecList = document.getElementsByName('docSpec');
        let doctorSpec;

        for (let i = 0; i < doctorSpecList.length; i++) {
            doctorSpecList[i].disabled=true;
            if (doctorSpecList[i].checked) {
                doctorSpec = doctorSpecList[i];
            }
        }

        let data = {
            name: name,
            surname: surname,
            fathername: fathername,
            userId: userId,
            doctorSpec: doctorSpec.value.toUpperCase()
        };
        await createDocDocument(data, token);
        console.log(data);
        errMes.innerHTML = 'Created';
    } else {
        errMes.innerHTML = 'Not all fields are correct or document exist';
    }
}

async function genDeleteDocDocument() {
    let errMes = document.getElementById('errMes');
    let token = localStorage.getItem('token');
    let userData = await getUserByToken(token);
    let text = await userData.text();
    let textData = JSON.parse(text);
    let isNotExist = await isDocDocumentExist({id: textData['id']}, token);
    let doctorSpecList = document.getElementsByName('docSpec');
    if (await isAuth() && !isNotExist.ok) {

        let result = await deleteDocDocument({id: textData['id']}, token);
        if(result.ok) {

            for (let i = 0; i < doctorSpecList.length; i++) {
                doctorSpecList[i].disabled = false;
            }
            errMes.innerHTML = 'deleted';
        }else{
            errMes.innerHTML = 'U still have patients';
        }

    } else {
        errMes.innerHTML = 'nothing to delete';
    }
}

function validateDocument() {
    let nameL = document.getElementById('name').value.length;
    let surnameL = document.getElementById('surname').value.length;
    let fathernameL = document.getElementById('fathername').value.length;

    if (!(nameL >= 2 && nameL <= 16)) {
        return false;
    }
    if (!(surnameL >= 4 && surnameL <= 16)) {
        return false;
    }
    if (!(fathernameL >= 4 && fathernameL <= 16)) {
        return false;
    }
    return true;

}

async function isDoctor() {
    let user = await getUser();
    return user['role'] === 'ROLE_DOCTOR';
}

async function genUpdateDocDocument() {
    let errMes = document.getElementById('errMes');
    let token = localStorage.getItem('token');
    let userData = await getUserByToken(token);
    let text = await userData.text();
    let textData = JSON.parse(text);
    let isNotExist = await isDocDocumentExist({id: textData['id']}, token);
    if (await isAuth() && !isNotExist.ok) {

        let res = await getDocDocument(textData['id'], token);
        let text  = await res.text();
        let docInfo  = JSON.parse(text);
        let doctorSpecList = document.getElementsByName('docSpec');
        let doctorSpec;

        for (let i = 0; i < doctorSpecList.length; i++) {
            if (doctorSpecList[i].checked) {
                doctorSpec = doctorSpecList[i];
                break;
            }
        }
        if(validateDocument()) {
            await updateDocDocument({
                id: docInfo['id'],
                name: document.getElementById('name').value,
                surname: document.getElementById('surname').value,
                fathername: document.getElementById('fathername').value,
                spec: doctorSpec.value.toUpperCase()
            }, token);
            errMes.innerHTML = 'updated';
        }else{
            errMes.innerHTML = 'not validate data';
        }

    } else {
        errMes.innerHTML = 'nothing to update';
    }
}

async function genDocUpdate() {
    let create = document.querySelector('.update');
    let deleteButton = button(genUpdateDocDocument, 'update');
    create.appendChild(deleteButton);
}

async function publishRecommendation(patientCard, recommendation, token) {
    patientCard['recommendation'] = recommendation;
    await updateFullPatientCard({
        id: patientCard['id'],
        name: patientCard['name'],
        surname: patientCard['surname'],
        fathername: patientCard['fathername'],
        recommendation: patientCard['recommendation'],
        patientReport: patientCard['patientReport'],
    }, token);

    await deleteCardDocumentByCardIdFromDoctor({id: patientCard['id']}, token);
    let info = document.querySelector('.neededInfo');
    info.innerHTML='';
    await genListOfSubscribedUsers();
}

async function genListOfSubscribedUsers() {
    let token = localStorage.getItem('token');
    let userData = await getUserByToken(token);
    let text = await userData.text();
    let textUserData = JSON.parse(text);
    let subPatients = await getAllSubPatients(textUserData['id'], token);


    let info = document.querySelector('.neededInfo');
    let table = document.createElement('table');
    table.setAttribute('class' , 'table');

    for (let i = 0; i < subPatients.length; i++) {

        if (i === 0) {
            let tr = document.createElement('tr');
            let th1 = document.createElement('th');
            th1.innerHTML = 'Name';
            let th2 = document.createElement('th');
            th2.innerHTML = 'Surname';
            let th3 = document.createElement('th');
            th3.innerHTML = 'FatherName';
            let th4 = document.createElement('th');
            th4.innerHTML = 'Patient Report';
            let th5 = document.createElement('th');
            th5.innerHTML = 'Sick';
            let th6 = document.createElement('th');
            th6.innerHTML = 'Recommendation';
            let th7 = document.createElement('th');
            th7.innerHTML = 'Publish Recommendation';
            tr.appendChild(th1);
            tr.appendChild(th2);
            tr.appendChild(th3);
            tr.appendChild(th4);
            tr.appendChild(th5);
            tr.appendChild(th6);
            tr.appendChild(th7);
            table.appendChild(tr);
        }
        let tr = document.createElement('tr');
            for (let y = 0; y < 7; y++) {
                let th = document.createElement('th');


                switch (y) {
                    case 0: {
                        th.innerHTML = subPatients[i]['name'];
                        break;
                    }
                    case 1: {
                        th.innerHTML = subPatients[i]['surname'];
                        break;
                    }
                    case 2: {
                        th.innerHTML = subPatients[i]['fathername'];
                        break;
                    }
                    case 3: {
                        th.innerHTML = subPatients[i]['patientReport'];
                        break;
                    }
                    case 4: {
                        th.innerHTML = subPatients[i]['sicks'][0];
                        break;
                    }
                    case 5: {
                        let inputInfo = input('text',subPatients[i]['name'],'for patient');
                        th.appendChild(inputInfo);
                        break;
                    }
                    case 6: {
                        let subButton = buttonWithParams('publish');
                        subButton.onclick= async () => {
                            await publishRecommendation(subPatients[i],
                                document.getElementById(subPatients[i]['name']).value,
                                token
                            );
                        };
                        th.appendChild(subButton);
                        break;
                    }
                }
                tr.appendChild(th);

        }
        table.appendChild(tr);
    }
    info.appendChild(table);
}

async function genDocSearchButton(){
    let token = localStorage.getItem('token');
    let userData = await getUserByToken(token);
    let text = await userData.text();
    let textUserData = JSON.parse(text);
    let subPatients = await getAllSubPatients(textUserData['id'], token);
    let info = document.querySelector('.neededInfo');

    let inputResult = document.getElementById('searchPatientCard').value;
    info.innerHTML='';

    if(inputResult.length===0){
        await genListOfSubscribedUsers();
    }else{
        let table = document.createElement('table');
        table.setAttribute('class' , 'table');

        for (let i = 0; i < subPatients.length; i++) {

            if (i === 0) {
                let tr = document.createElement('tr');
                let th1 = document.createElement('th');
                th1.innerHTML = 'Name';
                let th2 = document.createElement('th');
                th2.innerHTML = 'Surname';
                let th3 = document.createElement('th');
                th3.innerHTML = 'FatherName';
                let th4 = document.createElement('th');
                th4.innerHTML = 'Patient Report';
                let th5 = document.createElement('th');
                th5.innerHTML = 'Sick';
                let th6 = document.createElement('th');
                th6.innerHTML = 'Recommendation';
                let th7 = document.createElement('th');
                th7.innerHTML = 'Publish Recommendation';
                tr.appendChild(th1);
                tr.appendChild(th2);
                tr.appendChild(th3);
                tr.appendChild(th4);
                tr.appendChild(th5);
                tr.appendChild(th6);
                tr.appendChild(th7);
                table.appendChild(tr);
            }
            if(inputResult===subPatients[i]['name']) {
                let tr = document.createElement('tr');
                for (let y = 0; y < 7; y++) {
                    let th = document.createElement('th');

                    switch (y) {
                        case 0: {
                            th.innerHTML = subPatients[i]['name'];
                            break;
                        }
                        case 1: {
                            th.innerHTML = subPatients[i]['surname'];
                            break;
                        }
                        case 2: {
                            th.innerHTML = subPatients[i]['fathername'];
                            break;
                        }
                        case 3: {
                            th.innerHTML = subPatients[i]['patientReport'];
                            break;
                        }
                        case 4: {
                            th.innerHTML = subPatients[i]['sicks'][0];
                            break;
                        }
                        case 5: {
                            let inputInfo = input('text', subPatients[i]['name'], 'for patient');
                            th.appendChild(inputInfo);
                            break;
                        }
                        case 6: {
                            let subButton = buttonWithParams('publish');
                            subButton.onclick = async () => {
                                await publishRecommendation(subPatients[i],
                                    document.getElementById(subPatients[i]['name']).value,
                                    token
                                );
                            };
                            th.appendChild(subButton);
                            break;
                        }
                    }
                    tr.appendChild(th);

                }

            table.appendChild(tr);
            }
        }
        info.appendChild(table);
    }
}

async function genDocSearch() {

    let search = document.querySelector('.search');
    let searchButton = button(await genDocSearchButton, 'Search');
    let searchPatientCard = input('text','searchPatientCard','patient name');
    searchButton.id = 'docCreateButton';
    search.appendChild(searchPatientCard);
    search.appendChild(searchButton);
}