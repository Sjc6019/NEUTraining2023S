let questionnaireTitle = '问卷标题'
let questionnaireDescription = '问卷说明'
var problem = []

onload = () => {
  let questionnaireId = $util.getPageParam('questionnaireId')
    console.log(questionnaireId, 'questionnaireId')
  if (questionnaireId != null) {
    console.log(questionnaireId, 'projectId')
    fetchProblem(questionnaireId)
  }
}

/**
 * 添加问题
 * 
 * @param {*} type 1：单选，2：多选，3：填空，4：矩阵，5：量表
 */
const onAddQuestion = (type) => {
  let ele
  switch (type) {
    case 1:
      ele = handleAddSingleChoice()
      break;
    case 2:
      ele = handleAddMultipleChoice()
      break;
    case 3:
      ele = handleAddFillBlanks()
      break;
    case 4:
      ele = handleAddMatrix()
      break;
    case 5:
      ele = handleAddGauge()
      break;
    default:
      break;
  }
  $('#problem').append(ele)
  console.log($util.getPageParam('questionnaireId'))
  problem.push({ questionnaireId: $util.getPageParam('questionnaireId'), problemName: '', mustAnswer: true, problemOptions: [{}], problemType: type })

  $(".question").hover(() => {
    let problemIndex = $('.question:hover').attr('data-problemIndex')
    let ele = `
      <div class="operation">
      <div class="button" onclick="handleMoveUp(${problemIndex})">上移</div>
      <div class="button" onclick="handleMoveDown(${problemIndex})">下移</div>
        <div class="button" onclick="handleEdit(${problemIndex})">编辑</div>
        <div class="button" onclick="handleDelete(${problemIndex})">删除</div>
      </div>
    `
    $('.question:hover').append(ele)
    $(".question:hover").css('border', '1px solid #fdb553')
  }, () => {
    $('.question > .operation').remove()
    $(".question").css('border', '1px solid #ffffff')
  })
}

const onInput = (problemIndex, optionIndex, key) => {
  if (optionIndex || optionIndex === 0)
    problem[problemIndex].problemOptions[optionIndex][key] = $(`#question${problemIndex} #optionItem${optionIndex} #${key}`)[0].value
  else
    problem[problemIndex][key] = $(`#question${problemIndex} #${key}`)[0].value
}

const onMustAnswerClick = (problemIndex) => {
  problem[problemIndex].mustAnswer = !problem[problemIndex].mustAnswer
  if (problem[problemIndex].mustAnswer) $(`#question${problemIndex} #mustAnswer`).text('必答题')
  else $(`#question${problemIndex} #mustAnswer`).text('非必答题')
}

const cancelEdit = (problemIndex) => {
  $(`#question${problemIndex} .bottom`).css('display', 'none')
  $(`#question${problemIndex} .bottom2`).css('display', 'block')
}

const handleMoveUp = (problemIndex) => {
  if (problemIndex === 0) return
  $(`#question${problemIndex - 1}`).before($(`#question${problemIndex}`))
  let i = problem[problemIndex]
  problem[problemIndex] = problem[problemIndex - 1]
  problem[problemIndex - 1] = i
  moveCommon()
}

const handleMoveDown = (problemIndex) => {
  if (problemIndex === problem.length - 1) return
  $(`#question${problemIndex + 1}`).after($(`#question${problemIndex}`))
  let i = problem[problemIndex]
  problem[problemIndex] = problem[problemIndex + 1]
  problem[problemIndex + 1] = i
  moveCommon()
}

