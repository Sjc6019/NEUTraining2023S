onload = () => {
  $('#headerUsername').text($util.getItem('userInfo')[0].username)
  $('#headerDivB').text('创建问卷')

  let projectId = $util.getPageParam('createQuestionnaire')
    console.log(projectId, 'projectId')
  fetchProjectList(projectId)
}

const onCreateTemplate = () => {
  $util.setPageParam('questionnaireProjectId', $('#selectProject').val())
  $util.setPageParam('questionnaireType', $('#selectType').val())
  location.href = "/pages/createNewQuestionnaire/index.html"
}

const importHistoryQuestionnaire = () => {
  $('#divider').css('display', 'flex')
  $('#templateB').html('')
  $('#templateB').append(`
    <div class="template-item">
      <div class="item-t">
        <img class="img" src="../../static/images/blank_template.png">
        <div>
          <div class="title">测试</div>
          <div>页面测试数据</div>
        </div>
      </div>
      <div class="item-b">
        <button type="button" class="btn btn-default">导 入</button>
      </div>
    </div>
  `)
}

const surveyTypeTemplate = () => {
  $('#divider').css('display', 'flex')
  $('#templateB').html('')
  $('#templateB').append(`
    <div class="template-item">
      <div class="item-t">
        <img class="img" src="../../static/images/blank_template.png">
        <div>
          <div class="title">创建模板</div>
          <div>题库抽题，限时作答，成绩查询，自动阅卷</div>
        </div>
      </div>
      <div class="item-b">
        <button type="button" class="btn btn-default" onclick="createTemplate()">创 建</button>
      </div>
    </div>
    <div class="template-item">
      <div class="item-t">
        <img class="img" src="../../static/images/blank_template.png">
        <div>
          <div class="title">测试</div>
          <div></div>
        </div>
      </div>
      <div class="item-b">
        <button type="button" class="btn btn-default" onclick="handleEdit()" style="margin-right: 10px;">编 辑</button>
        <button type="button" class="btn btn-default">导 入</button>
      </div>
    </div>
  `)
}

const createTemplate = () => {
  $('#createTemplateModal').modal('show')
}

const handleEdit = () => {
  open('/pages/designQuestionnaire/index.html')
}

const fetchProjectList = (id) => {
  $.ajax({
    url: API_BASE_URL + '/queryProjectList',
    type: 'POST',
    data: '{\n' +
        '  "createdBy": "admin",\n' +
        '  "projectName": ""\n' +
        '}',
    dataType: 'json',
    contentType: 'application/json',
    success(res) {
      // 获取到select元素
      var selectElement = $('#selectProject');

      // 清除当前所有option
      selectElement.html('');

      // 添加默认option
      selectElement.append(`<option value="0" disabled selected hidden>请选择</option>`)

      // 根据获取到的数据，动态添加option
      res.data.map((item, index) => {
        selectElement.append(`<option value="${item.id}">${item.projectName}</option>`)
      })
      selectElement.val(id)
    }
  })

}
