var problem = [];
var questionnaireId;
var answerUserName;
var answerid;
let problemType = [];
onload = () => {

  answerUserName = $util.getPageParam('answerUser');
  console.log(answerUserName, 'answerUserName');
  answerid = $util.getPageParam('answerId');
  console.log(answerid, 'answerid');
  //从url中获取问卷id
  let urlParams = new URLSearchParams(window.location.search);
  questionnaireId = urlParams.get('questionnaireId');
  if (questionnaireId == null) {
    questionnaireId = $util.getPageParam('questionnaireId');
    console.log(questionnaireId, 'questionnaireId');
  }
  fetchProblem(questionnaireId);
  problemType = getProblemType(questionnaireId);
  // getQuestionnaireName(answerUserName);

  $('#answer-user-name').text($util.getPageParam('answerUser'));
  loadAnswer(answerid)
};





const getQuestionnaireName = (answerUserName) => {
return `
    <div class="answer-user">
        <span>答卷人：</span>
        <label class="answer-user-name" id="answerUserName">${answerUserName}</label>
    </div>  
`
}

const getProblemType = (questionnaireId) => {
  let params2 = {
    questionnaireId: questionnaireId,
  };
  $.ajax({
      url: API_BASE_URL + '/queryProblem',
      type: "POST",
      data: JSON.stringify(params2),
      dataType: "json",
      contentType: "application/json",
      success(res) {
          console.log("res", res);
          let info = res.data;
          problemType = info.map((item) => {
            return item.problemType;
          });
          console.log(problemType);
      }
  });
};
  

const fetchProblem = (questionnaireId) => {
  let params = {
    questionnaireId: questionnaireId,
  };
  $.ajax({
    url: API_BASE_URL + '/queryProblem',
    type: 'POST',
    dataType: 'json',
    contentType: 'application/json',
    data: JSON.stringify(params),
    success(res) {
      console.log(res);
      problem = res.data;
      loadProblem();
    },
  });
};



const loadProblem = () => {
  function loadQuestion(type, option) {
    let question = '';
    let length = option.length;
    switch (type) {
      case 1:
        option.forEach((item, index) => {
          let optionIndex = index + 1;
          question += `
                <div style="display: flex; align-items: center; margin-bottom: 3px;">
                <label class="radio-inline">
                    <input type="radio" name="chooseTerm" value="${item.chooseTerm}" />${item.chooseTerm}
                </label>
                </div>
                `;
        });
        break;
      case 2:
        option.forEach((item, index) => {
          let optionIndex = index + 1;
          question += `
                <div style="display: flex; align-items: center; margin-bottom: 3px;">
                <label class="checkbox-inline">
                    <input type="checkbox" name="chooseTerm${optionIndex}" value="${item.chooseTerm}"/>${item.chooseTerm}
                </label>
                </div>
                `;
        });
        break;
      case 3:
        question += `
                <div class="bottom">
                <textarea class="form-control" placeholder="请输入" rows="4" style="width: 70%;"></textarea>
                </div>
                `;
        break;
      case 5:
        question += `
                <div class="bottom" style="display: flex; align-items: center; justify-content: space-between;">
                <div>${option[0].chooseTerm}</div>
                
                ${option
                  .map((item, index) => {
                    let optionIndex = index + 1;
                    return `
                    <div>
                    <label class="radio-inline">
                        <input type="radio" name="fraction" value="${item.fraction}" />${item.chooseTerm}
                    </label>
                    </div>
                    `;
                  })
                  .join('')}
                
                <div>${option[length - 1].chooseTerm}</div>
                </div>
                `;

      default:
        break;
    }
    return question;
  }

  function loadTitle(type) {
    switch (type) {
      case 1:
        return '单选题';
      case 2:
        return '多选题';
      case 3:
        return '填空题';
      case 4:
        return '矩阵';
      case 5:
        return '量表';
      default:
        return '未知题型';
    }
  }

  problem.forEach((item, index) => {
    let problemIndex = index + 1;
    let type = item.problemType;
    let mustAnswer = item.mustAnswer;
    let title = item.problemName;
    let option = item.problemOptions;
    let leftTitle = item.leftTitle;
    let trs = leftTitle ? leftTitle.split(',') : [];
    var question = '';
    if (trs.length > 0 && type == 4) {
      question = `
            <div class="question" id="question${problemIndex}" data-type="${type}" data-problemIndex="${problemIndex}">
            <div class="top">
                <span class="question-title" id="questionTitle">${loadTitle(
                  type,
                )}</span>
                ${
                  mustAnswer
                    ? '<span class="must-answer" id="mustAnswer">必答题</span>'
                    : '<span class="must-answer" id="mustAnswer">选答题</span>'
                }
            </div>
            <div class="bottom">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th></th>
                            ${trs
                              .map((item, index) => {
                                return `<th>${item}</th>`;
                              })
                              .join('')}
                        </tr>
                    </thead>
                    <tbody>
                        ${option
                          .map((item1, index1) => {
                            return `
                            <tr>
                                <td>${item1.chooseTerm}</td>
                                ${trs
                                  .map((item2, index2) => {
                                    return `<td><input type="radio" name="chooseTerm${index1}" value="${item2}" /></td>`;
                                  })
                                  .join('')}
                            </tr>
                            `;
                          })
                          .join('')}
                    </tbody>
                </table>
            </div>
            </div>
        `;
    } else {
      question = `
            <div class="question" id="question${problemIndex}" data-type="${type}" data-problemIndex="${problemIndex}">
            <div class="top">
                <span class="question-title" id="questionTitle">${loadTitle(
                  type,
                )}</span>
                ${
                  mustAnswer
                    ? '<span class="must-answer" id="mustAnswer">必答题</span>'
                    : '<span class="must-answer" id="mustAnswer">选答题</span>'
                }
            </div>
            <div class="bottom">
                ${loadQuestion(type, option)}
            </div>
            </div>
        `;
    }
    $('#problem').append(question);
  });
};

