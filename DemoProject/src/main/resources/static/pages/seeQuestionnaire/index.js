let questionnaireName = '';
let currentPage = 1;

onload = () => {
    $('#headerUsername').text($util.getItem('userInfo')[0].username);
    $('#headerDivB').text('答卷详情');

    let questionnaireId = $util.getPageParam('questionnaireId');
    console.log(questionnaireId, 'questionnaireId');

    questionnaireName = getQuestionnaireName(questionnaireId);
    console.log(questionnaireName, 'questionnaireName');
    fetchQuestionnaireList(questionnaireId, currentPage);
};

const onQSearch = () => {
    let questionnaireId = $util.getPageParam('questionnaireId');
    console.log(questionnaireId, 'questionnaireId');
    fetchQuestionnaireList(questionnaireId, currentPage);
};

const previousPage = () => {
    if (currentPage > 1) {
        currentPage--;
        let questionnaireId = $util.getPageParam('questionnaireId');
        fetchQuestionnaireList(questionnaireId, currentPage);
    }
};

const nextPage = () => {
    currentPage++;
    let questionnaireId = $util.getPageParam('questionnaireId');
    fetchQuestionnaireList(questionnaireId, currentPage);
};

const fetchQuestionnaireList = (id, page) => {
    let params = {
        questionnaireId: id,
        page: page
    };
    $.ajax({
        url: API_BASE_URL + '/queryAnswerSheetInfo',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            console.log(res, 'res');
            let info = res.data;

            let tbody = $('.table').find("tbody");
            tbody.empty(); // 清空表格内容，避免重复插入
            info.forEach((item) => {
                let tr = $("<tr></tr>");
                let QNameTd = $("<td></td>").text(questionnaireName); // 问卷名称
                let UNameTd = $("<td></td>").text(item.answerUser); // 答卷人
                let timeTd = $("<td></td>").text(item.answerTime); 

                let operationTd = $("<td></td>");
                let operationBtn1 = $("<button></button>").text("明细").addClass("btn btn-primary");
                operationBtn1.on("click", function () {
                    console.log("操作按钮点击，当前行数据：", item);
                    $util.setPageParam('answerUser', item.answerUser);
                    console.log($util.getPageParam('answerUser'), 'answerUser');
                    $util.setPageParam('answerId', item.id);
                    console.log($util.getPageParam('answerId'), 'answerId');
                    location.href = "/pages/showAnswerSheet/index.html";
                });
                tr.append(QNameTd, UNameTd, timeTd, operationTd.append(operationBtn1));
                tbody.append(tr);
            });
        },
        error(err) {
            console.log(err);
        }
    });
};

const getQuestionnaireName = (id) => {
    let params = {
        id: id
    };
    $.ajax({
        url: API_BASE_URL + '/queryQuestionnaireInfo',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            console.log("res", res);
            questionnaireName = res.data[0].questionnaireName;
        }
    });
};


const analyse = () => {
    let questionnaireId = $util.getPageParam('questionnaireId');
    console.log(questionnaireId, 'questionnaireId');
    location.href = "/pages/analyseQuestionnaire";
}