require_relative'question.rb'
require_relative'master_question.rb'
require_relative'advanced_question.rb'
require_relative'intermediate_question.rb'
require_relative'basic_question.rb'
require 'test/unit'
class QuestionTest <Test::Unit::TestCase
  def setup
    @question = Question.new
    @master_question = MasterQuestion.new
    @intermediate_question = IntermediateQuestion.new
    @advance_question = AdvancedQuestion.new
    @basic_question = BasicQuestion.new
  end
  def test_range
    assert(((@basic_question.x).between?(1,9)&&(@basic_question.y).between?(1,9)),"Basic question numbers are not in range")
    assert(((@intermediate_question.x).between?(10,99)&&(@intermediate_question.y).between?(10,99)),"Intermediate question numbers are not in range")
    assert(((@advance_question.x).between?(1,9)&&(@advance_question.y).between?(1,9)),"Advance question numbers are not in range")
    assert(((@master_question.x).between?(10,99)&& (@master_question.y).between?(10,99)),"Master question numbers are not in range")
  end
  def test_operations
    assert((@basic_question.operation=='-' ||@basic_question.operation=='+'),'Basic question operations is not correct')
    assert((@intermediate_question.operation=='-' ||@intermediate_question.operation=='+'),'Intermediate question operations is not correct')
    assert((@advance_question.operation=='/' ||@advance_question.operation=='*'),'Advance question operations is not correct')
    assert((@master_question.operation=='/' ||@master_question.operation=='*'),'Master question operations is not correct')
  end
  def test_module_chatty
    assert(@basic_question.name=='Basic','Module chatty not working')
    assert(@intermediate_question.name=='Intermediate','Module chatty not working')
    assert(@advance_question.name=='Advanced','Module chatty not working')
    assert(@master_question.name=='Master','Module chatty not working')
  end
end