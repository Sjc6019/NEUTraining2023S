let answerSheet = [];
let problemList = [];
let questionnaireId = '';
onload = () => {
    $('#headerDivB').text('项目详情');

    questionnaireId = $util.getPageParam('questionnaireId');
    console.log(questionnaireId, 'qID');
    fetchQuestionnaireList(questionnaireId);

};

const fetchQuestionnaireList = (id) => {
    let params = {
        questionnaireId: id,
    };
    $.ajax({
        url: API_BASE_URL + '/queryAnswerSheetInfo',
        type: 'POST',
        data: JSON.stringify(params),
        dataType: 'json',
        contentType: 'application/json',
        success(res) {
            console.log(res, 'res');
            if (res.code === '666') {
                answerSheet = res.data;
                fetchProblemList(questionnaireId);
            }
        },
        error(err) {
            console.log(err);
        },
    });
};

const fetchProblemList = (id) => {
    let params = {
        questionnaireId: id,
    };
    $.ajax({
        url: API_BASE_URL + '/queryProblem',
        type: 'POST',
        data: JSON.stringify(params),
        dataType: 'json',
        contentType: 'application/json',
        success(res) {
            console.log(res, 'res');
            if (res.code === '666') {
                problemList = res.data;
                problemList.forEach((item, index) => {
                    switch (item.problemType) {
                        case 1:
                            $('#problems').append(
                                `<div class="singleChoice"> <div class="problem-title">
                  ${item.problemName}
                  </div> <div id="singleChoice${index}" style="width: 600px;height:400px;"></div> </div>`,
                            );
                            handleSingleChoice(index, item)
                            break;
                        case 2:
                            $('#problems').append(
                                `<div class="multipleChoice"> <div class="problem-title">
                  ${item.problemName}
                    </div> <div id="multipleChoice${index}" style="width: 600px;height:400px;"></div> </div>`,
                            );
                            handleMultipleChoice(index, item)
                            break;
                        case 3:
                            $('#problems').append(
                                `<div class="fillBlank"> 
                                    <div class="problem-title">
                                        ${item.problemName}
                                    </div>
                                    <table  class="table table-bordered">
                                        <tbody id="fillBlank${index}">
                                        </tbody>
                                    </table> 
                                </div>
                                `
                            );
                            handleFillBlank(index, item)
                            break;
                        case 4:
                            $('#problems').append(
                                `<div class="matrix" id="matrix"> <div class="problem-title">
                                    ${item.problemName}
                                    </div> <div id="matrix${index}" style="width: 600px;height:400px;"></div> </div>`,
                            );
                            handleMatrix(index, item)
                            break;
                        case 5:
                            $('#problems').append(
                                '<div class="gauge" id="gauge"> <div class="problem-title">' +
                                item.problemName +
                                '</div> </div>',
                            );
                            break;
                        default:
                            break;
                    }
                });
            }
        },
        error(err) {
            console.log(err);
        },
    });
};

const handleSingleChoice = (index, problem) => {
    var chartDom = $('#singleChoice' + index).get(0)
    console.log(problem)
    console.log(problem.id)
    var myChart = echarts.init(chartDom);
    var option;

    let xData = [];

    problem.problemOptions.map((item) => {
        xData.push(item.chooseTerm)
    })

    let tempYData = [];
    answerSheet.forEach((item) => {
        item.answer.forEach((item1) => {
            if (item1.problemId === problem.id) {
                // console.log(item1.answer)
                tempYData.push(item1.answer)
            }
        })
    });
    console.log(tempYData)
    let yCount = {};
    for (let i = 0; i < tempYData.length; i++) {
        let item = tempYData[i];
        yCount[item] = yCount[item] ? yCount[item] + 1 : 1;
    }
    let data = Object.keys(yCount).map(key => ({name: key, value: yCount[key]}));
    console.log(data)
    option = {
        xAxis: {
            type: 'category',
            data: xData
        },
        yAxis: {
            type: 'value',
        },
        series: [
            {
                data: data,
                type: 'bar',
                showBackground: true,
                backgroundStyle: {
                    color: 'rgba(180, 180, 180, 0.2)',
                },
            },
        ],
    };
    option && myChart.setOption(option);
};

const handleMultipleChoice = (index, problem) => {
    var chartDom = $('#multipleChoice' + index).get(0)
    var myChart = echarts.init(chartDom);
    var option;

    let xData = [];

    problem.problemOptions.map((item) => {
        xData.push(item.chooseTerm)
    })

    let tempYData = [];
    let localAnswerSheet = answerSheet;
    console.log(localAnswerSheet)
    localAnswerSheet.forEach((item) => {
        item.answer.forEach((item1) => {
            if (item1.problemId === problem.id) {
                // console.log(item1.answer)
                if (item1.answer.indexOf(',') !== -1) {
                    let tempArr = item1.answer.split(',')
                    tempArr.map((item2) => {
                        tempYData.push(item2)
                    })
                } else {
                    tempYData.push(item1.answer)
                }
                // console.log(item1.answer)
            }
        })
    });
    // console.log(tempYData)
    let yCount = {};
    for (let i = 0; i < tempYData.length; i++) {
        let item = tempYData[i];
        yCount[item] = yCount[item] ? yCount[item] + 1 : 1;
    }
    let data = Object.keys(yCount).map(key => ({name: key, value: yCount[key]}));
    console.log(data)
    option = {
        xAxis: {
            type: 'category',
            data: xData
        },
        yAxis: {
            type: 'value',
        },
        series: [
            {
                data: data,
                type: 'bar',
                showBackground: true,
                backgroundStyle: {
                    color: 'rgba(180, 180, 180, 0.2)',
                },
            },
        ],
    };
    option && myChart.setOption(option);
};

const handleFillBlank = (index, problem) => {
    let data = [];
    let localAnswerSheet = answerSheet;
    console.log(localAnswerSheet)
    localAnswerSheet.forEach((item) => {
        item.answer.forEach((item1) => {
            if (item1.problemId === problem.id) {
                console.log(item1.answer)
                data.push(item1.answer)
            }
        })
    });
    console.log(data)
    data.map((item) => {
        $('#fillBlank' + index).append(
            `<tr>
                <td>${item}</td>
            </tr>`
        )

    })

};


