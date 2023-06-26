onload = () => {
    $('#headerUsername').text($util.getItem('userInfo')[0].username)
    $('#headerDivB').text('答卷详情')

    let questionnaieId = $util.getPageParam('questionnaireId')
    console.log(questionnaieId, 'questionnaireId')
    fetchQuestionnaireList(questionnaieId)
}

const fetchQuestionnaireList = (id) => {
    let params = {
        id
    }
    $.ajax({
        url: API_BASE_URL + '/queryAnswerSheetInfo',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            console.log(res, 'res')
            // projectList = res.data
            let info = res.data;

            let tbody = $('.table').find("tbody");
            tbody.empty(); // 清空表格内容，避免重复插入

            info.forEach((item) => {
                let tr = $("<tr></tr>");
                let QNameTd = $("<td></td>").text(item.questionnaireName); // 问卷名称
                let UNameTd = $("<td></td>").text(item.answeruser); // 答卷人
                let timeTd = $("<td></td>").text(item.creationDate); // 发布时间

                let operationTd = $("<td></td>");
                let operationBtn1 = $("<button></button>").text("明细").addClass("btn btn-primary");
                operationBtn1.on("click", function () {
                    console.log("操作按钮点击，当前行数据：", item);
                    $util.setPageParam('questionnaireId', item.id)
                    console.log($util.getPageParam('questionnaireName'), 'questionnaireName')
                    location.href = "/pages/seeQuestionnaire/index.html"
                });
                tr.append(QNameTd, UNameTd, timeTd, operationTd.append(operationBtn1));
                tbody.append(tr);
            });
        },
        error(err) {
            console.log(err)
        }
    })
}