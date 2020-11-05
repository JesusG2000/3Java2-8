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

    if (!isNotExist.ok) {
        let res = await getDocDocument(textUserData['id'], token);
        let text = await res.text();
        let docDocumentText = JSON.parse(text);
        nameValue = docDocumentText['name'];
        surnameValue = docDocumentText['surname'];
        fathernameValue = docDocumentText['fathername'];
        isCheckedJunior = false;
        console.log(docDocumentText);
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
    radioProLabel.appendChild(radioPro);
    radioPro.checked=isCheckedPro;

    let radioMiddleLabel = label('Middle');
    let radioMiddle = input('radio', 'middle', '', 'Middle', 'docSpec');
    radioMiddleLabel.appendChild(radioMiddle);
    radioMiddle.checked = isCheckedMiddle;

    let radioJuniorLabel = label('Junior');
    let radioJunior = input('radio', 'junior', '', 'Junior', 'docSpec');
    radioJunior.checked = isCheckedJunior;
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
            if (doctorSpecList[i].checked) {
                doctorSpec = doctorSpecList[i];
                break;
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
    if (await isAuth() && !isNotExist.ok) {

        await deleteDocDocument({id: textData['id']}, token);
        errMes.innerHTML = 'deleted';

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