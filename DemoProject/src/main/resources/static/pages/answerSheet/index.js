var problem = []
onload = () => {
    let questionnaireId = $util.getPageParam('questionnaireId')
    console.log(questionnaireId, 'questionnaireId')
    fetchProblem(questionnaireId)
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
            problem = res.data
            loadProblem()
        }
    })
}

const loadProblem = () => {
    function loadQuestion(type, option)  {
        let question = ''
        let length = option.length
        switch (type) {
            case 1:
                option.forEach((item, index) => {
                    let optionIndex = index + 1
                    question += `
                <div style="display: flex; align-items: center; margin-bottom: 3px;">
                <label class="radio-inline">
                    <input type="radio" name="chooseTerm" value="${item.chooseTerm}" />${item.chooseTerm}
                </label>
                </div>
                `
                })
                break;
            case 2:
                option.forEach((item, index) => {
                    let optionIndex = index + 1
                    question += `
                <div style="display: flex; align-items: center; margin-bottom: 3px;">
                <label class="checkbox-inline">
                    <input type="checkbox" name="chooseTerm${optionIndex}" value="${item.chooseTerm}"/>${item.chooseTerm}
                </label>
                </div>
                `
                })
                break;
            case 3:
                question += `
                <div class="bottom">
                <textarea class="form-control" placeholder="请输入" rows="4" style="width: 70%;"></textarea>
                </div>
                `
                break;
            case 5:
                question += `
                <div class="bottom" style="display: flex; align-items: center; justify-content: space-between;">
                <div>${option[0].chooseTerm}</div>
                
                ${option.map((item, index) => { 
                    let optionIndex = index + 1
                    return `
                    <div>
                    <label class="radio-inline">
                        <input type="radio" name="fraction" value="${item.fraction}" />${item.chooseTerm}
                    </label>
                    </div>
                    `
                }).join('')}
                
                <div>${option[length - 1].chooseTerm}</div>
                </div>
                `


            default:
                break;
        }
        return question
    }

    function loadTitle(type) {
        switch (type) {
            case 1:
                return '单选题'
            case 2:
                return '多选题'
            case 3:
                return '填空题'
            case 4:
                return '矩阵'
            case 5:
                return '量表'
            default:
                return '未知题型'
        }
    }

    problem.forEach((item, index) => {
        let problemIndex = index + 1
        let type = item.problemType
        let mustAnswer = item.mustAnswer
        let title = item.problemName
        let option = item.problemOptions
        let leftTitle = item.leftTitle
        let trs = leftTitle ? leftTitle.split(',') : []
        var question = ''
        if (trs.length > 0 && type == 4) {
            question = `
            <div class="question" id="question${problemIndex}" data-type="${type}" data-problemIndex="${problemIndex}">
            <div class="top">
                <span class="question-title" id="questionTitle">${loadTitle(type)}</span>
                ${mustAnswer ? '<span class="must-answer" id="mustAnswer">必答题</span>' : '<span class="must-answer" id="mustAnswer">选答题</span>'}
            </div>
            <div class="bottom">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th></th>
                            ${trs.map((item, index) => {
                                return `<th>${item}</th>`
                            }).join('')}
                        </tr>
                    </thead>
                    <tbody>
                        ${option.map((item1, index1) => {
                            return `
                            <tr>
                                <td>${item1.chooseTerm}</td>
                                ${trs.map((item2, index2) => {
                                    return `<td><input type="radio" name="chooseTerm${index1}" value="${item2}" /></td>`
                                }).join('')}
                            </tr>
                            `
                        }).join('')}
                    </tbody>
                </table>
            </div>
            </div>
        `

        }
        else {
            question = `
            <div class="question" id="question${problemIndex}" data-type="${type}" data-problemIndex="${problemIndex}">
            <div class="top">
                <span class="question-title" id="questionTitle">${loadTitle(type)}</span>
                ${mustAnswer ? '<span class="must-answer" id="mustAnswer">必答题</span>' : '<span class="must-answer" id="mustAnswer">选答题</span>'}
            </div>
            <div class="bottom">
                ${loadQuestion(type, option)}
            </div>
            </div>
        `
        }
        $('#problem').append(question)
    })
}

const submit = () => {
    let params = []
    problem.forEach((item, index) => {
        let problemIndex = index + 1
        let type = item.problemType
        let problemId = item.id
        let answer = ''
        switch (type) {
            case 1:
                answer = $(`#question${problemIndex} input[type='radio']:checked`).val()
                break;
            case 2:
                answer = []
                $(`#question${problemIndex} input[type='checkbox']:checked`).each(function () {
                    answer.push($(this).val())
                })
                answer = answer.join(',')
                break;
            case 3:
                answer = $(`#question${problemIndex} textarea`).val()
                break;
            case 4:
                answer = []
                $(`#question${problemIndex} input[type='radio']:checked`).each(function () {
                    answer.push($(this).val())
                })
                answer = answer.join(',')
                break;
            case 5:
                answer = $(`#question${problemIndex} input[type='radio']:checked`).val()
                break;
            default:
                break;
        }
        params.push({
            problemId: problemId,
            answer: answer
        })
    })
    console.log(params)
    $.ajax({
        url: API_BASE_URL + '/submitAnswer',
        type: "POST",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(params),
        success(res) {
            console.log(res)
            if (res.code == 200) {
                alert('提交成功')
                window.location.href = '/pages/answerSheet/index.html'
            }
        }
    })
}
