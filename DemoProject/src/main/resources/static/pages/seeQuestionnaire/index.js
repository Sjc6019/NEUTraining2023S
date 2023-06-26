onload = () => {
    $('#headerUsername').text($util.getItem('userInfo')[0].username)
    let projectId = $util.getPageParam('seeProject')
    console.log(projectId, 'projectId')
    fetchQuestionnaireList(projectId)
}

const fetchQuestionnaireList = (id) => {
    let params = {
        // createdBy: $util.getItem('userInfo')[0].username
        id
    }
    $.ajax({
        url: API_BASE_URL + '/queryProjectList',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            let info = res.data[0]
            console.log(info, 'res')
        }
    })

    let params2 = {
        projectId: id
    }
    $.ajax({
        url: API_BASE_URL + '/queryQuestionnaireInfo',
        type: "POST",
        data: JSON.stringify(params2),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            console.log(res, 'res')
            // projectList = res.data
            let info = res.data;

            let tbody = $('.table').find("tbody");
            tbody.empty(); // 清空表格内容，避免重复插入

            info.forEach((item, index) => {
                let tr = $("<tr></tr>");
                let QNameTd = $("<td></td>").text(index + 1); // 问卷名称
                let UNameTd = $("<td></td>").text(item.questionnaireName); // 答卷人
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