

async function genPatSearch() {

}

async function load() {

    let result = document.querySelector('.results');
    await generateListOfUsers(result);
    genPrev();
    await genNext();


    if (await isAuth()) {
        genLogout();
        if (await isDoctor()) {
            await genDocument();
            await genDocCreate();
            await genDocUpdate();
            await genDocDelete();
            await genDocSearch();
            await genListOfSubscribedUsers();


        } else {
            await patientVisit();
            await genCard();
            await genCardCreate();
            await genCardUpdate();
            await genCardDelete();
            await genPatSearch();
            await getListOfDoctors();
            await genPatientInfo();
        }
    } else {
        genLogReg(result);

    }

}