const moveCommon = () => {
  $('.question').map((index, item) => {
    item.setAttribute('id', `question${index}`)
    item.setAttribute('data-problemIndex', index)
    let type = +$(`#question${index}`).attr('data-type')
    let value;
    value = $(`#question${index} #problemName`).attr('oninput').replace(/\(\d+,/g, `(${index},`)
    $(`#question${index} #problemName`).attr('oninput', value)
    $(`#question${index} #mustAnswer`).attr('onclick', `onMustAnswerClick(${index})`)
    $(`#question${index} #cancelEdit`).attr('onclick', `cancelEdit(${index})`)
    switch (type) {
      case 1:
        $(`#question${index} #chooseTerm`).map(((chooseTermIndex, chooseTermItem) => {
          chooseTermItem.oninput = onInput.bind(this, index, chooseTermIndex, 'chooseTerm')
        }))
        $(`#question${index} .option-del`).map(((delIndex, delItem) => {
          delItem.oninput = onInput.bind(this, index, delIndex, 'chooseTerm')
        }))
        $(`#question${index} .btn-add-option`).attr('onclick', `singleChoiceAddOption(${index})`)
        $(`#question${index} #editFinish`).attr('onclick', `singleChoiceEditFinish(${index})`)
        break;
      case 2:
        $(`#question${index} #chooseTerm`).map(((chooseTermIndex, chooseTermItem) => {
          chooseTermItem.oninput = onInput.bind(this, index, chooseTermIndex, 'chooseTerm')
        }))
        $(`#question${index} .option-del`).map(((delIndex, delItem) => {
          delItem.oninput = onInput.bind(this, index, delIndex, 'chooseTerm')
        }))
        $(`#question${index} .btn-add-option`).attr('onclick', `multipleChoiceAddOption(${index})`)
        $(`#question${index} #editFinish`).attr('onclick', `multipleChoiceEditFinish(${index})`)
        break;
      case 3:
        $(`#question${index} #editFinish`).attr('onclick', `fillBlanksEditFinish(${index})`)
        break;
      case 4:
        $(`#question${index} #chooseTerm`).map(((chooseTermIndex, chooseTermItem) => {
          chooseTermItem.oninput = onInput.bind(this, index, chooseTermIndex, 'chooseTerm')
        }))
        $(`#question${index} .option-del`).map(((delIndex, delItem) => {
          delItem.oninput = onInput.bind(this, index, delIndex, 'chooseTerm')
        }))
        value = $(`#question${index} #leftTitle`).attr('oninput').replace(/\(\d+,/g, `(${index},`)
        $(`#question${index} #leftTitle`).attr('oninput', value)
        $(`#question${index} .btn-add-option`).attr('onclick', `matrixAddOption(${index})`)
        $(`#question${index} #editFinish`).attr('onclick', `matrixEditFinish(${index})`)
        break;
      case 5:
        $(`#question${index} #chooseTerm`).map(((chooseTermIndex, chooseTermItem) => {
          chooseTermItem.oninput = onInput.bind(this, index, chooseTermIndex, 'chooseTerm')
        }))
        $(`#question${index} #fraction`).map(((fractionIndex, fractionItem) => {
          fractionItem.oninput = onInput.bind(this, index, fractionIndex, 'chooseTerm')
        }))
        $(`#question${index} .option-del`).map(((delIndex, delItem) => {
          delItem.oninput = onInput.bind(this, index, delIndex, 'chooseTerm')
        }))
        $(`#question${index} .btn-add-option`).attr('onclick', `gaugeAddOption(${index})`)
        $(`#question${index} #editFinish`).attr('onclick', `gaugeEditFinish(${index})`)
        break;
      default:
        break;
    }
  })
}

const handleEdit = (problemIndex) => {
  $(`#question${problemIndex} .bottom`).css('display', 'block')
  $(`#question${problemIndex} .bottom2`).css('display', 'none')
}

const handleDelete = (problemIndex) => {
  $(`#question${problemIndex}`).remove()
  problem.splice(problemIndex, 1)
}

const handleAddSingleChoice = () => {
  let ele = `
    <div class="question" id="question${problem.length}" data-type="1" data-problemIndex="${problem.length}">
      <div class="top">
        <span class="question-title" id="questionTitle">1.请编辑问题？</span>
        <span class="must-answer" id="mustAnswer" onclick="onMustAnswerClick(${problem.length})">必答题</span>
      </div>
      <div class="bottom">
        <textarea class="form-control textarea" id="problemName" placeholder="单选题目" rows="4" oninput="onInput(${problem.length}, ${undefined}, 'problemName')"></textarea>
        <div class="option" id="option">
          <div class="option-item" id="optionItem0">
            <input type="text" class="form-control" id="chooseTerm" placeholder="选项【单选】" oninput="onInput(${problem.length}, 0, 'chooseTerm')" />
            <span class="option-del" onclick="singleChoiceDelOption(${problem.length}, 0)">删除</span>
          </div>
        </div>
        <div>
          <button type="button" class="btn btn-link btn-add-option" onclick="singleChoiceAddOption(${problem.length})">添加选项</button>
        </div>
        <div class="btn-group">
          <button type="button" id="cancelEdit" class="btn btn-default" onclick="cancelEdit(${problem.length})">取消编辑</button>
          <button type="button" id="editFinish" class="btn btn-default" onclick="singleChoiceEditFinish(${problem.length})">完成编辑</button>
        </div>
      </div>
      <div class="bottom2" style="display: none;">
        
      </div>
    </div>
  `
  return ele
}

const singleChoiceAddOption = (problemIndex) => {
  $(`#question${problemIndex} #option`).append(`
    <div class="option-item" id="optionItem${problem[problemIndex].problemOptions.length}">
      <input type="text" class="form-control" id="chooseTerm" placeholder="选项【单选】" oninput="onInput(${problemIndex}, ${problem[problemIndex].problemOptions.length}, 'chooseTerm')" />
      <span class="option-del" onclick="singleChoiceDelOption(${problemIndex}, ${problem[problemIndex].problemOptions.length})">删除</span>
    </div>
  `)
  problem[problemIndex].problemOptions.push({})
}

const singleChoiceDelOption = (problemIndex, optionIndex) => {
  $(`#question${problemIndex} .option-item`)[optionIndex].remove()
  problem[problemIndex].problemOptions.splice(optionIndex, 1)
  $(`#question${problemIndex} .option-del`).map((item, index) => {
    index.onclick = singleChoiceDelOption.bind(this, problemIndex, item)
  })
}

const singleChoiceEditFinish = (problemIndex) => {
  $(`#question${problemIndex} .bottom`).css('display', 'none')
  $(`#question${problemIndex} .bottom2`).css('display', 'inline')
  $(`#question${problemIndex} #questionTitle`).text(`${problemIndex + 1}.${problem[problemIndex].problemName}`)
  $(`#question${problemIndex} .bottom2`).html('')
  problem[problemIndex].problemOptions.map(item => {
    $(`#question${problemIndex} .bottom2`).append(`
      <div style="display: flex; align-items: center;">
        <label class="radio-inline">
          <input type="radio">${item.chooseTerm ? item.chooseTerm : ''}
        </label>
      </div>
    `)
  })
}

const handleAddMultipleChoice = () => {
  let ele = `
    <div class="question" id="question${problem.length}" data-type="2" data-problemIndex="${problem.length}">
      <div class="top">
        <span class="question-title" id="questionTitle">1.请编辑问题？</span>
        <span class="must-answer" id="mustAnswer" onclick="onMustAnswerClick(${problem.length})">必答题</span>
      </div>
      <div class="bottom">
        <textarea class="form-control textarea" id="problemName" placeholder="多选题目" rows="4" oninput="onInput(${problem.length}, ${undefined}, 'problemName')"></textarea>
        <div class="option" id="option">
          <div class="option-item" id="optionItem0">
            <input type="text" class="form-control" id="chooseTerm" placeholder="选项【多选】" oninput="onInput(${problem.length}, 0, 'chooseTerm')" />
            <span class="option-del" onclick="multipleChoiceDelOption(${problem.length}, 0)">删除</span>
          </div>
        </div>
        <div>
          <button type="button" class="btn btn-link btn-add-option" onClick="multipleChoiceAddOption(${problem.length})">添加选项</button>
        </div>
        <div class="btn-group">
          <button type="button" id="cancelEdit" class="btn btn-default" onclick="cancelEdit(${problem.length})">取消编辑</button>
          <button type="button" id="editFinish" class="btn btn-default" onClick="multipleChoiceEditFinish(${problem.length})">完成编辑</button>
        </div>
      </div>
      <div class="bottom2" style="display: none;">
        
      </div>
    </div>
  `
  return ele
}

const multipleChoiceAddOption = (problemIndex) => {
  $(`#question${problemIndex} #option`).append(`
    <div class="option-item" id="optionItem${problem[problemIndex].problemOptions.length}">
      <input type="text" class="form-control" id="chooseTerm" placeholder="选项【多选】" oninput="onInput(${problemIndex}, ${problem[problemIndex].problemOptions.length}, 'chooseTerm')" />
      <span class="option-del" onclick="multipleChoiceDelOption(${problemIndex}, ${problem[problemIndex].problemOptions.length})">删除</span>
    </div>
  `)
  problem[problemIndex].problemOptions.push({})
}

const multipleChoiceDelOption = (problemIndex, optionIndex) => {
  $(`#question${problemIndex} .option-item`)[optionIndex].remove()
  problem[problemIndex].problemOptions.splice(optionIndex, 1)
  $(`#question${problemIndex} .option-del`).map((item, index) => {
    index.onclick = multipleChoiceDelOption.bind(this, problemIndex, item)
  })
}

const multipleChoiceEditFinish = (problemIndex) => {
  $(`#question${problemIndex} .bottom`).css('display', 'none')
  $(`#question${problemIndex} .bottom2`).css('display', 'inline')
  $(`#question${problemIndex} #questionTitle`).text(`${problemIndex + 1}.${problem[problemIndex].problemName}`)
  $(`#question${problemIndex} .bottom2`).html('')
  problem[problemIndex].problemOptions.map(item => {
    $(`#question${problemIndex} .bottom2`).append(`
      <div style="display: flex; align-items: center;">
        <label class="checkbox-inline">
          <input type="checkbox">${item.chooseTerm ? item.chooseTerm : ''}
        </label>
      </div>
    `)
  })
}

const handleAddFillBlanks = () => {
  let ele = `
    <div class="question" id="question${problem.length}" data-type="3" data-problemIndex="${problem.length}">
      <div class="top">
        <span class="question-title" id="questionTitle">1.请编辑问题？</span>
        <span class="must-answer" id="mustAnswer" onclick="onMustAnswerClick(${problem.length})">必答题</span>
      </div>
      <div class="bottom">
        <textarea class="form-control textarea" id="problemName" placeholder="请输入题目" rows="4" oninput="onInput(${problem.length}, ${undefined}, 'problemName')"></textarea>
        <div class="btn-group">
          <button type="button" id="cancelEdit" class="btn btn-default" onclick="cancelEdit(${problem.length})">取消编辑</button>
          <button type="button" id="editFinish" class="btn btn-default" onClick="fillBlanksEditFinish(${problem.length})">完成编辑</button>
        </div>
      </div>
      <div class="bottom2" style="display: none;">
        
      </div>
    </div>
  `
  return ele
}

const fillBlanksEditFinish = (problemIndex) => {
  $(`#question${problemIndex} .bottom`).css('display', 'none')
  $(`#question${problemIndex} .bottom2`).css('display', 'inline')
  $(`#question${problemIndex} #questionTitle`).text(`${problemIndex + 1}.${problem[problemIndex].problemName}`)
  $(`#question${problemIndex} .bottom2`).html(`
    <div style="border: 1px solid #CCCCCC; width: 50%; height: 70px;"></div>
  `)
}

const handleAddMatrix = () => {
  let ele = `
    <div class="question" id="question${problem.length}" data-type="4" data-problemIndex="${problem.length}">
      <div class="top">
        <span class="question-title" id="questionTitle">1.请编辑问题？</span>
        <span class="must-answer" id="mustAnswer" onclick="onMustAnswerClick(${problem.length})">必答题</span>
      </div>
      <div class="bottom">
        <textarea class="form-control textarea" id="problemName" placeholder="请编辑问题！" rows="4" oninput="onInput(${problem.length}, ${undefined}, 'problemName')"></textarea>
        <div style="margin-bottom: 10px;">左标题</div>
        <textarea class="form-control textarea" id="leftTitle" placeholder="例子：CCTV1,CCTV2,CCTV3" rows="4" oninput="onInput(${problem.length}, ${undefined}, 'leftTitle')"></textarea>
        <div class="option" id="option">
          <div class="option-item" id="optionItem0">
            <input type="text" class="form-control" id="chooseTerm" placeholder="选项" oninput="onInput(${problem.length}, 0, 'chooseTerm')" />
            <span class="option-del" onclick="matrixDelOption(${problem.length}, 0)">删除</span>
          </div>
        </div>
        <div>
          <button type="button" class="btn btn-link btn-add-option" onClick="matrixAddOption(${problem.length})">添加选项</button>
        </div>
        <div class="btn-group">
          <button type="button" id="cancelEdit" class="btn btn-default" onclick="cancelEdit(${problem.length})">取消编辑</button>
          <button type="button" id="editFinish" class="btn btn-default" onClick="matrixEditFinish(${problem.length})">完成编辑</button>
        </div>
      </div>
      <div class="bottom2" style="display: none; padding-left: 80px;"></div>
    </div>
  `
  return ele
}

const matrixAddOption = (problemIndex) => {
  $(`#question${problemIndex} #option`).append(`
    <div class="option-item" id="optionItem${problem[problemIndex].problemOptions.length}">
      <input type="text" class="form-control" id="chooseTerm" placeholder="选项" oninput="onInput(${problemIndex}, ${problem[problemIndex].problemOptions.length}, 'chooseTerm')" />
      <span class="option-del" onclick="matrixDelOption(${problemIndex}, ${problem[problemIndex].problemOptions.length})">删除</span>
    </div>
  `)
  problem[problemIndex].problemOptions.push({})
}

const matrixDelOption = (problemIndex, optionIndex) => {
  $(`#question${problemIndex} .option-item`)[optionIndex].remove()
  problem[problemIndex].problemOptions.splice(optionIndex, 1)
  $(`#question${problemIndex} .option-del`).map((item, index) => {
    index.onclick = matrixDelOption.bind(this, problemIndex, item)
  })
}

const matrixEditFinish = (problemIndex) => {
  $(`#question${problemIndex} .bottom`).css('display', 'none')
  $(`#question${problemIndex} .bottom2`).css('display', 'inline')
  $(`#question${problemIndex} #questionTitle`).text(`${problemIndex + 1}.${problem[problemIndex].problemName}`)
  $(`#question${problemIndex} .bottom2`).html('')
  let trs = problem[problemIndex].leftTitle ? problem[problemIndex].leftTitle.split(',') : []
  $(`#question${problemIndex} .bottom2`).append(`
    <table class="table">
      <thead>
        <tr>
          <th></th>
        </tr>
      </thead>
      <tbody>
        
      </tbody>
    </table>
  `)
  trs.map((item, index) => {
    $(`#question${problemIndex} .bottom2 tbody`).append(`
      <tr class="tr${index}">
        <td>${item}</td>
      </tr>
    `)
    problem[problemIndex].problemOptions.map(() => {
      $(`#question${problemIndex} .bottom2 tbody .tr${index}`).append(`
        <td>
          <input type="radio" name="radio${index}">
        </td>
      `)
    })
  })
  problem[problemIndex].problemOptions.map(item => {
    $(`#question${problemIndex} .bottom2 thead tr`).append(`
      <th>${item.chooseTerm}</th>
    `)
  })
  
}

const handleAddGauge = () => {
  let ele = `
    <div class="question" id="question${problem.length}" data-type="5" data-problemIndex="${problem.length}">
      <div class="top">
        <span class="question-title" id="questionTitle">1.请编辑问题？</span>
        <span class="must-answer" id="mustAnswer" onclick="onMustAnswerClick(${problem.length})">必答题</span>
      </div>
      <div class="bottom">
        <textarea class="form-control textarea" id="problemName" placeholder="请编辑问题！" rows="4" oninput="onInput(${problem.length}, ${undefined}, 'problemName')"></textarea>
        <div class="option" id="option">
          <div style="display: flex; margin-bottom: 10px;">
            <div style="width: calc(50% + 90px)">选项文字</div>
            <div style="width: 140px;">分数</div>
            <div>操作</div>
          </div>
          <div class="option-item" id="optionItem0">
            <input type="text" class="form-control" id="chooseTerm" oninput="onInput(${problem.length}, 0, 'chooseTerm')" />
            <input type="text" class="form-control" id="fraction" oninput="onInput(${problem.length}, 0, 'fraction')" style="width: 50px;" />
            <span class="option-del" onclick="gaugeDelOption(${problem.length}, 0)">删除</span>
          </div>
        </div>
        <div>
          <button type="button" class="btn btn-link btn-add-option" onClick="gaugeAddOption(${problem.length})">添加选项</button>
        </div>
        <div class="btn-group">
          <button type="button" id="cancelEdit" class="btn btn-default" onclick="cancelEdit(${problem.length})">取消编辑</button>
          <button type="button" id="editFinish" class="btn btn-default" onClick="gaugeEditFinish(${problem.length})">完成编辑</button>
        </div>
      </div>
      <div class="bottom2" style="display: none; align-items: center; justify-content: space-between;"></div>
    </div>
  `
  return ele
}

const gaugeAddOption = (problemIndex) => {
  $(`#question${problemIndex} #option`).append(`
    <div class="option-item" id="optionItem${problem[problemIndex].problemOptions.length}">
      <input type="text" class="form-control" id="chooseTerm" oninput="onInput(${problemIndex}, ${problem[problemIndex].problemOptions.length}, 'chooseTerm')" />
      <input type="text" class="form-control" id="fraction" oninput="onInput(${problemIndex}, ${problem[problemIndex].problemOptions.length}, 'fraction')" style="width: 50px;" />
      <span class="option-del" onclick="gaugeDelOption(${problemIndex}, ${problem[problemIndex].problemOptions.length})">删除</span>
    </div>
  `)
  problem[problemIndex].problemOptions.push({})
}

const gaugeDelOption = (problemIndex, optionIndex) => {
  $(`#question${problemIndex} .option-item`)[optionIndex].remove()
  problem[problemIndex].problemOptions.splice(optionIndex, 1)
  $(`#question${problemIndex} .option-del`).map((item, index) => {
    index.onclick = gaugeDelOption.bind(this, problemIndex, item)
  })
}

const gaugeEditFinish = (problemIndex) => {
  $(`#question${problemIndex} .bottom`).css('display', 'none')
  $(`#question${problemIndex} .bottom2`).css('display', 'flex')
  $(`#question${problemIndex} #questionTitle`).text(`${problemIndex + 1}.${problem[problemIndex].problemName}`)
  $(`#question${problemIndex} .bottom2`).html('')
  $(`#question${problemIndex} .bottom2`).append(`
    <div>${problem[problemIndex].problemOptions[0].chooseTerm}</div>
  `)
  problem[problemIndex].problemOptions.map(item => {
    $(`#question${problemIndex} .bottom2`).append(`
      <div>
        <label class="radio-inline">
          <input type="radio" name="fraction" value="${item.chooseTerm}"/>${item.chooseTerm}
        </label>
      </div>
    `)
  })
  $(`#question${problemIndex} .bottom2`).append(`
    <div>${problem[problemIndex].problemOptions[problem[problemIndex].problemOptions.length - 1].chooseTerm}</div>
  `)
}

const handleModifyTitle = () => {
  $('#modifyTitleModal').modal('show')
  $('#questionnaireTitle').val(questionnaireTitle)
  $('#questionnaireDescription').val(questionnaireDescription)
}


const handleEditFinish = () => {

  let params =  problem
  $.ajax({
    url: API_BASE_URL + '/addProblem',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      console.log(res)
      if(res.code === "666") {
        alert('添加成功')
        window.location.href = '/pages/questionnaire/index.html'
      }
    }
  })
}

const fetchProblem = (questionnaireId) => {
  let params = {
    questionnaireId: questionnaireId
  }
    $.ajax({
        url: API_BASE_URL + '/queryProblem',
        type: "POST",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(params),
        success(res) {
        console.log(res)
          if(res.code === 666) {
            problem = res.data
            loadProblem()
          }
        }

    })
}

const loadProblem = () => {
    problem.map((item, index) => {
        if (item.problemType === 1) {
        $('#problem').append(handleAddSingleChoice())
        } else if (item.problemType === 2) {
        $('#problem').append(handleAddMultipleChoice())
        } else if (item.problemType === 3) {
        $('#problem').append(handleAddText())
        } else if (item.problemType === 4) {
        $('#problem').append(handleAddScore())
        } else if (item.problemType === 5) {
        $('#problem').append(handleAddGauge())
        }
    })
}