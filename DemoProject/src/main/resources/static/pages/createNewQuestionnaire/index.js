onload = () => {
  $('#headerUsername').text($util.getItem('userInfo')[0].username)
  $('#headerDivB').text('创建调查问卷')

  $('#startTime').datetimepicker({
    language: 'zh-CN', // 显示中文
    format: 'yyyy-mm-dd', // 显示格式
    minView: "month", // 设置只显示到月份
    initialDate: new Date(), // 初始化当前日期
    autoclose: true, // 选中自动关闭
    todayBtn: true // 显示今日按钮
  })
  $('#endTime').datetimepicker({
    language: 'zh-CN', // 显示中文
    format: 'yyyy-mm-dd', // 显示格式
    minView: "month", // 设置只显示到月份
    initialDate: new Date(), // 初始化当前日期
    autoclose: true, // 选中自动关闭
    todayBtn: true // 显示今日按钮
  })
  let projectId = $util.getPageParam('questionnaireProjectId')
  let questionnaireType = $util.getPageParam('questionnaireType')
    console.log(projectId, 'projectId')
    console.log(questionnaireType, 'questionnaireType')
}

const createQuestionnaire = () => {
    let params = {
        questionnaireName: $('#questionnaireName').val(),
        questionnaireDescription: $('#questionnaireDescription').val(),
        questionnaireProjectId: $util.getPageParam('questionnaireProjectId'),
        questionnaireType: $util.getPageParam('questionnaireType'),
        questionnaireStartTime: $('#startTime').val(),
        questionnaireEndTime: $('#endTime').val()
    }
    console.log(params, 'params')
    $.ajax({
        url: API_BASE_URL + '/addQuestionnaireInfo',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
        console.log("res", res)
        if (res.code === 200) {
            alert('创建成功')
            location.href = "/pages/seeQuestionnaire/index.html"
        } else {
            alert('创建失败')
        }
        }
    })
}
