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
      $('#createTime').text(info.creationTime)
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
        let timeTd = $("<td></td>").text(item.creationDate); // 发布时间

        let operationTd = $("<td></td>");
        // 在操作栏添加一个操作按钮，这里只是例子，根据实际需要修改
        let operationBtn = $("<button></button>").text("操作").addClass("btn btn-primary");
        operationBtn.on("click", function() {
          // 按钮点击事件处理，具体实现按照需求来
          console.log("操作按钮点击，当前行数据：", item);
        });
        operationTd.append(operationBtn);

        tr.append(idTd, nameTd, timeTd, operationTd);
        tbody.append(tr);
      });

    },
    error(err) {
      console.error("Failed to fetch data", err);
    }
  })
}