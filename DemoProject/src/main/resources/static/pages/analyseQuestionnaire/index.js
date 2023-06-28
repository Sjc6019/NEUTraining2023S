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

const handleViewByType = () => {
    $('#problems').empty();
    $('#problems').append(
        `<div id="singleChoiceContainer"> 
        <h3>单选题</h3>
        </div>
        <div id="multipleChoiceContainer">
        <h3>多选题</h3>
        </div>
        <div id="textContainer">
        <h3>填空题</h3>
        </div>
        `,
    );
    fetchProblemListByType(questionnaireId);

}

const fetchProblemListByType = (id) => {
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
                            $('#singleChoiceContainer').append(
                                `<div class="singleChoice"> 
                                    <div class="problem-title">
                                        <h5>题目${index+1}:${item.problemName}</h5>
                                    </div>
                                    <div>
                                        <span>选项：</span>         
                                        <span><button class="btn btn-primary" id="tableSingleChoice${index}">表格</button></span>                               
                                        <span><button class="btn btn-primary" id="barSingleChoice${index}">柱状图</button></span>
                                        <span><button class="btn btn-primary" id="pieSingleChoice${index}">饼状图</button></span>
                                    </div>
                                    <div id="singleChoiceTable${index}" style="display:none;" ></div>                                    
                                    <div id="singleChoice${index}" style="width: 600px;height:400px; display:none;" ></div>
                                </div>`,
                            );
                            var tableButton = document.getElementById('tableSingleChoice' + index);
                            tableButton.onclick = function () {
                                handleSingleChoice(index, item, 0)
                            }
                            var barButton = document.getElementById('barSingleChoice' + index);
                            barButton.onclick = function () {
                                handleSingleChoice(index, item,1)
                            }
                            var pieButton = document.getElementById('pieSingleChoice' + index);
                            pieButton.onclick = function () {
                                handleSingleChoice(index, item,2)
                            }
                            handleSingleChoice(index, item, 0)
                            break;
                        case 2:
                            $('#multipleChoiceContainer').append(
                                `<div class="multipleChoice">
                                    <div class="problem-title">
                                        <h5>题目${index+1}:${item.problemName}</h5>
                                    </div>
                                    <div>
                                        <span>选项：</span>
                                        <span><button class="btn btn-primary" id="tableMultipleChoice${index}">表格</button></span>
                                        <span><button class="btn btn-primary" id="barMultipleChoice${index}">柱状图</button></span>
                                        <span><button class="btn btn-primary" id="pieMultipleChoice${index}">饼状图</button></span>
                                    </div>
                                    <div id="multipleChoiceTable${index}" style="display:none;" ></div>
                                    <div id="multipleChoice${index}" style="width: 600px;height:400px; display:none;" ></div>
                                </div>`,
                            );
                            var tableButton = document.getElementById('tableMultipleChoice' + index);
                            tableButton.onclick = function () {
                                handleMultipleChoice(index, item, 0)
                            }
                            var barButton = document.getElementById('barMultipleChoice' + index);
                            barButton.onclick = function () {
                                handleMultipleChoice(index, item,1)
                            }
                            var pieButton = document.getElementById('pieMultipleChoice' + index);
                            pieButton.onclick = function () {
                                handleMultipleChoice(index, item,2)
                            }
                            handleMultipleChoice(index, item, 0)
                            break;
                        case 3:
                            $('#textContainer').append(
                                `<div class="fillBlank"> 
                                    <div class="problem-title">
                                      <h5>题目${index+1}:${item.problemName}</h5>
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
                                `<div class="singleChoice"> 
                                    <div class="problem-title">
                                        <h5>题目${index+1}:${item.problemName}</h5>
                                    </div>
                                    <div>
                                        <span>选项：</span>         
                                        <span><button class="btn btn-primary" id="tableSingleChoice${index}">表格</button></span>                               
                                        <span><button class="btn btn-primary" id="barSingleChoice${index}">柱状图</button></span>
                                        <span><button class="btn btn-primary" id="pieSingleChoice${index}">饼状图</button></span>
                                    </div>
                                    <div id="singleChoiceTable${index}" style="display:none;"></div>                                    
                                    <div id="singleChoice${index}" style="width: 600px;height:400px; display:none;"></div>
                                </div>`,
                            );
                            var tableButton = document.getElementById('tableSingleChoice' + index);
                            tableButton.onclick = function () {
                                handleSingleChoice(index, item, 0)
                            }
                            var barButton = document.getElementById('barSingleChoice' + index);
                            barButton.onclick = function () {
                                handleSingleChoice(index, item,1)
                            }
                            var pieButton = document.getElementById('pieSingleChoice' + index);
                            pieButton.onclick = function () {
                                handleSingleChoice(index, item,2)
                            }
                            handleSingleChoice(index, item, 0)
                            break;
                        case 2:
                            $('#problems').append(
                                `<div class="multipleChoice">
                                    <div class="problem-title">
                                        <h5>题目${index+1}:${item.problemName}</h5>
                                    </div>
                                    <div>
                                        <span>选项：</span>
                                        <span><button class="btn btn-primary" id="tableMultipleChoice${index}">表格</button></span>
                                        <span><button class="btn btn-primary" id="barMultipleChoice${index}">柱状图</button></span>
                                        <span><button class="btn btn-primary" id="pieMultipleChoice${index}">饼状图</button></span>
                                    </div>
                                    <div id="multipleChoiceTable${index}" style="display:none;"></div>
                                    <div id="multipleChoice${index}" style="width: 600px;height:400px; display:none;"></div>
                                </div>`,
                            );
                            var tableButton = document.getElementById('tableMultipleChoice' + index);
                            tableButton.onclick = function () {
                                handleMultipleChoice(index, item, 0)
                            }
                            var barButton = document.getElementById('barMultipleChoice' + index);
                            barButton.onclick = function () {
                                handleMultipleChoice(index, item,1)
                            }
                            var pieButton = document.getElementById('pieMultipleChoice' + index);
                            pieButton.onclick = function () {
                                handleMultipleChoice(index, item,2)
                            }
                            handleMultipleChoice(index, item, 0)
                            break;
                        case 3:
                            $('#problems').append(
                                `<div class="fillBlank"> 
                                    <div class="problem-title">
                                        <h5>题目${index+1}:${item.problemName}</h5>
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

const handleSingleChoice = (index, problem, type) => {
    if (type === 0) {
        $('#singleChoice' + index).css('display', 'none')
        $('#singleChoiceTable' + index).css('display', 'block')
    } else {
        $('#singleChoiceTable' + index).css('display', 'none')
        $('#singleChoice' + index).css('display', 'block')
    }
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
    let data1 = Object.keys(yCount).map(key => ([key,yCount[key]]));
    let data2 = Object.keys(yCount).map(key => ({name:key,value:yCount[key]}));
    console.log(data1)
    switch (type) {
        case 0:
            var tableDom = $('#singleChoiceTable' + index)
            tableDom.css('display', 'block')
            tableDom.empty()
            tableDom.append(
                `<table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>选项</th>
                            <th>人数</th>
                        </tr>
                    </thead>
                    <tbody id="singleChoiceTableContent${index}">
                    </tbody>
                </table>`
            )
            var table = document.getElementById('singleChoiceTableContent' + index);
            for (let i = 0; i < data1.length; i++) {
                let tr = document.createElement('tr');
                let td1 = document.createElement('td');
                let td2 = document.createElement('td');
                td1.innerHTML = data1[i][0];
                td2.innerHTML = data1[i][1];
                tr.appendChild(td1);
                tr.appendChild(td2);
                table.appendChild(tr);
            }
            break;
        case 1:
            option = {
                xAxis: {
                    type: 'category',
                    data: xData,
                },
                yAxis: {
                    type: 'value',
                },
                series: [
                    {
                        data: data1,
                        type: 'bar',
                        showBackground: true,
                        backgroundStyle: {
                            color: 'rgba(180, 180, 180, 0.2)',
                        },
                    },
                ],
            };
            break;
        case 2: // 饼状图
            option = {
                title: {
                    text: problem.problemName,
                    left: 'center',
                    top: 20,
                    textStyle: {
                        color: '#ccc',
                    },
                },
                tooltip: {
                    trigger: 'item',
                },
                legend: {},
                series: [
                    {
                        type: 'pie',
                        radius: '50%',
                        data: data2,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                            },
                        },
                    },
                ],
            };
    }

    if (type != 0) {
        option && myChart.setOption(option);
    }
};

