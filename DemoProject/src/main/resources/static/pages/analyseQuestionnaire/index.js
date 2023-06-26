let answerSheet = []
onload = () => {
    $('#headerDivB').text('项目详情')

    let questionnaireId = $util.getPageParam('questionnaireId')
    console.log(questionnaireId,'qID')
    fetchQuestionnaireList(questionnaireId)

}

const fetchQuestionnaireList = (id) => {
  let params = {
      questionnaireId: id
  }
  $.ajax({
      url: API_BASE_URL + '/queryAnswerSheetInfo',
      type: "POST",
      data: JSON.stringify(params),
      dataType: "json",
      contentType: "application/json",
      success(res) {
          console.log(res, 'res')
          if (res.code === "666") {
            answerSheetß = res.data
          }
          
      },
      error(err) {
          console.log(err)
      }
  })
}