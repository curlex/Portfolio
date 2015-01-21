require_relative 'results.rb'
require 'test/unit'
class ResultsTest<Test::Unit::TestCase
  def setup
    @results = Results.new
    @results.add 'What is 4 + 3?',:Correct
    @results.add 'What is 10 + 3?',:Incorrect
  end
  def test_add
    assert(@results.result['What is 4 + 3?']==:Correct.to_s,'Add method is not working')
    assert(@results.result['What is 10 + 3?']==:Incorrect.to_s,'Add method is not working')
  end
  def test_to_s
   str="Your results where as follows:"+"\n"+'What is 4 + 3?'+'Correct'+"\n"+'What is 10 + 3?'+'Incorrect'
    assert(@results.to_s==str,'To_s method not working')
  end
  def test_sort
    @results.add 'What is 4 + 2?',:Correct
    my_sort = [["What is 4 + 3?", "Correct"],["What is 4 + 2?", "Correct"], ["What is 10 + 3?", "Incorrect"]]
    assert(@results.sort.eql?(my_sort),'Sort method is not sorting properly')
  end
end