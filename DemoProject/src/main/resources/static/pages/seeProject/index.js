onload = () => {
    $('#headerDivB').text('项目详情')

    let projectId = $util.getPageParam('seeProject')
    console.log(projectId, 'projectId')
    fetchProjectInfo(projectId)
}

const fetchProjectInfo = (id) => {
    let params = {
        id
    }
    $.ajax({
        url: API_BASE_URL + '/queryProjectList',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            console.log("res", res)
            let info = res.data[0]
            console.log(info, 'res')
            $('#projectName').text(info.projectName)
            $('#createTime').text(info.creationDate)
            $('#personInCharge').text(info.createdBy)
            $('#projectDescription').text(info.projectContent)

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
            console.log("res", res)
            let info = res.data;

            let tbody = $(".table").find("tbody");
            tbody.empty(); // 清空表格内容，避免重复插入

            info.forEach((item, index) => {
                let tr = $("<tr></tr>");

                let idTd = $("<td></td>").text(index + 1); // 序号
                let nameTd = $("<td></td>").text(item.questionnaireName); // 试卷名称
                let timeTd = $("<td></td>").text(item.releaseTime); // 发布时间

                let operationTd = $("<td></td>");
                // 在操作栏添加一个操作按钮，这里只是例子，根据实际需要修改
                let operationBtn1 = $("<button></button>").text("发布").addClass("btn btn-primary");
                operationBtn1.on("click", function () {
                    // 按钮点击事件处理，具体实现按照需求来
                    console.log("操作按钮点击，当前行数据：", item);
                    $util.setPageParam('questionnaireId', item.id)
                    console.log($util.getPageParam('questionnaireId'), 'questionnaireId')
                    //弹窗生成链接
                    let parentUrl = window.location.host
                    let url = 'http://' + parentUrl + '/pages/answerSheet/index.html?questionnaireId=' + item.id
                    console.log(url)
                    $('#url').text(url)
                    $('#url').attr('href', url)
                    $('#myModal').modal('show')
                    releaseQuestionnaire(item.id)
                });
                operationTd.append(operationBtn1);
                let operationBtn2 = $("<button></button>").text("预览").addClass("btn btn-primary");
                operationBtn2.on("click", function () {
                    // 按钮点击事件处理，具体实现按照需求来
                    console.log("操作按钮点击，当前行数据：", item);
                    $util.setPageParam('questionnaireId', item.id)
                    console.log($util.getPageParam('questionnaireId'), 'questionnaireId')
                    location.href = "/pages/answerSheet/index.html"
                });
                operationTd.append(operationBtn2);
                let operationBtn3 = $("<button></button>").text("统计").addClass("btn btn-primary");
                operationBtn3.on("click", function () {
                    // 按钮点击事件处理，具体实现按照需求来
                    console.log("操作按钮点击，当前行数据：", item);
                    $util.setPageParam('questionnaireId', item.id)
                    console.log($util.getPageParam('questionnaireId'), 'questionnaireId')
                    location.href = "/pages/seeQuestionnaire/index.html"
                });
                operationTd.append(operationBtn3);
                let operationBtn4 = $("<button></button>").text("删除").addClass("btn btn-primary");
                operationBtn4.on("click", function () {
                    // 按钮点击事件处理，具体实现按照需求来
                    console.log("删除按钮点击，当前行数据：", item);
                    deleteQuestionnaire(item.id)

                });
                operationTd.append(operationBtn4);

                tr.append(idTd, nameTd, timeTd, operationTd);
                tbody.append(tr);
            });

        },
        error(err) {
            console.error("Failed to fetch data", err);
        }
    })
}

const deleteQuestionnaire = (id) => {
    let params = {
        id,
        status: 0
    }
    $.ajax({
        url: API_BASE_URL + '/modifyQuestionnaireInfo',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            console.log("res", res)
            window.location.reload()
        }
    })
}

const releaseQuestionnaire = (id) => {
    let date = new Date().getTime()
    let params = {
        id,
        releaseTime: date
    }
    $.ajax({
        url: API_BASE_URL + '/modifyQuestionnaireInfo',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            console.log("res", res)
        }
    })
}