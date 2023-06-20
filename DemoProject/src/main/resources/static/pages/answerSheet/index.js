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
    function loadQuestion(type, option) {
        let question = ''
        let length = option.length
        switch (type) {
            case 1:
                option.forEach((item, index) => {
                    let optionIndex = index + 1
                    question += `
                <div style="display: flex; align-items: center; margin-bottom: 3px;">
                <label class="radio-inline">
                    <input type="radio" name="chooseTerm${optionIndex}" />${item}
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
                    <input type="checkbox" name="chooseTerm${optionIndex}" />${item}
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
            case 4:


            default:
                break;
        }


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
                return '问答题'
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
        let question = `
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
        $('#problem').append(question)
    })

}