const loadAnswer = (answerid) => {
  let params = {
    id: answerid,
  };
  $.ajax({
    url: API_BASE_URL + '/queryAnswerSheetInfo',
    type: 'POST',
    dataType: 'json',
    contentType: 'application/json',
    data: JSON.stringify(params),
    success(res) {
      console.log(res);
      let answer = res.data[0];
      answer.answer.forEach((item, index) => {
        let problemIndex = index + 1;
        let tempAnswer = item.answer;
        console.log(tempAnswer);
        let tempParams = {
          id: item.problemId,
        };
        $.ajax({
          url: API_BASE_URL + '/queryProblem',
          type: 'POST',
          dataType: 'json',
          contentType: 'application/json',
          data: JSON.stringify(tempParams)
        }).then(res => {
          console.log(res);
          let type = res.data[0].problemType
          
          console.log(type)
          switch (type) {
            case 1:
              $(`#question${problemIndex} input[type='radio'][value='${tempAnswer}']`).prop('checked', true);
              break;
            case 2:
              let tempAnswerArray = tempAnswer.split(',');
              tempAnswerArray.forEach((answer) => {
                $(`#question${problemIndex} input[type='checkbox'][value='${answer}']`).prop('checked', true);
              });
              break;
            case 3:
              $(`#question${problemIndex} textarea`).val(tempAnswer);
              break;
            case 4:
              let tempAnswerIndex = tempAnswer.split(',');
              tempAnswerIndex.forEach((answer, index) => {
                $(`#question${problemIndex} input[type='radio'][name='chooseTerm${index}'][value='${answer}']`).prop('checked', true);
              });
              break;
            case 5:
              $(`#question${problemIndex} input[type='radio'][value='${tempAnswer}']`).prop('checked', true);
              break;
            default:
              break;
          }
        })
        // let type = problemType
        // console.log(type)
        // console

        // switch (type) {
        //   case 1:
        //     $(`#question${problemIndex}`).val(tempAnswer);
        //     break;
        //   case 2:
        //     let tempAnswerArray = tempAnswer.split(',');
        //     tempAnswerArray.forEach((answer) => {
        //       $(`#question${problemIndex} input[type='checkbox'][value='${answer}']`).prop('checked', true);
        //     });
        //     break;
        //   case 3:
        //     $(`#question${problemIndex} textarea`).val(tempAnswer);
        //     break;
        //   case 4:
        //     let tempAnswerIndex = tempAnswer.split(',');
        //     tempAnswerIndex.forEach((answer, index) => {
        //       $(`#question${problemIndex} input[type='radio'][name='chooseTerm${index}'][value='${answer}']`).prop('checked', true);
        //     });
        //     break;
        //   case 5:
        //     $(`#question${problemIndex} input[type='radio'][value='${tempAnswer}']`).prop('checked', true);
        //     break;
        //   default:
        //     break;
        // }
        

      });
    },
  });
};

const submit = () => {
  let answerUser = $('#answer-user').val();
  let answer = [];
  problem.forEach((item, index) => {
    let problemIndex = index + 1;
    let type = item.problemType;
    let problemId = item.id;
    let tempAnswer = '';
    switch (type) {
      case 1:
        tempAnswer = $(
          `#question${problemIndex} input[type='radio']:checked`,
        ).val();
        break;
      case 2:
        tempAnswer1 = [];
        $(`#question${problemIndex} input[type='checkbox']:checked`).each(
          function () {
            tempAnswer1.push($(this).val());
          },
        );
        tempAnswer = tempAnswer1.join(',');
        break;
      case 3:
        tempAnswer = $(`#question${problemIndex} textarea`).val();
        break;
      case 4:
        tempAnswer1 = [];
        $(`#question${problemIndex} input[type='radio']:checked`).each(
          function () {
            tempAnswer1.push($(this).val());
          },
        );
        tempAnswer = tempAnswer1.join(',');
        break;
      case 5:
        tempAnswer = $(
          `#question${problemIndex} input[type='radio']:checked`,
        ).val();
        break;
      default:
        break;
    }
    answer.push({
      problemId: problemId,
      answer: tempAnswer,
    });
  });
  let params = {
    questionnaireId: questionnaireId,
    answerUser: answerUser,
    answer: answer,
  };
  console.log(params);
  $.ajax({
    url: API_BASE_URL + '/addAnswerSheetInfo',
    type: 'POST',
    dataType: 'json',
    contentType: 'application/json',
    data: JSON.stringify(params),
    success(res) {
      console.log(res);
      if (res.code == 666) {
        alert('提交成功');
        //禁用提交按钮
        $("#submit").hide();
      }
    },
  });
};
