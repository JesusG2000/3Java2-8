

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
            genListOfSubscribedUsers();
            genUserTable();
        } else {
            genCard();
        }
    } else {
        genLogReg(result);

    }

}