const handleMultipleChoice = (index, problem,type) => {
    if (type === 0) {
        $('#multipleChoice' + index).css('display', 'none')
        $('#multipleChoiceTable' + index).css('display', 'block')
    } else {
        $('#multipleChoiceTable' + index).css('display', 'none')
        $('#multipleChoice' + index).css('display', 'block')
    }
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
    let data1 = Object.keys(yCount).map(key => ([key, yCount[key]]));
    let data2 = Object.keys(yCount).map(key => ({name: key, value: yCount[key]}));
    console.log(data1)
    switch (type) {
        case 0:
            var tableDom = $('#multipleChoiceTable' + index)
            tableDom.css('display', 'block')
            tableDom.empty()
            tableDom.append(
                `<table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>选项</th>
                            <th>人数</th>
                        </tr>
                    </thead>
                    <tbody id="multipleChoiceTableContent${index}">
                    </tbody>
                </table>`
            )
            var table = document.getElementById('multipleChoiceTableContent' + index);
            for (let i = 0; i < data1.length; i++) {
                let tr = document.createElement('tr');
                let td1 = document.createElement('td');
                let td2 = document.createElement('td');
                td1.innerHTML = data1[i][0];
                td2.innerHTML = data1[i][1];
                tr.appendChild(td1);
                tr.appendChild(td2);
                table.appendChild(tr);
            }
            break;
        case 1:
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
                        data: data1,
                        type: 'bar',
                        showBackground: true,
                        backgroundStyle: {
                            color: 'rgba(180, 180, 180, 0.2)',
                        },
                    },
                ],
            };
            break;
        case 2: // 饼状图
            option = {
                title: {
                    text: problem.problemName,
                    left: 'center',
                    top: 20,
                },
                tooltip: {
                    trigger: 'item',
                },
                legend: {},
                series: [
                    {
                        type: 'pie',
                        radius: '50%',
                        data: data2,
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                            }
                        }
                    }
                ]
            };
            break;
    }

    if(type !=0){
        option && myChart.setOption(option);
    }
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


