require 'test/unit'
require_relative 'question_list.rb'
class QuestionListTest <Test::Unit::TestCase
  def setup
    @question_list = QuestionList.new
    srand(1234)
  end
  def test_generate_question
    assert((@question_list.generate_question 1).last.name=='Basic','Wrong question level generated')
    assert((@question_list.generate_question 2).last.name=='Intermediate','Wrong question level generated')
    assert((@question_list.generate_question 3).last.name=='Advanced','Wrong question level generated')
    assert((@question_list.generate_question 4).last.name=='Master','Wrong question level generated')
  end
  def test_last
    @question_list.generate_question 1
    assert(@question_list.last==@question_list.questions.last,'Last method is not working')
  end
  def test_calculator
    @question_list.generate_question 1
    result = 4-7
    assert(result == @question_list.answer,'Calculator answer method not working')
  end


end